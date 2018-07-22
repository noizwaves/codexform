package io.noizwaves.codexform.step

class InstanceMethod(name: String, parameterTypes: List<String>) : Method(name, parameterTypes) {
    constructor(name: String) : this(name, emptyList())
}