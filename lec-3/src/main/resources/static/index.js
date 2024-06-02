var marketApp = angular.module('myLibrary', ['ngRoute']);

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
    function ($scope, $http, $location) {
    $location.path('/');
});