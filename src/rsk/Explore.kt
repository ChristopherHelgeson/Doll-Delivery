package rsk

fun explore(startingLocation: String,
            targetLocation: String,
            edges: List<Map<String, Any?>>,
            legs: MutableList<Leg?>)
        : Map<String, Any> {

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

        // get how far we have come so far
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

        printTravelData(legs)
    }

    val expectedOutput: Map<String, Any> = buildResultString (legs)

    //expectedOutput: Map<String, Any> = mapOf("distance" to 31, "path" to "Kruthika's abode => Brian's apartment => Wesley's condo => Byrce's den => Craig's haunt")

    return expectedOutput
}