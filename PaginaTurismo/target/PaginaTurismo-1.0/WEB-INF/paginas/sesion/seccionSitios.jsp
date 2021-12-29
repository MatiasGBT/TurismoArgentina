<%--Librer�a JSTL de Core--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section id="sitios">
    <h1>Sitios tur�sticos</h1>
    <p>Agregar, editar o eliminar sitios tur�sticos de la p�gina principal.</p>

    <table>
        <thead>
        <th>#</th>
        <th>Nombre</th>
        <th></th>
        </thead>
        <tbody>
            <c:forEach var="lugar" items="${lugares}">
                <tr>
                    <td>${lugar.idLugar}</td>
                    <td>${lugar.nombre}</td>
                    <td class="flex">
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=mostrar&idLugar=${lugar.idLugar}"
                           class="btn btn__ver">
                            <i class="fas fa-eye"></i>
                        </a>
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=editarLugar&idLugar=${lugar.idLugar}"
                           class="btn btn__editar">
                            <i class="fas fa-edit"></i>
                        </a>
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=eliminarLugar&idLugar=${lugar.idLugar}"
                           class="btn btn__borrar">
                            <i class="fas fa-trash"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/ServletControlador?accion=agregarLugar"
       class="btn__agregar">
        Agregar sitio tur�stico
    </a>
</section>