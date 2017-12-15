package rsk

fun main(args: Array<String>) {

    val inputString = readInputFile()
    val startingLocation = extractStartingLocation(inputString)
    val targetLocation = extractTargetLocation(inputString)
    val edges = buildEdgesListMap(inputString)

    writeOutputFile(startingLocation, targetLocation, edges)

    println(dijkstra(startingLocation, targetLocation, edges))
}

fun dijkstra(startingLocation: String,
             targetLocation: String,
             edges: List<Map<String, Any>>)
        : Map<String, Any> {

    return explore(startingLocation, targetLocation, edges)
}





