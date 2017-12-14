package rsk

fun main(args: Array<String>) {

    println(dijkstra(startingLocation, targetLocation, edges))
}

fun dijkstra(startingLocation: String,
             targetLocation: String,
             edges: List<Map<String, Any>>)
        : Map<String, Any> {

    val legs = buildTravelDataSet()

    val expectedOutput = explore(startingLocation, targetLocation, edges, legs)

    return expectedOutput
}





