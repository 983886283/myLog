(function () {
    'use strict';
    angular.module("BlurAdmin.pages.set")
        .controller("setUser",setUser);

    function setUser($scope,$http,$window,toastr,common) {
    	$scope.basePath = common.basePath;
		$scope.flag=0;//flag(0表示显示查询页、1表示进入添加修改页、-1表示进入删除页)
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
		        url: $scope.basePath+"/back/user/getUser/"+$scope.pagination.pageSize
		    }).then(function successCallback(response) {
		    	if("noLogin" === response.data){
    	    		$window.location.href=$scope.basePath+"/login.html";
    	    	}
		    	$scope.list = response.data.allUser;
		    	console.log($scope.list)
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
		        url: $scope.basePath+"/back/user/getUser/"+($scope.pagination.pageSize-1)
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
				url: $scope.basePath+"/back/user/getUser/"+($scope.pagination.pageSize+1)
			}).then(function successCallback(response) {
				if(response.data!=null){
					$scope.pagination.pageSize ++;
					$scope.list = response.data.allData;
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
			$scope.formData = $scope.list[index];
			$scope.flag = -1;
		}
		
		//进入修改页面
		$scope.edit = function(index) {
			$scope.formData = $scope.list[index];
			$scope.flag = 1;
		}
		//进入添加页面
		$scope.add = function(index) {
			$scope.formData = {};
			$scope.flag = 1;
		}
		
		$scope.changeFlag = function(){
			$scope.flag = 0;
		}
		
		$scope.delDatas = function(){
			var url = $scope.basePath+"/back/user/delUser/"+$scope.formData.userid;
			$http.get(url)
		    .success(function (response) {
		    	getAllDatas();
		    	if(response.data==='success'){
		    		changeFlag();
	    			toastr.success("ヽ(￣▽￣)ﾉ删除成功!");
		    	}else{
	    			toastr.error("(▼へ▼メ)删除失败!", 'Error');
		    	}
		    });
		}
		
		//点击保存添加和修改的内容
		$scope.saveDatas = function(){
			$scope.formData.userlv = $('#userlv').val();
			$scope.formData.state = $('#state').val();
			var url = $scope.basePath+"/back/user/saveUser";
			$scope.saveData(url,$scope.formData);
			changeFlag();
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