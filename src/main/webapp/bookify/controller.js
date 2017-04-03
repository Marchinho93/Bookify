bookify.controller("mainCtrl", function ($scope, loginUtility) {

    $scope.loginDiag = function ($event) {
        loginUtility.loginDiag($event);
    };

    $scope.activeMenu = "Home";

    $scope.menu = [{
        "label": "Home",
        "link": "/"
    }];
});

bookify.controller('mainDiagCtrl', function ($scope, $mdDialog, authEngine) {
    $scope.login = function () {
        authEngine.login($scope.id, $scope.password);
        $mdDialog.hide();
    };
});

bookify.controller("adminCtrl", function ($scope, authEngine, session, adminUtility, $mdSidenav) {
    var self = this;
    if (authEngine.authRequired('ADMIN') == true) {
        self.adminData = session.authUser;

        $scope.activeMenu = "Admin";
        $scope.menu = [{
            "label": "Admin",
            "link": "/admin"
        }, {
            "label": "Logout",
            "link": "/logout"
        }];

        $scope.toggleLeft = function (navID) {
            $mdSidenav(navID).toggle()
        };
        $scope.close = function (navId) {
            $mdSidenav(navId).close()
        };

        $scope.editAdmin = function ($event) {
            adminUtility.editAdmin($event);
            $scope.$on('updated', function () {
                self.adminData = session.getAuthUser();
            });
        };

        $scope.content = null;
        $scope.select = function (refPage) {
            $scope.content = refPage; // obviously index isn't the best way :)
        };
    }
});

bookify.controller("adminDiagCtrl", function (session, adminUtility, $mdDialog) {
    var self = this;
    self.newData = session.getAuthUser();
    self.answer = function (answer) {
        adminUtility.writeEditedAdminData(self.newData);
        $mdDialog.hide(answer);
    };
    self.cancel = function () {
        $mdDialog.cancel();
    };
});

bookify.controller("bookManagerCtrl", function ($scope, authEngine, bookManager, $mdDialog) {
    var self = this;
    if (authEngine.authRequired('ADMIN') == true) {
        $scope.activeMenu = "Admin";

        $scope.menu = [{
            "label": "Admin",
            "link": "/admin"
        }, {
            "label": "Logout",
            "link": "/logout"
        }];

        $scope.action = "view/showBooks.htm";
        $scope.selectAction = function (refPage) {
            $scope.action = refPage;
        };

        bookManager.populate();
        $scope.$on('updatedBooksList', function () {
            self.bookList = bookManager.bookList;
        });
        $scope.$on('updatedAuthorsList', function () {
            self.authorList = bookManager.authorList;
        });
        $scope.$on('updatedCategoriesList', function () {
            self.categoryList = bookManager.categoryList;
        });
        $scope.$on('updatedSeriesList', function () {
            self.serieList = bookManager.serieList;
        });
        $scope.$on('getBack', function () {
            self.action='view/showBooks.htm';
        });

        $scope.orderBy = function (orderLabel) {
            $scope.orderLabel = orderLabel;
        };

        $scope.selectedBook = {};

        $scope.getDetails = function (book, $event) {
            bookManager.selectedBook = book;
            $mdDialog.show({
                controller: "bookManagerDiagCtrl",
                templateUrl: 'view/showBook.htm',
                parent: angular.element(popupContainer),
                targetEvent: $event,
                clickOutsideToClose: false
            }).then(function (answer) {
                if(answer === 'edit'){

                }
                else if(answer === 'del'){

                }
            });
        };

        $scope.newBook = {};
        $scope.newBook.authors = [];

        $scope.addBook = function () {
            bookManager.addBook($scope.newBook);
            bookManager.populate();
            $scope.newBook='';
            self.action='view/showBooks.htm';
        };

        $scope.abort = function (){
            $scope.newBook='';
            self.action='view/showBooks.htm';
        };

        $scope.toggleCategory = function (c) {

            if ($scope.newBook.category == c) {
                $scope.newBook.category = '';
            }
            else {
                $scope.newBook.category = c;
            }
        };
        $scope.toggleAuthor = function (a) {

            var index = $scope.newBook.authors.indexOf(a);
            if (index !== -1)
                $scope.newBook.authors.splice(index, 1);
            else
                $scope.newBook.authors.push(a);
        };
        $scope.toggleSerie = function (s) {

            if ($scope.newBook.serie == s) {
                $scope.newBook.serie = '';
            }
            else {
                $scope.newBook.serie = s;
            }
        };
        $scope.addNewCategory = function ($event) {
            $mdDialog.show({
                controller: "bookManagerDiagCtrl",
                templateUrl: 'view/addCategory.htm',
                parent: angular.element(popupContainer),
                targetEvent: $event,
                clickOutsideToClose: false
            }).then(function (data) {
                $scope.addCategory(data);
                console.log(data);
                console.log($scope);
            });
        };
        $scope.addNewAuthor = function ($event) {
            $mdDialog.show({
                controller: "bookManagerDiagCtrl",
                templateUrl: 'view/addAuthor.htm',
                parent: angular.element(popupContainer),
                targetEvent: $event,
                clickOutsideToClose: false
            }).then(function (data) {
                $scope.addAuthor(data);
            });
        };
        $scope.addNewSerie = function ($event) {
            $mdDialog.show({
                controller: "bookManagerDiagCtrl",
                templateUrl: 'view/addSerie.htm',
                parent: angular.element(popupContainer),
                targetEvent: $event,
                clickOutsideToClose: false
            }).then(function (data) {
                $scope.addSerie(data);
            });
        };

        $scope.addCategory = function (c) {
            self.categoryList.push(c);
        };
        $scope.addAuthor = function (a) {
            self.authorList.push(a);
        };
        $scope.addSerie = function (s) {
            self.serieList.push(s);
        };
    }
});

bookify.controller('bookManagerDiagCtrl', function ($scope, $mdDialog, bookManager) {
    $scope.selectedBook = bookManager.selectedBook;
    $scope.answer = function (data) {
        $mdDialog.hide(data);
    };
    $scope.cancel = function () {
        $mdDialog.cancel();
    };
});

bookify.controller("logoutCtrl", function ($scope, authEngine) {
    authEngine.logout();
});