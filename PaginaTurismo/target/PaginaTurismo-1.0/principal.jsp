<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="WEB-INF/paginas/comunes/etiquetasHead.jsp"/>
    </head>
    <body>
        <%--MENU NAVEGACION--%>
        <jsp:include page="WEB-INF/paginas/comunes/menu.jsp"/>
        <main>
            <%--PORTADA--%>
            <jsp:include page="WEB-INF/paginas/index/seccionPortada.jsp"/>
            <%--CAROUSEL--%>
            <jsp:include page="WEB-INF/paginas/index/seccionCarousel.jsp"/>
            <%--ACTIVIDADES--%>
            <jsp:include page="WEB-INF/paginas/index/seccionActividades.jsp"/>
            <%--CONTACTO--%>
            <jsp:include page="WEB-INF/paginas/index/seccionContacto.jsp"/>
        </main>

        <%--FOOTER--%>
        <jsp:include page="WEB-INF/paginas/comunes/footer.jsp"/>
        
        <%--SCRIPTS--%>
        <jsp:include page="WEB-INF/paginas/comunes/scripts.jsp"/>
    </body>
</html>
