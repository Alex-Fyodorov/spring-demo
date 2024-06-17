angular.module('myLibrary')
    .controller('bookController', function ($scope, $rootScope, $http) {
    //const contextPathBooks = 'http://localhost:8190/lib-core/books';

    $scope.loadBooks = function() {
        $rootScope.getCoreAddress();
        console.log($rootScope.coreAddress);
        $http.get($rootScope.coreAddress + '/lib-core/books')
            .then(function(response) {
                console.log(response.data);
                $scope.BookList = response.data;
            });
    };

    $scope.loadBooks();
});