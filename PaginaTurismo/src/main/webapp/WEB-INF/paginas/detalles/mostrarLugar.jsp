<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/paginas/detalles/etiquetasHeadDetalles.jsp"/>
    </head>
    <body style="background-image: url(${lugar.portada})";>
        <jsp:include page="/WEB-INF/paginas/detalles/menuDetalles.jsp"/>
        <main>
            <%--PORTADA--%>
            <jsp:include page="/WEB-INF/paginas/detalles/seccionPortadaDetalles.jsp"/>
            <%--DESCRIPCION--%>
            <jsp:include page="/WEB-INF/paginas/detalles/seccionDescripcion.jsp"/>
            <%--GALERIA--%>
            <jsp:include page="/WEB-INF/paginas/detalles/seccionGaleria.jsp"/>
            <%--VIAJA--%>
            <jsp:include page="/WEB-INF/paginas/detalles/seccionViaja.jsp"/>
        </main>
        
        <%--SCRIPTS--%>
        <script src="https://cdn.jsdelivr.net/npm/@fancyapps/ui/dist/fancybox.umd.js"></script>
        <jsp:include page="/WEB-INF/paginas/comunes/scripts.jsp"/>
    </body>
</html>
