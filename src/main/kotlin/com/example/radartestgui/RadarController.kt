package com.example.radartestgui

import javafx.animation.FadeTransition
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import javafx.util.Duration
import java.text.DecimalFormat
import kotlin.math.cos
import kotlin.math.sin

class RadarController : Pane() {
    @FXML
    lateinit var detectionPoint : ImageView
    @FXML
    lateinit var angle : Label
    @FXML
    lateinit var distance : Label
    @FXML
    lateinit var coordX : Label
    @FXML
    lateinit var coordY : Label
    @FXML
    lateinit var button : Button

    private val MAX_DIST = 240
    private val MAX_DIST_PIXELS = 380
    private val CENTER_X = 450
    private val CENTER_Y = 450
    private var fade : FadeTransition? = null
    private var shouldFade = false
    private val decimalFormat = DecimalFormat("0.0000")

    fun displayReading(reading : Deteccion){
        angle.text = "Ángulo: ${decimalFormat.format(reading.angulo)}"
        distance.text = "Distancia: ${decimalFormat.format(reading.distancia)}"

        val x = reading.distancia * cos(Math.toRadians(reading.angulo.toDouble()))
        val y = reading.distancia * sin(Math.toRadians(reading.angulo.toDouble()))

        coordX.text = "X: ${decimalFormat.format(x)}"
        coordY.text = "Y: ${decimalFormat.format(y)}"

        if(fade!=null&&shouldFade) {
            fade!!.stop()
            shouldFade = false
        }
        detectionPoint.opacity = 1.0

        detectionPoint.layoutX = (x*MAX_DIST_PIXELS/MAX_DIST) + CENTER_X - detectionPoint.boundsInParent.width/2
        detectionPoint.layoutY = (-y*MAX_DIST_PIXELS/MAX_DIST) + CENTER_Y - detectionPoint.boundsInParent.height/2
    }

    fun startFade(){
        if (!shouldFade) {
                shouldFade=true
                fade = FadeTransition(Duration.seconds(2.0), detectionPoint)
                fade!!.fromValue = 1.0
                fade!!.toValue = 0.0
            fade!!.play()
        }
    }


    private var buttonState = false
    @FXML
    fun onButtonPressed(){
        if(buttonState)
            button.text="STOP"
        else
            button.text="RUN"

        val arr = ByteArray(1)
        arr[0] = 1
        PORT.writeBytes(arr,1)

        buttonState=!buttonState
    }
}