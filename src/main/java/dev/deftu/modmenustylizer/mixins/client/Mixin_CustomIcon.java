package dev.deftu.modmenustylizer.mixins.client;

//#if MC >= 1.18.2
import com.terraformersmc.modmenu.util.mod.fabric.FabricIconHandler;
//#else
//$$ import com.terraformersmc.modmenu.util.mod.ModIconHandler;
//#endif

import dev.deftu.modmenustylizer.client.ModMenuStylizerClient;
import net.fabricmc.loader.api.ModContainer;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.nio.file.Path;

//#if MC >= 1.18.2
@Mixin(value = FabricIconHandler.class, remap = false)
//#else
//$$ @Mixin(value = ModIconHandler.class, remap = false)
//#endif
public class Mixin_CustomIcon {

    @Redirect(
            method = "createIcon",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/fabricmc/loader/api/ModContainer;getPath(Ljava/lang/String;)Ljava/nio/file/Path;"
            ),
            remap = false
    )
    private Path modmenustylizer$createIcon(ModContainer instance, String iconPath) {
        @Nullable Path path = ModMenuStylizerClient.getCustomIcon(instance);
        //noinspection deprecation
        return path != null ? path : instance.getPath(iconPath);
    }

}
