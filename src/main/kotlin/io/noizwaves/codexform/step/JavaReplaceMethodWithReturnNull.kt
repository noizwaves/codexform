package io.noizwaves.codexform.step

import java.nio.file.Path

class JavaReplaceMethodWithReturnNull(
        packageName: String,
        private val className: String,
        private val method: Method
) : JavaReplaceMethodWith(packageName, className, method) {
    override fun substitutedLines(workingDir: Path) = listOf("return null;")

    override fun toString(): String {
        return "JavaReplaceMethodWithReturnNull($className, $method)"
    }
}
