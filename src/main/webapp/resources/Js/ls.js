function guardarDatos() {
    localStorage.usuario = document.getElementById("usuario").value;
    localStorage.nombre = document.getElementById("nombre").value;
}

function recuperarDatos() {
    if ((localStorage.nombre != undefined) && (localStorage.usuario != undefined)) {
        document.getElementById("datos").innerHTML = "Nombre: " + localStorage.nombre + " Usuario: " + localStorage.usuario;
    } else {
        document.getElementById("datos").innerHTML = "No has ingresado tu usuario";
    }
}
