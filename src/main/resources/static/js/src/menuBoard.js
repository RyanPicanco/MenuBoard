var app = angular.module('menu-board', []);

app.controller('instanceController', function($scope, $http, $filter) {
    $scope.menuItems = [];

    $http.get("/api//menuItems/")
        .success(function(response) {
            $scope.menuItems = response;
        });

});