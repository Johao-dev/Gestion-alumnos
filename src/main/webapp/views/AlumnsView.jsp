<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%-- Bootstrap 5 --%>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>

        <%-- Font Awesome --%>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" 
              integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" 
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>CRUD Alumnos</title>
    </head>
    <body>

        <style>
            body {
                margin: 0;
                padding: 0;
                background-color: #dedede;
            }
        </style>


        <header class="container text-white p-2">
            <div class="row" style="background-color: #32405c">
                <div class="col-9">
                    <h1>Administar alumnos</h1>
                </div>
                <div class="col-3 d-flex justify-content-end">
                    <button type="button" class="btn btn-success">
                        <a href="${pageContext.request.contextPath}/AlumnsServlet/new"
                           style="text-decoration: none; color: #fff"><i class="fa-solid fa-circle-plus"></i> Agregar alumno</a>
                    </button>
                </div>
            </div>
        </header>

        <br>

        <nav class="container">
            <div class="row d-flex justify-content-end">
                <div class="col-3">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="codigo" aria-label="codigo" aria-describedby="basic-addon1" id="buscarPorCodigo" name="buscarPorCodigo">
                        <span class="input-group-text bg-info text-white" id="basic-addon1"><i class="fa-solid fa-magnifying-glass"></i></span>
                    </div>
                </div>
            </div>
        </nav>

        <br>

        <table class="container table table-bordered">
            <thead>
                <tr>
                    <th class="col-1">Codigo</th>
                    <th class="col-3">Nombre</th>
                    <th class="col-1">Sexo</th>
                    <th class="col-1">Edad</th>
                    <th class="col-1">Acciones</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="alumn" items="${alumns}">
                <tr>
                    <td class="col-1"><c:out value="${alumn.code}" /></td>
                    <td class="col-3"><c:out value="${alumn.name}" /></td>
                    <td class="col-1"><c:out value="${alumn.gener}" /></td>
                    <td class="col-1"><c:out value="${alumn.age}" /></td>
                    <td class="col-1">
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/AlumnsServlet/edit?alumnId=${alumn.alumnId}">Editar</a>
                        <a class="btn btn-danger delete-link" href="${pageContext.request.contextPath}/AlumnsServlet/delete?alumnId=${alumn.alumnId}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <script>
            const deleteButtons = document.querySelectorAll(".delete-link");
            deleteButtons.forEach(function(button) {
                button.addEventListener('click', function(event) {
                    event.preventDefault();
                    if(confirm('¿Esta seguro de querer eliminar el registro?'))
                        window.location.href = this.href;
                });
            });
        </script>
    </body>
</html>
