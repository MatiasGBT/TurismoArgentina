<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/paginas/sesion/etiquetasHeadSesion.jsp"/>
        <title>Panel de control | Turismo Argentina</title>
    </head>
    <body id="cuerpo__panel">
        <main>
            <%--MENU--%>
            <jsp:include page="/WEB-INF/paginas/sesion/menuSesion.jsp"/>

            <div id="contenido__panel">
                <%--SECCION INICIO--%>
                <jsp:include page="/WEB-INF/paginas/sesion/seccionInicio.jsp"/>
                
                <%--SECCION SITIOS--%>
                <jsp:include page="/WEB-INF/paginas/sesion/seccionSitios.jsp"/>
                
                <%--SECCION ACTIVIDADES--%>
                <jsp:include page="/WEB-INF/paginas/sesion/seccionActividades.jsp"/>
                
                <%--SECCION CONTACTO--%>
                <jsp:include page="/WEB-INF/paginas/sesion/seccionContacto.jsp"/>
            </div>
        </main>
            
        <footer></footer> <%--Este footer solo esta para que el menu del panel en modo mobile no tape contenido--%>

        <%--SCRIPTS--%>
        <script src="https://kit.fontawesome.com/94d22320fc.js" crossorigin="anonymous"></script>
    </body>
</html>
