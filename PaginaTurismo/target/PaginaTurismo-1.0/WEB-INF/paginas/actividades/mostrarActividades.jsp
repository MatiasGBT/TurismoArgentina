<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/paginas/actividades/etiquetasHeadActividades.jsp"/>
    </head>
    <body>
        <%--MENU NAVEGACION--%>
        <jsp:include page="/WEB-INF/paginas/actividades/menuActividades.jsp"/>
        <main>
            <%--CONTENIDO PRINCIPAL--%>
            <jsp:include page="/WEB-INF/paginas/actividades/mainActividades.jsp"/>
        </main>

        <%--FOOTER--%>
        <jsp:include page="/WEB-INF/paginas/comunes/footer.jsp"/>
        
        <%--SCRIPTS--%>
        <script src="https://cdn.jsdelivr.net/npm/@fancyapps/ui/dist/fancybox.umd.js"></script>
        <jsp:include page="/WEB-INF/paginas/comunes/scripts.jsp"/>
    </body>
</html>
