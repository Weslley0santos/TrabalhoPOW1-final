<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<div class="login-container">
  <h2>Login</h2>

  <c:if test="${not empty erro}">
    <div style="color:red;">${erro}</div>
  </c:if>

  <c:if test="${not empty mensagem}">
    <div style="color:green;">${mensagem}</div>
  </c:if>

  <form action="<c:url value="/login"/>" method="post">
    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" required><br><br>

    <label for="senha">Senha:</label><br>
    <input type="password" id="senha" name="senha" required><br><br>

    <button type="submit">Entrar</button>
  </form>

  <p>Não tem cadastro?
    <a href="<c:url value="/cadastro"/>">Cadastre-se aqui</a>
  </p>

</div>
</body>
</html>