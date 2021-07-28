<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%
    String loginError = null;
    if (session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") != null) {
        loginError = ((Exception) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage();
    }
%>
<!DOCTYPE html>
<html ng-app="myApp" ng-controller="myController">
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="resources/styles/login.css">
    <title><s:message text="Log In Page"/></title>
</head>
<body ng-cloak>
<script type="text/javascript">
    var app = angular.module("myApp", []);
</script>
<ul class="menu" style="text-align: right">
<%--    <li><a href="#">Catalog</a></li>--%>
    <li><a href="#">Log In</a></li>
</ul>

<div id="login-container">
    <table style="width:100%;">
        <tr>
            <td style="width: 100%"><h1><s:message text="Input credentials to log in"/></h1></td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="input-group tab-elm">
					<span class="input-group-addon">
						<s:message text="Input username"/>
					</span>
                    <input type="text" id="idUser" class="form-control" ng-model="userName"
                           ng-keypress="userChanged($event)">
                </div>
            </td>
        </tr>
        <tr ng-hide="showReset">
            <td colspan="2">
                <div class="input-group tab-elm">
					<span class="input-group-addon">
						<s:message text="Input password"/>
					</span>
                    <input type="password" id="idPassword" class="form-control" ng-model="password"
                           ng-keypress="passChanged($event)">
                </div>
            </td>
        </tr>
        <tr ng-show="loginError && !showReset">
            <td colspan="2">
                <div style="color:red; margin-top:10px; margin-bottom:10px">
                    <span ng-if="loginError == 'bad.credentials'">
                        <s:message text="Wrong credentials"/>
                    </span>
                    <span ng-if="loginError == 'user.is.blocked'">
                        <s:message text="User is blocked"/>
                    </span>
                    <span ng-if="loginError != 'bad.credentials' && loginError != 'user.is.blocked'">
                        {{loginError}}
                    </span>
                </div>
            </td>
        </tr>
        <tr ng-if="errorMessage">
            <td colspan="2">
                <div style="color:red; margin-top:10px; margin-bottom:10px">
                    {{errorMessage}}
                </div>
            </td>
        </tr>
        <tr ng-if="resetMessage">
            <td colspan="2">
                <div style="margin-top:10px; margin-bottom:10px">
                    {{resetMessage}}
                </div>
            </td>
        </tr>
        <tr ng-hide="showReset">
            <td colspan="2">
                <div class="input-group tab-elm">
                    <button type="button" class="btn btn-default" ng-click="enter()"
                            ng-disabled="userName == '' || password == ''">
                        <s:message text="Log In"/>
                    </button>
                </div>
            </td>
        </tr>
        <tr ng-hide="showReset">
            <td colspan="2">
                <div class="input-group tab-elm" style="text-align:right;">
                    <a style="color: #5757a0; cursor:pointer" ng-click="toReset()"><s:message
                            text="Forgot password"/></a>
                </div>
            </td>
        </tr>
        <tr ng-show="showReset">
            <td colspan="2">
                <div class="input-group tab-elm">
                    <button type="button" class="btn btn-default" ng-click="reset()"
                            ng-disabled="userName == ''"><s:message
                            text="Reset password"/></button>
                </div>
            </td>
        </tr>
        <tr ng-show="showReset">
            <td colspan="2">
                <div class="input-group tab-elm" style="text-align:right;">
                    <a style="color: #5757a0; cursor:pointer" ng-click="hideReset()"><s:message text="Cancel"/></a>
                </div>
            </td>
        </tr>
    </table>
</div>

<script type="text/javascript">
    // console.log("fgbf");
    app.controller("myController", function ($scope, $http) {
        $scope.userName = "";
        $scope.password = "";
        $scope.showReset = false;
        $scope.language = "${pageContext.response.locale.language}";
        $scope.baseLocation = window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/'));
        $scope.errorMessage = null;
        $scope.resetMessage = null;
        $scope.loginError = <%= loginError != null ? "\"" + loginError + "\"" : null %>;

        <%-- Вход пользователя --%>
        $scope.enter = function () {
            $scope.resetMessage = null;
            postForm({
                action: "login", fields: [
                    {name: "idUser", value: $scope.userName},
                    {name: "idPassword", value: $scope.password}
                ]
            });
        };

        /**
         * Функция выполняет отправку запроса на сервер в виде формы
         * @param {Object} data - данные формы
         */
        function postForm(data) {
            var id = (data.id) ? data.id : "tmpForm";
            var form = $("<form/>").attr("id", id).attr("action", data.action).attr("method", "post");
            if (data.target) {
                form.attr("target", data.target);
            }
            console.log(data.fields);
            if (data.fields) {
                data.fields.forEach(function (index, element) {
                    var input = $("<input/>");
                    input.attr("type", "hidden");
                    input.attr("name", element.name);
                    input.attr("value", element.value);
                    form.append(input);
                });
            }
            form.appendTo($(document.body));
            form.submit();
  <%-- ???--%>          $("#" + id).remove();
        }

        <%-- Призак того, что пользователь ввёл данные --%>
        $scope.userChanged = function (e) {
            if (e && e.key && e.key === "Enter" && $scope.userName.length > 0) {
                if ($scope.showReset === false) {
                    if ($scope.password.length > 0) {
                        $scope.enter();
                    } else {
                        document.getElementById("idPassword").focus();
                    }
                }
            }
        };

        <%-- Призак, что пользователь ввёл пароль --%>
        $scope.passChanged = function (e) {
            if (e && e.key && e.key === "Enter" && $scope.password.length > 0) {
                if ($scope.userName.length > 0) {
                    $scope.enter();
                } else {
                    document.getElementById("idUser").focus();
                }
            }
        };

        <%-- Переход в режим сброса пароля --%>
        $scope.toReset = function () {
            $scope.resetMessage = null;
            $scope.errorMessage = null;
            $scope.showReset = true;
        };

        <%-- Скрытие режима сброса пароля --%>
        $scope.hideReset = function () {
            $scope.resetMessage = null;
            $scope.errorMessage = null;
            $scope.showReset = false;
        };


        <%-- Сброс пароля --%>
        $scope.reset = function () {
            if ($scope.userName.length > 0) {
                $scope.resetMessage = null;
                $scope.errorMessage = null;
                $http.post("reset_password", {
                    userName: $scope.userName,
                }).then(function (response) {
                    $scope.resetMessage = '<s:message text="Password was successfully reset" />';
                    $scope.showReset = false;
                }, function (response) {
                    $scope.errorMessage = response.data.error;
                });
            }
        };

        <%-- Обработка загрузки страницы --%>
        angular.element(document).ready(function () {
            document.getElementById("idUser").focus();
        });
    });
</script>
</body>
</html>