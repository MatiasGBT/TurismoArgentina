<%--Librería JSTL de Core--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--Librería para dar formato al precio--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_AR"/>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/paginas/comunes/etiquetasHead.jsp"/>
        <title>Carrito | Turismo Argentina</title>
    </head>
    <body>
        <%--MENU NAVEGACION--%>
        <jsp:include page="/WEB-INF/paginas/actividades/menuActividades.jsp"/>

        <main id="carrito__main">
            <section>
                <h2>Lugares/sitios turísticos en el carrito</h2>
                <div class="gridbox">
                    <c:forEach var="lugar" items="${lugaresCarrito}">
                        <div class="caja__articulo">
                            <img src="${pageContext.request.contextPath}/ServletImagen?accion=listarPortada&id=${lugar.idLugar}" alt="${lugar.nombre}">
                            <p>${lugar.nombre}</p>
                            <p>ARS<fmt:formatNumber value="${lugar.precio}" type="currency"/></p>
                            <a href="${pageContext.request.contextPath}/ServletControlador?accion=quitarLugarCarrito&idLugar=${lugar.idLugar}" class="btn__actividades">
                                <i class="fas fa-times"></i> Quitar del carrito
                            </a>
                        </div>
                    </c:forEach>
                    <br>
                </div>  
            </section>

            <section>
                <h2>Actividades turísticas en el carrito</h2>
                <div class="gridbox">
                    <c:forEach var="actividad" items="${actividadesCarrito}">
                        <div class="caja__articulo">
                            <img src="${pageContext.request.contextPath}/ServletImagen?accion=listarActividad&id=${actividad.idActividad}" alt="${actividad.nombre}">
                            <p>${actividad.nombre}</p>
                            <p>ARS<fmt:formatNumber value="${actividad.precio}" type="currency"/></p>
                            <a href="${pageContext.request.contextPath}/ServletControlador?accion=quitarActividadCarrito&idActividad=${actividad.idActividad}" class="btn__actividades">
                                <i class="fas fa-times"></i> Quitar del carrito
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </section>

            <section>
                <p>${mensaje}</p>
                <p>Precio total: ARS<fmt:formatNumber value="${total}" type="currency"/></p>
                <a href="${pageContext.request.contextPath}/ServletControlador?accion=descargarFactura" class="btn__comprar btn__carrito">Comprar</a>
                <a href="principal.jsp" class="btn__actividades btn__carrito">Volver al inicio</a>
            </section>

        </main>

        <%--SCRIPTS--%>
        <script src="https://kit.fontawesome.com/94d22320fc.js" crossorigin="anonymous"></script>
        <script src="script/myscript.js"></script>
    </body>
</html>
