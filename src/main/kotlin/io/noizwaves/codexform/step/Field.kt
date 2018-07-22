package io.noizwaves.codexform.step

class Field(private val name: String, val type: String) {
    fun isDeclaredOn(line: String): Boolean {
        return line.contains("$type $name =")
    }

    override fun toString(): String {
        return name
    }
}