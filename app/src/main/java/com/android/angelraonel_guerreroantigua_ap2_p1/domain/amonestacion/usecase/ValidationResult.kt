package com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.usecase

data class ValidationResult (
    val isValid: Boolean,
    val error: String? = null
)

fun validateRazon(razon: String): ValidationResult{
    return when{
        razon.isBlank() -> ValidationResult(false, "Beneficiario no puede estar vacio")
        razon.trim().length < 2 -> ValidationResult(false,"Debe contener mas de" +
                "3 Carácteres")
        else-> ValidationResult(true)
    }
}

fun validateNombre(nombres: String) : ValidationResult{
    return when{
        nombres.isBlank() -> ValidationResult(false, "Beneficiario no puede estar vacio")
        nombres.trim().length < 2 -> ValidationResult(false,"Debe contener mas de" +
                "3 Carácteres")

        !nombres.all { it.isLetter() || it.isWhitespace() } ->(
                ValidationResult(false, "El nombre no puede tener numero ni" +
                        "caracteres especiales")
                )

        nombres.length >16 -> {
            ValidationResult(false, "El nombre no puede contener mas de 16" +
                    "caracteres")
        }
        else-> ValidationResult(true)
    }
}


fun validateMonto(monto: String): ValidationResult{
    return when{
        monto.isBlank() -> ValidationResult(false, "El monto no puede estar vacio")
        monto.toDoubleOrNull() == null -> ValidationResult(false, "Ingrese un sueldo" +
                "valido")
        monto.toDouble() <0.0 -> ValidationResult(false,"El monto no puede ser" +
                "menor a 0")
        else-> ValidationResult(true)
    }
}
