package io.noizwaves.codexform.transformation

import io.noizwaves.codexform.step.Step
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors.toList


class Transformation(private val steps: List<Step>) {
    fun apply(directory: Path) {
        steps.forEach { applyStep(directory, it) }
    }

    private fun applyStep(directory: Path, step: Step) {
        Files.walk(directory).collect(toList())
                .stream()
                .filter { Files.isRegularFile(it) }
                .filter(step::appliesTo)
                .forEach {
                    println("> $it : Applying $step")
                    step.applyChange(it)
                }
    }
}