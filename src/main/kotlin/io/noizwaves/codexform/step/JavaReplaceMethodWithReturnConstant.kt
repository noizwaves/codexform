package io.noizwaves.codexform.step

class JavaReplaceMethodWithReturnConstant(
        private val packageName: String,
        private val className: String,
        private val method: Method,
        private val constant: String
) : JavaReplaceMethodWith(packageName, className, method) {
    override fun substitutedLines(): List<String> {
        return listOf("return $constant;")
    }

    override fun toString(): String {
        return "JavaReplaceMethodWithReturnConstant($className, $method)"
    }
}
