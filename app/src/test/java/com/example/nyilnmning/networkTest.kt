package com.example.nyilnmning
import org.tensorflow.lite.Tensor
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import org.tensorflow.lite.support.common.TensorOperator
/*
import org.junit.Test
import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.api.ops.impl.transforms.strict.Sigmoid
import org.nd4j.linalg.factory.Nd4j
import org.nd4j.linalg.ops.transforms.Transforms
import kotlin.random.Random


class NeuralTest{


    fun inputLayer(): INDArray{
        //list of data

        val value = arrayOf(
            doubleArrayOf(1.010e+02, 1.010e+02, 2.010e+02, 4.000e+00, 2.000e+00, 7.500e+02, 8.100e+00, 4.500e+00, 2.012e+03, 8.530e+01),
            doubleArrayOf(1.020e+02, 1.020e+02, 2.020e+02, 4.000e+00, 2.000e+00, 1.200e+03, 8.500e+00, 5.000e+00, 2.001e+03, 9.010e+01),
            doubleArrayOf(1.030e+02, 1.050e+02, 2.030e+02, 4.000e+00, 2.000e+00, 5.600e+02, 7.100e+00, 4.000e+00, 2.005e+03, 7.540e+01),
            doubleArrayOf(1.040e+02, 1.070e+02, 2.040e+02, 4.000e+00, 1.000e+00, 8.000e+01, 8.200e+00, 4.700e+00, 2.019e+03, 8.850e+01),
            doubleArrayOf(1.050e+02, 1.080e+02, 2.050e+02, 4.000e+00, 2.000e+00, 3.500e+01, 7.300e+00, 3.800e+00, 1.988e+03, 6.520e+01),
            doubleArrayOf(1.060e+02, 1.090e+02, 2.060e+02, 4.000e+00, 8.000e+00, 1.900e+01, 8.200e+00, 4.500e+00, 2.006e+03, 7.280e+01),
            doubleArrayOf(1.070e+02, 1.100e+02, 2.070e+02, 4.000e+00, 1.000e+00, 2.000e+01, 6.900e+00, 3.700e+00, 1.982e+03, 6.840e+01),
            doubleArrayOf(1.080e+02, 1.110e+02, 2.080e+02, 4.000e+00, 9.000e+00, 7.000e+01, 7.600e+00, 4.200e+00, 2.007e+03, 7.730e+01),
            doubleArrayOf(1.090e+02, 1.120e+02, 2.090e+02, 4.000e+00, 2.000e+00, 2.500e+01, 7.200e+00, 4.000e+00, 1.982e+03, 6.070e+01),
            doubleArrayOf(1.100e+02, 1.140e+02, 2.100e+02, 4.000e+00, 2.000e+00, 2.700e+01, 7.400e+00, 4.300e+00, 1.984e+03, 7.300e+01),
            doubleArrayOf(1.110e+02, 1.150e+02, 2.110e+02, 4.000e+00, 2.000e+00, 1.000e+02, 5.100e+00, 3.000e+00, 2.006e+03, 5.040e+01),
            doubleArrayOf(1.120e+02, 1.160e+02, 2.120e+02, 4.000e+00, 2.000e+00, 1.100e+01, 7.400e+00, 4.100e+00, 1.981e+03, 6.380e+01),
            doubleArrayOf(1.130e+02, 1.170e+02, 2.130e+02, 4.000e+00, 2.000e+00, 1.800e+02, 6.100e+00, 3.500e+00, 2.007e+03, 7.050e+01),
            doubleArrayOf(1.140e+02, 1.020e+02, 2.140e+02, 4.000e+00, 2.000e+00, 9.500e+01, 5.900e+00, 3.200e+00, 2.010e+03, 6.790e+01),
            doubleArrayOf(1.150e+02, 1.180e+02, 2.150e+02, 4.000e+00, 9.000e+00, 1.600e+01, 8.000e+00, 4.700e+00, 1.987e+03, 8.210e+01),
            doubleArrayOf(1.160e+02, 1.190e+02, 2.160e+02, 4.000e+00, 2.000e+00, 2.450e+01, 6.500e+00, 3.900e+00, 1.986e+03, 5.920e+01),
            doubleArrayOf(1.170e+02, 1.200e+02, 2.170e+02, 4.000e+00, 1.000e+00, 1.750e+02, 6.700e+00, 4.000e+00, 2.017e+03, 7.600e+01),
            doubleArrayOf(1.180e+02, 1.210e+02, 2.180e+02, 4.000e+00, 1.000e+00, 1.250e+02, 5.800e+00, 3.400e+00, 2.010e+03, 6.650e+01),
            doubleArrayOf(1.190e+02, 1.220e+02, 2.160e+02, 1.000e+00, 8.000e+00, 1.400e+02, 7.700e+00, 4.600e+00, 2.003e+03, 8.100e+01),
            doubleArrayOf(1.200e+02, 1.230e+02, 2.190e+02, 4.000e+00, 2.000e+00, 1.500e+02, 6.200e+00, 3.600e+00, 2.007e+03, 6.240e+01)
        )
        println("Layout Layer Activayed")
        return Nd4j.create(value)
    }

    fun weights(matrix: INDArray): INDArray{
        val rows = matrix.columns()
        println("Weights Layer Activayed")
        return Nd4j.rand(rows, 200)
    }

    fun multiply(matrix1: INDArray, matrix2: INDArray): INDArray{
        val matrix3 =  matrix1.mmul(matrix2)
        val cols = matrix3.columns()
        val bias = Nd4j.rand(1,cols)
        matrix3.addiRowVector(bias)
        println("Multiplicaiton Activayed")
        return matrix3
    }

    fun generateHiddenLayerOne(): INDArray{
        val inputMatrix = inputLayer()
        val inputWeights = weights(inputMatrix)
        val hiddenLayer = multiply(inputMatrix, inputWeights)

        return hiddenLayer
    }

    fun generateHiddenLayerTwo(): INDArray{
        val layerOneMatrix = generateHiddenLayerOne()
        val weights = weights(layerOneMatrix)
        val hiddenLayer = multiply(layerOneMatrix, weights)

        return hiddenLayer
    }

    fun generateHiddenLayerThree(): INDArray{
        val layerTwoMatrix = generateHiddenLayerTwo()
        val weights = weights(layerTwoMatrix)
        val hiddenLayer = multiply(layerTwoMatrix, weights)

        return hiddenLayer
    }

    fun generateHiddenLayerFour(): INDArray{
        val layerFourMatrix = generateHiddenLayerThree()
        val weights = weights(layerFourMatrix)
        val hiddenLayer = multiply(layerFourMatrix, weights)

        return hiddenLayer
    }

    fun generateOutput(): INDArray? {
        val layerFiveMatrix = generateHiddenLayerFour()
        val weights = weights(layerFiveMatrix)
        val output = multiply(layerFiveMatrix, weights)

        return Transforms.sigmoid(output)
    }

    fun feedforward(){
        generateHiddenLayerOne()
        generateHiddenLayerTwo()
        generateHiddenLayerThree()
        generateHiddenLayerFour()
        generateOutput()
    }

    @Test
    fun neuralNetwork(){
        feedforward()
    }


}*/