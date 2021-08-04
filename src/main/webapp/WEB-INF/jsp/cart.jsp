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
            <td width="100px"><input id="{{$index}}" value="{{item.productId}}"></td>
            <td><p>Вы добавили {{item.quantity}} товара</p></td>
            <td>
                <div class="number" data-step="1" data-min="1" data-max="100">
                    <input class="number-text" type="text" value="{{item.count ? item.count : 1}}">
                    <span class="number-unit">шт</span>
                    <span ng-show="showMaxCount">Максимальное число товаров</span>
                    <div class="number-controls">
                        <div class="number-plus" ng-click="increaseNumber(item, $index)">+</div>
                        <div class="number-minus" ng-click="decreaseNumber(item, $index)">−</div>
                    </div>
                </div>
            </td>
            <td>
                <button class="btn" ng-click="deleteFromCart($index)">Удалить из корзины</button>
            </td>
        </tr>
    </table>
    <input class="address" id="address" placeholder="Введите адрес" ng-show="showAddressLine">
    <button class="btn" ng-click="placeOrder()" ng-show="showAddressLine">Оформить заказ</button>
</div>
<script type="text/javascript">
    app.controller("myCartController", function ($scope, $http) {

        $scope.showMaxCount = false;
        $scope.orderInCart = [];
        $scope.showCart = true;
        $scope.showEmpty = false;
        $scope.showAddressLine = true;
        $scope.productIdForDelete = '';

        <%-- Получение товара в корзине--%>
        $scope.getOrderInCartProductList = function () {
            $http.get("api/pharmacy/order/cartProducts")
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

        <%-- Получение заказа в корзине для orderId--%>
        $scope.getOrderInCart = () => {
            $http.get("api/pharmacy/order/cart")
                .then(
                    function (response) {
                        $scope.orderInCart4Id = response.data;
                        console.log($scope.orderInCart4Id);
                    },
                    function (errResp) {
                        console.error(errResp);
                    }
                );
        }

        $scope.deleteFromCart = ($index) => {
            console.log($index);
            $scope.productIdForDelete = document.getElementById($index).value;
            console.log($scope.productIdForDelete);
            $http.post("api/pharmacy/order/cart", $scope.productIdForDelete);
            $scope.getOrderInCartProductList();
            window.location.reload();
        }
        //
        // $scope.place = () => {
        //     // $scope.productIdForUpdate = document.getElementById($index).value;
        //     // if (!count) {
        //     //     count = 1;
        //     // }
        //     // $scope.productsToBuy = {
        //     //     orderId: $scope.orderInCart4Id.orderId,
        //     //     productId: $scope.productIdForUpdate,
        //     //     quantity: count
        //     // }
        //     // $http.put("api/pharmacy/order/cart", $scope.productsToBuy);
        //     // console.log('added to cart product ' + productId + ' at number of ' + count);
        //     // console.log($scope.productsToBuy);
        //     $scope.placeOrder();
        // }

        $scope.placeOrder = () => {
            $scope.address = document.getElementById('address').value;
            console.log($scope.address);
            $http.post("api/pharmacy/order", $scope.address);
            $scope.getOrderInCartProductList();
        }

        $scope.increaseNumber = (product, $index) => {
            if (product.count == product.quantity) {
                product.count = product.quantity;
                $scope.showMaxCount = true;
            } else {
                product.count = product.count ? product.count + 1 : 2;
            }
            console.log(product.count);

            $scope.productIdForUpdate = document.getElementById($index).value;
            // if (!product.count) {
            //     product.count = 1;
            // }
            $scope.productsToBuy = {
                orderId: $scope.orderInCart4Id.orderId,
                productId: $scope.productIdForUpdate,
                quantity: product.count
            }
            $http.put("api/pharmacy/order/cart", $scope.productsToBuy);
            console.log('added to cart product ' + $scope.productsToBuy.productId + ' at number of ' + $scope.productsToBuy.quantity);
        }

        $scope.decreaseNumber = (product, $index) => {
            product.count = product.count && product.count > 1 ? product.count - 1 : 1;
            $scope.showMaxCount = false;
            console.log(product.count);

            $scope.productIdForUpdate = document.getElementById($index).value;
            // if (!product.count) {
            //     product.count = 1;
            // }
            $scope.productsToBuy = {
                orderId: $scope.orderInCart4Id.orderId,
                productId: $scope.productIdForUpdate,
                quantity: product.count
            }
            $http.put("api/pharmacy/order/cart", $scope.productsToBuy);
            console.log('added to cart product ' + $scope.productsToBuy.productId + ' at number of ' + $scope.productsToBuy.quantity);
        }


        $scope.updateCart = () => {
            console.log('updating cart')
            //....$http.post()
        }

        angular.element(document).ready(function () {
            console.log('page loading completed');
            $scope.getOrderInCartProductList();
            $scope.getOrderInCart();
        });
    });
</script>
</body>
</html>