package rsk

import org.junit.Assert.*
import org.junit.Test

class InputKtTest {

    @Test
    fun inputFileNotNull() {
        val inputString = readInputFile()
        assertNotNull(inputString)
    }

    @Test
    fun startingLocationNotNull() {
        val inputString = readInputFile()
        val startingLocation = extractStartingLocation(inputString)
        assertNotNull(startingLocation)
    }

    @Test
    fun targetLocationNotNull() {
        val inputString = readInputFile()
        val targetLocation = extractTargetLocation(inputString)
        assertNotNull(targetLocation)
    }

    @Test
    fun edgesNotNull() {
        val inputString = readInputFile()
        val edges = buildEdgesListMap(inputString)
        assertNotNull(edges)
    }

}