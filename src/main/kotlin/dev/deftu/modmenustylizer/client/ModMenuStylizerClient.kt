package dev.deftu.modmenustylizer.client

import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.terraformersmc.modmenu.util.mod.Mod
import dev.deftu.modmenustylizer.ModMenuStylizerConstants
import dev.deftu.omnicore.annotations.GameSide
import dev.deftu.omnicore.annotations.Side
import net.fabricmc.loader.api.ModContainer
import org.jetbrains.annotations.ApiStatus.Internal
import java.awt.Color
import java.io.File
import java.nio.file.Path

@GameSide(Side.CLIENT)
object ModMenuStylizerClient {

    private val deftuDir by lazy {
        val deftuDir = File("Deftu")
        if (!deftuDir.exists() && !deftuDir.mkdirs()) {
            throw IllegalStateException("Could not create Deftu directory!")
        }

        deftuDir
    }

    private val modDir by lazy {
        val modDir = File(deftuDir, ModMenuStylizerConstants.NAME)
        if (!modDir.exists() && !modDir.mkdirs()) {
            throw IllegalStateException("Could not create Deftu/${ModMenuStylizerConstants.NAME} directory!")
        }

        modDir
    }

    //#if MC <=1.17.1
    //$$ private val jsonParser = JsonParser()
    //#endif
    private val cachedModConfigs = mutableMapOf<String, ModConfig>()

    internal fun onInitializeClient() {
    }

    @Internal
    @JvmStatic
    @GameSide(Side.CLIENT)
    fun getCustomIcon(container: ModContainer): Path? {
        val iconFile = File(modDir, "${container.metadata.id}.png")
        return if (iconFile.exists()) iconFile.toPath() else null
    }

    @Internal
    @JvmStatic
    @GameSide(Side.CLIENT)
    fun getCustomConfig(mod: Mod): ModConfig? {
        if (cachedModConfigs.containsKey(mod.id)) {
            return cachedModConfigs[mod.id]
        }

        val configFile = File(modDir, "${mod.id}.json")
        if (!configFile.exists()) {
            return null
        }

        val badges = mutableSetOf<ModConfig.ModBadge>()

        parseJson(configFile.readText()).let { json ->
            if (!json.isJsonObject) {
                return null
            }

            val obj = json.asJsonObject
            if (obj.has("badges")) {
                val badgesArr = obj.getAsJsonArray("badges")
                badgesArr.forEach { badgeElement ->
                    if (!badgeElement.isJsonObject) {
                        return@forEach
                    }

                    val badgeObj = badgeElement.asJsonObject
                    val text = badgeObj.get("text")?.asString
                    val color = badgeObj.get("color")?.let(::parseColor)
                    val outlineColor = badgeObj.get("outline_color")?.let(::parseColor)

                    if (text != null && color != null) {
                        badges.add(ModConfig.ModBadge(text, color, outlineColor ?: color))
                    }
                }
            }
        }

        return ModConfig(badges).also { config -> cachedModConfigs[mod.id] = config }
    }

    private fun parseJson(json: String): JsonElement {
        //#if MC >= 1.18.2
        return JsonParser.parseString(json)
        //#else
        //$$ return jsonParser.parse(json)
        //#endif
    }

    /**
     * Either checks if the color is an object containing `red`, `green`, and `blue` keys or an integer.
     */
    private fun parseColor(element: JsonElement): Color {
        if (element.isJsonObject) {
            val obj = element.asJsonObject
            val red = obj.get("red")?.asInt
            val green = obj.get("green")?.asInt
            val blue = obj.get("blue")?.asInt

            if (red != null && green != null && blue != null) {
                return Color(red, green, blue)
            }
        } else if (element.isJsonPrimitive) {
            val color = element.asInt
            return Color(color)
        }

        return Color.BLACK
    }

}
