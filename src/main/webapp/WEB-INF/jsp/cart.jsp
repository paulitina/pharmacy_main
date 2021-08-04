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
<p ng-show="showEmpty">У вас нет товаров в корзине</p>
<div class="product-container">
    <table style="width: 100%" ng-show="showCart">
        <tr ng-repeat="item in orderInCart">
            <td width="100px">{{item.productId}}</td>
            <td>
                <div class="number" data-step="1" data-min="1" data-max="100">
                    <input class="number-text" type="text" value="{{item.count ? item.count : 1}}">
                    <span class="number-unit">шт</span>
                    <div class="number-controls">
                        <div class="number-plus" ng-click="increaseNumber(item)">+</div>
                        <div class="number-minus" ng-click="decreaseNumber(item)">−</div>
                    </div>
                </div>
            </td>
            <td><button class="btn" ng-click="deleteFromCart()">Удалить из корзины</button></td>
        </tr>
    </table>
    <input class="address" id= "address" placeholder="Введите адрес" ng-show="showAddressLine">
    <button class="btn" ng-click="placeOrder()" ng-show="showAddressLine">Оформить заказ</button>
<%--    addToCartAndPlace(item.productId, item.count)--%>
</div>
<script type="text/javascript">
    app.controller("myCartController", function ($scope, $http) {

        $scope.orderInCart = [];
        $scope.showCart = true;
        $scope.showEmpty = false;
        $scope.showAddressLine = true;

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
                            $scope.showAddressLine = false;
                        }
                    },
                    function (errResp) {
                        console.error(errResp);
                    }
                );
        }

        // $scope.addToCartAndPlace = (productId, count) => {
        //     if (!count) {
        //         count = 1;
        //     }
        //     $scope.orderInCart = [];
        //
        //     $scope.list = $http.get('api/pharmacy/order/cart')
        //         .then(
        //             function (response) {
        //                 $scope.orderInCart = response.data;
        //                 console.log("dfcedsc" + $scope.orderInCart);
        //             },
        //             function (errResp) {
        //                 console.error(errResp);
        //             }
        //         );
        //     console.log('added to cart product ' + productId + ' at number of ' + count);
        //     console.log("dfcedsc" + $scope.list);
        //     $scope.productsToBuy = {
        //         productId: productId,
        //         orderId: $scope.list.orderId,
        //         quantity: count
        //     }
        //     console.log($scope.productsToBuy);
        //     $scope.updateCart();
        //     $scope.placeOrder();
        // }
        //
        //
        // $scope.updateCart = () => {
        //     console.log('updating cart');
        //     $http.put("api/pharmacy/order/cart", $scope.productsToBuy);
        // }

        $scope.placeOrder = () => {
            $scope.address = document.getElementById('address').value;
            console.log($scope.address);
            $http.post("api/pharmacy/order", $scope.address);
            $scope.getOrderInCart();
        }

        $scope.increaseNumber = (item) => {
            item.count = item.count ? item.count + 1 : 2;
            console.log(item.count);
        }


        $scope.decreaseNumber = (item) => {
            item.count = item.count && item.count > 1 ? item.count - 1 : 1;
            console.log(item.count);
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