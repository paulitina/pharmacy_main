<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html ng-app="myApp" ng-controller="myProductController">
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.js"></script>
    <link rel="stylesheet" href="http://localhost:8080/resources/styles/product_page.css">
    <link rel="stylesheet" href="http://localhost:8080/resources/styles/product_catalog.css">
    <link rel="canonical" href="https://snipp.ru/tools/base64-img-decode">
    <title>Product</title>
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

<%
    String productId = request.getParameter("productId");
%>

<div class="product-container" ng-model="product">
    <img class="image" width="100px">{{product.image ? product.image : "Нет изображения"}}</img>
    <div class="upperPart">
        <span class="prodName">{{product.name}}</span>
        <div class="prodPrice"><span>{{product.price ? product.price : "Данные отсутствуют."}} руб.</span></div>
        <div class="number" data-step="1" data-min="1" data-max="100">
            <input class="number-text" type="text" value="{{product.count ? product.count : 1}}">
            <span class="number-unit">шт</span>
            <div class="number-controls">
                <div class="number-plus" ng-click="increaseNumber(product)">+</div>
                <div class="number-minus" ng-click="decreaseNumber(product)">−</div>
            </div>
        </div>
        <div>
            <button type="button" class="btn btn-default" ng-click="addToCart(product.productId, product.count)">
                Добавить в корзину
            </button>
        </div>
    </div>
    <div class="manufacturer">
        <h2>Информация о производителе</h2>
        <span class="prodManufacturerInfo">{{product.manufacturerInfo}}</span>
    </div>
    <div class="indications">
        <h2>Показания к применению</h2>
        <span class="prodIndications">{{product.indications}}</span>
    </div>
    <div class="sideEffects">
        <h2>Побочные эффекты</h2>
        <span class="prodSideEffects">{{product.sideEffects}}</span>
    </div>
    <div class="prescibed">
        <h2>Отпускается по рецепту?</h2>
        <span class="prodPrescribed" ng-model="recipeRus">{{recipeRus}}</span>
    </div>
</div>
<script type="text/javascript">
    app.controller("myProductController", function ($scope, $http) {

        $scope.product = {
            productId: <%= productId %>,
        };

        $scope.recipeRus = 'jhbjhb';

        $scope.translatePrescribedIntoRussian = () => {
            if ($scope.product.prescribed == false) {
                $scope.recipeRus = 'Нет';
            } else {
                $scope.recipeRus = 'Да';
            }
        };

        <%-- Получение списка товаров--%>
        $scope.readProductInfo = function () {
            console.log($scope.product.productId);
            $scope.list = $http.get("api/pharmacy/product/{productId}?productId=" + $scope.product.productId, $scope.product.productId)
                .then(
                    function (response) {
                        $scope.product = response.data;
                        console.log($scope.product);
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
            console.log('updating cart')
            //....$http.post()
        }

        angular.element(document).ready(function () {
            console.log('page loading completed');
            $scope.readProductInfo();
            $scope.translatePrescribedIntoRussian();
        });

    });
</script>
</body>
</html>