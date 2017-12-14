package rsk

fun main(args: Array<String>) {

    dijkstra(startingLocation, targetLocation, edges)

}

fun dijkstra(startLocation: String, endLocation: String, edges: List<Map<String, Any>>): Map<String, Any> {
    val expectedOutput: Map<String, Any> = mapOf("distance" to 31, "path" to "Kruthika's abode => Brian's apartment => Wesley's condo => Byrce's den => Craig's haunt")

    val legs = buildTravelDataSet()
    explore(startingLocation, targetLocation, rsk.edges, legs)


    return expectedOutput
}

fun explore(startingLocation: String, targetLocation: String, edges: List<Map<String, Any?>>, legs: List<Leg?>) {

    for (i in 1..legs.count()) {

        // set starting vertex to closest unvisited vertex
        val sortedTravelData = legs
                .filter { it?.visited == false }
                .sortedBy { it?.shortestDistFromStart }
        val currentLocation = sortedTravelData[0]?.vertex

        // find all neighbors to current vertex
        val neighbors = edges
                .filter { it["startLocation"] == currentLocation }
                .map { it["endLocation"] }

        // get how far we have com so far
        val currentDistFromStart = legs
                .filter { it -> it?.vertex == currentLocation }
                .map { it?.shortestDistFromStart }.single()!!.toInt()

        println("\ncurrentLocation: $currentLocation")
        println("currentDistFromStart: $currentDistFromStart")
        println("neighbors and distance from here:")

        for (neighbor in neighbors) {

            // get distance from current vertex to this neighbor
            val newDistance = edges
                    .filter { it -> it["startLocation"] == currentLocation && it["endLocation"] == neighbor }
                    .map { it["distance"] }.single().toString().toInt()
            println("$neighbor, newDistance: $newDistance")
            var oldDistance = -1
            // get currently stored distance from current vertex to this neighbor (maxValue at init), if not visited before
            try {
                oldDistance = legs
                        .filter { it -> it?.vertex == neighbor && it?.visited == false }
                        .map { it?.shortestDistFromStart }.single()!!.toInt()
            } catch (e: Exception) {
                println(e)
            }
            println("$neighbor, oldDistance: $oldDistance")

            if (currentDistFromStart + newDistance < oldDistance) {

                // update distance from start
                legs.find { it -> it?.vertex == neighbor }.let { it?.shortestDistFromStart = currentDistFromStart + newDistance }

                // update previous vertex to equal where we are now
                legs.find { it -> it?.vertex == neighbor }.let { it?.previousVertex = currentLocation.toString() }
            }
        }

        legs.find { it -> it?.vertex == currentLocation }.let { it?.visited = true }

        println("\nAfter visiting $currentLocation:\n")

        // duplicate code -- why can't I call the fun printTravelData (legs: MutableList<Leg?>) in TravelData
        for (leg in legs) {
            println("vertex: ${leg?.vertex}, shortestDistFromStart: ${leg?.shortestDistFromStart}, previousVertex: ${leg?.previousVertex}, visited?: ${leg?.visited}")
        }
    }

    // PRINT RESULTS - TURN THIS INTO A FUNCTION
    //build path
    val path = mutableListOf<String>()
    var currentSpot = targetLocation
    path.add(currentSpot)
    var previous = ""
    while (previous != startingLocation){
        previous = legs
                .filter { it -> it?.vertex == currentSpot }
                .map { it?.previousVertex }.single().toString()
        path.add(previous)
        currentSpot = previous
    }
    path.reverse()

    val shortestRoute = legs
            .filter { it -> it?.vertex == targetLocation }
            .map { it?.shortestDistFromStart }.single().toString().toInt()

    print("{\"distance\" = $shortestRoute, \"path\" = \"")

    for (i in 0..path.count().minus(2)){
        print("${path[i]} => ")
    }
    println(path[path.count().minus(1)] + "\"")
}





