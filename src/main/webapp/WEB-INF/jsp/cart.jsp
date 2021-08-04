<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html ng-app="myApp" ng-controller="myCartController">
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.js"></script>
    <link rel="stylesheet" href="resources/styles/cart.css">
    <title>Cart</title>
</head>
<body ng-cloak>
<script type="text/javascript">
    var app = angular.module("myApp", []);
</script>
<ul class="menu" style="text-align: right">
    <li><a href="catalog">Catalog</a></li>
    <li class="current"><a href="cart">Cart</a></li>
    <li><a href="account">My account</a></li>
    <li><a href="logout">Log Out</a></li>
</ul>
<div class="product-container">
    <p ng-show="showEmpty">У вас нет товаров в корзине</p>
    <table style="width: 100%" ng-show="showCart">
        <tr ng-repeat="item in orderInCart">
            <td width="100px">{{item.productId}}</td>
<%--            <td>{{item.quantity}}</td>--%>
            <td>
                <div class="number" data-step="1" data-min="1" data-max="100">
                    <input class="number-text" type="text" value="{{item.quantity ? item.quantity : 1}}">
                    <span class="number-unit">шт</span>
                    <div class="number-controls">
                        <div class="number-plus" ng-click="increaseNumber(item)">+</div>
                        <div class="number-minus" ng-click="decreaseNumber(item)">−</div>
                    </div>
                </div>
            </td>
            <td><button class="deleteButton" ng-click="deleteFromCart()">Удалить из корзины</button></td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    app.controller("myCartController", function ($scope, $http) {

        $scope.orderInCart = [];
        $scope.showCart = true;
        $scope.showEmpty = false;

        <%-- Получение товара в корзине--%>
        $scope.getOrderInCart = function () {
            $http.get("api/pharmacy/order/cart")
                .then(
                    function (response) {
                        $scope.orderInCart = response.data;
                        console.log($scope.orderInCart);
                        if ($scope.orderInCart.length == 0) {
                            $scope.showCart = false;
                            $scope.showEmpty = true;
                        }
                    },
                    function (errResp) {
                        console.error(errResp);
                    }
                );
        }


        $scope.increaseNumber = (item) => {
            item.quantity = item.quantity ? item.quantity + 1 : 2;
            console.log(item.quantity);
        }


        $scope.decreaseNumber = (item) => {
            item.quantity = item.quantity && item.quantity > 1 ? item.quantity - 1 : 1;
            console.log(item.quantity);
        }


        $scope.updateCart = () => {
            console.log('updating cart')
            //....$http.post()
        }

        angular.element(document).ready(function () {
            console.log('page loading completed');
            $scope.getOrderInCart();
        });
    });
</script>
</body>
</html>