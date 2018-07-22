package io.noizwaves.codexform.application

import io.noizwaves.codexform.step.JavaReplaceMethodWithComment
import io.noizwaves.codexform.step.JavaReplaceMethodWithReturnNull
import io.noizwaves.codexform.step.Method
import io.noizwaves.codexform.transformation.Transformation
import java.nio.file.Paths

fun main(args: Array<String>) {
    val lab5 = Transformation(listOf(
            // FleetTruck
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", Method("fleetDomainEvents")),

            // FleetTruckEventStoreRepositoryAdapter
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", Method("save")),
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", Method("findOne")),
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", Method("findAll")),

            // EventPublishingFleetTruckRepositoryDecorator
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", Method("save")),
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", Method("findOne")),
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", Method("findAll")),
            JavaReplaceMethodWithComment("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", Method("delete"), "Implement me"),
            JavaReplaceMethodWithComment("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", Method("deleteAll"), "Implement me")
    ))

    if (args.isEmpty()) {
        showUsage()
        return
    }

    val targetPath = args[0]
    lab5.apply(Paths.get(targetPath))
}

fun showUsage() {
    println("""Usage: java -jar codexform.jar [path to transformation target]""")
}

