<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/paginas/sesion/etiquetasHeadSesion.jsp"/>
        <title>Inicio de sesi?n | Turismo Argentina</title>
    </head>
    <body id="contenido__cuerpo">
        <h1>Inicio de sesi?n</h1>
        <div>
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=verificar" method="POST" id="formulario__sesion">
                <div>
                    <label for="usuario"><i class="fas fa-user"></i> Nombre de usuario:*</label>
                    <input type="text" name="usuario" required>
                    <label for="password"><i class="fas fa-key"></i> Contrase?a:*</label>
                    <input type="password" name="password" required>
                    <input type="submit" value="Iniciar sesi?n">
                    <p>${mensaje}</p>
                </div>
            </form>
        </div>
                
        <%--SCRIPTS--%>
        <script src="https://kit.fontawesome.com/94d22320fc.js" crossorigin="anonymous"></script>
    </body>
</html>
