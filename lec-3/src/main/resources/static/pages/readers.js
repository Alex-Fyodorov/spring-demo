angular.module('myLibrary')
    .controller('readerController', function ($scope, $http) {
    const contextPathReaders = 'http://localhost:8190/book-lib/readers';

    $scope.loadReaders = function() {
        $http.get(contextPathReaders)
            .then(function(response) {
                console.log(response.data);
                $scope.ReaderList = response.data;
            });
    };

    $scope.loadReaders();
});