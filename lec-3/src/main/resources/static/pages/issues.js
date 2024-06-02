angular.module('myLibrary')
    .controller('issueController', function ($scope, $http) {
    const contextPathIssues = 'http://localhost:8190/book-lib/issues';

    $scope.loadIssues = function() {
        $http.get(contextPathIssues)
            .then(function(response) {
                console.log(response.data);
                $scope.IssueList = response.data;
            });
    };

    $scope.loadIssues();
});