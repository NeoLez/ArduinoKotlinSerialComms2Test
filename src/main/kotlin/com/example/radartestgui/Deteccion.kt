package com.example.radartestgui

import java.nio.ByteBuffer

class Deteccion(val angulo: Short, val distancia : Float) {

    companion object{
        val TAMANIO_PAQUETE = 6
        fun fromByteArray1(array: ByteArray) : Deteccion{
            val buf = ByteBuffer.wrap(array)
            val angulo = buf.getShort()
            val distancia = buf.getFloat()
            return Deteccion(angulo,distancia)
        }
        fun fromByteArray2(array: ByteArray) : Deteccion{
            val buf = ByteBuffer.wrap(array)
            val distancia = buf.getFloat()
            val angulo = buf.getShort()
            return Deteccion(angulo,distancia)
        }
        fun fromByteArray3(array: ByteArray) : Deteccion{
            array.reverse()
            val buf = ByteBuffer.wrap(array)
            val angulo = buf.getShort()
            val distancia = buf.getFloat()
            return Deteccion(angulo,distancia)
        }
        fun fromByteArray4(array: ByteArray) : Deteccion{
            array.reverse()
            val buf = ByteBuffer.wrap(array)
            val distancia = buf.getFloat()
            val angulo = buf.getShort()
            return Deteccion(angulo,distancia)
        }
    }

    override fun toString(): String {
        return "Deteccion(angulo=$angulo, distancia=$distancia)"
    }


}