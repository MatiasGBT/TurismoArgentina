<%--Librer�a para dar formato al saldo--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_AR"/>

<section id="sec3">
    <h1 id="viaja">�Reserva tu pasaje a ${lugar.nombre}!</h1>
    <div class="flexbox">
        <div class="caja">
            <%
                int numero = (int) (Math.random() * 4 + 1);
            %>
            <img src="img/personas/<%=numero%>.png" width="400">
        </div>
        <div class="caja">
            <h2>Compr� el pasaje</h2>
            <p>ARS<fmt:formatNumber value="${lugar.precio}" type="currency"/></p>
            <a>
                <button class="btn__comprar">Comprar</button>
            </a>
            <p style="color: lightgray";>*No incluye estad�a.</p>
        </div>
    </div>
</section>