<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html ng-app="myApp" ng-controller="myAccountController">
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.js"></script>
    <link rel="stylesheet" href="resources/styles/product_catalog.css">
    <link rel="stylesheet" href="resources/styles/account.css">
    <title>Catalog</title>
</head>
<body ng-cloak>
<script type="text/javascript">
    var app = angular.module("myApp", []);
</script>
<ul class="menu" style="text-align: right">
    <li><a href="catalog">Catalog</a></li>
    <li><a href="cart">Cart</a></li>
    <li><a href="account">My account</a></li>
    <li><a href="#">Log Out</a></li>
</ul>

<div class="user-container" ng-model="user">
<%--    <div>--%>
<%--        <span class="userIdHead">Это ваш личный пользовательский номер:  </span>--%>
<%--        <span class="userId">{{user.userId}}</span>--%>
<%--    </div>--%>
    <div>
        <span class="userNameHead">Ваше пользовательское имя:  </span>
        <span class="userName">{{user.userName}}</span>
        <button type="button" class="btn" ng-click="">Сменить имя пользователя</button>
        <input type="text" ng-show="showResetOfUserName">
    </div>
    <div class="middleAndBottom">
        <span class="emailHead">Ваш email:  </span>
        <span class="email">{{user.email}}</span>
        <button type="button" class="btn" ng-click="">Сменить email</button>
        <input type="text" ng-show="ShowResetOfEmail">
    </div>
    <div class="middleAndBottom">
        <span class="passwordHead">Пароль засекречен в целях безопасности ваших данных:  </span>
        <span class="password">{{user.password}}</span>
        <button type="button" class="btn" ng-click="">Сменить пароль</button>
        <input type="text" ng-show="ShowResetOfPassword">
    </div>
</div>
<script type="text/javascript">
    app.controller("myAccountController", function ($scope, $http) {

        $scope.user = {};

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


        $scope.getProductId = (productId) => {

            // $http.get("api/pharmacy/product/" + productId);
        }

        $scope.textInSearch = "aaa";

        $scope.readLine = (text) => {
            $scope.textInSearch = text;
        }

        $scope.searchProduct = (textInSearch) => {
            if ($scope.products.name === $scope.textInSearch) {
                $scope.products = 'a';
                console.log($scope.textInSearch);
                console.log($scope.products);
            }
        }

        angular.element(document).ready(function () {
            console.log('page loading completed');
            $scope.readUserInfo();
        });
    });
</script>
</body>
</html>