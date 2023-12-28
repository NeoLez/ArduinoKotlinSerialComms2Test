package com.example.radartestgui

import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage
import java.util.*

class App : Application() {
    override fun start(stage: Stage) {
        val loader = FXMLLoader()
        val root = loader.load(
            javaClass.getResourceAsStream("radar.fxml")
        ) as Pane

        val scene = Scene(root, 900.0, 600.0)
        val radarController : RadarController = loader.getController()

        stage.isResizable = false
        stage.title = "Radar"
        stage.scene = scene
        stage.show()


        val task = object : TimerTask() {
            override fun run() {
                Platform.runLater {
                    if(PORT.bytesAvailable()>=Deteccion.TAMANIO_PAQUETE) {//SI HAY 6 BYTES PARA LEER
                        val byteArray = ByteArray(Deteccion.TAMANIO_PAQUETE)
                        PORT.readBytes(byteArray, Deteccion.TAMANIO_PAQUETE)

                        radarController.displayReading(Deteccion.fromByteArray4(byteArray))//CAMBIAR EL NUMERO DE METODO SI NO FUNCIONA
                    }else{
                        radarController.startFade()
                    }
                    //radarController.displayReading(Deteccion(30.toShort(),200.0f))
                }
            }
        }

        Timer().scheduleAtFixedRate(task, 0, 15)
    }

    fun open(){
        launch()
    }
}