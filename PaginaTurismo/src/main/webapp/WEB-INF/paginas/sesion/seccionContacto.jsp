<%--Librería JSTL de Core--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section id="contacto" class="seccion">
    <h1>Contacto</h1>
    <p>Revisar los comentarios que dejan los visitantes de la página.</p>

    <table>
        <thead>
        <th>#</th>
        <th>Nombre</th>
        <th></th>
        </thead>
        <tbody>
            <%
                String id = "btn__modal__contactos";
                int cant = 0;
            %>
            <c:forEach var="contacto" items="${contactos}">
                <%
                    id = "btn__modal__contactos";
                    cant++;
                    id = id + cant;
                %>
                <tr>
                    <td id="tabledata__1">${contacto.idContacto}</td>
                    <td id="tabledata__2">${contacto.nombre}</td>
                    <td id="tabledata__3" class="flex">
                        <a href="${pageContext.request.contextPath}/ServletPanel?accion=mostrarContacto&idContacto=${contacto.idContacto}"
                           class="btn btn__ver">
                            <i class="fas fa-eye"></i>
                        </a>

                        <a href="mailto:${contacto.email}?Subject=Respuesta%20a%20comentario%20Turismo%20Argentina"
                           class="btn btn__editar">
                            <i class="fas fa-envelope"></i></i>
                        </a>

                        <label for="<%=id%>" class="btn btn__borrar"><i class="fas fa-trash"></i></label>
                        <input type="checkbox" id="<%=id%>" class="btn__modal">
                        <div class="modal">
                            <div class="contenedor__modal">
                                <header>Confirmar acción.</header>
                                <label for="<%=id%>" class="label__header"><i class="fas fa-times"></i></label>
                                <div class="contenido__modal">
                                    <p>¿Esta seguro/a que desea eliminar este registro? Se eliminara permanentemente.</p>
                                    <div class="flex">
                                        <a href="${pageContext.request.contextPath}/ServletPanel?accion=eliminarContacto&idContacto=${contacto.idContacto}"
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
</section>