/**
 * Created by David on 4/6/2016.
 */
var appModule = angular.module('myApp', []);

appModule.controller('MainCtrl', ['mainService', '$scope', '$http',
    function (mainService, $scope, $http) {
        $scope.greeting = 'Schlooby Dooby Doo';
        $scope.error = null;
        $scope.searched = false;
        $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
        $http.defaults.headers.common.Authorization = 'Bearer ' + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUb2J5IiwiaWQiOjF9.pRKQFSz0tEJvWeBO4ji85HWKHBnYEZ3GC7w4dxG2m54";
        $scope.groupId = 1;
        $scope.group = null;
        $scope.token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUb2J5IiwiaWQiOjF9.pRKQFSz0tEJvWeBO4ji85HWKHBnYEZ3GC7w4dxG2m54";
        $scope.groupCreated = null;
        $scope.posts = null;




        $scope.findGroup = function () {
            mainService.findBook($scope.groupId).then(function(groupData) {
                $scope.groupCreated = true;
                $scope.group = groupData;
                $scope.posts = groupData.posts;
                console.log($scope.posts);
            });

        };

        $scope.loggedIn = function () {
            return $scope.token !== null && $scope.token !== undefined;
        };

        $scope.inGroup = function () {
            return $scope.groupCreated !== null;
        };

        $scope.reply = function () {
            mainService.reply($scope.groupId, $scope.text).then(function(response) {
                console.log(response.data);
            });

        };




    }
]);


appModule.service('mainService', function ($http) {
    return {
        findBook: function (groupId) {
            return $http.get('http://localhost:8080/groups/' + groupId).then(function (response) {
                console.log(response.data);
                return response.data;
            })
        },
        reply: function (groupId, text) {

            var data = "text=" + text;

            return $http.post('http://localhost:8080/groups/' + groupId, data).then(function (response) {
                console.log(response.data);
                return response.data;

            });
        }
    }

});

