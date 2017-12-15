package rsk

fun explore(startingLocation: String, targetLocation: String, edges: List<Map<String, Any>>): Map<String, Any> {

    val legs = buildRouteTree(startingLocation, edges)

    for (i in 1..legs.count()) {

        // set starting vertex to closest unvisited vertex
        val currentLocation = legs
                .asSequence()
                .filter { !it.visited}
                .sortedBy { it.shortestDistFromStart }
                .map{it.vertex}.first().toString()

        // find all neighbors to current vertex
        val neighbors = edges
                .asSequence()
                .filter { it["startLocation"] == currentLocation }
                .map { it["endLocation"] }

        // get how far we have come so far
        val currentDistFromStart = legs
                .filter { it -> it.vertex == currentLocation }
                .map { it.shortestDistFromStart }.single().toInt()

        println("\ncurrentLocation: $currentLocation")
        println("currentDistFromStart: $currentDistFromStart")
        println("unvisited neighbors (if any) and their distance from here:")

        for (neighbor in neighbors) {

            // get distance from current vertex to this neighbor
            val newDistance = edges
                    .filter { it -> it["startLocation"] == currentLocation && it["endLocation"] == neighbor }
                    .map { it["distance"] }.single().toString().toInt()

            var oldDistance = Int.MIN_VALUE
            // get currently stored distance from current vertex to this neighbor (maxValue at init), if not visited before
            if (legs.any { it.vertex == neighbor && !it.visited }){
                oldDistance = legs
                        .filter { it -> it.vertex == neighbor && !it.visited }
                        .map { it.shortestDistFromStart }.single().toInt()
            }

            if (oldDistance > Int.MIN_VALUE) {
                println("$neighbor, newDistance: $newDistance")
                println("$neighbor, oldDistance: $oldDistance")
            }

            if (currentDistFromStart + newDistance < oldDistance) {

                // update distance from start
                legs.find { it -> it.vertex == neighbor }.let { it?.shortestDistFromStart = currentDistFromStart + newDistance }

                // update previous vertex to equal where we are now
                legs.find { it -> it.vertex == neighbor }.let { it?.previousVertex = currentLocation}
            }
        }

        legs.find { it -> it.vertex == currentLocation }.let { it?.visited = true }

        println("\nAfter visiting $currentLocation:\n")
        printTravelData(legs)
    }
    return buildResultString (startingLocation, targetLocation, legs)
}