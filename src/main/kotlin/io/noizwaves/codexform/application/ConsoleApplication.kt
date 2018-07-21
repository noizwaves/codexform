package io.noizwaves.codexform.application

import io.noizwaves.codexform.step.JavaReplaceMethodWithComment
import io.noizwaves.codexform.step.JavaReplaceMethodWithReturnNull
import io.noizwaves.codexform.transformation.Transformation
import java.nio.file.Paths

fun main(args: Array<String>) {
    val example = Transformation(listOf(
            JavaReplaceMethodWithComment("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", "returnFromInspection", "Implement me"),
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", "fleetDomainEvents")
    ))

    if (args.isEmpty()) {
        showUsage()
        return
    }

    val targetPath = args[0]
    example.apply(Paths.get(targetPath))
}

fun showUsage() {
    println("""Usage: java -jar codexform.jar [path to transformation target]""")
}

