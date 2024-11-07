package com.example.nyilnmning.nn

import androidx.annotation.Size
import kotlin.random.Random

class NeuralNetwork(val inputSize: Int, val hiddenLayerSize: Int, val outputSize: Int) {


    // forward propagation

    // input matris

    // dolt (ingångs)lager vikt 1

    // i * hl = dolt lager 1 = lager 1



    // lager 1 * dolt lager vikt 2 = lager 2

    // justera antal lager här + kanske extra bearbetning av data innan eller efter?

    //lager 2 * output lager vikt = output lager


    // back propagation

    // räkna ut MSE med output mot rätt resultat

    //justera träningsvkter närmare rätt resultat (med rätt antal epoker så vi inte övertränar för en typ av data)

    // pröva igen med ny data



    

    // normaliserar värden för sannolikhetsberäkning
    private fun sigmoid(x: Double): Double {
        return 1/(1+ Math.exp(-x))
    }

}
