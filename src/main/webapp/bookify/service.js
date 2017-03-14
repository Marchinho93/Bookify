bookify.factory('initUser', ['$http', 'session', '$location', '$mdDialog', function ($http, session, $location, $mdDialog) {
    var self = this;
    self.dbAlert = function () {
        $mdDialog.show(
            $mdDialog.alert()
                .parent(angular.element(document.querySelector('#popupContainer')))
                .clickOutsideToClose(true)
                .title('Database Error')
                .textContent('Please, retry later')
                .ariaLabel('Alert')
                .ok('Ok')
        ).then(function () {
            $location.path("");
        });
    };

    return {
        initAdmin: function (id) {
            var packet = {
                params: {
                    'id': id
                }
            };
            $http.post('initAdmin', "", packet)
                .success(function (data) {
                    userData = {
                        name: data.name,
                        surname: data.surname,
                        birthday: data.birthday,
                        address: data.address,
                        phone: data.phone,
                        city: data.city,
                        email: data.email
                    };
                    session.setAuthUser(userData);
                    $location.path("admin");
                })
                .error(function () {
                    self.dbAlert();
                    session.invalidate();
                    return new Error("dbError");
                });
        },
        initUser: function (id) {
            var packet = {
                params: {
                    'id': id
                }
            };
            $http.post('initUser', "", packet)
                .success(function (data) {
                    userData = {
                        name: data.name,
                        surname: data.surname,
                        birthday: data.birthday,
                        subscription: data.subscription,
                        address: data.address,
                        phone: data.phone,
                        city: data.city,
                        email: data.email
                    };
                    session.setAuthUser(userData);
                    $location.path("user");
                })
                .error(function () {
                    self.dbAlert();
                    session.invalidate();
                    return new Error("dbError");
                });
        }
    }
}]);

bookify.factory('session', [function () {
    var self = this;
    var authUser = null;
    var authenticated = false;
    var authType = null;

    return {
        setAuthenticated: function (value) {
            this.authenticated = value;
        },
        getAuthenticated: function () {
            return this.authenticated;
        },
        setAuthType: function (role) {
            this.authType = role;
        },
        getAuthType: function () {
            return this.authType;
        },
        setAuthUser: function (user) {
            this.authUser = user;
        },
        getAuthUser: function () {
            return this.authUser;
        },
        invalidate: function () {
            this.authenticated = false;
            this.authType = null;
            this.authUser = null;
        }
    }

}]);

bookify.factory('authEngine', ['$http', 'initUser', 'session', '$location', '$mdDialog', function ($http, initUser, session, $location, $mdDialog) {
    var self = this;

    self.loginAlert = function () {
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

    self.authAlert = function () {
        $mdDialog.show(
            $mdDialog.alert()
                .parent(angular.element(document.querySelector('#popupContainer')))
                .clickOutsideToClose(true)
                .title('Not enough permission')
                .textContent("You don't have enough permission for this action")
                .ariaLabel('Alert')
                .ok('Ok')
        ).then(function () {
            $location.path('');
        });
    };


    return {
        login: function (id, password) {
            var packet = {
                params: {
                    "id": id,
                    "password": password
                }
            };
            $http.post("login", "", packet)
                .success(function (data) {
                    session.setAuthenticated(true);
                    session.setAuthType(data.roles);
                    if (session.getAuthType().indexOf("ADMIN") !== -1) {
                        initUser.initAdmin(data.id);
                    }
                    else if (session.getAuthType().indexOf("USER") !== -1) {
                        initUser.initUser(data.id);
                    }
                })
                .error(function () {
                    self.loginAlert();
                    session.invalidate();
                });

        },
        logout: function () {
            $http.post("logout", "")
                .success(function () {
                    $location.path("");
                    session.invalidate();
                })
                .error()
        },
        authRequired: function (required) {
            if (session.getAuthenticated() !== true || session.getAuthType().indexOf(required) === -1 || session.getAuthUser() === null) {
                self.authAlert();
                session.invalidate();
            }
        }
    };
}]);