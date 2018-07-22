package io.noizwaves.codexform.step

import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.streams.toList

class JavaReplaceMethodWithOtherMethodBody(
        targetPackageName: String,
        private val targetClassName: String,
        private val method: Method,
        private val sourceFile: Path
) : JavaReplaceMethodWith(targetPackageName, targetClassName, method) {
    override fun substitutedLines(workingDir: Path): List<String> {
        val lines = Files.readAllLines(workingDir.resolve(sourceFile))

        val maybeDeclaredOn = findLineNumContainingMethod(lines, method)
        if (!maybeDeclaredOn.isPresent) {
            throw RuntimeException("Method `$method` not found in `$sourceFile`")
        }

        val declaredOn = maybeDeclaredOn.get()
        val declarationLine = lines.get(declaredOn)

        val closedOn = findMethodClosureLineNum(lines, declarationLine)

        // Assuming single line signature
        val bodyStartsAt = declaredOn + 1
        val bodyEndsAt = closedOn - 1

        return lines.subList(bodyStartsAt, bodyEndsAt + 1)
    }

    override fun toString(): String {
        return "JavaReplaceMethodWithOtherMethodBody($targetClassName, $method, '$sourceFile')"
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
