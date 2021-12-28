<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/paginas/sesion/etiquetasHeadSesion.jsp"/>
        <title>Editar lugar | Turismo Argentina</title>
    </head>
    <body id="cuerpo__editar">
        <h1>Editar lugar</h1>
        <div>
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=editarLugar" method="POST" id="formulario__sesion">
                <div>
                    <label for="nombre"><i class="fas fa-map-marked-alt"></i> Nombre:</label>
                    <input type="text" name="nombre" required value="${lugar.nombre}">
                    <label for="descripcion"><i class="fas fa-align-justify"></i> Descripción:</label>
                    <br>
                    <textarea name="descripcion" cols="30" rows="10" maxlength="600" placeholder="Máximo 600 caracteres." required></textarea>
                    <br>
                    <label for="portada"><i class="fas fa-image"></i> Imagen de portada:</label>
                    <input type="file" name="portada" required accept="image/jpeg">
                    <label for="img1"><i class="fas fa-image"></i> Imagen n1:</label>
                    <input type="file" name="img1" required accept="image/jpeg">
                    <label for="img2"><i class="fas fa-image"></i> Imagen n2:</label>
                    <input type="file" name="img2" required accept="image/jpeg">
                    <label for="img3"><i class="fas fa-image"></i> Imagen n3:</label>
                    <input type="file" name="img3" required accept="image/jpeg">
                    <input type="submit" value="Editar lugar">
                </div>
            </form>
        </div>
                
        <%--SCRIPTS--%>
        <script src="https://kit.fontawesome.com/94d22320fc.js" crossorigin="anonymous"></script>
    </body>
</html>
