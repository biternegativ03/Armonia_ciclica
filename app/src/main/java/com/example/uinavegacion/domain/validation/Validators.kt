package com.example.uinavegacion.domain.validation

import android.util.Patterns

// ================================
// Reglas de negocio (puedes ajustar)
// ================================
private const val MIN_AGE = 10
private const val MAX_AGE = 80
private const val MIN_WEIGHT = 30f   // kg
private const val MAX_WEIGHT = 200f  // kg
private const val MIN_HEIGHT = 120   // cm
private const val MAX_HEIGHT = 220   // cm

// ==================================================
// Validaciones existentes (con mejoras puntuales)
// ==================================================

// Nombre: no vacío y solo letras (con acentos) y espacios
fun validateNameLettersOnly(nombre: String): String? {
    if (nombre.isBlank()) return "El nombre es obligatorio"
    val regex = Regex("^[A-Za-zÁÉÍÓÚÑáéíóúñ ]+$")
    return if (!regex.matches(nombre)) "Solo se aceptan letras y espacios" else null
}

// Correo: formato y no vacío (mensajes guiados + verificación final con Patterns)
fun validateEmail(email: String): String? {
    if (email.isBlank()) return "El correo es obligatorio"
    if (!email.contains("@")) return "El correo debe contener @"
    if (!email.contains(".")) return "El correo debe contener un dominio (.com, .cl, etc.)"

    val parts = email.split("@")
    if (parts.size != 2) return "Formato de correo inválido"

    val localPart = parts[0]
    val domain = parts[1]

    if (localPart.isBlank()) return "El correo debe tener un nombre de usuario"
    if (domain.isBlank()) return "El correo debe tener un dominio"
    if (!domain.contains(".")) return "El dominio debe tener una extensión (.com, .cl, etc.)"

    val domainParts = domain.split(".")
    if (domainParts.size < 2) return "El dominio debe tener al menos una extensión"
    if (domainParts.any { it.isBlank() }) return "El dominio no puede contener partes vacías"

    // Verificación final de formato con Android
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) return "Formato de correo inválido"
    return null
}

// Teléfono: no vacío, solo dígitos, largo 8–9
fun validatePhoneDigitsOnly(phone: String): String? {
    if (phone.isBlank()) return "El teléfono es obligatorio"
    if (!phone.all { it.isDigit() }) return "Solo se aceptan números"
    if (phone.length !in 8..9) return "Debe contener entre 8 y 9 dígitos"
    return null
}

// Contraseña fuerte (arreglo: exige carácter especial correctamente)
fun validateStrongPass(pass: String): String? {
    if (pass.isBlank()) return "Debes escribir tu contraseña"
    if (pass.length < 8) return "Debe tener una longitud de al menos 8 caracteres"
    if (!pass.any { it.isUpperCase() }) return "Debe contener al menos una mayúscula"
    if (!pass.any { it.isDigit() }) return "Debe contener al menos un número"
    if (!pass.any { it.isLowerCase() }) return "Debe contener al menos una minúscula"
    // Importante: carácter especial = NO alfanumérico
    if (!pass.any { !it.isLetterOrDigit() }) return "Debe contener al menos un carácter especial"
    if (pass.contains(' ')) return "No puede contener espacios en blanco"
    return null
}

// Confirmación de contraseña
fun validateConfirm(pass: String, confirm: String): String? {
    if (confirm.isBlank()) return "Debe confirmar su contraseña"
    return if (pass != confirm) "Las contraseñas no son iguales" else null
}

// ==================================================
// NUEVAS validaciones para Datos Básicos del perfil
// ==================================================

// Edad: requerida, entero, rango permitido
fun validateAge(age: String): String? {
    if (age.isBlank()) return "La edad es obligatoria"
    val n = age.toIntOrNull() ?: return "Debe ser un número entero"
    if (n !in MIN_AGE..MAX_AGE) return "Ingresa una edad entre $MIN_AGE y $MAX_AGE"
    return null
}

// Peso (kg): requerido, decimal, rango permitido
fun validateWeight(weight: String): String? {
    if (weight.isBlank()) return "El peso es obligatorio"
    val n = weight.replace(',', '.').toFloatOrNull() ?: return "Debe ser numérico (ej: 62.5)"
    if (n !in MIN_WEIGHT..MAX_WEIGHT) return "Peso fuera de rango ($MIN_WEIGHT–$MAX_WEIGHT kg)"
    return null
}

// Altura (cm): requerida, entero, rango permitido
fun validateHeight(height: String): String? {
    if (height.isBlank()) return "La altura es obligatoria"
    val n = height.toIntOrNull() ?: return "Debe ser un número entero (cm)"
    if (n !in MIN_HEIGHT..MAX_HEIGHT) return "Altura fuera de rango ($MIN_HEIGHT–$MAX_HEIGHT cm)"
    return null
}

// Método anticonceptivo: requerido (si usas dropdown, valida que no sea el placeholder)
fun validateContraceptive(method: String): String? {
    if (method.isBlank()) return "Selecciona un método"
    if (method.equals("Selecciona...", ignoreCase = true)) return "Selecciona un método válido"
    return null
}
