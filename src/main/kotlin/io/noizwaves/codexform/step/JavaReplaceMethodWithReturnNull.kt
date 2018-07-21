package io.noizwaves.codexform.step

class JavaReplaceMethodWithReturnNull(
        private val packageName: String,
        private val className: String,
        private val methodName: String
) : JavaReplaceMethodWith(packageName, className, methodName) {
    override fun substitutedLines() = listOf("return null;")

    override fun toString(): String {
        return "JavaReplaceMethodWithReturnNull($packageName, $className, $methodName)"
    }
}
