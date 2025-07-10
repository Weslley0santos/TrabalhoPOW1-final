<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${curso.id != null ? 'Editar Curso' : 'Novo Curso'}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/formulario.css">
</head>
<body>

<div class="container">
    <div class="form-container">
        <h2>${curso.id != null ? 'Editar Curso' : 'Novo Curso'}</h2>

        <form action="<c:url value="${curso.id != null ? '/curso/atualizar' : '/curso/inserir'}"/>" method="post" accept-charset="UTF-8">
            <%-- Se o ID não for nulo, significa que é uma atualização, então enviamos o ID --%>
            <c:if test="${curso.id != null}">
                <input type="hidden" name="id" value="${curso.id}" />
            </c:if>

            <label for="nome">Nome do Curso:</label>
            <input type="text" id="nome" name="nome" value="${curso.nome}" required>

            <label for="professor">Professor Responsável:</label>
            <input type="text" id="professor" name="professor" value="${curso.professor}" required>

            <div class="botoes-formulario">
                <button type="submit" class="botao-salvar">${curso.id != null ? 'Atualizar' : 'Cadastrar'}</button>
                <a href="<c:url value="/curso/listar"/>" class="botao-voltar">← Voltar</a>
            </div>
        </form>
    </div>
</div>

</body>
</html>