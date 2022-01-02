<%--Librería JSTL de Core--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Actividades</h1>
<div class="gridbox">
    <c:forEach var="actividad" items="${actividades}">
        <div>
            <a href="ServletImagen?accion=listarActividad&id=${actividad.idActividad}" data-fancybox="galeria">
                <img src="ServletImagen?accion=listarActividad&id=${actividad.idActividad}">
            </a>
            <h3>${actividad.nombre}</h3>
            <p>ARS$${actividad.precio} por adulto.</p>
            <a>
                <button class="btn__actividades">Reservar</button>
            </a>
        </div>
    </c:forEach>
</div>