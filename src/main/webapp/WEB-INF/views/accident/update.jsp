<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form action="<c:url value='/save?id=${accident.id}'/>" method='POST'>
    <table>
        <tr>
            <td>Название:</td>
            <td><input type='text' name='name' value="${accident.name}"></td>
            <td><input type='text' name='type.id' value="${accidentType}"></td>
            <td><input type='text' name='rIds' value="${accidentRules}"></td>
            <td><input type='text' name='address' value="${accidentAdress}"></td>
            <td><input type='text' name='text' value="${accidentText}"></td>
        </tr>
        <tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</body>
</html>