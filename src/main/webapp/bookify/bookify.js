//create the module "bookify"
var bookify = angular.module("bookify", ["ngRoute", "ngMaterial", "ngMdIcons"]);

bookify.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "view/main.htm",
            controller: "mainCtrl"
        })
        .when("/login", {
            templateUrl: "view/login.htm",
            controller: "loginCtrl"
        })
        .when("/admin", {
            templateUrl: "view/admin.htm",
            controller: "adminCtrl"
        })
        .when("/user", {
            templateUrl: "view/user.htm"
        })
        .when("/logout", {
            templateUrl: "view/main.htm"
        })
});

bookify.controller("mainCtrl", function ($scope) {

    $scope.activeMenu = "home";

    $scope.menu = [{
        "label": "Home",
        "link": "/"
    }, {
        "label": "Login",
        "link": "/login"
    }];
});

bookify.controller("loginCtrl", function ($scope, $http, $mdDialog, $location, $rootScope) {

    $scope.activeMenu = "login";

    $scope.menu = [{
        "label": "Home",
        "link": "/"
    }, {
        "label": "Login",
        "link": "/login"
    }];

    $scope.showAlert = function () {
        $mdDialog.show(
            $mdDialog.alert()
                .parent(angular.element(document.querySelector('#popupContainer')))
                .clickOutsideToClose(true)
                .title('Username or Password incorrect')
                .textContent('Please, insert correct credentials')
                .ariaLabel('Alert')
                .ok('Ok')
        );
    };

    $scope.login = function () {
        var packet = {
            params: {
                "id": $scope.id,
                "password": $scope.password
            }
        };
        console.log(packet);
        $http.post("login", "", packet)
            .success(function (data) {
                $rootScope.loggedUser = data;
                if (data.roles.indexOf("ADMIN") !== -1) {
                    $location.path("admin");
                }
                else if(data.roles.indexOf("USER")!== -1){
                    $location.path("user");
                    console.log(data);
                }
            })
            .error(function () {
                $scope.showAlert()
            });
    }
});

bookify.controller("adminCtrl", function($scope, $http, $mdDialog, $rootScope, $location){
    if(angular.isUndefined($rootScope.loggedUser)){
        $location.path("");
    }
    else if($rootScope.loggedUser.roles.indexOf("ADMIN") === -1){
        $location.path("");
    }
    $scope.activeMenu = "admin";
    $scope.loggedUser = $rootScope.loggedUser;

    $scope.menu = [{
        "label": "Admin",
        "link": "/admin"
    }, {
        "label": "Logout",
        "link": "/logout"
    }];

});