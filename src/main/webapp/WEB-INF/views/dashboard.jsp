<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="parts/header.jsp"%>
<head>
    <title>Donations</title>
    <style>

         th, td {
             border-collapse: collapse;
             border: 1px groove;
             border-radius: 12px;
             padding: 5px;
              width: 50%;
        }
         td{
             padding: 10px;
             text-align: center;
         }

        th {
            background-color: lightblue;
        }
    </style>
</head>
<body>
<header class="header--main-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
        </ul>

        <ul>
            <li><a href="#" class="btn btn--without-border active">Start</a></li>
            <li><a href="#" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="#" class="btn btn--without-border">O nas</a></li>
            <li><a href="#" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="/form" class="btn btn--without-border">Przekaż dary</a></li>
            <li><a href="#" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>

    <div class="slogan container container--90">

        <div class="table">

                <table>
                    <tr>
                        <th>Data oddania</th>
                        <th>Nazwa insytutcji</th>
                        <th>Ilość oddanych worków</th>
                        <th>Test</th>
                        <th>Test</th>
                    </tr>

                    <c:forEach var="d" items="${donations}">
                        <tr>
                            <td>${d.pickUpDate}</td>
                            <td>${d.institution.name}</td>
                            <td>${d.quantity}</td>
                            <td>test</td>
                            <td>test</td>
                        </tr>
                    </c:forEach>
                </table>
        </div>
    </div>
</header>

AY
<%@include file="parts/footer.jsp"%>

