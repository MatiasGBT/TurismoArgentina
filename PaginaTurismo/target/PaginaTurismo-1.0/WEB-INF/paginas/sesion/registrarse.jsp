<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/paginas/sesion/etiquetasHeadSesion.jsp"/>
        <title>Registrarse | Turismo Argentina</title>
    </head>
    <body id="contenido__cuerpo">
        <h1>Registrarse</h1>
        <div>
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=registrarse" method="POST" id="formulario__sesion">
                <div>
                    <label for="usuario"><i class="fas fa-user"></i> Nombre de usuario:*</label>
                    <input type="text" name="usuario" required>
                    <label for="email"><i class="fas fa-envelope"></i> Email:*</label>
                    <input type="email" name="email" required>
                    <label for="password"><i class="fas fa-key"></i> Contraseña:*</label>
                    <input type="password" name="password" required>
                    <label for="confpassword"><i class="fas fa-key"></i> Confirmar contraseña:*</label>
                    <input type="password" name="confpassword" required>
                    <input type="submit" value="Registrarse">
                </div>
            </form>
        </div>
                
        <%--SCRIPTS--%>
        <script src="https://kit.fontawesome.com/94d22320fc.js" crossorigin="anonymous"></script>
    </body>
</html>
