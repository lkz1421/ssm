$script.path('pages/'); //script base path

app.config(function($routeProvider) {
	$routeProvider
		.when('/index', {
			templateUrl: 'pages/temp.html',
			resolve: {load: app.async('index.js')},
		    controller:'index'
		})
		.when('/main', {
			templateUrl: 'pages/temp.html?w=1',
			resolve: {load: app.async(['main.js'])},
		    controller:'main'
		})
		.otherwise({redirectTo: '/index'});
	}
)