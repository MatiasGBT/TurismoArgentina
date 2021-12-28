<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="WEB-INF/paginas/comunes/etiquetasHead.jsp"/>
    </head>
    <body>
        <main>
            <h1>¡Hola, bienvenido a Turismo Argentina!</h1>
            <p>Para continuar, por favor registrese o cree inicie sesión si ya tiene una cuenta.</p>
            <div class="flexbox">
                <a>
                    <button id="iniciar__sesion">Iniciar sesión</button>
                </a>
                <a>
                    <button id="registrarse">Registrarse</button>
                </a>
            </div>
        </main>

        <%--SCRIPTS--%>
        <jsp:include page="WEB-INF/paginas/comunes/scripts.jsp"/>
    </body>
</html>
