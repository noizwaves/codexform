package io.noizwaves.codexform.application

import io.noizwaves.codexform.step.JavaReplaceMethodWithComment
import io.noizwaves.codexform.step.JavaReplaceMethodWithReturnNull
import io.noizwaves.codexform.transformation.Transformation
import java.nio.file.Paths

fun main(args: Array<String>) {
    val example = Transformation(listOf(
            // FleetTruck
            JavaReplaceMethodWithComment("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", "returnFromInspection", "Implement me"),
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", "fleetDomainEvents"),

            // FleetTruckEventStoreRepositoryAdapter
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", "save"),
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", "findOne"),
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", "findAll"),

            // EventPublishingFleetTruckRepositoryDecorator
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", "save"),
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", "findOne"),
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", "findAll"),
            JavaReplaceMethodWithComment("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", "delete", "Implement me"),
            JavaReplaceMethodWithComment("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", "deleteAll", "Implement me")
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

