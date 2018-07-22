package io.noizwaves.codexform.step

import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.streams.toList

class JavaDeleteMethod(private val packageName: String, private val className: String, private val method: Method) : Step {
    override fun appliesTo(file: Path): Boolean {
        val lines = Files.readAllLines(file)

        val packageMatch = lines.stream().anyMatch { it == "package $packageName;" }
        val classMatch = lines.stream().anyMatch { it.contains("class $className")}

        return packageMatch && classMatch
    }

    override fun applyChange(file: Path) {
        val lines = Files.readAllLines(file)

        val maybeDeclaredOn = findLineNumContainingMethod(lines, method)
        if (!maybeDeclaredOn.isPresent) {
            throw RuntimeException("Method `$method` not found in `$className`")
        }

        val declaredOn = maybeDeclaredOn.get()
        val closedOn = walkForward(lines, declaredOn)

        val newContents = sequenceOf(
                lines.subList(0, declaredOn),
                lines.subList(closedOn + 1, lines.size)
        ).flatten().toList()

        Files.write(file, newContents)
    }

    private fun walkForward(lines: List<String>, declaredOn: Int): Int {
        // until initialization is finished
        var closingLine = extractIndentation(lines[declaredOn]) + "}"

        var closedOn = declaredOn
        while (!lines.get(closedOn).equals(closingLine)) {
            closedOn++
        }

        // If the line after is empty, delete it as well
        if (lines.get(closedOn + 1).isBlank()) {
            return closedOn + 1
        } else {
            return closedOn
        }
    }

    private fun extractIndentation(line : String) : String {
        var indentation = ""
        for (char in line) {
            if (char.isWhitespace()) {
                indentation += char
            } else {
                break;
            }
        }

        return indentation
    }

    override fun toString(): String {
        return "DeleteJavaMethod($packageName, $className, $method)"
    }
}

private fun findLineNumContainingMethod(lines: List<String>, method: Method) : Optional<Int> {
    val matchingLines = lines.stream()
            .filter(method::isDeclaredOn)
            .toList()

    if (matchingLines.size > 1) {
        throw RuntimeException("Multiple methods matching $method found")
    }

    val matchingLine = matchingLines.stream().findFirst()

    return matchingLine.map { lines.indexOf(it) }
}