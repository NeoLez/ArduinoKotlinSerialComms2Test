package com.example.radartestgui

import com.fazecast.jSerialComm.SerialPort
import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

class App : Application() {
    override fun start(stage: Stage) {
        val loader = FXMLLoader()
        val root = loader.load(
            javaClass.getResourceAsStream("radar.fxml")
        ) as Pane

        val scene = Scene(root, 600.0, 600.0)
        val radarController : RadarController = loader.getController()

        stage.isResizable = false
        stage.title = "Radar"
        stage.scene = scene
        stage.show()



        val task = object : TimerTask() {
            override fun run() {
                Platform.runLater {
                    val port = SerialPort.getCommPort("COM1")//ACA VA EL NOMBRE DEL PUERTO
                    port.openPort()
                    if(port.bytesAvailable()>=Deteccion.TAMANIO_PAQUETE) {//SI HAY 6 BYTES PARA LEER
                        val byteArray = ByteArray(Deteccion.TAMANIO_PAQUETE)
                        port.readBytes(byteArray, Deteccion.TAMANIO_PAQUETE)

                        radarController.displayReading(Deteccion.fromByteArray2(byteArray))//CAMBIAR EL NUMERO DE METODO SI NO FUNCIONA
                    }
                    //radarController.displayReading(Deteccion(Random.nextInt(0..360).toShort(),Random.nextFloat()))
                    port.closePort()
                }
            }
        }

        Timer().scheduleAtFixedRate(task, 0, 15)
    }

    fun open(){
        launch()
    }
}