bookify.controller("mainCtrl", function ($scope) {

    $scope.activeMenu = "Home";

    $scope.menu = [{
        "label": "Home",
        "link": "/"
    }, {
        "label": "Login",
        "link": "/login"
    }];
});

bookify.controller("loginCtrl", function ($scope, authEngine) {

    $scope.activeMenu = "Login";

    $scope.menu = [{
        "label": "Home",
        "link": "/"
    }, {
        "label": "Login",
        "link": "/login"
    }];

    $scope.login = function () {
        authEngine.login($scope.id, $scope.password);
    }
});

bookify.controller("adminCtrl", function ($scope, authEngine, session, adminUtility) {
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

        $scope.editAdmin = function ($event) {
            adminUtility.editAdmin($event);
            $scope.$on('updated', function () {
                self.adminData = session.getAuthUser();
            });
        };

        this.loanManager = function () {

        };

        this.userManager = function () {

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

bookify.controller("bookManagerCtrl", function ($scope, authEngine, bookManager) {
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

        bookManager.populate();
        $scope.$on('updated', function () {
            self.bookList = bookManager.bookList;
        });
    }
});

bookify.controller("logoutCtrl", function ($scope, authEngine) {
    authEngine.logout();
});