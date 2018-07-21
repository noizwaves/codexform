package io.noizwaves.codexform.step

class JavaReplaceMethodWithComment(
        private val packageName: String,
        private val className: String,
        private val methodName: String,
        private val comment: String
) : JavaReplaceMethodWith(packageName, className, methodName) {
    override fun substitutedLines(): List<String> {
        return listOf("// $comment")
    }

    override fun toString(): String {
        return "JavaReplaceMethodWithComment($packageName, $className, $methodName)"
    }
}
