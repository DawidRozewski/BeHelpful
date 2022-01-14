<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="parts/header.jsp"%>
<head>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li><a href="/login">Zaloguj</a></li>
            <li class="highlighted"><a href="/register">Załóż konto</a></li>
        </ul>

        <ul>
            <li><a href="" class="btn btn--without-border active">Start</a></li>
            <li><a href="#" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="#" class="btn btn--without-border">O nas</a></li>
            <li><a href="#" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="/form" class="btn btn--without-border">Przekaż dary</a></li>
            <li><a href="#" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
</header>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form modelAttribute="appUser">
        <div class="form-group">
            <form:input type="text" path="firstName" placeholder="Imię" /><br/>
            <form:errors path="firstName" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:input type="text" path="lastName" placeholder="Nazwisko" /><br/>
            <form:errors path="lastName" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:input type="email" path="email" placeholder="Email" /><br/>
            <form:errors path="email" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:input type="password" path="password" placeholder="Hasło" /><br/>
            <form:errors path="password" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:input type="password" path="repassword" placeholder="Powtórz hasło" /><br/>
            <form:errors path="repassword" cssClass="error"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<%@include file="parts/footer.jsp"%>

