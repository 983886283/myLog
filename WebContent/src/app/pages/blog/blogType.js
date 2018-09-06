(function () {
    'use strict';
    angular.module("BlurAdmin.pages.blog")
        .controller("blogType",blogType);

    function blogType($scope,$http,$window,toastr,common) {
    	$scope.common = common;
    		$scope.flag=true;
    		$scope.delflag=false;
    		//所有数据
    		$scope.list = [];
    		//所有类型
    		$scope.types = [];
    		//单条数据
    		$scope.formData = {};
    		//翻页
    		$scope.pagination = {
    				pageSize:1,
    				total:1
    		};
    		//查询所有数据
    		var getAllDatas = function(){
    			$http({
    		        method: 'GET',
    		        url: $scope.common.basePath+"/back/getType/"+$scope.pagination.pageSize
    		    }).then(function successCallback(response) {
    		    	if("" === response.data){
        	    		$window.location.href=$scope.common.basePath+"/login.html";
        	    	}
    		    	$scope.list = response.data.allType;
    		    	$scope.pagination.pageSize = 1;
    		    	$scope.pagination.total = Math.ceil(response.data.PageTotal.TOTAL/8);
    		        }, function errorCallback(response) {
    		        	toastr.error("查询失败!", 'Error');
    		    });
    		}
    		
    		//翻页前一页
    		$scope.pre = function(){
    			if($scope.pagination.pageSize===1){
    				return false;
    			}
    			$http({
    		        method: 'GET',
    		        url: $scope.common.basePath+"/back/getType/"+($scope.pagination.pageSize-1)
    		    }).then(function successCallback(response) {
    		    	if(response.data!=null){
    			    	$scope.pagination.pageSize --;
    			    	$scope.list = response.data.allType;
			    	}
		        }, function errorCallback(response) {
		        	toastr.error("翻页失败!", 'Error');
    		    });
    		}
    		//翻后后一页
    		$scope.nex = function(){
    			if($scope.pagination.pageSize===$scope.pagination.total){
    				return false;
    			}
    			$http({
    				method: 'GET',
    				url: $scope.common.basePath+"/back/getType/"+($scope.pagination.pageSize+1)
    			}).then(function successCallback(response) {
    				if(response.data!=null){
    					$scope.pagination.pageSize ++;
    					$scope.list = response.data.allType;
    				}
    			}, function errorCallback(response) {
    				toastr.error("翻页失败!", 'Error');
    			});
    		}
    		
    		//页面加载时显示第一页数据
    		$(function() {
    			getAllDatas();
    		});
    		
    		//进入删除页面
    		$scope.del = function(index) {
    			$scope.delFlag=!$scope.delFlag;
    			$scope.formData = $scope.list[index];
    		}
    		
    		//进入修改页面
    		$scope.edit = function(index) {
    			$scope.flag = true;
    			$scope.formData = $scope.list[index];
    			$scope.flag=!$scope.flag;
    		}
    		//进入添加页面
    		$scope.add = function(index) {
    			$scope.formData = {};
    			$scope.flag=!$scope.flag;
    		}
    		
    		$scope.delDatas = function(){
    			var url = $scope.common.basePath+"/back/delType/"+$scope.formData.typeid;
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
    		$scope.changeFlag = function(){
    			$scope.flag=!$scope.flag;
    		}
    		$scope.changeDelFlag = function(){
        		$scope.delFlag=!$scope.delFlag;
        	}
    		//点击保存添加和修改的内容
    		$scope.saveDatas = function(){
    			$scope.formData.state = $('#typeState').val();
    			var url = $scope.common.basePath+"/back/saveType";
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