package io.noizwaves.codexform.transformation

import io.noizwaves.codexform.step.Step
import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.toList


class Transformation(private val steps: List<Step>) {
    fun apply(transformDir: Path, workingDir: Path) {
        steps.forEach { applyStep(transformDir, workingDir, it) }
    }

    private fun applyStep(transformDir: Path, workingDir: Path, step: Step) {
        Files.walk(transformDir).toList()
                .stream()
                .filter { Files.isRegularFile(it) }
                .filter(step::appliesTo)
                .forEach {
                    println("> Applying $step")
                    step.applyChange(it, workingDir)
                }
    }
}