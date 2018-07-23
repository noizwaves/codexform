package io.noizwaves.codexform.application

import io.noizwaves.codexform.step.*
import io.noizwaves.codexform.transformation.Transformation
import java.nio.file.Paths

val lab5StartTransformation = Transformation(listOf(
        // FleetTruck
        JavaDeleteImport("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", "java.util.stream.Collectors"),
        JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", Constructor("FleetTruck", "List<FleetTruckEvent>")),
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", InstanceMethod("fleetDomainEvents")),
        JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", InstanceMethod("handleEvent", "FleetTruckEvent")),
        JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", InstanceMethod("handleEvent", "FleetTruckPurchased")),
        JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", InstanceMethod("handleEvent", "FleetTruckReturnedFromInspection")),
        JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", InstanceMethod("handleEvent", "FleetTruckSentForInspection")),
        JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", InstanceMethod("handleEvent", "FleetTruckRemovedFromYard")),
        JavaDeleteMethod("io.pivotal.pal.wehaul.fleet.domain", "FleetTruck", InstanceMethod("handleEvent", "FleetTruckReturnedToYard")),

        // Copy FleetTruck implementation from lab 4
        JavaReplaceMethodWithOtherMethodBody(
                "io.pivotal.pal.wehaul.fleet.domain", "FleetTruck",
                InstanceMethod("returnFromInspection", "String", "int"),
                Paths.get("40-event-collaboration/fleet/src/main/java/io/pivotal/pal/wehaul/fleet/domain/FleetTruck.java")
        ),
        JavaReplaceMethodWithOtherMethodBody(
                "io.pivotal.pal.wehaul.fleet.domain", "FleetTruck",
                InstanceMethod("sendForInspection"),
                Paths.get("40-event-collaboration/fleet/src/main/java/io/pivotal/pal/wehaul/fleet/domain/FleetTruck.java")
        ),
        JavaReplaceMethodWithOtherMethodBody(
                "io.pivotal.pal.wehaul.fleet.domain", "FleetTruck",
                InstanceMethod("removeFromYard"),
                Paths.get("40-event-collaboration/fleet/src/main/java/io/pivotal/pal/wehaul/fleet/domain/FleetTruck.java")
        ),
        JavaReplaceMethodWithOtherMethodBody(
                "io.pivotal.pal.wehaul.fleet.domain", "FleetTruck",
                InstanceMethod("returnToYard", "int"),
                Paths.get("40-event-collaboration/fleet/src/main/java/io/pivotal/pal/wehaul/fleet/domain/FleetTruck.java")
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
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", InstanceMethod("save", "S")),
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", InstanceMethod("findOne", "String")),
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "FleetTruckEventStoreRepositoryAdapter", InstanceMethod("findAll")),

        // EventPublishingFleetTruckRepositoryDecorator
        JavaDeleteImport("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", "java.util.stream.StreamSupport"),
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("save", "S")),
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("save", "Iterable<S>")),
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("findOne", "String")),
        JavaReplaceMethodWithReturnConstant("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("exists", "String"), "false"),
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("findAll")),
        JavaReplaceMethodWithReturnNull("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("findAll", "Iterable<String>")),
        JavaReplaceMethodWithReturnConstant("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("count"), "-1"),
        JavaReplaceMethodWithComment("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("delete", "String"), "Implement me"),
        JavaReplaceMethodWithComment("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("delete", "FleetTruck"), "Implement me"),
        JavaReplaceMethodWithComment("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("delete", "Iterable<? extends FleetTruck>"), "Implement me"),
        JavaReplaceMethodWithComment("io.pivotal.pal.wehaul.adapter", "EventPublishingFleetTruckRepositoryDecorator", InstanceMethod("deleteAll"), "Implement me")
))