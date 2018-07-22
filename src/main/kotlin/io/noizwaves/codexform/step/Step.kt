package io.noizwaves.codexform.step

import java.nio.file.Path

interface Step {
    fun appliesTo(file: Path) : Boolean

    fun applyChange(file: Path, workingDir: Path)
}