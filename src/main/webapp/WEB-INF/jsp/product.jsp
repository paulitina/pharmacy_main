<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalog</title>
    <style>
        .menu {
            list-style-type: none;
            margin: 0;
            padding: 0;
            background-color: #9292d1;
        }

        .menu a {
            text-decoration: none;
            font-family: sans-serif;
            color: #5757a0;
            display: inline-block;
            padding: 10px 20px;
            background-color: lavender;
            border-bottom: 5px solid #5757a0;
        }

        .menu li {
            display: inline;
        }

        * {
            box-sizing: border-box;
        }

        form {
            position: relative;
            width: 60%;
            margin: 2% auto;
            background: #ffffff;
            border-bottom: 4px solid #5757a0;
        }

        input {
            width: 100%;
            font-family: sans-serif;
            font-size: 15px;
            height: 42px;
            padding-left: 15px;
        }

        .search-button {
            position: absolute;
            top: 0;
            right: 0px;
            width: 42px;
            height: 42px;
            border: none;
            background: lavender;
            border-radius: 0 5px 5px 0;
            cursor: pointer;
        }

        .btn{
            text-decoration: none;
            font-family: sans-serif;
            color: #5757a0;
            display: inline-block;
            padding: 10px 20px;
            background-color: lavender;
            border-bottom: 5px solid #5757a0;
        }

        .search-button:before {
            content: "\f002";
            font-family: FontAwesome;
            font-size: 16px;
            color: #5757a0;
        }

        table{
            table-layout: fixed;
        }

        table tr td:last-child {
            /*padding-right: 100px;*/
        }
    </style>
</head>
<body>
<ul class="menu" style="text-align: right">
    <li><a href="#">Catalog</a></li>
    <li><a href="#">Cart</a></li>
    <li><a href="#">My account</a></li>
    <li><a href="#">Log Out</a></li>
</ul>

<form>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <input type="text" placeholder="Поиск по каталогу...">
    <button type="submit" class="search-button"></button>
</form>
<div class="product-container">
    <table style="width: 100%">
        <tbody id="tableData">
<%--        <td>--%>
<%--            <button type="button" class="btn btn-default">Добавить в корзину</button>--%>
<%--        </td>--%>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    var product = [
        {
            image: "https://static.detmir.st/media_out/039/491/3491039/1500/0.jpg?1610679614975",
            name: "Аскорбиновая кислота",
            price: "19 руб."
        },
        {
            image: "https://www.google.com/url?sa=i&url=https%3A%2F%2Fsamson-pharma.ru%2Fcatalog%2Fproduct%2Fnovigan-tab-p-p-o-400mg-20%2F&psig=AOvVaw2UngTDx6WS1Q3bVFduwBlG&ust=1627137585037000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCIiImoO2-fECFQAAAAAdAAAAABAD",
            name: "Новиган",
            price: "307 руб."
        },
        {
            image: "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.rigla.ru%2Fproduct%2F46300&psig=AOvVaw2vb4dRO0wEWQX9FXDdCeOa&ust=1627137704364000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCMCtkby2-fECFQAAAAAdAAAAABAD",
            name: "Бинт марлевый",
            price: "307 руб."
        }
    ]
    var table = '<tbody>';
    for (i = 0; i < product.length; i++) {
        table += '<tr>';
        table += '<td>' + product[i].image + '</td>';
        table += '<td>' + product[i].name + '</td>';
        table += '<td>' + product[i].price + '</td>';
        table += '<td><button type="button" class="btn btn-default">Добавить в корзину</button></td>'
        table += '</tr>';
    }
    table += '</tbody>';
    document.getElementById('tableData').innerHTML = table;
</script>
</body>
</html>