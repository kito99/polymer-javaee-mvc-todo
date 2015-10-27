<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--
@license
Copyright (c) 2015 The Polymer Project Authors. All rights reserved.
Modifications (c) 2015 Virtua, Inc. All rights reserved.

This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
-->

<fmt:setBundle basename="ValidationMessages" var="msgs"/>

<html>

<head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="generator" content="Polymer Starter Kit"/>
    <title>${msgs.resourceBundle['ApplicationName']} -- ${msgs.resourceBundle['LoginTitle']}</title>
    <!-- Place favicon.ico in the `app/` directory -->

    <!-- Chrome for Android theme color -->
    <meta name="theme-color" content="#2E3AA1">

    <!-- Web Application Manifest -->
    <link rel="manifest" href="manifest.json">

    <!-- Tile color for Win8 -->
    <meta name="msapplication-TileColor" content="#3372DF">

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="application-name" content="${msgs.resourceBundle['ApplicationName']}">
    <link rel="icon" sizes="192x192" href="${mvc.contextPath}/images/touch/chrome-touch-icon-192x192.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Polymer Starter Kit">
    <link rel="apple-touch-icon" href="${mvc.contextPath}/images/touch/apple-touch-icon.png">

    <!-- Tile icon for Win8 (144x144) -->
    <meta name="msapplication-TileImage"
          content="${mvc.contextPath}/images/touch/ms-touch-icon-144x144-precomposed.png">

    <!-- build:css styles/main.css -->
    <link rel="stylesheet" href="${mvc.contextPath}/styles/main.css">
    <!-- endbuild-->

    <!-- build:js bower_components/webcomponentsjs/webcomponents-lite.min.js -->
    <script src="${mvc.contextPath}/bower_components/webcomponentsjs/webcomponents-lite.js"></script>
    <!-- endbuild -->

    <!-- will be replaced with elements/elements.vulcanized.html -->
    <link rel="import" href="${mvc.contextPath}/elements/elements.html"/>
    <!-- endreplace-->

</head>

<body unresolved class="fullbleed layout vertical">
<template is="dom-bind" id="login">

    <paper-material>
        <paper-header-panel class="flex">
            <paper-toolbar>
                <div>${msgs.resourceBundle['ApplicationName']}</div>
            </paper-toolbar>

            <div class="horizontal center-justified layout login-subtitle">${msgs.resourceBundle['LoginSubTitle']}</div>
            <div class="horizontal center-justified layout error">${loginError ? msgs.resourceBundle['LoginErrorMessage'] : ''}</div>

            <!-- TODO: We should be using Polymer's error messages instead. -->
            <c:if test="${not empty messages.errors}">
                <div class="vertical center layout error" role="alert">
                    <c:forEach var="error" items="${messages.errors}">
                        <div>${mvc.encoders.html(error)}</div>
                    </c:forEach>
                </div>
            </c:if>

            <!-- Use a standard form instead of <iron-form> because it uses Ajax and doesn't work with redirects -->
            <form id="loginForm" method="post" action="${mvc.contextPath}${mvc.applicationPath}/login"
                  disable-native-valid>
                <div class="vertical center layout login-panel">
                    <paper-input id="paperUserId" class="flex" error-message="${msgs.resourceBundle['InvalidUserId']}"
                                 pattern="^([1-zA-Z0-1@.\s]{2,10})$"
                                 label="User ID:" required></paper-input>
                    <paper-input id="paperPassword" type="password" class="flex"
                                 error-message="${msgs.resourceBundle['InvalidPassword']}"
                                 label="Password:"
                                 pattern="^(?=[^_].*?\d)\w(\w|[!@#$%]){4,20}"
                                 required></paper-input>
                </div>
                <div class="horizontal center-justified layout">
                    <paper-button class="login-button" onclick="submitLoginForm(event)">
                        Login
                        <iron-icon icon="arrow-forward"></iron-icon>
                    </paper-button>
                </div>

                <!-- These fields are required because Paper elements aren't sent with a normal form. -->
                <input name="userId" type="hidden"/>
                <input name="password" type="hidden"/>

                <!-- CSRF protection is enabled in "TodoApplication", so we need to submit the token like this -->
                <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}"/>
            </form>

        </paper-header-panel>
    </paper-material>
</template>

<script>
    function submitLoginForm(event) {
        // Since we're using a standard <form> element, we must validate manually.
        var paperUserId = document.getElementById("paperUserId");
        var paperPassword = document.getElementById("paperPassword");
        var validated = paperUserId.validate();
        if (paperPassword.validate() && validated) {
            // Retrieve the values from the Paper elements, since they won't be submitted in a normal form.
            document.getElementsByName("userId")[0].value = paperUserId.value;
            document.getElementsByName("password")[0].value = paperPassword.value;
            document.getElementById('loginForm').submit();
        }
    }
</script>

</body>

</html>
