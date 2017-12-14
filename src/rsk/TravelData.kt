package rsk

class TravelData (startingLocation: String, targetLocation: String, edges: List<Map<String, Any>>)

fun buildTravelDataSet (): MutableList<Leg?> {

    var legs = routeTree(edges)
    printTravelData(legs)
    return legs
}

fun routeTree (edges: List<Map<String, Any>>) : MutableList<Leg?> {

    // create comprehensive list of unique vertices from input data
    val startLocations = rsk.edges.map { it["startLocation"] }
            .distinct()
    val endLocations = rsk.edges.map { it["endLocation"] }
            .distinct()
    val vertices = startLocations.union(endLocations)
            .distinct()

    //use list of vertices to create 'table' of data with vertex, shortest distance from start, previous vertex, visited? info
    var legs = mutableListOf<Leg?>()
    for (vertex in vertices) {
        if (vertex.toString() == startingLocation) {
            legs.add(Leg(vertex.toString(), 0, "N/A", false))
        } else {
            legs.add(Leg(vertex.toString(), Int.MAX_VALUE, "N/A", false))
        }
    }

    return legs
}

fun printTravelData (legs: MutableList<Leg?>) {

    for (leg in legs) {
        println("vertex: ${leg?.vertex}, shortestDistFromStart: ${leg?.shortestDistFromStart}, previousVertex: ${leg?.previousVertex}, visited?: ${leg?.visited}")
    }
    println()
}
