/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function() {
	'use strict';

	angular.module('BlurAdmin.pages.dashboard').controller(
			'DashboardPieChartCtrl', DashboardPieChartCtrl);

	/** @ngInject */
	function DashboardPieChartCtrl($scope, $interval, $http, $window, common) {
		$scope.common = common;
		var myChart;
		var myChart2;
		var option;
		var option2;
		$scope.getAllDatas = function() {
			$http(
					{
						method : 'GET',
						url : $scope.common.basePath+"/back/getDataForCharts"
					}).then(
					function successCallback(response) {
						if ("" === response.data) {
							$window.location.href = $scope.common.basePath
									+ "/login.html";
						}
						var clickAll = response.data.clickSum;
						var countAll = response.data.total;
						var nameAll = response.data.typeVal;
						var dateAll = response.data.dateAll;
						var dateCountAll = response.data.dateCountAll;
						option.xAxis[0].data = nameAll;
						option.series[0].data = countAll;
						option.series[1].data = clickAll;
						option2.xAxis.data = dateAll;
						option2.series[0].data = dateCountAll;
						myChart.setOption(option);
						myChart2.setOption(option2);
					},
					function errorCallback(response) {
						$window.location.href = $scope.common.basePath + "/404.html";
					});
		}

		$(function() {
			myChart = echarts.init(document.getElementById('myChart'));
			myChart2 = echarts.init(document.getElementById('myChart2'));
			var colors = [ '#5793f3', '#d14a61', '#675bba' ];
			option = {
				color : colors,

				tooltip : {
					trigger : 'axis',
					axisPointer : {
						type : 'cross'
					}
				},
				grid : {
					right : '25%'
				},
				legend : {
					data : [ '数量', '点击量' ]
				},
				xAxis : [ {
					type : 'category',
					axisTick : {
						alignWithLabel : true
					},
					data : []
				} ],
				yAxis : [ {
					type : 'value',
					name : '数量',
					position : 'right',
					axisLine : {
						lineStyle : {
							color : colors[0]
						}
					},
					axisLabel : {
						formatter : '{value} 条'
					}
				}, {
					type : 'value',
					name : '点击量',
					position : 'right',
					offset : 80,
					axisLine : {
						lineStyle : {
							color : colors[1]
						}
					},
					axisLabel : {
						formatter : '{value} 次'
					}
				} ],
				series : [ {
					name : '数量',
					type : 'bar',
					data : []
				}, {
					name : '点击量',
					type : 'bar',
					yAxisIndex : 1,
					data : []
				} ]
			};

			
			option2 = {
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['当日博客条数']
				    },
				    toolbox: {
				        show: true,
				        feature: {
				            dataZoom: {
				                yAxisIndex: 'none'
				            },
				            dataView: {readOnly: false},
				            magicType: {type: ['line', 'bar']},
				            restore: {},
				            saveAsImage: {}
				        }
				    },
				    xAxis:  {
				        type: 'category',
				        boundaryGap: false,
				        data: []
				    },
				    yAxis: {
				        type: 'value',
				        axisLabel: {
				            formatter: '{value} 条'
				        }
				    },
				    series: [
				        {
				            name:'当日博客条数',
				            type:'line',
				            data:[],
				            markPoint: {
				                data: [
				                    {type: 'max', name: '最大值'},
				                    {type: 'min', name: '最小值'}
				                ]
				            },
				            markLine: {
				                data: [
				                    {type: 'average', name: '平均值'}
				                ]
				            }
				        }
				    ]
				};
			myChart.setOption(option);
			myChart2.setOption(option2);
			$scope.getAllDatas();
		});
	}
})();