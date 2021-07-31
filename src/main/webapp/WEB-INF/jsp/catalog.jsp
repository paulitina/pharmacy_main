<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html ng-app="myApp" ng-controller="myCatalogController">
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.js"></script>
    <link rel="stylesheet" href="resources/styles/product_catalog.css">
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

<form>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <input type="text" placeholder="Поиск по каталогу...">
    <button type="submit" class="search-button" ng-click = "searchProduct(product.name)"></button>
</form>
<div class="product-container">
    <table style="width: 100%">
        <tr ng-repeat="product in products">
            <td width="100px">{{product.image ? product.image : "Нет изображения"}}</td>
            <td ng-click = "getProductId(product.productId)">{{product.name}}</td>
            <td>{{product.price ? product.price : "Данные отсутствуют."}} руб.</td>
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
    app.controller("myCatalogController", function ($scope, $http) {

        $scope.products = [];

        <%-- Получение списка товаров--%>
        $scope.readProductList = function () {
            $scope.list = $http.get("api/pharmacy/product")
                .then(
                    function (response) {
                        $scope.products = response.data;
                        console.log($scope.products);
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


        $scope.addToCart = (productId, count) => {
            if (!count) {
                count = 1;
            }
            console.log('added to cart product ' + productId + ' at number of ' + count);
        }


        $scope.updateCart = () => {
            console.log('updating cart');
            console.log($scope.products);
            $http.put("api/pharmacy/order/cart", $scope.products);
        }

        $scope.openPage = function () {
            if (!$scope.productId) {
                return;
            }
            // $scope.popover.close();
            let link = $("#openLink")[0];
            link.href = "http://localhost:8080/product_page.jsp/productId?productId=" + $scope.productId + '\'';
            console.log(link.href);
            link.click();
        };

        $scope.productId = '';

        $scope.getProductId = (productId) => {
            $scope.productId = productId;
            console.log(productId);
            console.log($scope.productId);
            $scope.openPage();
            // $http.get("api/pharmacy/product/" + productId);
        }

        $scope.textInSearch = "aaa";

        $scope.readLine = (text) => {
            $scope.textInSearch = text;
        }

        $scope.searchProduct = (textInSearch) => {
            if ($scope.products.name === $scope.textInSearch){
                $scope.products = 'a';
                console.log($scope.textInSearch);
                console.log($scope.products);
            }
        }

        angular.element(document).ready(function () {
            console.log('page loading completed');
            $scope.readProductList();
        });
    });
</script>
</body>
</html>