package io.noizwaves.codexform.application

import java.nio.file.Paths

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        showUsage()
        return
    }

    val transformation = lab5StartTransformations
    val transformDir = args[0]
    val workingDir = args[1]

    transformation.apply(Paths.get(transformDir), Paths.get(workingDir))
}

fun showUsage() {
    println("""Usage: java -jar codexform.jar (transform dir) (working dir)""")
}

