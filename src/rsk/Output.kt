package rsk

fun buildResultString (legs: List<Leg?>): Map<String, Any> {
    val path = traceBackToStart(legs)
    val result = buildResultMap(legs, path)
    return result
}

fun traceBackToStart(legs: List<Leg?>): MutableList<String> {

    val path = mutableListOf<String>()
    var currentSpot = targetLocation
    path.add(currentSpot)
    var previous = ""
    while (previous != startingLocation) {
        previous = legs
                .filter { it -> it?.vertex == currentSpot }
                .map { it?.previousVertex }.single().toString()
        path.add(previous)
        currentSpot = previous
    }
    path.reverse()
    return path
}

fun buildResultMap (legs: List<Leg?>,
                    path: MutableList<String>)
        : Map<String, Any> {

    val shortestRoute = legs
            .filter { it -> it?.vertex == targetLocation }
            .map { it?.shortestDistFromStart }.single().toString().toInt()

    var resultPath = ""

    for (i in 0..path.count().minus(2)){
        //print("${path[i]} => ")
        resultPath += "${path[i]} => "
    }
    resultPath += path[path.count().minus(1)]

    val result = mapOf("distance" to shortestRoute, "path" to resultPath)
    return result
}
