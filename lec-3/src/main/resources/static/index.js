var marketApp = angular.module('myLibrary', ['ngRoute', 'ngStorage']);

marketApp.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : 'pages/welcome.html',
            controller  : 'welcomeController'
        })
        .when('/books', {
            templateUrl: 'pages/books.html',
            controller: 'bookController'
        })
        .when('/readers', {
            templateUrl: 'pages/readers.html',
            controller: 'readerController'
        })
        .when('/issues', {
            templateUrl: 'pages/issues.html',
            controller: 'issueController'
        })
        .when('/personal/:id', {
            templateUrl: 'pages/personal.html',
            controller: 'personalController'
        })
        .otherwise({
            redirectTo: '/'
        });
});

marketApp.controller('indexController',
    function ($scope, $http, $location, $localStorage) {
    const contextPathAuth = 'http://localhost:8190/book-lib/authenticate';
    $location.path('/');

//При перезагрузке заставляет снова подвязывать токен к запросам
    if ($localStorage.libraryWebUser) {
        try {
            let jwt = $localStorage.libraryWebUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!!");
                delete $localStorage.libraryWebUser.token;
                $http.defaults.headers.common.Authorization = '';
                alert("Token is expired!!!");
                $scope.tryToLogout();
            } else {
                $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.libraryWebUser.token;
            }
        } catch (e) {
        }
    }

// Проверка, вошёл ли пользователь
    $scope.isUserLoggedIn = function() {
        if ($localStorage.libraryWebUser) {
            return true;
        } else {
            return false;
        }
    };

// Вход в учётную запись
    $scope.tryToAuth = function() {
        $http.post(contextPathAuth, $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.libraryWebUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;
                    $location.path('/');
                }
            }, function errorCallback(response) {
                alert("Incorrect username or password.")
            });
    };

// Получение имени пользователя
    $scope.getUsername = function() {
        return $localStorage.libraryWebUser.username;
    };

// Выход из учётной записи
    $scope.tryToLogout = function() {
        delete $localStorage.libraryWebUser;
        $http.defaults.headers.common.Authorization = '';
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
        $location.path('/');
    };
});