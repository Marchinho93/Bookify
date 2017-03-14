//create the module "bookify"
var bookify = angular.module('bookify', ['ngRoute', 'ngMaterial', 'ngMdIcons']);

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
            templateUrl: "view/main.htm",
            controller: "logoutCtrl"
        })
});