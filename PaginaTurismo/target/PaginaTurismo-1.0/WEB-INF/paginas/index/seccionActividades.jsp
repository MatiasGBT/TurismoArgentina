<%--Librería JSTL de Core--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section id="seccion__actividades">
    <h1>Actividades</h1>
    <div class="flexbox">
        <c:forEach var="actividad" items="${actividades}">
            <div class="flexbox__hija">
                <img src="ServletImagen?accion=listarActividad&id=${actividad.idActividad}">
                <h3>${actividad.nombre}</h3>
                <p>ARS$${actividad.precio} por adulto.</p>
                <a href="${pageContext.request.contextPath}/ServletControlador?accion=formularioActividad&idActividad=${actividad.idActividad}" class="btn__actividades">
                    Reservar
                </a>
            </div>
        </c:forEach>
    </div>
    <a href="${pageContext.request.contextPath}/ServletControlador?accion=listar" id="btn__ver__mas" class="btn__actividades">
        Ver todas las actividades
    </a>
</section>
<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320" style="background: white;" id="contacto">
    <path fill="#3da9fc" fill-opacity="1" d="M0,64L48,80C96,96,192,128,288,138.7C384,149,480,139,576,128C672,117,768,107,864,106.7C960,107,1056,117,1152,117.3C1248,117,1344,107,1392,101.3L1440,96L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path>
</svg>