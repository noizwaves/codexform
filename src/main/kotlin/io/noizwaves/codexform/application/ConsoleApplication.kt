package io.noizwaves.codexform.application

import io.noizwaves.codexform.step.*
import io.noizwaves.codexform.transformation.Transformation
import java.nio.file.Paths

fun main(args: Array<String>) {
    val lab5 = Transformation(listOf(
            // FleetTruck
            JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", Method("FleetTruck", listOf("List<FleetTruckEvent>"))),
            JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", Method("fleetDomainEvents")),
            JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", Method("handleEvent", listOf("FleetTruckEvent"))),
            JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", Method("handleEvent", listOf("FleetTruckPurchased"))),
            JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", Method("handleEvent", listOf("FleetTruckReturnedFromInspection"))),

            // Fleet truck copy lab 4 implementation
            JavaReplaceMethodWithOtherMethodBody(
                    "io.pivotal.pal.wehaul.fleet.domain", "FleetTruck",
                    Method("returnFromInspection", listOf("String", "int")),
                    Paths.get("banana/FleetTruck.java")
            ),
            JavaReplaceMethodWithOtherMethodBody(
                    "io.pivotal.pal.wehaul.fleet.domain", "FleetTruck",
                    Method("sendForInspection"),
                    Paths.get("banana/FleetTruck.java")
            ),
            JavaReplaceMethodWithOtherMethodBody(
                    "io.pivotal.pal.wehaul.fleet.domain", "FleetTruck",
                    Method("removeFromYard"),
                    Paths.get("banana/FleetTruck.java")
            ),
            JavaReplaceMethodWithOtherMethodBody(
                    "io.pivotal.pal.wehaul.fleet.domain", "FleetTruck",
                    Method("returnToYard", listOf("int")),
                    Paths.get("banana/FleetTruck.java")
            ),

            JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", Method("handleEvent", listOf("FleetTruckSentForInspection"))),
            JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", Method("handleEvent", listOf("FleetTruckRemovedFromYard"))),
            JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", Method("handleEvent", listOf("FleetTruckReturnedToYard"))),

            // FleetTruckEventStoreRepositoryAdapter
            JavaDeleteInitializedField("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", Field("objectMapper", "ObjectMapper")),
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

    val transformDir = args[0]
    val workingDir = args[1]
    lab5.apply(Paths.get(transformDir), Paths.get(workingDir))
}

fun showUsage() {
    println("""Usage: java -jar codexform.jar (transform dir) (working dir)""")
}

