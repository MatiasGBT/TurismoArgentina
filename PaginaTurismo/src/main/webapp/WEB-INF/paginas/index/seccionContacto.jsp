<section id="seccion__contacto">
    <h1>Contacto</h1>
    <form action="${pageContext.request.contextPath}/ServletControlador?accion=enviar" method="POST" id="formulario__contacto">
        <div>
            <label for="nombre">Nombre:*</label>
            <input type="text" name="nombre" required>
            <br>
            <label for="email">Email:*</label>
            <input type="email" name="email" required>
        </div>
        <div>
            <br>
            <label>Comentarios:*</label>
            <br>
            <textarea cols="30" rows="10"  minlength="100" maxlength="600" placeholder="Escribe un comentario de al menos 100 caracteres con un máximo de 600." required=""></textarea>
        </div>
    </form>
    <input type="submit" value="Enviar" form="formulario__contacto">
</section>