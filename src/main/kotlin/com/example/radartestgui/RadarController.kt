package com.example.radartestgui

import javafx.fxml.FXML
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import javafx.scene.shape.Circle
import kotlin.math.cos
import kotlin.math.sin

class RadarController : Pane() {
    @FXML
    lateinit var imageView : ImageView
    @FXML
    lateinit var circle : Circle

    fun displayReading(reading : Deteccion){
        //println(reading)

        //val fadeTransition = FadeTransition(Duration(5000.0), circle)

        val x = reading.distancia * cos(Math.toRadians(reading.angulo.toDouble()))
        val y = reading.distancia * sin(Math.toRadians(reading.angulo.toDouble()))

        circle.layoutX = y*250+300
        circle.layoutY = x*-250+300
    }
}