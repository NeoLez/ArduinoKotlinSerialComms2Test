package com.example.radartestgui

import javafx.animation.FadeTransition
import javafx.fxml.FXML
import javafx.scene.layout.Pane
import javafx.scene.shape.Circle
import javafx.util.Duration
import kotlin.math.cos
import kotlin.math.sin

class RadarController : Pane() {
    @FXML
    lateinit var circle : Circle
    private val MAX_DIST = 500
    private val TAMANIO_INTERFAZ=600
    private var fade : FadeTransition? = null
    private var shouldFade = false

    fun displayReading(reading : Deteccion){
        println(reading)

        val x = reading.distancia * cos(Math.toRadians(reading.angulo.toDouble()))
        val y = reading.distancia * sin(Math.toRadians(reading.angulo.toDouble()))

        println("X: $x Y: $y")

        if(fade!=null&&shouldFade) {
            fade!!.stop()
            shouldFade = false
        }

        circle.opacity = 1.0
        circle.layoutX = (y*(TAMANIO_INTERFAZ/(2*MAX_DIST)))+(TAMANIO_INTERFAZ/2)
        circle.layoutY = -(x*(TAMANIO_INTERFAZ/(2*MAX_DIST)))+(TAMANIO_INTERFAZ/2)
    }

    fun startFade(){
        if (!shouldFade) {
                shouldFade=true
                fade = FadeTransition(Duration.seconds(2.0), circle)
                fade!!.fromValue = 1.0
                fade!!.toValue = 0.0
            fade!!.play()
        }
    }
}