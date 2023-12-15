package com.example.radartestgui

import com.fazecast.jSerialComm.SerialPort

fun main() {
    //test()
    /*val port = SerialPort.getCommPort("COM1")//ACA VA EL NOMBRE DEL PUERTO
    while(true){
        if(port.bytesAvailable()>=TAMANIO_PAQUETE) {//SI HAY 6 BYTES PARA LEER
            val byteArray = ByteArray(TAMANIO_PAQUETE)
            port.readBytes(byteArray, TAMANIO_PAQUETE)

            println(Deteccion.fromByteArray2(byteArray))//PROBAR CON LOS CUATRO POSIBLES METODOS A VER CUAL FUNCIONA
        }
    }*/
    val app = App()
    app.open()

}

private fun printPorts(){
    val ports = SerialPort.getCommPorts()
    for(pIndex in ports.indices) {
        val port = ports[pIndex]
        println("Index: $pIndex Desc: ${port.descriptivePortName} BaudRate: ${port.baudRate}")
    }
}

private fun test(){
    val byteArray1 = ByteArray(6)
    byteArray1[0]= 0//INT
    byteArray1[1]= -104

    byteArray1[2]= 65//FLOAT
    byteArray1[3]= -110
    byteArray1[4]= -72
    byteArray1[5]= 82

    val byteArray2 = ByteArray(6)
    byteArray2[0]= 65//FLOAT
    byteArray2[1]= -110
    byteArray2[2]= -72
    byteArray2[3]= 82

    byteArray2[4]= 0//INT
    byteArray2[5]= -104

    val d1 = Deteccion.fromByteArray1(byteArray1)
    val d2 = Deteccion.fromByteArray2(byteArray2)
    println(d1)
    println(d2)
}