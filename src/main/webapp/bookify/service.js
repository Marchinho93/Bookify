bookify.factory('authUtility', ['$http', function ($http) {
    return {
        refreshCSRF: function () {
            return $http.get('refreshCSRF');
        },
        setAdminData: function (data) {
            userData = {
                name: data.name,
                surname: data.surname,
                birthday: data.birthday,
                address: data.address,
                phone: data.phone,
                city: data.city,
                email: data.email
            };
            return userData;
        },
        setUserData: function (data) {
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
            return userData;
        },
        initAdmin: function (id) {
            var packet = {
                params: {
                    'id': id
                }
            };
            return $http.post('initAdmin', "", packet);

        },
        initUser: function (id) {
            var packet = {
                params: {
                    'id': id
                }
            };
            return $http.post('initUser', "", packet);
        }
    }
}]);

bookify.factory('session', [function () {
    var self = this;
    var authUser = null;
    var authenticated = false;
    var authType = null;
    var created = null;

    return {
        init: function () {
            this.authenticated = false;
            this.authUser = null;
            this.authType = [];
            this.created = null;
        },
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
        getCreated: function () {
          return this.created;  
        },
        setCreated: function (created) {
            this.created = created;
        },
        invalidate: function () {
            this.authenticated = false;
            this.authType = [];
            this.authUser = null;
            this.created = null;
        }
    }
}]);

bookify.factory('authEngine', ['$http', 'authUtility', 'session', 'sessionCacheService', '$location', 'exceptionHandler', function ($http, authUtility, session, sessionCacheService, $location, exceptionHandler) {
    session.init();
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
                    session.setCreated((new Date).getTime());
                    if (session.getAuthType().indexOf("ADMIN") !== -1) {
                        authUtility.refreshCSRF().success(function () {
                            authUtility.initAdmin(data.id).success(function (data) {
                                session.setAuthUser(authUtility.setAdminData(data));
                                sessionCacheService.saveSession();
                                $location.path("admin");
                            }).error(function () {
                                exceptionHandler.dbAlert();
                                session.invalidate();
                                return new Error("dbError");
                            });
                        });
                    } else if (session.getAuthType().indexOf("USER") !== -1) {
                        authUtility.refreshCSRF().success(function () {
                            authUtility.initUser(data.id).success(function (data) {
                                session.setAuthUser(authUtility.setUserData(data));
                                sessionCacheService.saveSession();
                                
                                $location.path("user");
                            }).error(function () {
                                exceptionHandler.dbAlert();
                                session.invalidate();
                                return new Error("dbError");
                            });
                        });
                    } //non funziona, metter dentro e finire implementazione

                })
                .error(function () {
                    exceptionHandler.loginAlert();
                    session.invalidate();
                });
        },
        logout: function () {
            var packet = "";
            $http.post("logout", "", packet)
                .success(function () {
                    $location.path("");
                    session.invalidate();
                })
                .error(function () {})
        },
        authRequired: function (required) {
            if (session.getAuthenticated() != true || session.getAuthType().indexOf(required) == -1 || session.getAuthUser() == null) {
                console.log(session.getAuthenticated() != true);
                console.log(session.getAuthType().indexOf(required) == -1);
                console.log(session.getAuthUser() == null);
                exceptionHandler.authAlert();
                session.invalidate();
                return false;
            }
            return true;
        }
    };
}]);

bookify.factory('exceptionHandler', ['$mdDialog', '$location', function ($mdDialog, $location) {
    return {
        dbAlert: function () {
            $mdDialog.show(
                $mdDialog.alert()
                .parent(angular.element(document.querySelector('#popupContainer')))
                .clickOutsideToClose(true)
                .title('Database Error')
                .textContent('Please, retry later')
                .ariaLabel('Alert')
                .ok('Ok')
            ).then(function () {
                $location.path("login");
            });
        },
        loginAlert: function () {
            $mdDialog.show(
                $mdDialog.alert()
                .parent(angular.element(document.querySelector('#popupContainer')))
                .clickOutsideToClose(true)
                .title('Username or Password incorrect')
                .textContent('Please, insert correct credentials')
                .ariaLabel('Alert')
                .ok('Ok')
            );
        },
        authAlert: function () {
            $location.path('');
            $mdDialog.show(
                $mdDialog.alert()
                .parent(angular.element(document.querySelector('#popupContainer')))
                .clickOutsideToClose(true)
                .title('Not enough permission')
                .textContent("You don't have enough permission for this action")
                .ariaLabel('Alert')
                .ok('Ok')
            ).then(function () {});
        },
        cacheAlert: function () {
            console.log("nada");
            $mdDialog.show(
                $mdDialog.alert()
                .parent(angular.element(document.querySelector('#popupContainer')))
                .clickOutsideToClose(true)
                .title('Cache not available')
                .textContent("Your browser isn't compatible with local storage.")
                .ariaLabel('Alert')
                .ok('Ok')
            ).then(function () {});
        }
    };
}]);

bookify.factory('sessionCacheService', ['localStorageService', 'session', '$location', 'exceptionHandler', function (localStorageService, session, $location, exceptionHandler) {
		if (localStorageService.isSupported) {
            console.log("è supportato");
            if (localStorageService.get('authenticated') && (((new Date).getTime()) - (localStorageService.get('created'))) < 1800000) {
                console.log("sono nell'if");
                session.setAuthenticated(localStorageService.get('authenticated'));
                session.setAuthType(localStorageService.get('authType'));
                session.setAuthUser(localStorageService.get('authUser'));
                session.setCreated(localStorageService.get('created'));
                $location.path('admin');
            }
            else{
                console.log(localStorageService.get('authenticated'));
                console.log(((new Date).getTime()) - (localStorageService.get('created')) < 1800000);
                session.setAuthenticated(false);
                session.setAuthType([]);
                session.setAuthUser(null);
                session.setCreated(null);
            };
        return {
            saveSession: function () {
                localStorageService.set('authenticated', session.getAuthenticated());
                localStorageService.set('authType', session.getAuthType());
                localStorageService.set('authUser', session.getAuthUser());
                localStorageService.set('created', session.getCreated());
            },
            clear: function () {
                localStorageService.clearAll();
            }
        };
    } else {
        exceptionHandler.cacheAlert();
    }
}]);
