//Cuando la página carga, ejecuta una función
window.addEventListener('load', function () {
    //Al carousel se le pasan primero la lista y luego las opciones que queremos para este
    new Glider(document.querySelector('.carousel__lista'), {
        slidesToShow: 1, //Cuantos slides mostrar en el carousel
        slidesToScroll: 1, //Cuantos slides desplazarse al apretar un botón
        rewind: true, //Hace que vuelva al inicio una vez apretar siguiente en el final
        draggable: true, //Para que se pueda arrastrar con el mouse
        dragVelocity: 1,
        dots: '.carousel__indicadores', //Indicadores
        arrows: {
            prev: '.carousel__anterior',
            next: '.carousel__siguiente'
        },
        responsive: [
            {
                //Pantalla >= 775px
                breakpoint: 800,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2
                }
            }, {
                //Pantalla >= 1024px
                breakpoint: 1024,
                settings: {
                    slidesToShow: 4,
                    slidesToScroll: 4
                }
            }
        ]
    });
});