package io.noizwaves.codexform.step

import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.toList

class JavaDeleteImport(
        private val packageName: String,
        private val className: String,
        private val import: String
) : Step {
    override fun appliesTo(file: Path): Boolean {
        val lines = Files.readAllLines(file)

        val packageMatch = lines.stream().anyMatch { it == "package $packageName;" }
        val classMatch = lines.stream().anyMatch { it.contains("class $className")}

        return packageMatch && classMatch
    }

    override fun applyChange(file: Path, workingDir: Path) {
        val lines = Files.readAllLines(file)

        val anticipatedLine = "import $import;"

        lines.contains(anticipatedLine)

        if (!lines.contains(anticipatedLine)) {
            throw RuntimeException("Import of `$import` not found in `$className`")
        }

        val newContents = lines.stream()
                .filter { it != anticipatedLine }
                .toList()

        Files.write(file, newContents)
    }

    override fun toString(): String {
        return "JavaDeleteImport($className, '$import')"
    }


}
