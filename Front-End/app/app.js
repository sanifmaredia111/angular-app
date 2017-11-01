var app = angular.module('userapp', ['ui.router','angular-loading-bar', ,'angularUtils.directives.dirPagination']);

app.config(['$stateProvider','$urlRouterProvider',
	function ($stateProvider,$urlRouterProvider) {
		$urlRouterProvider.otherwise('/home');
		$stateProvider
			.state('home', {
				url: "/home",
				templateUrl: "templates/home.html"
			})
			.state('NewUser', {
				url: "/addnew",
				templateUrl: "templates/newuser.html"
			})
			.state('EditUser', {
				url: "/edituser",
				templateUrl: "templates/edituser.html"
			})
	}
]);

app.run(function($rootScope, $state) {
	$rootScope.$state = $state;
});

app.service('shareduser', function () {
	var property = [];
	return {
		getProperty: function () {
			return property;
		},
		setProperty: function(value) {
			property = value;
		}
	};
});

//controller for fetching data
app.controller('user-list',function($scope,$state,$http,shareduser,$filter){
	$scope.userlist = {};
	$scope.pageSize = 5;
	$scope.currentPage = 1;
	$http.get("http://localhost:8080/users").success(function(response){
		if(typeof response =='object'){

			$scope.userlist = response;

		}else{
			$scope.userlist = [];
		}
	});

	$scope.editUser = function($index){
		$scope.number = ($scope.pageSize * ($scope.currentPage - 1)) + $index;

		shareduser.setProperty($scope.userlist[$scope.number]);
        console.log($scope.userlist[$scope.number]);
		$state.go('EditUser');
	};

	$scope.deleteUser = function($index){
		$scope.number = ($scope.pageSize * ($scope.currentPage - 1)) + $index;
		$http.delete("http://localhost:8080/users/"+$scope.userlist[$scope.number].id).success(function(res){
			$state.go($state.current, {}, {reload: true});
		});
	};
});

//controller for add new user functionaliy
app.controller('add-new-user',function($scope,$http,$state){
	$scope.userdata = {};
        $scope.addUser = function () {
                var payload = {
                    "name": $scope.userdata.Name,
                    "email": $scope.userdata.Email,
                }
            if( (payload.name != null) && (payload.email != null) ) {
                $http.post("http://localhost:8080/users", payload).success(function (response) {
                    if (typeof response == 'object') {
                        $state.go("home");
                    } else {
                        alert(res);
                    }
                });
            }
            else {
                swal({
                    title: 'ERROR!',
                    text: 'Please make sure you have entered both Name and E-mail.',
                    confirmButtonColor: '#348c74',
                    type: 'error'
                });
            }
		};


});

//controller for edit user functionaliy
app.controller('edit-user',function($scope,$http,$state,shareduser){
	$scope.userdata = shareduser.getProperty();

	$scope.updateUser = function(){
		var payload = {
			"id":$scope.userdata.id,
			"name":$scope.userdata.name,
			"email":$scope.userdata.email
		}
       // $scope.number = ($scope.pageSize * ($scope.currentPage - 1)) + $index;
        if( (payload.name != null) && (payload.email != null) ) {
            $http.put("http://localhost:8080/users/" + payload.id, payload).success(function (response) {
                if (typeof response == 'object') {
                    $state.go("home");
                } else {
                    alert(res);
                }
            });
        }
        else {
            swal({
                title: 'ERROR!',
                text: 'Please make sure you have entered both Name and E-mail.',
                confirmButtonColor: '#348c74',
                type: 'error'
            });
		}
	};
	$scope.cancel = function(){
		$state.go("home");
	};
});