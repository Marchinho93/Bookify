//create the module "bookify"
var bookify = angular.module('bookify', ['ngRoute', 'LocalStorageModule', 'ngMaterial', 'ngMdIcons']);

bookify.config(function ($routeProvider,localStorageServiceProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "view/main.htm",
            controller: "mainCtrl"
        })
        .when("/admin", {
            templateUrl: "view/admin.htm",
            controller: "adminCtrl",
            controllerAs: "ctrl"
        })
        .when("/bookManager", {
            templateUrl: "view/bookManager.htm",
            controller: "bookManagerCtrl",
            controllerAs: "bookCtrl"
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