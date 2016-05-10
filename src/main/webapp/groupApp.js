/**
 * Created by David on 4/6/2016.
 */
var appModule = angular.module('myApp', []);

appModule.controller('MainCtrl', ['mainService', '$scope', '$http',
    function (mainService, $scope, $http) {
        $scope.greeting = 'Book Group';
        $scope.error = null;
        $scope.searched = false;
        $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
        $http.defaults.headers.common.Authorization = 'Bearer ' + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUb2J5IiwiaWQiOjF9.pRKQFSz0tEJvWeBO4ji85HWKHBnYEZ3GC7w4dxG2m54";
        $scope.groupId = 1;
        $scope.group = null;
        $scope.token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUb2J5IiwiaWQiOjF9.pRKQFSz0tEJvWeBO4ji85HWKHBnYEZ3GC7w4dxG2m54";
        $scope.groupCreated = null;
        $scope.posts = null;
        $scope.isReply = false;
        $scope.children = null;




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

        $scope.upvote = function(postId){
           // var postId = 2;
            mainService.upvote(postId).then(function(group){
                $scope.posts = group.posts;

                return group;
            })
        };

        $scope.report = function(postId) {
            // var postId = 2;
            mainService.report(postId).then(function (group) {
                $scope.posts = group.posts;

                return group;
            })
        };

        $scope.reply = function () {
            mainService.reply($scope.groupId, $scope.text).then(function(response) {
                $scope.posts = response.posts;
                $scope.text = '';
                return response;
            });

        };

        $scope.replyTo = function (postId, text) {
            mainService.replyTo($scope.groupId, postId, text).then(function(response) {
                $scope.posts = response.posts;
                //$scope.children = response;
                $scope.isReply = false;
                return response;
            });

        };

        $scope.setReply = function () {
            $scope.isReply = true;
        }

        $scope.showReply = function () {
            return $scope.isReply;
        }

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
        },
        replyTo: function (groupId, postId, text) {

            var data = "text=" + text;
            console.log(data);

            return $http.post('http://localhost:8080/groups/' + groupId + "/" + postId, data).then(function (response) {
                console.log(response.data);
                return response.data;

            });
        },
        upvote: function (postId) {
            return $http.put('http://localhost:8080/groups/1/'+ postId +'/upvote'). then(function (response) {
                console.log(response.data);
                return response.data;
            });
        },

        report: function (postId) {
            return $http.put('http://localhost:8080/groups/1/'+ postId +'/report'). then(function (response) {
                console.log(response.data);
                return response.data;
            });
        }


    }

});

