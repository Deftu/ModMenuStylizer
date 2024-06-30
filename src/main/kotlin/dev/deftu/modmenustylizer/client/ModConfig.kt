package dev.deftu.modmenustylizer.client

import dev.deftu.omnicore.annotations.GameSide
import dev.deftu.omnicore.annotations.Side
import java.awt.Color

@GameSide(Side.CLIENT)
data class ModConfig(
    val badges: Set<ModBadge>
) {

    @GameSide(Side.CLIENT)
    data class ModBadge(
        val text: String,
        val color: Color,
        val outlineColor: Color
    )

}