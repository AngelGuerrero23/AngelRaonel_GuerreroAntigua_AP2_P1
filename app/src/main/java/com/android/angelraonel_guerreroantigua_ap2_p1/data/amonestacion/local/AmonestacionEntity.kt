package com.android.angelraonel_guerreroantigua_ap2_p1.data.amonestacion.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "amonestacion_table")
data class AmonestacionEntity(
    @PrimaryKey(autoGenerate = true)
    val amonestacionId: Int = 0,
    var nombre: String? = " ",
    var razon: String? = " ",
    var monto: Double = 0.0
)
