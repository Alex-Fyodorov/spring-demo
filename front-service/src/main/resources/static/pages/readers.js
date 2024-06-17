angular.module('myLibrary')
    .controller('readerController', function ($scope, $rootScope, $http) {
    //const contextPathReaders = 'http://localhost:8191/lib-reader/readers';

    $scope.loadReaders = function() {
        $rootScope.getReaderAddress();
        console.log($rootScope.readerAddress);
        $http.get($rootScope.readerAddress + '/lib-reader/readers')
            .then(function(response) {
                console.log(response.data);
                $scope.ReaderList = response.data;
            });
    };

    $scope.loadReaders();
});