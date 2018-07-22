package io.noizwaves.codexform.application

import io.noizwaves.codexform.step.*
import io.noizwaves.codexform.transformation.Transformation
import java.nio.file.Paths

val lab5StartTransformations = Transformation(listOf(
        // FleetTruck
        JavaDeleteImport("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", "java.util.stream.Collectors"),
        JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", Constructor("FleetTruck", listOf("List<FleetTruckEvent>"))),
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", InstanceMethod("fleetDomainEvents")),
        JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", InstanceMethod("handleEvent", listOf("FleetTruckEvent"))),
        JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", InstanceMethod("handleEvent", listOf("FleetTruckPurchased"))),
        JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", InstanceMethod("handleEvent", listOf("FleetTruckReturnedFromInspection"))),
        JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", InstanceMethod("handleEvent", listOf("FleetTruckSentForInspection"))),
        JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", InstanceMethod("handleEvent", listOf("FleetTruckRemovedFromYard"))),
        JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", InstanceMethod("handleEvent", listOf("FleetTruckReturnedToYard"))),

        // Copy FleetTruck implementation from lab 4
        JavaReplaceMethodWithOtherMethodBody(
                "io.pivotal.pal.wehaul.fleet.domain", "FleetTruck",
                InstanceMethod("returnFromInspection", listOf("String", "int")),
                Paths.get("banana/FleetTruck.java")
        ),
        JavaReplaceMethodWithOtherMethodBody(
                "io.pivotal.pal.wehaul.fleet.domain", "FleetTruck",
                InstanceMethod("sendForInspection"),
                Paths.get("banana/FleetTruck.java")
        ),
        JavaReplaceMethodWithOtherMethodBody(
                "io.pivotal.pal.wehaul.fleet.domain", "FleetTruck",
                InstanceMethod("removeFromYard"),
                Paths.get("banana/FleetTruck.java")
        ),
        JavaReplaceMethodWithOtherMethodBody(
                "io.pivotal.pal.wehaul.fleet.domain", "FleetTruck",
                InstanceMethod("returnToYard", listOf("int")),
                Paths.get("banana/FleetTruck.java")
        ),


        // FleetTruckEventStoreRepositoryAdapter
        JavaDeleteImport("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", "com.fasterxml.jackson.core.JsonProcessingException"),
        JavaDeleteImport("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", "com.fasterxml.jackson.databind.ObjectMapper"),
        JavaDeleteImport("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", "com.fasterxml.jackson.databind.SerializationFeature"),
        JavaDeleteImport("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", "io.pivotal.pal.wehaul.event.store.FleetTruckEventStoreEntity"),
        JavaDeleteImport("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", "io.pivotal.pal.wehaul.event.store.FleetTruckEventStoreEntityKey"),
        JavaDeleteImport("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", "io.pivotal.pal.wehaul.fleet.domain.event.FleetTruckEvent"),
        JavaDeleteImport("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", "org.springframework.data.domain.Sort"),
        JavaDeleteImport("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", "java.io.IOException"),
        JavaDeleteImport("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", "java.util.ArrayList"),
        JavaDeleteImport("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", "java.util.Comparator"),
        JavaDeleteImport("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", "java.util.List"),
        JavaDeleteImport("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", "java.util.Map"),
        JavaDeleteImport("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", "java.util.stream.Collectors"),
        JavaDeleteInitializedField("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", Field("objectMapper", "ObjectMapper")),
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", InstanceMethod("save", listOf("S"))),
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", InstanceMethod("findOne", listOf("String"))),
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", InstanceMethod("findAll")),

        // EventPublishingFleetTruckRepositoryDecorator
        JavaDeleteImport("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", "java.util.stream.StreamSupport"),
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("save", listOf("S"))),
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("save", listOf("Iterable<S>"))),
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("findOne", listOf("String"))),
        JavaReplaceMethodWithReturnConstant("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("exists", listOf("String")), "false"),
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("findAll")),
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("findAll", listOf("Iterable<String>"))),
        JavaReplaceMethodWithReturnConstant("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("count"), "-1"),
        JavaReplaceMethodWithComment("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("delete", listOf("String")), "Implement me"),
        JavaReplaceMethodWithComment("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("delete", listOf("FleetTruck")), "Implement me"),
        JavaReplaceMethodWithComment("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("delete", listOf("Iterable<? extends FleetTruck>")), "Implement me"),
        JavaReplaceMethodWithComment("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("deleteAll"), "Implement me")
))