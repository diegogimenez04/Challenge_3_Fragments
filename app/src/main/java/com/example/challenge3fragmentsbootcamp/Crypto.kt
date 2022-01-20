package com.example.challenge3fragmentsbootcamp

import com.example.challenge3fragmentsbootcamp.api.Image

data class Crypto(val nombre: String, val abreviatura: String, val imagen: Image,
                  val fecha_creacion: String, val precio: Long)