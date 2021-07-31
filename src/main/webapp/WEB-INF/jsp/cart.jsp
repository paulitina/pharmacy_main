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
    <li><a href="product">Catalog</a></li>
    <li class="current"><a href="cart">Cart</a></li>
    <li><a href="account">My account</a></li>
    <li><a href="#">Log Out</a></li>
</ul>
<div class="product-container">
    <table style="width: 100%">
        <tr ng-repeat="item in orderInCart">
            <td width="100px">{{item.address}}</td>
<%--            <td>{{item.name}}</td>--%>
            <td></td>
            <td>
                <div class="number" data-step="1" data-min="1" data-max="100">
                    <input class="number-text" type="text" value="{{product.count ? product.count : 1}}">
                    <span class="number-unit">шт</span>
                    <div class="number-controls">
                        <div class="number-plus" ng-click="increaseNumber(product)">+</div>
                        <div class="number-minus" ng-click="decreaseNumber(product)">−</div>
                    </div>
                </div>
            </td>
            <td>
                <button type="button" class="btn btn-default" ng-click="addToCart(product.productId, product.count)">
                    Добавить в корзину
                </button>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    app.controller("myCartController", function ($scope, $http) {

        $scope.orderInCart = [];

            <%-- Получение товара в корзине--%>
        $scope.readOrderInCart = function () {
            $http.get("api/pharmacy/order/cart")
                .then(
                    function (response) {
                        $scope.orderInCart = response.data;
                        console.log($scope.orderInCart);
                    },
                    function (errResp) {
                        console.error(errResp);
                    }
                );
        }


        $scope.increaseNumber = (product) => {
            product.count = product.count ? product.count + 1 : 2;
            console.log(product.count);
        }


        $scope.decreaseNumber = (product) => {
            product.count = product.count && product.count > 1 ? product.count - 1 : 1;
            console.log(product.count);
        }


        $scope.updateCart = () => {
            console.log('updating cart')
            //....$http.post()
        }

        angular.element(document).ready(function () {
            console.log('page loading completed');
            $scope.readOrderInCart();
        });
    });
</script>
</body>
</html>