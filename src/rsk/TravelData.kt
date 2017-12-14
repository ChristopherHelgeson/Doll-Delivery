package rsk

var legs = buildRouteTree()

fun buildRouteTree () : MutableList<Leg> {

    // create comprehensive list of unique vertices from input data
    val startLocations = edges.map { it["startLocation"] }
            .distinct()
    val endLocations = edges.map { it["endLocation"] }
            .distinct()
    val vertices = startLocations.union(endLocations)
            .distinct()

    //use list of vertices to create 'table' of data with vertex, shortest distance from start, previous vertex, visited? info
    val legs = mutableListOf<Leg>()
    for (vertex in vertices) {
        if (vertex.toString() == startingLocation) {
            legs.add(Leg(vertex.toString(), 0, "N/A", false))
        } else {
            legs.add(Leg(vertex.toString(), Int.MAX_VALUE, "N/A", false))
        }
    }
    return legs
}

fun printTravelData () {

    for (leg in legs) {
        println("vertex: ${leg?.vertex}, shortestDistFromStart: ${leg?.shortestDistFromStart}, previousVertex: ${leg?.previousVertex}, visited?: ${leg?.visited}")
    }
    println()
}
