<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/paginas/sesion/etiquetasHeadSesion.jsp"/>
        <title>Comprar pasaje | Turismo Argentina</title>
    </head>
    <body id="cuerpo__formulario__actividad" class="cuerpo__formulario">
        <h1>Comprar pasaje</h1>
        <div class="caja__comprar">
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=agregarLugarCarrito&idLugar=${lugar.idLugar}" method="POST" id="formulario__sesion" enctype="multipart/form-data">
                <div>
                    
                    <label for="cantidad"><i class="fas fa-users"></i> ¿Cuantas personas irán?</label>
                    <input type="number" name="cantidad" required min="1" max="5">
                    
                    <p>Nota: solo se puede comprar hasta un máximo de 5 pasajes.</p>
                    
                    <input type="submit" value="Comprar">
                </div>
            </form>
        </div>
                
        <%--SCRIPTS--%>
        <script src="https://kit.fontawesome.com/94d22320fc.js" crossorigin="anonymous"></script>
    </body>
</html>