var basePath = window.location.protocol+'//'+window.location.host+'/myLog';

/*滚动监听回到顶部的显示与隐藏*/
window.onscroll=function(){
	var top=$(window).scrollTop();
	if(top<60){$('#goTop').hide();
	}else{
		$("#goTop").show()
		}
	};
/* 显示左测菜单栏 */
function displaynavbar(obj){
	if($(obj).hasClass("open")){
		$(obj).removeClass("open");
		$("body").removeClass("big-page");
	} else {
		$(obj).addClass("open");
		$("body").addClass("big-page");
	}
}
/* 左侧菜单栏显示子菜单 */
$(".has-sub>a").click(function () {
	$(this).parent().siblings().find(".sub-menu").slideUp();
	$(this).parent().find(".sub-menu").slideToggle();
	var is = $(this).parent().siblings().find("i");
	for ( var i = 0; i < is.length; i++) {
		if($(is[i]).hasClass("glyphicon-menu-up")){
			$(is[i]).removeClass("glyphicon-menu-up");
			$(is[i]).addClass("glyphicon-menu-down");
		}
	}
	if($(this).children("i").hasClass("glyphicon-menu-up")){
		$(this).children("i").removeClass("glyphicon-menu-up");
		$(this).children("i").addClass("glyphicon-menu-down");
	}else if($(this).children("i").hasClass("glyphicon-menu-down")){
		$(this).children("i").removeClass("glyphicon-menu-down");
		$(this).children("i").addClass("glyphicon-menu-up");
	}
});

var blogsIndex = angular.module('blogsIndex',[]);
//标题>18省略
blogsIndex.filter('lengthEighteen',function(){
	return function(string){
		var newString = '';
			if(string!=undefined){
			if(string.length>18){
				newString = string.substring(0,18)+' . . .';
			}else{
				newString = string;
			}
		}
		return newString;
	}
});

blogsIndex.controller('blogsIndexCtrl', ['$scope','filterFilter','$http','$window',function ($scope,filterFilter,$http,$window) {
	
	//搜索内容筛选
	$scope.search = "";
	//类型内容筛选
	$scope.tp = "";
	//所有分类
	$scope.tpList = [];
	//所有数据
	$scope.list2 = [];
	
	$scope.list = [];
	
	//改变筛选类型
	$scope.changeTp = function(ite){
		if($scope.tp!==ite){
			$scope.tp = ite;
		}else{
			$scope.tp = '';
		}
	}
	//排序
	$scope.orderType = 'createtime';
	$scope.order = '-';
	$scope.changeOrder=function(type){
		if($scope.orderType !== type){
			$scope.order = '';
		}
		$scope.orderType = type;
		if($scope.order === '-'){
			$scope.order = '';
		}else{
			$scope.order = '-';
		}
	}
	
	$scope.pagination = {};
	$scope.$watch('search',function(newValue,oldValue, scope){
		filtrate(newValue,'search');
	});
	$scope.$watch('tp',function(newValue,oldValue, scope){
		filtrate(newValue,'tp');
	});
	
	function filtrate(newValue,ar){
		$scope.list = $scope.list2;
		if(ar==='search'){
			$scope.list = filterFilter($scope.list, newValue);
			$scope.list = filterFilter($scope.list,{'typeval':$scope.tp});
		}else if(ar==='tp'){
			$scope.list = filterFilter($scope.list, {'typeval':newValue});
			$scope.list = filterFilter($scope.list,$scope.search);
		}
		$scope.pagination.total=Math.ceil($scope.list.length/5);
		if($scope.pagination.pageSize>$scope.pagination.total){
			$scope.pagination.pageSize=$scope.pagination.total;
		}
		if($scope.pagination.total<=0){
			$scope.pagination.total=1;
		}
		if($scope.pagination.pageSize<=0){
			$scope.pagination.pageSize=1;
		}
	}
	//页面加载时显示第一页数据
	$(function() {
		$scope.pagination = {
				pageSize:1,
				total:1
		};
		$http({
	        method: 'GET',
	        url: basePath+"/front/getData"
	    }).then(function successCallback(response) {
	    	$scope.list = response.data;
	    	$scope.list2 = response.data;
	    	if(response.data!=null){
	    		getAllType();
	    		var tota = 1;
	    		if($scope.list.length>0){
	    			tota = $scope.list.length;
	    		}
	    		$scope.pagination.total = Math.ceil(tota/5);
	    		$(".article").removeClass("hide");
	    	}else{
	    		$window.location.href=basePath+"/404.html";
	    	}
        }, function errorCallback(response) {
			$window.location.href=basePath+"/404.html";
		});
	});
	
	function getAllType(){
		angular.forEach($scope.list2, function (items) {
			var key = items.typeval;
			if ($scope.tpList.indexOf(key) === -1) {
				$scope.tpList.push(key);
			}
		})
	}
	//翻页前一页
	$scope.pre = function(){
		if($scope.pagination.pageSize<=1){
			$scope.pagination.pageSize=1
			return false;
		}
		$scope.pagination.pageSize --;
	}
	//翻后前一页
	$scope.nex = function(){
		if($scope.pagination.pageSize>=$scope.pagination.total){
			$scope.pagination.pageSize=$scope.pagination.total
			return false;
		}
		$scope.pagination.pageSize ++;
	}
}]);