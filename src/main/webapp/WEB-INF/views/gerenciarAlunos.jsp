<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Gerenciar Alunos</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alunos.css">
</head>
<body>

<div class="container">
  <h2>Lista de Alunos</h2>

  <c:if test="${not empty mensagem}">
    <p class="mensagem" style="color: green;">${mensagem}</p>
  </c:if>
  <c:if test="${not empty erro}">
    <p class="erro" style="color: red;">${erro}</p>
  </c:if>

  <c:if test="${empty alunos}">
    <p>Nenhum aluno cadastrado.</p>
  </c:if>

  <c:if test="${not empty alunos}">
    <table class="tabela-alunos">
      <thead>
      <tr>
        <th>ID</th> <%-- Adicionei o ID, útil para visualização --%>
        <th>Nome</th>
        <th>Matrícula</th>
        <th>Curso</th>
        <th>Ações</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="aluno" items="${alunos}">
        <tr>
          <td><c:out value="${aluno.id}"/></td> <%-- Exibindo o ID --%>
          <td><c:out value="${aluno.nome}"/></td>
          <td><c:out value="${aluno.matricula}"/></td>
          <td><c:out value="${aluno.curso.nome}"/></td>
          <td>
            <a href="<c:url value="/aluno/editar?id=${aluno.id}"/>" class="botao botao-editar">Editar</a>
            <a href="<c:url value="/aluno/excluir?id=${aluno.id}"/>" class="botao botao-excluir" onclick="return confirm('Deseja excluir este aluno?');">Excluir</a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:if>

  <div class="botoes-acoes">
    <a href="<c:url value="/aluno/novo"/>" class="botao botao-novo">+ Novo Aluno</a>
    <a href="<c:url value="/principal"/>" class="botao botao-voltar">← Voltar para a Página Principal</a>
  </div>
</div>

</body>
</html>