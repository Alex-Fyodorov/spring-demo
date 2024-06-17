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
        .when('/personal/:name', {
            templateUrl: 'pages/personal.html',
            controller: 'personalController'
        })
        .otherwise({
            redirectTo: '/'
        });
});

marketApp.controller('indexController',
    function ($scope, $rootScope, $http, $localStorage, $location) {
    //const contextPathAuth = 'http://localhost:8191/lib-reader/authenticate';
    const contextPathAddresses = 'http://localhost:3000/lib-front/addresses';
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

// Вход в учётную запись
    $scope.tryToAuth = function() {
        $rootScope.getReaderAddress();
        console.log($rootScope.readerAddress);
        $http.post($rootScope.readerAddress + '/lib-reader/authenticate', $scope.user)
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

// Проверка, вошёл ли пользователь
    $scope.isUserLoggedIn = function() {
        if ($localStorage.libraryWebUser) {
            return true;
        } else {
            return false;
        }
    };

// Получение адреса для отправки запроса
    $rootScope.getCoreAddress = function() {
        $http.get(contextPathAddresses + '/core-service')
            .then(function (response) {
                $rootScope.coreAddress = response.data.address;
            });
    };

// Получение адреса для отправки запроса
    $rootScope.getReaderAddress = function() {
        $http.get(contextPathAddresses + '/reader-service')
            .then(function (response) {
                $rootScope.readerAddress = response.data.address;
            });
    };

    $scope.getCoreAddress();
    $scope.getReaderAddress();
});