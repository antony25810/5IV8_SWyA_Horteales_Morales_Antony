$(document).ready(function(){
    $('#descifrado').click(function(){
        var aux = true;
        var clave = document.getElementById('clave').value;
        var contenido = document.getElementById('contenido').value;
        
        if(contenido == ""){
            alert("No se ha seleccionado ningun archivo");
            aux = false;
        }
        if(clave == ""){
            alert("Debe de llenar el campo de clave");
            aux = false;
        }
        if(aux){
            var descifrado = CryptoJS.AES.decrypt(contenido, clave);
            document.getElementById('mensaje-descifrado-hex').value = descifrado;
            document.getElementById('mensaje-descifrado').value = descifrado.toString(CryptoJS.enc.Utf8);
        }
    });
});