<%--Librería JSTL de Core--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section id="sec2">
    <h1>Actividades</h1>
    <div class="flexbox">
        <c:forEach var="actividad" items="${actividades}">
        <div>
            <img src="${actividad.imagen}">
            <h3>${actividad.nombre}</h3>
            <p>ARS$${actividad.precio} por adulto.</p>
        </div>
        </c:forEach>
    </div>
    <a>
        <button class="btn__actividades">Ver todas las actividades</button>
    </a>
</section>