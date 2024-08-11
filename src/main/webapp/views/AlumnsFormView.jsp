<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <%-- Bootstrap 5 --%>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>
        <title>CRUD Alumnos</title>
    </head>
    <body>

        <header class="container text-white p-2">
            <div class="row" style="background-color: #32405c">
                <div class="col-9">
                    <h1>Administar alumnos</h1>
                </div>
                <div class="col-3 d-flex justify-content-end">
                    <button type="button" class="btn btn-success">
                        <a href="${pageContext.request.contextPath}/AlumnsServlet"
                           style="text-decoration: none; color: #fff"><i class="fa-solid fa-circle-plus"></i> Regresar</a>
                    </button>
                </div>
            </div>
        </header>
                           
        <br>
        
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${alumn != null}">
                    <form action="update" method="post">
                    </c:if>
                    <c:if test="${alumn == null}">
                    <form action="insert" method="post">
                    </c:if>

                        <caption>
                            <h2>
                            <c:if test="${alumn != null}">
                                Editar Alumno
                            </c:if>
                            <c:if test="${alumn == null}">
                                Agregar Alumno
                            </c:if>
                            </h2>
                        </caption>

                        <c:if test="${alumn != null}">
                        <input type="hidden" name="alumnId" value="${alumn.alumnId}">
                        </c:if>

                        <fieldset class="form-group">
                            <label>Código</label>
                            <input type="text" value="${alumn.code}" class="form-control" name="code" required>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Nombre</label>
                            <input type="text" value="${alumn.name}" class="form-control" name="name" required>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Sexo</label>
                            <input type="text" value="${alumn.gener}" class="form-control" name="gener" required>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Edad</label>
                            <input type="number" value="${alumn.age}" class="form-control" name="age" required>
                        </fieldset>

                        <button type="submit" class="btn btn-success">Guardar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>