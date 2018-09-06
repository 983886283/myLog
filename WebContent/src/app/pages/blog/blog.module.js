(function () {
    'use strict';

    angular.module('BlurAdmin.pages.blog',[]).config(routeConfig);

    function routeConfig($stateProvider) {

        $stateProvider.state("blog",{
            url:'/blog',
            template:"<ui-view autoscroll='true' autoscroll-body-top></ui-view>",
            abstract:true,
            title:'笔记',
            sidebarMeta:{
                icon: 'ion-clipboard',
                order:20
            }
        }).state("blog.Manage",{
            url:'/blogManage',
            templateUrl:'src/app/pages/blog/model/blogManage.html',
            controller:'blogManage',
            title:"笔记管理",
            sidebarMeta:{
                order:20
            }
        }).state("blog.Type",{
            url:'/blogType',
            templateUrl:'src/app/pages/blog/model/blogType.html',
            controller:'blogType',
            title:"笔记类型",
            sidebarMeta:{
                order:20
            }
        })
    }

})();
