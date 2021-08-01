<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html ng-app="myApp" ng-controller="myReqistrationController">
<head>
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
                    <input type="text" class="form-control" ng-model="userName"
                           ng-keypress="userChanged($event)">
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="input-group tab-elm">
					<span class="input-group-addon">
						<s:message text="Почта"/>
					</span>
                    <input type="email" class="form-control" ng-model="password"
                           ng-keypress="passChanged($event)">
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="input-group tab-elm">
					<span class="input-group-addon">
						<s:message text="Пароль"/>
					</span>
                    <input type="email" class="form-control" ng-model="password"
                           ng-keypress="passChanged($event)">
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="input-group tab-elm">
					<span class="input-group-addon">
						<s:message text="Повторите пароль"/>
					</span>
                    <input type="email" class="form-control" ng-model="password"
                           ng-keypress="passChanged($event)">
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="input-group tab-elm">
                    <button type="button" class="btn btn-default" ng-click="enter()"
                            ng-disabled="userName == '' || password == ''">
                        <s:message text="Войти"/>
                    </button>
                </div>
            </td>
        </tr>
    </table>
</div>


<script type="text/javascript">
    app.controller("myReqistrationController", function ($scope, $http) {

        $scope.user = {};

        <%-- Регистрация пользователя--%>
        $scope.regUser = function () {
            $scope.list = $http.post("api/pharmacy/user", $scope.user)
        }

        angular.element(document).ready(function () {
            console.log('page loading completed');
        });
    });
</script>
</body>
</html>