<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html ng-app="myApp" ng-controller="myAccountController"
xmlns:th="http://www.thymeLeaf.org">
<head>
    <link rel="shortcut icon" href="#">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.js"></script>
    <link rel="stylesheet" href="resources/styles/product_catalog.css">
    <link rel="stylesheet" href="resources/styles/account.css">
    <title>Account</title>
</head>
<body ng-cloak>
<script type="text/javascript">
    var app = angular.module("myApp", []);
</script>
<ul class="menu" style="text-align: right">
    <li><a href="catalog">Catalog</a></li>
    <li><a href="cart">Cart</a></li>
    <li><a href="account">My account</a></li>
    <li><a href="logout">Log Out</a></li>
</ul>

<div class="user-container" ng-model="user">
    <div>
        <span class="userNameHead">Ваше пользовательское имя:  </span>
        <span class="userName">{{user.userName}}</span>
        <button type="button" class="btn" ng-click="changeUserNameClick()" ng-hide="showResetOfUserName">Сменить имя пользователя</button>
        <input id="newUserName" type="text" ng-show="showResetOfUserName">
        <button type="button" class="btn" ng-show="showResetOfUserName" ng-click="changeUserName()">Сменить</button>
    </div>
    <div class="middleAndBottom">
        <span class="emailHead">Ваш email:  </span>
        <span class="email">{{user.email}}</span>
        <button type="button" class="btn" ng-click="changeEmail()">Сменить email</button>
        <input id="email" type="text" ng-show="ShowResetOfEmail">
    </div>
    <div class="middleAndBottom">
        <span class="passwordHead">Пароль засекречен в целях безопасности ваших данных:  </span>
        <span class="password">{{user.password}}</span>
        <button type="button" class="btn" ng-click="changePassword()">Сменить пароль</button>
        <input id="newPassword" type="text" ng-show="ShowResetOfPassword">
    </div>
    <button class="btn" ng-click="showOrders()">Посмотреть мои заказы</button>
</div>
<script type="text/javascript">
    app.controller("myAccountController", function ($scope, $http) {

        $scope.user = {};
        $scope.showResetOfUserName = false;
        $scope.showResetOfEmail = false;
        $scope.showResetOfPassword = false;

        <%-- Редирект на заказы пользователя--%>
        $scope.showOrders = () =>{
            let link = $("#openLink");
            link.href = "http://localhost:8080/user_orders";
            window.open(link.href);
        }

        <%-- Показать окно для смены пользовательского имени--%>
        $scope.changeUserNameClick = function (){
            $scope.showResetOfUserName = true;
        }

        <%-- Смена пользовательского имени--%>
        $scope.changeUserName = function (){
            $scope.userName = document.getElementById('newUserName').value;
            console.log($scope.userName);
            $scope.list = $http.get("api/pharmacy/user")
                .then(
                    function (response) {
                        $scope.user = response.data;
                    },
                    function (errResp) {
                        console.error(errResp);
                    }
                );
            let newUser = {
                userId: $scope.user.userId,
                userName: $scope.userName,
                email: $scope.user.email,
                password: $scope.user.password
            }
            console.log('dbjchd' + JSON.stringify(newUser));
            // $http.put("api/pharmacy/user/"+$scope.user.userName, JSON.stringify(newUser), application/json);
            $http({
                method: 'PUT',
                url: "api/pharmacy/user/"+$scope.user.userName,
                data: JSON.stringify(newUser),
                headers: {'Content-Type': 'application/json'}
            });
            $scope.showResetOfUserName = false;
            $scope.readUserInfo();
        }

        <%-- Получение списка товаров--%>
        $scope.readUserInfo = function () {
            $scope.list = $http.get("api/pharmacy/user")
                .then(
                    function (response) {
                        $scope.user = response.data;
                        console.log($scope.user);
                    },
                    function (errResp) {
                        console.error(errResp);
                    }
                );
        }

        angular.element(document).ready(function () {
            console.log('page loading completed');
            $scope.readUserInfo();
        });
    });
</script>
</body>
</html>