package io.noizwaves.codexform.step

import java.util.*
import kotlin.streams.toList

abstract class Method(private val name: String, private val parameterTypes: List<String>) {

    constructor(name: String) : this(name, ArrayList())

    fun isDeclaredOn(line: String): Boolean {
        val nameMatches = line.contains(" $name(")

        val extractedParams = extractParameterTypes(line)
        val parametersMatch = extractedParams.isPresent && extractedParams.get() == parameterTypes

        return nameMatches && parametersMatch
    }

    private fun extractParameterTypes(line: String): Optional<List<String>> {
        val openParen = line.indexOfFirst { it == '(' }
        val closeParen = line.indexOfLast { it == ')' }

        if (openParen == -1 || closeParen == -1 || openParen > closeParen) {
            return Optional.empty()
        }

        val parameters = line.substring(openParen + 1, closeParen)
                .split(", ").stream()
                .filter { it.isNotEmpty() }

        val types = parameters
                .map {
                    val parts = it.split(" ")
                    parts.subList(0, parts.size - 1).joinToString(separator = " ")
                }
                .toList()

        return Optional.of(types)
    }

    override fun toString(): String {
        return name
    }
}