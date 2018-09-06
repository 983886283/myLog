(function () {
    'use strict';

    angular.module('BlurAdmin.pages.set',[]).config(routeConfig);

    function routeConfig($stateProvider) {

        $stateProvider.state("set",{
            url:'/set',
            template:"<ui-view autoscroll='true' autoscroll-body-top></ui-view>",
            abstract:true,
            title:'设置',
            sidebarMeta:{
                icon: 'ion-gear-a',
                order:20
            }
        }).state("set.User",{
            url:'/setUser',
            templateUrl:'src/app/pages/set/model/setUser.html',
            controller:'setUser',
            title:"用户管理",
            sidebarMeta:{
                order:20
            }
        })
    }

})();
