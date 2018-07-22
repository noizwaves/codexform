package io.noizwaves.codexform.step

class JavaReplaceMethodWithReturnNull(
        private val packageName: String,
        private val className: String,
        private val method: Method
) : JavaReplaceMethodWith(packageName, className, method) {
    override fun substitutedLines() = listOf("return null;")

    override fun toString(): String {
        return "JavaReplaceMethodWithReturnNull($className, $method)"
    }
}
