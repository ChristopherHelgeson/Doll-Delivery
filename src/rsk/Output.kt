package rsk

import java.io.File

fun buildResultString (startingLocation: String, targetLocation: String, legs: MutableList<Leg>): Map<String, Any> {
    val path = traceBackToStart(startingLocation, targetLocation, legs)
    return buildResultMap(targetLocation, path, legs)
}

fun traceBackToStart(startingLocation: String, targetLocation: String, legs: MutableList<Leg>): MutableList<String> {

    val path = mutableListOf<String>()
    var currentSpot = targetLocation
    path.add(currentSpot)
    var previous = ""
    while (previous != startingLocation) {
        previous = legs
                .filter { it -> it.vertex == currentSpot }
                .map { it.previousVertex }.single().toString()
        path.add(previous)
        currentSpot = previous
    }
    path.reverse()
    return path
}

fun buildResultMap (targetLocation: String, path: MutableList<String>, legs: MutableList<Leg>): Map<String, Any> {

    val shortestRoute = legs
            .filter { it -> it.vertex == targetLocation }
            .map { it.shortestDistFromStart }.single().toString().toInt()

    var resultPath = ""

    for (i in 0..path.count().minus(2)){
        resultPath += "${path[i]} => "
    }
    resultPath += path[path.count().minus(1)]

    return mapOf("distance" to shortestRoute, "path" to resultPath)
}

fun writeOutputFile(startingLocation: String, targetLocation: String, edges: MutableList<Map<String, Any>>) {

    val outString: String = (dijkstra(startingLocation, targetLocation, edges)).toString()
    File("Doll-Delivery_Output_Data.txt").printWriter().use { out -> out.println(outString) }
}