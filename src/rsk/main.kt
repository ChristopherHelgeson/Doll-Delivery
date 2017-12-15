package rsk

import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {

    // read text file containing supplied input data
    val inputStream: InputStream = File("Doll-Delivery_Input_Data.txt").inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }

    //make list of startLocations to build edges map
    val startLocations = inputString.split("mapOf(\"startLocation\" to \"")
    var startLocationMutable = mutableListOf<String>()
    for (i in 1..(startLocations.count()-1)){
        var indexOfFirstQuotationMark = startLocations[i].indexOf("\"")
        var extractedString = startLocations[i].slice(0.until(indexOfFirstQuotationMark))
        startLocationMutable.add(extractedString)
    }

    //make list of endLocations to build edges map
    val endLocations = inputString.split("\"endLocation\" to \"")
    var endLocationMutable = mutableListOf<String>()
    for (i in 1..(endLocations.count()-1)){
        var indexOfFirstQuotationMark = endLocations[i].indexOf("\"")
        var extractedString = endLocations[i].slice(0.until(indexOfFirstQuotationMark))
        endLocationMutable.add(extractedString)
    }

    //make list of distances to build edges map
    val distances = inputString.split("\"distance\" to ")
    var distanceMutable = mutableListOf<Int>()
    for (i in 1..(distances.count()-1)){
        var indexOfClosingParenthesis = distances[i].indexOf(")")
        var extractedInt = distances[i].slice(0.until(indexOfClosingParenthesis)).toInt()
        distanceMutable.add(extractedInt)
    }

    // build List<Map<String, Any>> called edges
    var testEdgeMap = mapOf<String, Any>()
    var testEdgesList = mutableListOf<Map<String, Any>>()
    for (i in 0.until(startLocationMutable.count())){
        testEdgeMap = mapOf("startLocation" to startLocationMutable[i],
                "endLocation" to endLocationMutable[i],
                "distance" to distanceMutable[i])
        testEdgesList.add(testEdgeMap)
        println("$i, $testEdgeMap")
    }

    val edges = testEdgesList

    // extract startLocation
    val inputSplit = inputString.split(",")
    val startString = inputSplit[0].split("\"")
    val startingLocation = startString[1]
    println("startingLocation: $startingLocation")

    // extract targetLocation
    val targetString = inputSplit[1].split("\"")
    val targetLocation = targetString[1]
    println("targetLocation: $targetLocation")

    println("---------------------------------")

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





