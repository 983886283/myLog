(function () {
    'use strict';
    angular.module("BlurAdmin.pages.blog")
        .controller("blogManage",blogManage).directive('tagInput', tagInput);
	
    function tagInput() {
	    return {
	      restrict: 'A',
	      link: function( $scope, elem, attr) {
	        $(elem).tagsinput({
	          tagClass:  'label label-' + attr.tagInput
	        });
	      }
	    };
	  }
    
    function blogManage($scope,$http,$window,toastr,common) {
    	$scope.common = common;
    	$scope.flag=true;
    	$scope.delflag=false;
    	$scope.editor;
    	//所有数据
    	$scope.list = [];
    	$scope.switches = [true];
    	//所有类型
    	$scope.types = [];
    	//单条数据
    	$scope.formData = {};
    	//翻页
    	$scope.pagination = {
    			pageSize:1,
    			total:1
    	};
    	
    	//页面加载时显示第一页数据
    	$(function() {
    		$scope.editor = CKEDITOR.replace( 'ckeditor' );
    		getAllDatas();
    	});
    	
    	//查询所有数据
    	var getAllDatas = function(){
    		$http({
    	        method: 'GET',
    	        url: $scope.common.basePath+"/back/getData/"+$scope.pagination.pageSize
    	    }).then(function successCallback(response) {
    	    	if("" === response.data){
    	    		$window.location.href=$scope.common.basePath+"/login.html";
    	    	}
    	    	$scope.pagination.total = Math.ceil(response.data.PageTotal.TOTAL/8);
    	    	if($scope.pagination.pageSize>$scope.pagination.total){
    	    		$scope.pagination.pageSize=$scope.pagination.total;
    	    		getAllDatas();
    	    		return;
    	    	}
    	    	$scope.list = response.data.allData;
    	    	$scope.types = response.data.allTypes;
    	        }, function errorCallback(response) {
    	        	$window.location.href=$scope.common.basePath+"/404.html";
    	    });
    	}
    	
    	//翻页前一页
    	$scope.pre = function(){
    		if($scope.pagination.pageSize===1){
    			return false;
    		}
    		$http({
    	        method: 'GET',
    	        url: $scope.common.basePath+"/back/getData/"+($scope.pagination.pageSize-1)
    	    }).then(function successCallback(response) {
    	    	if(response.data!=null){
    		    	$scope.pagination.pageSize --;
    		    	$scope.list = response.data.allData;
		    	}
	        }, function errorCallback(response) {
	        	toastr.error("翻页失败!", 'Error');
    	    });
    	}
    	//翻后前一页
    	$scope.nex = function(){
    		if($scope.pagination.pageSize===$scope.pagination.total){
    			return false;
    		}
    		$http({
    			method: 'GET',
    			url: $scope.common.basePath+"/back/getData/"+($scope.pagination.pageSize+1)
    		}).then(function successCallback(response) {
    			if(response.data!=null){
    				$scope.pagination.pageSize ++;
    				$scope.list = response.data.allData;
    			}
    		}, function errorCallback(response) {
    			toastr.error("翻页失败!", 'Error');
    		});
    	}

    	//进入删除页面
    	$scope.del = function(index) {
    		$scope.delFlag=!$scope.delFlag;
    		$scope.formData = $scope.list[index];
    	}
    	
    	//进入查询页面
    	$scope.read = function(index) {
    		var mcid = $scope.list[index].contentid;
    		var url = $scope.common.basePath+"/detail/"+mcid+".html";
    		$window.open(url);
    	}
    	
    	//进入修改页面
    	$scope.edit = function(index) {
    		$scope.flag = true;
    		$scope.formData = $scope.list[index];
    		$scope.tags = $scope.formData.tag.split(',');
    		$('#mctag').tagsinput('removeAll');
    		for (var i=0;i<$scope.tags.length ;i++ ) {
    			$('#mctag').tagsinput('add', $scope.tags[i]); //分割后的字符输出 
    		}
    		$scope.editor.setData($scope.formData.body);
    		$scope.flag=!$scope.flag;
    	}
    	//进入添加页面
    	$scope.add = function(index) {
    		$scope.formData = {};
    		$('#mctag').tagsinput('removeAll');
    		$scope.editor.setData("");
    		$scope.flag=!$scope.flag;
    	}
    	//页面静态化
    	$scope.htmlStatic = function() {
    		var url = $scope.common.basePath+"/back/htmlStatic";
    		$http.get(url)
    		.success(function (response) {
    			if(response.data==='success'){
		    		toastr.success("ヽ(￣▽￣)ﾉ笔记静态化成功!");
		    	}else{
		    		toastr.error("(▼へ▼メ)笔记静态化失败!", 'Error');
		    	}
		    });
    	}
    	
    	$scope.changeFlag = function(){
    		$scope.formData = {}
			$scope.flag=!$scope.flag;
		}
    	$scope.changeDelFlag = function(){
    		$scope.delFlag=!$scope.delFlag;
    	}
    	
    	$scope.delDatas = function(){
			var url = $scope.common.basePath+"/back/delData/"+$scope.formData.contentid;
			$http.get(url)
		    .success(function (response) {
		    	getAllDatas();
		    	if(response.data==='success'){
		    		$scope.delFlag=!$scope.delFlag;
		    		toastr.success("ヽ(￣▽￣)ﾉ删除成功!");
		    	}else{
		    		toastr.error("(▼へ▼メ)删除失败!", 'Error');
		    	}
		    });
		}
    	
    	//点击保存添加和修改的内容
    	$scope.saveDatas = function(){
    		$scope.formData.tag = $("#mctag").val();
    		$scope.formData.body = $scope.editor.getData();
    		$scope.formData.typeid = $('#articleType').val();
    		$scope.formData.recommend = $('#articleRecommend').val();
    		var url = $scope.common.basePath+"/back/saveData";
    		$scope.saveData(url,$scope.formData);
    		$scope.flag=!$scope.flag;
		}
    	
    	//基本方法保存数据
    	$scope.saveData = function(url, formData){
    		//将参数传递的方式改成form
    		var postCfg = {
                    headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                    transformRequest: function (data) {
                        return $.param(data);
                    }
            };
    		//发送post请求，获取数据
    		$http.post(url, $scope.formData, postCfg)
    		    .success(function (response) {
    		    	getAllDatas();
    		    	if(response.data==='success'){
    		    		toastr.success("ヽ(￣▽￣)ﾉ成功!");
    		    	}else{
    		    		toastr.error(response.data, 'Error');
    		    	}
		    });
    	}
    }
})();