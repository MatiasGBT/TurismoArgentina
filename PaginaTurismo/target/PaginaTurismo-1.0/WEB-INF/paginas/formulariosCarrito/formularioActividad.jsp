<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/paginas/sesion/etiquetasHeadSesion.jsp"/>
        <title>Reservar actividad | Turismo Argentina</title>
    </head>
    <body id="cuerpo__formulario__actividad" class="cuerpo__formulario">
        <h1>Reservar actividad</h1>
        <div class="caja__comprar">
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=agregarActividadCarrito&idActividad=${actividad.idActividad}" method="POST" id="formulario__sesion" enctype="multipart/form-data">
                <div>
                    
                    <label for="cantidad"><i class="fas fa-users"></i> ¿Cuantos adultos irán?</label>
                    <input type="number" name="cantidad" required min="1" max="5">
                    
                    <p>Nota: una actividad solo puede ser reservada por un máximo de 5 adultos.</p>
                    
                    <input type="submit" value="Reservar">
                </div>
            </form>
        </div>
                
        <%--SCRIPTS--%>
        <script src="https://kit.fontawesome.com/94d22320fc.js" crossorigin="anonymous"></script>
    </body>
</html>