angular.module('myLibrary')
    .controller('personalController', function ($scope, $http, $routeParams) {
    const contextPathReaders = 'http://localhost:8190/book-lib/readers';
    const contextPathIssues = 'http://localhost:8190/book-lib/issues';
    var readerId = $routeParams.id;

    $scope.loadReader = function() {
        $http.get(contextPathReaders + '/' + readerId)
            .then(function(response) {
                console.log(response.data);
                $scope.Reader = response.data;
            });
    };

    $scope.loadPersonalIssueList = function() {
        $http.get(contextPathIssues + '/reader/' + readerId + '/current')
            .then(function(response) {
                console.log(response.data);
                $scope.PersonalIssueList = response.data;
            });
    };

    $scope.loadReader();
    $scope.loadPersonalIssueList();
});