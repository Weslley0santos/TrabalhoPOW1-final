<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>${aluno.id != null ? 'Editar Aluno' : 'Novo Aluno'}</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/formulario.css">
</head>
<body>
<div class="container"><div class="form-container">
  <h2>${aluno.id != null ? 'Editar Aluno' : 'Novo Aluno'}</h2>

  <form action="<c:url value="${aluno.id != null ? '/aluno/atualizar' : '/aluno/inserir'}"/>" method="post" accept-charset="UTF-8">
    <c:if test="${aluno.id != null}">
      <input type="hidden" name="id" value="${aluno.id}" />
    </c:if>

    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" value="${aluno.nome}" required>

    <label for="matricula">Matrícula:</label>
    <input type="text" id="matricula" name="matricula" value="${aluno.matricula}" required>

    <label for="cursoId">Curso:</label>
    <select id="cursoId" name="cursoId" required>
      <option value="">-- Selecione um curso --</option>
      <c:forEach var="curso" items="${cursos}">
        <option value="${curso.id}"
                <c:if test="${aluno.curso.id == curso.id}">selected</c:if>
        >${curso.nome}</option>
      </c:forEach>
    </select>

    <div class="botoes-formulario">
      <button type="submit" class="botao-salvar">${aluno.id != null ? 'Atualizar' : 'Cadastrar'}</button>
      <a href="<c:url value="/aluno/listar"/>" class="botao-voltar">← Voltar</a>
    </div>
  </form>
</div></div>
</body>
</html>