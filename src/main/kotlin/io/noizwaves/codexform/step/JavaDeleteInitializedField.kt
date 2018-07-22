package io.noizwaves.codexform.step

import java.nio.file.Files
import java.nio.file.Path
import java.util.*

class JavaDeleteInitializedField(private val packageName: String, private val className: String, private val field: Field) : Step {
    override fun appliesTo(file: Path): Boolean {
        val lines = Files.readAllLines(file)

        val packageMatch = lines.stream().anyMatch { it == "package $packageName;" }
        val classMatch = lines.stream().anyMatch { it.contains("class $className")}

        return packageMatch && classMatch
    }

    override fun applyChange(file: Path) {
        val lines = Files.readAllLines(file)

        val maybeDeclaredOn = findLineNumDeclaringField(lines, field)
        if (!maybeDeclaredOn.isPresent) {
            throw RuntimeException("`$field` not found in `$className`")
        }

        val declaredOn = maybeDeclaredOn.get()

        val declarationEnds = walkForward(lines, declaredOn)

        val newContents = sequenceOf(
                lines.subList(0, declaredOn),
                lines.subList(declarationEnds + 1, lines.size)
        ).flatten().toList()

        Files.write(file, newContents)

    }

    private fun findLineNumDeclaringField(lines: List<String>, field: Field): Optional<Int> {
        val match = lines.stream()
                .filter(field::isDeclaredOn)
                .findFirst()

        return match
                .map { lines.indexOf(it) }
    }

    private fun walkForward(lines: List<String>, declaredOn: Int): Int {
        // until initialization is finished
        var initializedOn = declaredOn
        while (!lines.get(initializedOn).endsWith(";")) {
            initializedOn++
        }

        // If the line after is empty, delete it as well
        if (lines.get(initializedOn + 1).isBlank()) {
            return initializedOn + 1
        } else {
            return initializedOn
        }
    }

    override fun toString(): String {
        return "DeleteJavaField($packageName, $className, $field)"
    }
}
