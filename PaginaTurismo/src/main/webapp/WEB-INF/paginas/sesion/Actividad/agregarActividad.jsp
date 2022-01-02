<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/paginas/sesion/etiquetasHeadSesion.jsp"/>
        <title>Agregar actividad | Turismo Argentina</title>
    </head>
    <body id="cuerpo__formulario__actividad" class="cuerpo__formulario">
        <h1>Agregar actividad</h1>
        <div>
            <form action="${pageContext.request.contextPath}/ServletPanel?accion=insertarActividad" method="POST" id="formulario__sesion" enctype="multipart/form-data">
                <div>
                    <label for="nombre"><i class="fas fa-map-marked-alt"></i> Nombre:</label>
                    <input type="text" name="nombre" required value="${actividad.nombre}">
                    
                    <label for="portada"><i class="fas fa-image"></i> Imagen:</label>
                    <input type="file" name="imagen" required accept="image/jpeg">
                    
                    <label for="precio"><i class="fas fa-dollar-sign"></i> Precio:</label>
                    <input type="number" name="precio" required value="${actividad.precio}">
                    
                    <p class="nota__formulario">Nota: Las imagenes deben tener unas dimensiones de 550x310px.</p>
                    
                    <input type="submit" value="Agregar actividad">
                </div>
            </form>
        </div>
                
        <%--SCRIPTS--%>
        <script src="https://kit.fontawesome.com/94d22320fc.js" crossorigin="anonymous"></script>
    </body>
</html>
