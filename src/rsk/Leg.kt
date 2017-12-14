package rsk

data class Leg(val vertex: String, var shortestDistFromStart: Int, var previousVertex: String, var visited: Boolean)
