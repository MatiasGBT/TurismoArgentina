<%--Librería JSTL de Core--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section id="actividades" class="seccion">
    <h1>Actividades turísticas</h1>
    <p>Agregar, editar o eliminar actividades turísticas de la página principal.</p>

    <table>
        <thead>
        <th>#</th>
        <th>Nombre</th>
        <th></th>
        </thead>
        <tbody>
            <%
                String id = "btn__modal";
                int cant = 0;
            %>
            <c:forEach var="actividad" items="${actividades}">
                <%
                    id = "btn__modal";
                    cant++;
                    id = id + cant;
                %>
                <tr>
                    <td id="tabledata__1">${actividad.idActividad}</td>
                    <td id="tabledata__2">${actividad.nombre}</td>
                    <td id="tabledata__3" class="flex">
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=mostrarActividad&idActividad=${actividad.idActividad}"
                           class="btn btn__ver">
                            <i class="fas fa-eye"></i>
                        </a>

                        <a href="${pageContext.request.contextPath}/ServletPanel?accion=editarActividad&idActividad=${actividad.idActividad}"
                           class="btn btn__editar">
                            <i class="fas fa-edit"></i>
                        </a>

                        <label for="<%=id%>" class="btn btn__borrar"><i class="fas fa-trash"></i></label>
                        <input type="checkbox" id="<%=id%>" class="btn__modal">
                        <div class="modal">
                            <div class="contenedor__modal">
                                <header>Confirmar acción</header>
                                <label for="<%=id%>" class="label__header"><i class="fas fa-times"></i></label>
                                <div class="contenido__modal">
                                    <p>¿Esta seguro/a que desea eliminar este registro? Se eliminara permanentemente.</p>
                                    <div class="flex">
                                        <a href="${pageContext.request.contextPath}/ServletPanel?accion=eliminarActividad&idActividad=${actividad.idActividad}"
                                           class="btn__interior__modal btn__borrar">
                                            Eliminar
                                        </a>
                                        <label for="<%=id%>" class="btn__interior__modal btn__editar">Cancelar</label>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/ServletPanel?accion=agregarActividad"
       class="btn__agregar">
        Agregar actividad turística
    </a>
</section>