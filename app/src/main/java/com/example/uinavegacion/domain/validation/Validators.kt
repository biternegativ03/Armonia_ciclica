package com.example.uinavegacion.domain.validation

import android.util.Patterns

//validaciones para el nombre:  no este vacio, solo letras
fun validateNameLettersOnly(nombre: String): String?{
    if(nombre.isBlank()) return "El nombre es obligatorio"
    val regex = Regex("^[A-Za-zÁÉÍÓÚÑáéíóúñ ]+$")
    return if(!regex.matches(nombre)) "Solo se aceptan letras y espacios" else null
}

//validaciones del correo: formato y no este vacio
fun validateEmail(email: String): String? {
    if(email.isBlank()) return "El correo es obligatorio"
    if(!email.contains("@")) return "El correo debe contener @"
    if(!email.contains(".")) return "El correo debe contener un dominio (.com, .es, etc.)"
    
    val parts = email.split("@")
    if(parts.size != 2) return "Formato de correo inválido"
    
    val localPart = parts[0]
    val domain = parts[1]
    
    if(localPart.isBlank()) return "El correo debe tener un nombre de usuario"
    if(domain.isBlank()) return "El correo debe tener un dominio"
    if(!domain.contains(".")) return "El dominio debe tener una extensión (.com, .es, etc.)"
    
    val domainParts = domain.split(".")
    if(domainParts.size < 2) return "El dominio debe tener al menos una extensión"
    if(domainParts.any { it.isBlank() }) return "El dominio no puede contener partes vacías"
    
    return null
}

//validacion de teléfono: no vacio, longitud, solo numeros
fun validatePhoneDigitsOnly(phone:String): String? {
    if(phone.isBlank()) return "El teléfono es obligatorio"
    if(!phone.all { it.isDigit() }) return "Solo se aceptan números"
    if(phone.length !in 8 .. 9) return "Debe contener entre 8 y 9 digitos"
    return null
}

//validaciones para la seguridad de la contraseña
fun validateStrongPass(pass: String): String? {
    if(pass.isBlank()) return "Debes escribir tu contraseña"
    if(pass.length < 8) return "Debe tener una logintud de más de 7 caracteres"
    if(!pass.any { it.isUpperCase() }) return "Debe contener al menos una mayúscula"
    if(!pass.any { it.isDigit() }) return "Debe contener al menos un número"
    if(!pass.any { it.isLowerCase() }) return "Debe contener al menos una minúscula"
    if(!pass.any { it.isLetterOrDigit() }) return "Debe contener al menos un caracter especial"
    if(pass.contains(' ')) return "No puede contener espacios en blanco"
    return null
}

//validar que las claves coincidan
fun validateConfirm(pass:String, confirm: String): String?{
    if(confirm.isBlank()) return "Debe confirmar su contraseña"
    return if(pass != confirm) "Las contraseñas no son iguales" else null
}