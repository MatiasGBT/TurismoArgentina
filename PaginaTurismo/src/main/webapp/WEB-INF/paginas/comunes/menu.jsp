<header class="header">
    <nav class="nav">
        <a href="${pageContext.request.contextPath}/ServletControlador" class="logo"><img src="logoBlanco.svg" alt="Logo del sitio"></a>
        <button class="nav__toggle" aria-label="Abrir men?">
            <i class="fas fa-bars"></i>
        </button>
        <ul class="nav__menu">
            <li class="nav__menu__item"><a href="#" class="nav__menu__link"><i class="fas fa-home"></i> Inicio</a></li>
            <li class="nav__menu__item"><a href="#turismo" class="nav__menu__link"><i class="fas fa-suitcase-rolling"></i> Turismo</a></li>
            <li class="nav__menu__item"><a href="#actividades" class="nav__menu__link"><i class="fas fa-snowboarding"></i> Actividades</a></li>
            <li class="nav__menu__item"><a href="#contacto" class="nav__menu__link"><i class="fas fa-comments"></i> Contacto</a></li>
            <li class="nav__menu__item"><a href="${pageContext.request.contextPath}/ServletControlador?accion=carrito" class="nav__menu__link"><i class="fas fa-shopping-cart"></i> Carrito</a></li>
        </ul>
    </nav>
</header>