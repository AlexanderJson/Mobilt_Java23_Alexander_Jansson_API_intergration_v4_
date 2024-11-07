package com.example.nyilnmning.nn

import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class ActivationFunction{

}
private fun initWeights(){

    val matrix = arrayOf(
        arrayOf(11,3.5,4.0),
        arrayOf(75,13,8),
        arrayOf(2024)
    )

    val numRows = matrix.size
    val numCols = 200
    val newMatrix = Array(numRows) { Array(numCols) { Random.nextDouble() } }
    for(row in newMatrix){
        println(row.joinToString(", "))
        println(newMatrix)
    }
}