package io.noizwaves.codexform.step

import java.nio.file.Files
import java.nio.file.Path

class JavaReplaceFile(
        private val packageName: String,
        private val className: String,
        private val source: Path
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

    override fun applyChange(target: Path, workingDir: Path) {
        Files.deleteIfExists(target)
        Files.copy(workingDir.resolve(source), target)
    }

    override fun toString(): String {
        return "JavaReplaceFile($className, '$source')"
    }
}
