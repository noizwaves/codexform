package io.noizwaves.codexform.application

import io.noizwaves.codexform.step.InstanceMethod
import io.noizwaves.codexform.step.JavaReplaceFile
import io.noizwaves.codexform.step.JavaReplaceMethodWithComment
import io.noizwaves.codexform.transformation.Transformation
import java.nio.file.Paths

val lab4StartTransformation = Transformation(listOf(
        // Restore controller orchestration from Lab 3
        JavaReplaceFile(
                "io.pivotal.pal.wehaul.controller", "FleetController",
                Paths.get("30-aggregate-design/application/src/main/java/io/pivotal/pal/wehaul/controller/FleetController.java")
        ),
        JavaReplaceFile(
                "io.pivotal.pal.wehaul.service", "FleetService",
                Paths.get("30-aggregate-design/application/src/main/java/io/pivotal/pal/wehaul/service/FleetService.java")
        ),
        JavaReplaceFile(
                "io.pivotal.pal.wehaul.controller", "RentalController",
                Paths.get("30-aggregate-design/application/src/main/java/io/pivotal/pal/wehaul/controller/RentalController.java")
        ),
        JavaReplaceFile(
                "io.pivotal.pal.wehaul.service", "RentalService",
                Paths.get("30-aggregate-design/application/src/main/java/io/pivotal/pal/wehaul/service/FleetService.java")
        ),

        // Zero out event handler implementations
        JavaReplaceMethodWithComment(
                "io.pivotal.pal.wehaul.eventlistener", "FleetEventListener",
                InstanceMethod("onFleetTruckSentForInspection", "FleetTruckSentForInspection"),
                "Implement me"
        ),
        JavaReplaceMethodWithComment(
                "io.pivotal.pal.wehaul.eventlistener", "FleetEventListener",
                InstanceMethod("onFleetTruckReturnedFromInspection", "FleetTruckReturnedFromInspection"),
                "Implement me"
        ),
        JavaReplaceMethodWithComment(
                "io.pivotal.pal.wehaul.eventlistener", "RentalEventListener",
                InstanceMethod("onRentalTruckReserved", "RentalTruckReserved"),
                "Implement me"
        ),
        JavaReplaceMethodWithComment(
                "io.pivotal.pal.wehaul.eventlistener", "RentalEventListener",
                InstanceMethod("onRentalTruckDroppedOff", "RentalTruckDroppedOff"),
                "Implement me"
        )
))