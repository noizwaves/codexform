package io.noizwaves.codexform.step

class Constructor(private val className: String, parameterTypes: List<String>) : Method(className, parameterTypes) {
    constructor(className: String) : this(className, emptyList())

    override fun toString(): String {
        return "$className()"
    }
}