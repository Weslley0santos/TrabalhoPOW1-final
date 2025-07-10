<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Gerenciar Cursos</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alunos.css"> <%-- Mantido se as classes CSS forem compartilhadas --%>
</head>
<body>

<div class="container">
  <h2>Lista de Cursos</h2>

  <%-- Adicionado para exibir mensagens de sucesso/erro do Controller --%>
  <c:if test="${not empty mensagem}">
    <p class="mensagem" style="color: green;">${mensagem}</p>
  </c:if>
  <c:if test="${not empty erro}">
    <p class="erro" style="color: red;">${erro}</p>
  </c:if>

  <c:if test="${empty cursos}">
    <p>Nenhum curso cadastrado.</p>
  </c:if>

  <c:if test="${not empty cursos}">
    <table class="tabela-alunos"> <%-- Verifique se as classes CSS são apropriadas para cursos --%>
      <thead>
      <tr>
        <th>ID</th>
        <th>Curso</th>
        <th>Professor</th>
        <th>Ações</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="curso" items="${cursos}" >
        <tr>
          <td><c:out value="${curso.id}"/></td>
          <td><c:out value="${curso.nome}"/></td>
          <td><c:out value="${curso.professor}"/></td>
          <td>
            <a href="<c:url value="/curso/editar?id=${curso.id}"/>" class="botao botao-editar">Editar</a>
            <a href="<c:url value="/curso/excluir?id=${curso.id}"/>" class="botao botao-excluir" onclick="return confirm('Deseja realmente excluir este curso?');">Excluir</a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:if>

  <div class="botoes-acoes">
    <a href="<c:url value="/curso/novo"/>" class="botao botao-novo">+ Novo Curso</a>
    <a href="<c:url value="/principal"/>" class="botao botao-voltar">← Voltar</a>
  </div>
</div>

</body>
</html>