package com.example.challenge3fragmentsbootcamp

import android.os.Parcelable
import com.example.challenge3fragmentsbootcamp.api.Image
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Crypto(val nombre: String, val abreviatura: String, val imagen: Image,
                  val fecha_creacion: String, val precio: Double): Parcelable