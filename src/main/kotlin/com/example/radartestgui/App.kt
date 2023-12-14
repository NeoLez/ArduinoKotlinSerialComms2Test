package com.example.radartestgui

import com.fazecast.jSerialComm.SerialPort
import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

class App : Application() {
    var radarController : RadarController? = null
    fun getController() : RadarController?{
        return radarController
    }
    override fun start(stage: Stage) {
        val loader = FXMLLoader()
        val root = loader.load(
            javaClass.getResourceAsStream("radar.fxml")
        ) as Pane

        val scene = Scene(root, 600.0, 600.0)

        radarController = loader.getController()

        stage.isResizable = false
        stage.title = "Hello!"
        stage.scene = scene
        stage.show()



        val task = object : TimerTask() {
            override fun run() {
                Platform.runLater {
                    val port = SerialPort.getCommPort("COM1")//ACA VA EL NOMBRE DEL PUERTO
                    if(port.bytesAvailable()>=TAMANIO_PAQUETE) {//SI HAY 6 BYTES PARA LEER
                        val byteArray = ByteArray(TAMANIO_PAQUETE)
                        port.readBytes(byteArray, TAMANIO_PAQUETE)

                        //radarController!!.displayReading(Deteccion.fromByteArray2(byteArray))
                    }
                    radarController!!.displayReading(Deteccion(Random.nextInt(0..360).toShort(),Random.nextFloat()))
                }
            }
        }
        Timer().scheduleAtFixedRate(task, 0, 2000)
    }

    fun open(){
        launch()
    }

    fun receiveData(det : Deteccion){
        println("RC $radarController")
        radarController!!.displayReading(det)
    }
}