package io.noizwaves.codexform.application

import io.noizwaves.codexform.transformation.Transformation
import java.nio.file.Paths
import java.util.*

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        showUsage()
        return
    }

    val enteredName = args[0]
    val resolved = resolveDesiredTransformation(transformations, enteredName)
    if (!resolved.isPresent) {
        println("ERROR!! Unknown transformation '$enteredName'")
        showUsage()
        return
    }

    val transformation = resolved.get()
    val transformDir = Paths.get(args[1])
    val workingDir2 = Paths.get(args[2])

    transformation.apply(transformDir, workingDir2)
}

fun resolveDesiredTransformation(choices: Map<String,Transformation>, name: String): Optional<Transformation> {
    return Optional.ofNullable(choices[name])
}

private val transformations = hashMapOf(
        Pair("lab4start", lab4StartTransformations),
        Pair("lab5start", lab5StartTransformations)
)

fun showUsage() {
    println("""Usage: java -jar codexform.jar (transformation) (transform dir) (working dir)""")
}

