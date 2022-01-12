<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/paginas/detalles/etiquetasHeadDetalles.jsp"/>
    </head>
    <body style="background-image: url(ServletImagen?accion=listarPortada&id=${lugar.idLugar})";>
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
        
        <%--FOOTER--%>
        <jsp:include page="/WEB-INF/paginas/comunes/footer.jsp"/>
        
        <%--SCRIPTS--%>
        <script src="https://kit.fontawesome.com/94d22320fc.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@fancyapps/ui/dist/fancybox.umd.js"></script>
        <script src="script/myscript.js"></script>
    </body>
</html>
