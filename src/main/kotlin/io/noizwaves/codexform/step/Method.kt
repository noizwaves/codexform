package io.noizwaves.codexform.step

class Method(private val name: String) {
    fun isDeclaredOn(line : String) : Boolean {
        return line.contains("$name(")
    }

    override fun toString(): String {
        return name;
    }
}