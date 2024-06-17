angular.module('myLibrary')
    .controller('issueController', function ($scope, $rootScope, $http) {
    //const contextPathIssues = 'http://localhost:8190/lib-core/issues';

    $scope.loadIssues = function() {
        $rootScope.getCoreAddress();
        console.log($rootScope.coreAddress);
        $http.get($rootScope.coreAddress + '/lib-core/issues')
            .then(function(response) {
                console.log(response.data);
                $scope.IssueList = response.data;
            });
    };

    $scope.loadIssues();
});