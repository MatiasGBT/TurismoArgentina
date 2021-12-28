<section>
    <h1>Inicio</h1>
    <p>Datos de interes.</p>

    <div id="caja__cards">
        <%--Cant. usuarios registrados--%>
        <div id="cantidad__usuarios" class="contenedor__datos">
            <h3>Cantidad total de usuarios registrados:</h3>
            <p>${totalUsuarios}</p>
            <p class="aclaracion">Cantidad de usuarios registrados en la base de datos (incluye al administrador).</p>
        </div>
        

        <%--Cant. sitios turísticos--%>
        <div id="cantidad__lugares" class="contenedor__datos">
            <h3>Cantidad total de sitios turísticos registrados:</h3>
            <p>${totalLugares}</p>
            <p class="aclaracion">Cantidad de sitios turísticos registrados en la base de datos.</p>
        </div>
        
        
        <%--Cant. actividades--%>
        <div id="cantidad__actividades" class="contenedor__datos">
            <h3>Cantidad total de actividades registradas:</h3>
            <p>${totalActividades}</p>
            <p class="aclaracion">Cantidad de actividades turísticas registradas en la base de datos.</p>
        </div>
        
    </div>  
</section>