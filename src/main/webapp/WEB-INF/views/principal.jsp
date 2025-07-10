<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Página Principal</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/principal.css">
</head>
<body>

<div class="container">

  <h2>Bem-vindo, <c:out value="${usuarioLogado.nome}"/>!</h2>

  <div class="botoes-principais">
    <a href="<c:url value="/aluno/listar"/>" class="botao botao-azul">Gerenciar Alunos</a>
    <a href="<c:url value="/curso/listar"/>" class="botao botao-azul">Gerenciar Cursos</a>
    <a href="<c:url value="/login/logout"/>" class="botao botao-vermelho">Sair</a>
  </div>
</div>

</body>
</html>