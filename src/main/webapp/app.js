/**
 * Created by David on 4/6/2016.
 */
var appModule = angular.module('myApp', []);

appModule.controller('MainCtrl', ['mainService','$scope','$http',
    function(mainService, $scope, $http) {
        $scope.greeting = 'BookBuddaroos';
        $scope.token = null;
        $scope.error = null;
        $scope.header = null;
//            $scope.roleUser = false;
//            $scope.roleAdmin = false;
//            $scope.roleFoo = false;

        $scope.login = function() {
            $scope.error = null;
            mainService.login($scope.userName, $scope.password).then(function(token) {
                    $scope.token = token;
                    console.log("token="+token);
                    $http.defaults.headers.common.Authorization = 'Bearer ' + token;
                    $scope.header = 'Bearer ' + token;
                    //$scope.checkRoles();
                },
                function(error){
                    $scope.error = error;
                    $scope.userName = '';
                    $scope.password = '';
                    $scope.question = '';
                    $scope.answer = '';
                });
        }
        $scope.createAccount = function() {
            $scope.error = null;
            mainService.createAccount($scope.userName, $scope.password, $scope.question, $scope.answer).then(function(token){
                    $scope.token = token;
                    console.log("token="+token);

                    $http.defaults.headers.common.Authorization = 'Bearer ' + token;
                },
                function(error){
                    $scope.error = error;
                    $scope.userName = '';
                    $scope.password = '';
                    $scope.question = '';
                    $scope.answer = '';
                });
        }
        $scope.getAccountDetails = function() {
            $scope.error = null;
            mainService.getAccountDetails().then(function(data){
                    $scope.data = data;
                    console.log("data in controller: "+data);
                },
                function(error){
                    $scope.error = error;
                });
        }



        $scope.logout = function() {
            $scope.userName = '';
            $scope.password = '';
            $scope.question = '';
            $scope.answer = '';
            $scope.token = null;
            $http.defaults.headers.common.Authorization = '';
        }

        $scope.loggedIn = function() {
            return $scope.token !== null;
        }


    } ]);



appModule.service('mainService', function($http) {
    return {
        login : function(username, password) {
            var data = "name="+username+"&password="+password;

            //var data = 'name='+username+'&password=ugh';

            //var data = "name="+username"&password="password;
            console.log(data)
            $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";


            return $http.post('http://localhost:8080/users/login', data).then(function(response) {
                return response.data.token;
            });

            return $http.post('http://localhost:8080/users/login', data).then(function(response){
                return response.data.token;
            })
        },

        createAccount : function(username, password, question, answer) {
            var data = "name="+username+"&password="+password+"&question="+question+"answer="+answer;

            console.log(data)
            $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";


            return $http.post('http://localhost:8080/users', data).then(function(response) {
                return response.data.token;
            });

//            return $http.post('http://localhost:8080/users', data).then(function(response){
//                return response.data.token;
//            })
        },

        getAccountDetails : function(header) {
            var data = "Authorization	: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJOaWNrIiwiaWQiOjF9.KhStwKp6-ma3ZxYI8EhLD8oRHz8AVnWNJC37-QljOMc'";

//            var settings = {
//                  "async": true,
//                  "crossDomain": true,
//                  "url": "http://localhost:8080/users/account",
//                  "method": "GET",
//                  "headers": {
//                    "authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJOaWNrIiwiaWQiOjF9.KhStwKp6-ma3ZxYI8EhLD8oRHz8AVnWNJC37-QljOMc",
//                    "cache-control": "no-cache",
//                    "postman-token": "faabcf28-a588-6e2e-6122-e73d8f6d71a4"
//                  },
//                  "data": {
//                    "name": "Nick",
//                    "password": "ugh",
//                    "question": "nope",
//                    "answer": "yup"
//                  }
//                }
//
            return $http.get('http://localhost:8080/users/account').then(function(response){
                console.log("response = "+response);
                console.log("response.data = " + response.data);
                return response.data;
            });
        },

        hasRole : function(role) {
            return $http.get('/api/role/' + role).then(function(response){
                console.log(response);
                return response.data;
            });
        }
    };
});