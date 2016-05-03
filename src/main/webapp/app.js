/**
 * Created by David on 4/6/2016.
 */
var appModule = angular.module('myApp', []);

appModule.controller('MainCtrl', ['mainService','$scope','$http',
    function(mainService, $scope, $http) {
        $scope.greeting = 'BookBuddaroos';
        $scope.token = null;
        $scope.error = null;
        $scope.threadCreated = false;

        $scope.login = function () {
            $scope.error = null;
            mainService.login($scope.userName, $scope.password).then(function (token) {
                    $scope.token = token;
                    console.log("token=" + token);
                    $http.defaults.headers.common.Authorization = 'Bearer ' + token;

                },
                function (error) {
                    $scope.error = error;
                    $scope.userName = '';
                    $scope.password = '';
                    $scope.question = '';
                    $scope.answer = '';
                });
        }
        $scope.createAccount = function () {
            $scope.error = null;
            mainService.createAccount($scope.userName, $scope.password, $scope.question, $scope.answer).then(function (token) {
                    $scope.token = token;
                    console.log("token=" + token);

                    $http.defaults.headers.common.Authorization = 'Bearer ' + token;
                },
                function (error) {
                    $scope.error = error;
                    $scope.userName = '';
                    $scope.password = '';
                    $scope.question = '';
                    $scope.answer = '';
                });
        }
        $scope.getAccountDetails = function () {
            $scope.error = null;
            mainService.getAccountDetails().then(function (data) {
                    $scope.data = data;
                    console.log("user ID=" + data);
                },
                function (error) {
                    $scope.error = error;
                });
        }


        $scope.logout = function () {
            $scope.userName = '';
            $scope.password = '';
            $scope.question = '';
            $scope.answer = '';
            $scope.token = null;
            $http.defaults.headers.common.Authorization = '';
        }

        $scope.loggedIn = function () {
            return $scope.token !== null;
        }


        $scope.findThread = function () {
            $scope.error = null;
            mainService.findThread($scope.recipient, $scope.message).then(function (token) {
                    $scope.token = token;
                    console.log("userName=" + recipient);
                    console.log("data=" + data)
                },
                function (error) {
                    $scope.error = error;
                    $scope.recipient = '';
                    $scope.message = '';
                });
        }
        $scope.createMessage = function () {
            $scope.error = null;
            mainService.createMessage($scope.recipient, $scope.message).then(function (message) {

                    console.log("text=" + message);
                    console.log("success");

                },
                function (error) {
                    $scope.error = error;
                    $scope.recipient = '';
                    $scope.message = '';
                });

            $scope.threadCreated = function () {
                return $scope.thread !== null;
            }
        }
        $scope.edit = function () {
            $scope.error = null;
            mainService.edit($scope.recipient, $scope.message).then(function (token) {
                    $scope.token = token;

                },
                function (error) {
                    $scope.error = error;
                    $scope.userName = '';
                    $scope.thread = '';
                    $scope.message = '';
                    $scope.created = '';
                });
        }
        $scope.delete = function () {
            $scope.error = null;
            mainService.delete($scope.recipient, $scope.message).then(function (token) {

                },
                function (error) {
                    $scope.error = error;
                    $scope.recipient = '';

                    $scope.message = '';

                });
        }

        $scope.threadCreated = function () {
            return $scope.message !== null;
        }
    }
]);



appModule.service('mainService', function($http) {
    return {
        login: function (username, password) {
            var data = "name=" + username + "&password=" + password;

            console.log(data)
            $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";


            return $http.post('http://localhost:8080/users/login', data).then(function (response) {
                return response.data.token;
            });

        },

        createAccount: function (username, password, question, answer) {
            var data = "name=" + username + "&password=" + password + "&question=" + question + "answer=" + answer;

            console.log(data)
            $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";


            return $http.post('http://localhost:8080/users', data).then(function (response) {
                return response.data.token;
            });

        },

        getAccountDetails: function () {
            var data = "Authorization	: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJOaWNrIiwiaWQiOjF9.KhStwKp6-ma3ZxYI8EhLD8oRHz8AVnWNJC37-QljOMc'";

            return $http.get('http://localhost:8080/users/account').then(function (response) {
                console.log("response.data.id = " + response.data.id);
                return response.data.id;
            });
        },

        findThread: function (username) {
            var data = "recipient=" + username;

            return $http.get('http://localhost:8080/messages').then(function (response) {
                console.log("response.data.id = " + response.data.id);
                return response.data.id;
            });
        },
        createMessage: function (username, message) {
            var data = "recipient=" + username + "&text=" + message;
            console.log("Data ugh = " + data);
            return $http.post('http://localhost:8080/messages', data).then(function (response) {
                console.log("response.data = " + response.data);
                return response.data;
            });
        },
        edit: function (username, id, message) {
            var data = "recipient=" + username + "messageId=" + id + "text=" + message;

            return $http.put('http://localhost:8080/messages').then(function (response) {
                console.log("response.data = " + response.data);
                return response.data.id;
            });
        },
        delete: function (id) {
            var data = "messageId=" + id;

            return $http.put('http://localhost:8080/messages').then(function (response) {
                console.log("response.data.id = " + response.data.id);
                return response.data.id;
            });
        }
    }

});
