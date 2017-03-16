//create the module "bookify"
var bookify = angular.module('bookify', ['ngRoute', 'LocalStorageModule', 'ngMaterial', 'ngMdIcons']);

bookify.config(function ($routeProvider,localStorageServiceProvider) {
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
        });

    localStorageServiceProvider
        .setPrefix('bookify')
        .setDefaultToCookie(false);
});