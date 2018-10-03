/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages', [
    'ui.router',
    'BlurAdmin.pages.dashboard',
    'BlurAdmin.pages.blog',
    'BlurAdmin.pages.set',
    'BlurAdmin.pages.profile',
  ])
  .service('common', function($window) {
	  this.basePath = $window.location.protocol+'//'+$window.location.host+'/myLog';
	})
  .config(routeConfig);

  /** @ngInject */
  function routeConfig($urlRouterProvider, baSidebarServiceProvider) {
    $urlRouterProvider.otherwise('/dashboard');
  }

})();
