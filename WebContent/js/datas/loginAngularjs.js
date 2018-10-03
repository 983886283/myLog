var basePath = window.location.protocol+'//'+window.location.host+'/myLog';
$(".layui-canvs").width($(window).width());
$(".layui-canvs").height($(window).height());

var login = angular.module('login',[]);

login.controller('loginCtrl', ['$scope','$http','$window',function ($scope,$http,$window) {
	
	$scope.loginData = {};
	
	$scope.login = function(){
	
	var url = basePath+"/back/login/in";
	
	postCfg = {
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
            transformRequest: function (data) {
                return $.param(data);
            }
    };
	//发送post请求，获取数据
	$http.post(url, $scope.loginData, postCfg)
	    .success(function (response) {
	    	if(response===1){
	    		$window.location.href = basePath+"/backstage.html";
	    	}else{
	    		alert("登录失败");
	    	}
	    })
	    .error(function() {
	    	alert("登录失败");
	    });
	}
}]);
