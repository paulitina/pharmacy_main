<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html ng-app="myApp" ng-controller="myAccountController">
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.js"></script>
    <link rel="stylesheet" href="resources/styles/product_catalog.css">
    <link rel="stylesheet" href="resources/styles/account.css">
    <title>MyOrders</title>
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

<p ng-show="showEmpty">У вас нет подтвержденных заказов</p>
<div class="user-container" ng-model="orders">
    <table style="width: 100%" ng-show="show">
        <tr>
            <th>Id заказа</th>
            <th>Статус</th>
            <th>Адрес</th>
            <th>Id товара</th>
            <th>Количество</th>
        </tr>
        <tr ng-repeat="order in orders">
            <td width="100px">{{order.orderId}}</td>
            <td>{{order.status}}</td>
            <td>{{order.address}}</td>
            <td ng-model="productDtoList">{{order.productDtoList}}</td>
            <td ng-model="productDtoList">{{productDtoList.quantity}}</td>
        </tr>
    </table>

</div>
<script type="text/javascript">
    app.controller("myAccountController", function ($scope, $http) {

        $scope.orders = {};
        $scope.productDtoList = [];
        $scope.show = true;
        $scope.showEmpty = false;

        <%-- Получение списка товаров--%>
        $scope.getUserOrders = function () {
            $scope.list = $http.get("api/pharmacy/order/all")
                .then(
                    function (response) {
                        $scope.orders = response.data;
                        console.log($scope.orders);
                        if ($scope.orders.length == 0) {
                            $scope.show = false;
                            $scope.showEmpty = true;
                        }
                        console.log($scope.orders.productDtoList);
                        // $scope.productDtoList = JSON.parse($scope.orders);
                        console.log($scope.productDtoList);
                        // $scope.productsInList = {
                        //     productId: $scope.orders.productDtoList,
                        //     // quantity: $scope.orders.productDtoList.quantity
                        // }
                    },
                    function (errResp) {
                        console.error(errResp);
                    }
                );
        }

        angular.element(document).ready(function () {
            console.log('page loading completed');
            $scope.getUserOrders();
        });
    });
</script>
</body>
</html>