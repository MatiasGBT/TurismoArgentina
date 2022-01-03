<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/paginas/sesion/etiquetasHeadSesion.jsp"/>
        <title>Mostrar actividad | Turismo Argentina</title>
    </head>
    <body id="cuerpo__formulario__actividad" class="cuerpo__formulario">
        <h1>${actividad.nombre}</h1>
        <br>
        <div class="caja__actividad">
            <p>Id de la actividad: ${actividad.idActividad}</p>
            <br>
            <p>Nombre de la actividad: ${actividad.nombre}</p>
            <br>
            <p>Imagen:</p>
            <img src="ServletImagen?accion=listarActividad&id=${actividad.idActividad}">
            <br>
            <p>Precio: ${actividad.precio}</p>
        </div>
                
        <%--SCRIPTS--%>
        <script src="https://kit.fontawesome.com/94d22320fc.js" crossorigin="anonymous"></script>
    </body>
</html>