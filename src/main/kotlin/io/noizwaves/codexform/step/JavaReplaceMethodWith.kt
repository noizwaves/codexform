package io.noizwaves.codexform.step

import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.streams.toList

abstract class JavaReplaceMethodWith(
        private val packageName: String,
        private val className: String,
        private val method: Method
) : Step {
    override fun appliesTo(file: Path): Boolean {
        if (file.fileName.toString() != "$className.java") {
            return false
        }

        val lines = Files.readAllLines(file)

        val packageMatch = lines.stream().anyMatch { it == "package $packageName;" }
        val classMatch = lines.stream().anyMatch { it.contains("class $className") }

        return packageMatch && classMatch
    }

    override fun applyChange(file: Path, workingDir: Path) {
        val lines = Files.readAllLines(file)

        val maybeDeclaredOn = findLineNumContainingMethod(lines, method)
        if (!maybeDeclaredOn.isPresent) {
            throw RuntimeException("Method `$method` not found in `$className`")
        }

        val declaredOn = maybeDeclaredOn.get()
        val declarationLine = lines.get(declaredOn)

        val closedOn = findMethodClosureLineNum(lines, declarationLine)

        val newContents = sequenceOf(
                lines.subList(0, declaredOn + 1),
                substitutedLines(workingDir),
                lines.subList(closedOn, lines.size)
        ).flatten().toList()

        Files.write(file, newContents)
    }

    abstract fun substitutedLines(workingDir: Path): List<String>
}

private fun findLineNumContainingMethod(lines: List<String>, method: Method): Optional<Int> {
    val matchingLines = lines.stream()
            .filter(method::isDeclaredOn)
            .toList()

    if (matchingLines.size > 1) {
        throw RuntimeException("Multiple methods matching $method found")
    }

    val matchingLine = matchingLines.stream().findFirst()

    return matchingLine.map { lines.indexOf(it) }
}

private fun findMethodClosureLineNum(lines: List<String>, declaration: String): Int {
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
