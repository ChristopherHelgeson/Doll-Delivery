package rsk

fun buildResultString (): Map<String, Any> {
    val path = traceBackToStart()
    return buildResultMap(path)
}

fun traceBackToStart(): MutableList<String> {

    val path = mutableListOf<String>()
    var currentSpot = targetLocation
    path.add(currentSpot)
    var previous = ""
    while (previous != startingLocation) {
        previous = legs
                .filter { it -> it.vertex == currentSpot }
                .map { it.previousVertex }.single().toString()
        path.add(previous)
        currentSpot = previous
    }
    path.reverse()
    return path
}

fun buildResultMap (path: MutableList<String>): Map<String, Any> {

    val shortestRoute = legs
            .filter { it -> it.vertex == targetLocation }
            .map { it.shortestDistFromStart }.single().toString().toInt()

    var resultPath = ""

    for (i in 0..path.count().minus(2)){
        resultPath += "${path[i]} => "
    }
    resultPath += path[path.count().minus(1)]

    return mapOf("distance" to shortestRoute, "path" to resultPath)
}
