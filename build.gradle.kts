import com.modrinth.minotaur.dependencies.DependencyType
import com.modrinth.minotaur.dependencies.ModDependency
import dev.deftu.gradle.utils.GameSide

plugins {
    java
    kotlin("jvm")
    id("dev.deftu.gradle.multiversion")
    id("dev.deftu.gradle.tools")
    id("dev.deftu.gradle.tools.resources")
    id("dev.deftu.gradle.tools.bloom")
    id("dev.deftu.gradle.tools.minecraft.loom")
    id("dev.deftu.gradle.tools.minecraft.releases")
}

toolkitMultiversion {
    moveBuildsToRootProject.set(true)
}

toolkitLoomHelper {
    disableRunConfigs(GameSide.SERVER)
}

toolkitReleases {
    modrinth {
        projectId.set("f2ohSWZi")

        dependencies.add(ModDependency("Ha28R6CL", DependencyType.REQUIRED)) // Fabric Language Kotlin
        dependencies.add(ModDependency("mOgUt4GM", DependencyType.REQUIRED)) // Mod Menu
        dependencies.add(ModDependency("T0Zb6DLv", DependencyType.REQUIRED)) // Textile
    }
}

dependencies {
    val textileVersion = "0.3.1"
    val omnicoreVersion = "0.6.0"
    modImplementation("dev.deftu:textile-$mcData:$textileVersion")
    modImplementation("dev.deftu:omnicore-$mcData:$omnicoreVersion")

    modImplementation("net.fabricmc.fabric-api:fabric-api:${mcData.dependencies.fabric.fabricApiVersion}")
    modImplementation("net.fabricmc:fabric-language-kotlin:${mcData.dependencies.fabric.fabricLanguageKotlinVersion}")
    modImplementation(mcData.dependencies.fabric.modMenuDependency)
}
