package rsk

import java.io.File
import java.io.InputStream

fun readInputFile(): String {

    val inputStream: InputStream = File("Doll-Delivery_Input_Data.txt").inputStream()
    return inputStream.bufferedReader().use { it.readText() }

}

fun extractStartingLocation(inputString: String): String {

    val inputSplit = inputString.split(",")
    val startString = inputSplit[0].split("\"")
    return startString[1]
}

fun extractTargetLocation(inputString: String): String {

    val inputSplit = inputString.split(",")
    val targetString = inputSplit[1].split("\"")
    return targetString[1]
}

fun buildEdgesListMap(inputString: String): MutableList<Map<String, Any>> {

    val startLocationMutable = extractStartLocations(inputString)
    val endLocationMutable = extractEndLocations(inputString)
    val distanceMutable = extractDistances(inputString)

    var edgeMap = mapOf<String, Any>()
    var edges = mutableListOf<Map<String, Any>>()
    for (i in 0.until(startLocationMutable.count())) {
        edgeMap = mapOf("startLocation" to startLocationMutable[i],
                "endLocation" to endLocationMutable[i],
                "distance" to distanceMutable[i])
        edges.add(edgeMap)
        println("$i, $edgeMap")
    }

    return edges
}

fun extractStartLocations(inputString: String): MutableList<String> {
//make list of startLocations to build edges map
    val startLocations = inputString.split("mapOf(\"startLocation\" to \"")
    var startLocationMutable = mutableListOf<String>()
    for (i in 1..(startLocations.count() - 1)) {
        var indexOfFirstQuotationMark = startLocations[i].indexOf("\"")
        var extractedString = startLocations[i].slice(0.until(indexOfFirstQuotationMark))
        startLocationMutable.add(extractedString)
    }
    return startLocationMutable
}

fun extractEndLocations(inputString: String): MutableList<String> {
//make list of endLocations to build edges map
    val endLocations = inputString.split("\"endLocation\" to \"")
    var endLocationMutable = mutableListOf<String>()
    for (i in 1..(endLocations.count() - 1)) {
        var indexOfFirstQuotationMark = endLocations[i].indexOf("\"")
        var extractedString = endLocations[i].slice(0.until(indexOfFirstQuotationMark))
        endLocationMutable.add(extractedString)
    }
    return endLocationMutable
}

fun extractDistances(inputString: String): MutableList<Int> {
//make list of distances to build edges map
    val distances = inputString.split("\"distance\" to ")
    var distanceMutable = mutableListOf<Int>()
    for (i in 1..(distances.count() - 1)) {
        var indexOfClosingParenthesis = distances[i].indexOf(")")
        var extractedInt = distances[i].slice(0.until(indexOfClosingParenthesis)).toInt()
        distanceMutable.add(extractedInt)
    }
    return distanceMutable
}



// These variables were hard-coded before I was using inputStream and printWriter
// DELETE WHEN COMFORTABLE WITH NEW CODE
// these are global variables, rendering many arguments to many functions unnecessary.

//val startingLocation: String = "Kruthika's abode"
//val targetLocation: String = "Craig's haunt"
//val edges: List<Map<String, Any>> =
//    listOf(
//            mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Mark's crib", "distance" to 9),
//            mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Greg's casa", "distance" to 4),
//            mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Matt's pad", "distance" to 18),
//            mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Brian's apartment", "distance" to 8),
//            mapOf("startLocation" to "Brian's apartment", "endLocation" to "Wesley's condo", "distance" to 7),
//            mapOf("startLocation" to "Brian's apartment", "endLocation" to "Cam's dwelling", "distance" to 17),
//            mapOf("startLocation" to "Greg's casa", "endLocation" to "Cam's dwelling", "distance" to 13),
//            mapOf("startLocation" to "Greg's casa", "endLocation" to "Mike's digs", "distance" to 19),
//            mapOf("startLocation" to "Greg's casa", "endLocation" to "Matt's pad", "distance" to 14),
//            mapOf("startLocation" to "Wesley's condo", "endLocation" to "Kirk's farm", "distance" to 10),
//            mapOf("startLocation" to "Wesley's condo", "endLocation" to "Nathan's flat", "distance" to 11),
//            mapOf("startLocation" to "Wesley's condo", "endLocation" to "Bryce's den", "distance" to 6),
//            mapOf("startLocation" to "Matt's pad", "endLocation" to "Mark's crib", "distance" to 19),
//            mapOf("startLocation" to "Matt's pad", "endLocation" to "Nathan's flat", "distance" to 15),
//            mapOf("startLocation" to "Matt's pad", "endLocation" to "Craig's haunt", "distance" to 14),
//            mapOf("startLocation" to "Mark's crib", "endLocation" to "Kirk's farm", "distance" to 9),
//            mapOf("startLocation" to "Mark's crib", "endLocation" to "Nathan's flat", "distance" to 12),
//            mapOf("startLocation" to "Bryce's den", "endLocation" to "Craig's haunt", "distance" to 10),
//            mapOf("startLocation" to "Bryce's den", "endLocation" to "Mike's digs", "distance" to 9),
//            mapOf("startLocation" to "Mike's digs", "endLocation" to "Cam's dwelling", "distance" to 20),
//            mapOf("startLocation" to "Mike's digs", "endLocation" to "Nathan's flat", "distance" to 12),
//            mapOf("startLocation" to "Cam's dwelling", "endLocation" to "Craig's haunt", "distance" to 18),
//            mapOf("startLocation" to "Nathan's flat", "endLocation" to "Kirk's farm", "distance" to 3)
//    )