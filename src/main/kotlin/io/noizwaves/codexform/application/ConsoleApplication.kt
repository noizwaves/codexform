package io.noizwaves.codexform.application

import io.noizwaves.codexform.step.JavaReplaceMethodWithComment
import io.noizwaves.codexform.step.JavaReplaceMethodWithReturnConstant
import io.noizwaves.codexform.step.JavaReplaceMethodWithReturnNull
import io.noizwaves.codexform.step.Method
import io.noizwaves.codexform.transformation.Transformation
import java.nio.file.Paths

fun main(args: Array<String>) {
    val lab5 = Transformation(listOf(
            // FleetTruck
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", Method("fleetDomainEvents")),

            // FleetTruckEventStoreRepositoryAdapter
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", Method("save", listOf("S"))),
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", Method("findOne", listOf("String"))),
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", Method("findAll")),

            // EventPublishingFleetTruckRepositoryDecorator
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", Method("save", listOf("S"))),
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", Method("save", listOf("Iterable<S>"))),
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", Method("findOne", listOf("String"))),
            JavaReplaceMethodWithReturnConstant("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", Method("exists", listOf("String")), "false"),
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", Method("findAll")),
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", Method("findAll", listOf("Iterable<String>"))),
            JavaReplaceMethodWithReturnConstant("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", Method("count"), "-1"),
            JavaReplaceMethodWithComment("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", Method("delete", listOf("String")), "Implement me"),
            JavaReplaceMethodWithComment("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", Method("delete", listOf("FleetTruck")), "Implement me"),
            JavaReplaceMethodWithComment("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", Method("delete", listOf("Iterable<? extends FleetTruck>")), "Implement me"),
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

