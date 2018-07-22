package io.noizwaves.codexform.application

import java.nio.file.Paths
import java.util.*

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        showUsage()
        return
    }

    val enteredName = args[0]
    val desiredTransformation = Optional.ofNullable(transformations[enteredName])
    if (!desiredTransformation.isPresent) {
        println("ERROR!! Unknown transformation '$enteredName'")
        showUsage()
        return
    }

    val transformation = desiredTransformation.get()
    val transformDir = Paths.get(args[1])
    val workingDir2 = Paths.get(args[2])

    transformation.apply(transformDir, workingDir2)
}

private val transformations = hashMapOf(
        Pair("lab4start", lab4StartTransformations),
        Pair("lab5start", lab5StartTransformations)
)

private fun showUsage() {
    val names = transformations.keys.toList().sorted()

    println("""
Usage: java -jar codexform.jar (transformation) (transform dir) (working dir)

Available transformations:""")
    names.forEach { println("- $it") }
}

