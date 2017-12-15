package rsk

import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {

    // read text file containing supplied input data
    val inputStream: InputStream = File("Doll-Delivery_Input_Data.txt").inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }
    println(inputString)

    // write text file containing expected output data
    val outString: String = (dijkstra(startingLocation, targetLocation, edges)).toString()
    File("Doll-Delivery_Output_Data.txt").printWriter().use { out -> out.println(outString) }

    println(dijkstra(startingLocation, targetLocation, edges))
}

fun dijkstra(startingLocation: String,
             targetLocation: String,
             edges: List<Map<String, Any>>)
        : Map<String, Any> {

    return explore(startingLocation, targetLocation, edges)
}





