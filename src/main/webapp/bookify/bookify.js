//create the module "bookify"
var bookify = angular.module('bookify', ['ngRoute', 'LocalStorageModule', 'ngMaterial', 'ngMdIcons', 'ngMessages']);

bookify.config(function ($routeProvider, localStorageServiceProvider) {
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

bookify.filter('findByAuthor', function () {
    return function (input, authorName, authorSurname) {
        var output = [];
        console.log(authorName + authorSurname);
        if (authorName === '' && authorSurname === '' || angular.isUndefined(authorName) && angular.isUndefined(authorSurname)) {
            return input;
        }
            angular.forEach(input, function (book) {
                angular.forEach(book.authors, function (author) {
                    if(angular.isUndefined(authorSurname) || authorSurname === ''){
                        if(author.name.toLowerCase().indexOf(authorName.toLowerCase())!== -1){
                            output.push(book);
                        }
                    }
                    else if(angular.isUndefined(authorName) || authorName === ''){
                        if(author.surname.toLowerCase().indexOf(authorSurname.toLowerCase())!== -1){
                            output.push(book);
                        }
                    }
                    else if (author.name.toLowerCase().indexOf(authorName.toLowerCase())!== -1 && author.surname.toLowerCase().indexOf(authorSurname.toLowerCase())!== -1) {
                        output.push(book);
                    }
                })
            });
        return output;
    }
});