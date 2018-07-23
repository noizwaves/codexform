package io.noizwaves.codexform.step

class Constructor(private val className: String, vararg parameterTypes: String) : Method(className, parameterTypes.asList()) {

    override fun toString(): String {
        return "$className()"
    }
}