package io.noizwaves.codexform.step

import java.nio.file.Path

class JavaReplaceMethodWithComment(
        packageName: String,
        private val className: String,
        private val method: Method,
        private val comment: String
) : JavaReplaceMethodWith(packageName, className, method) {
    override fun substitutedLines(workingDir: Path): List<String> {
        return listOf("// $comment")
    }

    override fun toString(): String {
        return "JavaReplaceMethodWithComment($className, $method)"
    }
}
