<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Cadastro</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">

</head>
<body>
<div class="container">
  <h2>Cadastro de Usuário</h2>

  <c:if test="${not empty erro}">
    <div style="color:red">${erro}</div>
  </c:if>

  <form action="<c:url value="/cadastro"/>" method="post">
    <label for="nome">Nome:</label><br>
    <input type="text" id="nome" name="nome" required><br><br>

    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" required><br><br>

    <label for="senha">Senha:</label><br>
    <input type="password" id="senha" name="senha" required><br><br>

    <label for="confirmarSenha">Confirmar Senha:</label><br>
    <input type="password" id="confirmarSenha" name="confirmarSenha" required><br><br>

    <button type="submit">Cadastrar</button>
  </form>

  <p>Já tem conta? <a href="<c:url value="/login"/>">Fazer login</a></p>
</div>
</body>
</html>