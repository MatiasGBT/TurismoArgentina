<%--Librería JSTL de Core--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="caja">
    <div class="carousel">
        <div class="carousel__contenedor">
            <%--Aria-label es importante para que en dispositivos de lectura con formato diferente no va a
            aparecer de forma visual, pero si va a aparecer el texto--%>
            <button aria-label="Anterior" class="carousel__anterior">
                <i class="fas fa-chevron-left"></i>
            </button>

            <%--Glider, como esta hecho con gliderjs solo hay que agregar un div y dentro elementos del glider--%>
            <div class="carousel__lista">
                <c:forEach var="lugar" items="${lugares}">
                    <div class="carousel__elemento">
                        <img src="${pageContext.request.contextPath}/ServletImagen?accion=listarPortada&id=${lugar.idLugar}" alt="${lugar.nombre}">
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=mostrar&idLugar=${lugar.idLugar}" title="Ver detalles" class="btn__carousel">
                            ${lugar.nombre}
                        </a>
                    </div>
                </c:forEach>
            </div>

            <button aria-label="Siguiente" class="carousel__siguiente">
                <i class="fas fa-chevron-right"></i>
            </button>
        </div>

        <%--DIV para indicadores, role tablist es solo para indicar que va a ser una lista de elementos de tabulación--%>
        <div role="tablist" class="carousel__indicadores"></div>
    </div>
</div>