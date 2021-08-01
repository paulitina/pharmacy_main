<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html ng-app="myApp" ng-controller="myReqistrationController">
<head>
    <link rel="shortcut icon" href="#">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.js"></script>
    <link rel="stylesheet" href="resources/styles/account.css">
    <link rel="stylesheet" href="resources/styles/login.css">

    <title>Registration</title>
</head>
<body ng-cloak>
<script type="text/javascript">
    var app = angular.module("myApp", []);
</script>
<ul class="menu" style="text-align: right">
    <li><a href="#">Log In</a></li>
</ul>

<div id="reg-container">
    <table style="width:100%;">
        <tr>
            <td style="width: 100%"><h1><s:message text="Зарегистрироваться"/></h1></td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="input-group tab-elm">
					<span class="input-group-addon">
						<s:message text="Имя пользователя"/>
					</span>
                    <input id="userName" type="text" class="form-control" ng-model="userName">
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="input-group tab-elm">
					<span class="input-group-addon">
						<s:message text="Почта"/>
					</span>
                    <input id="email" type="email" class="form-control" ng-model="email">
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="input-group tab-elm">
					<span class="input-group-addon">
						<s:message text="Пароль"/>
					</span>
                    <input id="password" type="email" class="form-control" ng-model="password">
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="input-group tab-elm">
					<span class="input-group-addon">
						<s:message text="Повторите пароль"/>
					</span>
                    <input id="password2" type="email" class="form-control" ng-model="password2">
                    <span ng-model="errorMessage">{{errorMessage}}</span>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="input-group tab-elm">
                    <button type="button" class="btn btn-default" ng-click="regUser()"
                            ng-disabled="userName == '' || password == '' || email== '' || password2 == ''">
                        <s:message text="Зарегистрироваться"/>
                    </button>
                </div>
            </td>
        </tr>
    </table>
</div>


<script type="text/javascript">
    app.controller("myReqistrationController", function ($scope, $http) {

        $scope.user = [];
        $scope.userName = '';
        $scope.email = '';
        $scope.password = '';
        $scope.password2 = '';
        $scope.errorMessage = '';


        <%-- Регистрация пользователя--%>
        $scope.regUser = function () {
            $scope.userName = document.getElementById('userName').value;
            $scope.email = document.getElementById('email').value;
            $scope.password = document.getElementById('password').value;
            $scope.password2 = document.getElementById('password2').value;
            $scope.user = {
                userName: $scope.userName,
                email: $scope.email,
                password: $scope.password
            }
            console.log($scope.user);
            if ($scope.password !== $scope.password2){
                $scope.errorMessage = 'Пароли не совпадают';
            }else{
                $scope.list = $http.post("api/pharmacy/user", $scope.user)
                let link = $("#openLink");
                link.href = "http://localhost:8080/login_page";
                window.open(link.href);
            }
        }

        angular.element(document).ready(function () {
            console.log('page loading completed');
        });
    });
</script>
</body>
</html>