angular.module('myLibrary')
    .controller('bookController', function ($scope, $http) {
    const contextPathBooks = 'http://localhost:8190/book-lib/books';

    $scope.loadBooks = function() {
        $http.get(contextPathBooks)
            .then(function(response) {
                console.log(response.data);
                $scope.BookList = response.data;
            });
    };

    $scope.loadBooks();
});