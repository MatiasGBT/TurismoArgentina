<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/paginas/sesion/etiquetasHeadSesion.jsp"/>
        <title>Agregar lugar | Turismo Argentina</title>
    </head>
    <body id="#cuerpo__formulario__lugar" class="cuerpo__formulario">
        <h1>Agregar lugar</h1>
        <div>
            <form action="${pageContext.request.contextPath}/ServletPanel?accion=insertarLugar" method="POST" id="formulario__sesion" enctype="multipart/form-data">
                <div>
                    <label for="nombre"><i class="fas fa-map-marked-alt"></i> Nombre:</label>
                    <input type="text" name="nombre" required value="${lugar.nombre}">
                    
                    <label for="descripcion"><i class="fas fa-align-justify"></i> Descripción:</label>
                    <br>
                    <textarea name="descripcion" cols="30" rows="10" maxlength="600" placeholder="Máximo 600 caracteres." required></textarea>
                    <br>
                    
                    <label for="portada"><i class="fas fa-image"></i> Imagen de portada:</label>
                    <input type="file" name="portada" required accept="image/jpeg">
                    
                    <label for="foto1"><i class="fas fa-image"></i> Imagen n1:</label>
                    <input type="file" name="foto1" required accept="image/jpeg">
                    
                    <label for="foto2"><i class="fas fa-image"></i> Imagen n2:</label>
                    <input type="file" name="foto2" required accept="image/jpeg">
                    
                    <label for="foto3"><i class="fas fa-image"></i> Imagen n3:</label>
                    <input type="file" name="foto3" required accept="image/jpeg">
                    
                    <label for="precio"><i class="fas fa-dollar-sign"></i> Precio:</label>
                    <input type="number" name="precio" required value="${lugar.precio}">
                    
                    <p class="nota__formulario">Nota: Las imagenes deben tener unas dimensiones de 550x310px.</p>
                    
                    <input type="submit" value="Agregar lugar">
                </div>
            </form>
        </div>
                
        <%--SCRIPTS--%>
        <script src="https://kit.fontawesome.com/94d22320fc.js" crossorigin="anonymous"></script>
    </body>
</html>
