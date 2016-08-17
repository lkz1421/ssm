var app = angular.module('myApp', ['ngRoute','pascalprecht.translate']);

app.controller('baseCtrl',function($rootScope, $scope, $http) {
	
});

/**
 * 按需加载 js , 依赖 script.js:https://github.com/ded/script.js
 */
app.config(function($controllerProvider, $compileProvider, $filterProvider,$provide,$translateProvider) {
	app.register = {
		controller : $controllerProvider.register,
		directive : $compileProvider.directive,
		filter : $filterProvider.register,
		factory : $provide.factory,
		service : $provide.service
	};
	app.async = function(js) {
		return function($q, $route, $rootScope) {
			var deferred = $q.defer();
			$script(js, function() {
				$rootScope.$apply(function() {
					deferred.resolve();
				});
			});
			return deferred.promise;
		}
	}
	
/**
 * i18n fn: i18n_en|i18n_zh return json object
 */
//	$translateProvider.translations('en',i18n_en);
//	$translateProvider.translations('zh',i18n_zh);
//	$translateProvider.preferredLanguage('en');
	
//	page : {{'title'|translate}}
});

app.service('$req',function($http){
	this.get = function(url, params, callback) {
		if (typeof params == 'function') {
			callback = params;
			params = {};
		}
		$http.get(url, {
			params : params
		}).success(function(data, status) {
			callback(data, status);
		}).error(function(data, status) {
			console.warn("获取数据失败   error:", data," status:", status);
		});
	}
	this.post = function(url, params, callback) {
		if (typeof params == 'function') {
			callback = params;
			params = {};
		}
		$http.post(url,params,{
			headers : {'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'},
			transformRequest : function(obj) {
				var str = [];
				for ( var p in obj) {
					str.push(encodeURIComponent(p)+ "=" + encodeURIComponent(obj[p]));
				}
				return str.join("&");
			}
		}).success(function(data, status) {
			callback(data, status);
		}).error(function(data, status) {
			console.warn("获取数据失败   error:",data, " status:", status);
		});
	}
})