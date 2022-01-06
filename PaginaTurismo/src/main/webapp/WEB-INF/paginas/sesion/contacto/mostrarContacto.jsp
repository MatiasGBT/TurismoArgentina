<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/paginas/sesion/etiquetasHeadSesion.jsp"/>
        <title>Mostrar comentario | Turismo Argentina</title>
    </head>
    <body id="cuerpo__formulario__actividad" class="cuerpo__formulario">
        <h1>Comentarios Turismo Argentina</h1>
        <br>
        <div class="caja__actividad">
            <p>Id del comentario: ${contacto.idContacto}</p>
            <br>
            <p>Nombre el emisor: ${contacto.nombre}</p>
            <br>
            <p>Comentario: ${contacto.comentario}</p>
        </div>
    </body>
</html>