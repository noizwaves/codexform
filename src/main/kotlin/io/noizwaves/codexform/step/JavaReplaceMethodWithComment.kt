package io.noizwaves.codexform.step

class JavaReplaceMethodWithComment(
        private val packageName: String,
        private val className: String,
        private val method: Method,
        private val comment: String
) : JavaReplaceMethodWith(packageName, className, method) {
    override fun substitutedLines(): List<String> {
        return listOf("// $comment")
    }

    override fun toString(): String {
        return "JavaReplaceMethodWithComment($packageName, $className, $method)"
    }
}
