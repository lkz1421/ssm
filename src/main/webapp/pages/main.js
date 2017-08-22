app.register.controller('main', function($scope, $req) {
	$req.get('', {search : 'a'}, function(data, status) {
		$scope.data = data;
	})
	$scope.text = 'main 1.2.29';
})