(function () {
  'use strict';

  angular.module('BlurAdmin.theme.components')
      .controller("pageTopCtrl", pageTopCtrl).directive('pageTop', pageTop);

  /** @ngInject */
  function pageTop() {
    return {
      restrict: 'E',
      templateUrl: 'src/app/theme/components/pageTop/pageTop.html',
      controller: 'pageTopCtrl'
    };
  }
  
  function pageTopCtrl($scope, $http, $window, toastr, common) {
	  $scope.signout = function() {
		  $scope.basePath = common.basePath;
		  var url = $scope.basePath + "/back/login/out";
		  $http.get(url).success(function(response) {
				toastr.success("ヽ(￣▽￣)ﾉ登出成功!");
				setTimeout(function(){
					$window.location.href = $scope.basePath + "/login.html";
				},1000)
			});
	  }
  }
  
})();