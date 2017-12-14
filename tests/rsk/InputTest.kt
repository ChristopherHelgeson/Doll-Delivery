package rsk

import org.junit.Assert.*
import org.junit.Test

class InputTest {

    // startingLocation tests:

    @Test
    fun startingLocationNotNull() {
        assertNotNull(startingLocation)
    }

    @Test
    fun startInEdges() {
        val startInEdges = edges
                .filter{ it -> it["startLocation"] == startingLocation}
                .map{ it["startLocation"] }.first()
        println(startInEdges)
        assertEquals (startInEdges, startingLocation)
    }

    // targetLocation tests:

    @Test
    fun targetLocationNotNull() {
        assertNotNull(targetLocation)
    }

    @Test
    fun targetInEdges() {
        val targetInEdges = edges
                .filter{ it -> it["endLocation"] == targetLocation}
                .map{ it["endLocation"] }.first()
        println(targetInEdges)
        assertEquals (targetInEdges, targetLocation)
    }

    // edges tests:

    @Test
    fun edgesNotNull() {
        assertNotNull(edges)
    }

    @Test
    fun edgesEqualOneOrMore() {
        val edgeQty = edges.count()
        assertTrue(edgeQty>0)
    }
}