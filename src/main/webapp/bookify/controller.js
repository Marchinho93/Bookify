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

        $scope.toggleLeft = function(navID){
            $mdSidenav(navID).toggle()
        };

        $scope.close = function(navId){
            $mdSidenav(navId).close()
        };

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