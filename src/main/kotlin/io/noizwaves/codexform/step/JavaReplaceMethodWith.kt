package io.noizwaves.codexform.step

import java.nio.file.Files
import java.nio.file.Path
import java.util.*

abstract class JavaReplaceMethodWith(
        private val packageName: String,
        private val className: String,
        private val methodName: String
) : Step {
    override fun appliesTo(file: Path): Boolean {
        val lines = Files.readAllLines(file)

        val packageMatch = lines.stream().anyMatch { it == "package $packageName;" }
        val classMatch = lines.stream().anyMatch { it.contains("class $className")}

        return packageMatch and classMatch
    }

    override fun applyChange(file: Path) {
        val lines = Files.readAllLines(file)

        val maybeDeclaredOn = findLineNumContainingString(lines, "$methodName(")
        if (!maybeDeclaredOn.isPresent) {
            throw RuntimeException("Method `$methodName` not found in `$className`")
        }

        val declaredOn = maybeDeclaredOn.get()
        val declarationLine = lines.get(declaredOn)

        val closedOn = findMethodClosureLineNum(lines, declarationLine)

        val newContents = sequenceOf(
                lines.subList(0, declaredOn + 1),
                substitutedLines(),
                lines.subList(closedOn, lines.size - 1)
        ).flatten().toList()

        Files.write(file, newContents)
    }

    abstract fun substitutedLines(): List<String>
}

private fun findLineNumContainingString(lines: List<String>, content: String) : Optional<Int> {
    val matchingLine = lines.stream()
            .filter { it.contains(content) }
            .findFirst()

    return matchingLine.map { lines.indexOf(it) }
}

private fun findMethodClosureLineNum(lines: List<String>, declaration: String) : Int {
    // determine declaration indentation
    var indentationSize = 0
    while (declaration.get(indentationSize) == ' ') {
        indentationSize += 1
    }

    val declaredOn = lines.indexOf(declaration)
    val candidates = lines.subList(declaredOn + 1, lines.size - 1)

    // iterate from declaration to find methods closing closure
    val indentation = " ".repeat(indentationSize) + "}"

    return candidates.indexOfFirst { it == indentation } + declaredOn + 1
}