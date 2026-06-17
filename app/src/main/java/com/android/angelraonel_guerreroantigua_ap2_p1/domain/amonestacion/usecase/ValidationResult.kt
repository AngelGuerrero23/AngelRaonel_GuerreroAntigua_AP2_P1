package com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase

data class ValidationResult(
    val isValid: Boolean,
    val error: String? = null
)

fun validateAmonestacion(amonestacionId: Int?): ValidationResult{
    return when{
        (amonestacionId ?: 0) < 0 -> ValidationResult(false, "El Id no puede ser negativo")
        else -> ValidationResult(true)
    }
}

fun validateNombres(nombres: String): ValidationResult{
    return when{
        nombres.isBlank() -> ValidationResult(false, "El nombre no puede estar vacio")
        nombres.trim().length < 3 -> ValidationResult(false,"Debe contener mas de 2 Carácteres")

        !nombres.all { it.isLetter() || it.isWhitespace()}->(
                ValidationResult(false, "El nombre no puede tener numero ni" +
                        "caracteres especiales")
                )

        nombres.length>16->{
            ValidationResult(false, "El nombre no puede contener mas de 16 carácteres")
        }
        else -> ValidationResult(true)
    }
}

fun validateRazon(razon: String): ValidationResult{
    return when{
        razon.isBlank()-> ValidationResult(false, "La razon no puede estar vacia")
        else-> ValidationResult(true)
    }
}
fun validateMonto(monto: String): ValidationResult {
    return when{
        monto.isBlank() -> ValidationResult(false, "El monto no puede estar vacio")
        monto.toDoubleOrNull() == null -> ValidationResult(false, "Ingrese un sueldo" +
                "valido")
        monto.toDouble() <0.0 -> ValidationResult(false,"El monto no puede ser" +
                "menor a 0")
        else-> ValidationResult(true)
    }
}
