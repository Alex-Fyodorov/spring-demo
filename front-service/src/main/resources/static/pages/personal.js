angular.module('myLibrary')
    .controller('personalController', function ($scope, $rootScope, $http, $routeParams) {
//    const contextPathReaders = 'http://localhost:8191/lib-reader/readers';
//    const contextPathIssues = 'http://localhost:8190/lib-core/issues';
    var readerName = $routeParams.name;

    $scope.loadReader = function() {
        $rootScope.getReaderAddress();
        console.log($rootScope.readerAddress);
        $http.get($rootScope.readerAddress + '/lib-reader/readers/name/' + readerName)
            .then(function(response) {
                console.log(response.data);
                $scope.Reader = response.data;
            });
    };

    $scope.loadPersonalIssueList = function() {
        $rootScope.getCoreAddress();
        console.log($rootScope.coreAddress);
        $http({
            url: $rootScope.coreAddress + '/lib-core/issues/reader/current',
            method: 'get',
            params: {
                reader_name: readerName
            }
        }).then(function(response) {
                console.log(response.data);
                $scope.PersonalIssueList = response.data;
            });
    };

//    $scope.loadPersonalIssueList = function() {
//        $http.get(contextPathIssues + '/reader/current')
//            .then(function(response) {
//                console.log(response.data);
//                $scope.PersonalIssueList = response.data;
//            });
//    };

    $scope.loadReader();
    $scope.loadPersonalIssueList();
});