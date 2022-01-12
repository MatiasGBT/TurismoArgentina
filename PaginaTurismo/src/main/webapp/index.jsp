<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Turismo Argentina</title>
        <link rel="icon" type="image/ico" href="favicon.ico">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Anton&family=Marcellus&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="estilos/estilos.css">
    </head>
    <body>
        <main>
            <div id="contenedor__index">
                <div class="centro">
                    <h1>Hola, ¡bienvenido a Turismo Argentina!</h1>
                    <p>Para continuar, por favor cree una cuenta o inicie sesión si ya tiene una.</p>
                    <a href="${pageContext.request.contextPath}/ServletControlador?accion=sesion">
                        <button id="iniciar__sesion">Iniciar sesión</button>
                    </a>
                    <a href="${pageContext.request.contextPath}/ServletControlador?accion=registrarse">
                        <button id="registrarse">Registrarse</button>
                    </a>
                </div>
            </div>
        </main>
    </body>
</html>