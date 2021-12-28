<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="WEB-INF/paginas/comunes/etiquetasHead.jsp"/>
    </head>
    <body>
        <main>
            <div id="contenedor__index">
                <div class="centro">
                    <h1>¡Hola, bienvenido a Turismo Argentina!</h1>
                    <p>Para continuar, por favor cree una cuenta o inicie sesión si ya tiene una.</p>
                    <a href="${pageContext.request.contextPath}/ServletControlador?accion=sesion">
                        <button id="iniciar__sesion">Iniciar sesión</button>
                    </a>
                    <a href="${pageContext.request.contextPath}/ServletControlador?accion=registrarse">
                        <button id="registrarse">Registrarse</button>
                    </a>
                </div>
            </div>
        </main>

        <%--SCRIPTS--%>
        <jsp:include page="WEB-INF/paginas/comunes/scripts.jsp"/>
    </body>
</html>