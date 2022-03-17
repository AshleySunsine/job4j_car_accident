<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="js/js.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<html>
<head>
    <title>Accident</title>
</head>
<body>
Hello : <c:out value='${user.username}'/>
<form name='login' action="<c:url value='/logout'/>" method='POST'>
    <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit" class="btn btn-primary">Выйти</button>
</form>
<div class="card-body">
    <a href="<c:url value='/create'/>">Добавить инцидент</a>
    <table class="table table-striped table-secondary" id="notDoneTable">
        <thead>
        <tr>
            <th scope="col">Пользователь</th>
        </tr>

        <c:forEach var="accident" items="${accidentList}">
            <tr><td>
                <c:out value="${accident}"/>
                <span>
             <a href="<c:url value='/update?id=${accident.id}'/>">Редактировать инцидент</a>
        </span>
            </td></tr>
        </c:forEach>

        </thead>
        <tbody>

        </tr>
        </tbody>
    </table>
</div>

</body>
</html>