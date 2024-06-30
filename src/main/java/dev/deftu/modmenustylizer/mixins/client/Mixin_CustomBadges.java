package dev.deftu.modmenustylizer.mixins.client;

//#if MC >= 1.20.1
import net.minecraft.client.gui.DrawContext;
//#elseif MC >= 1.16.5
//$$ import net.minecraft.client.util.math.MatrixStack;
//#endif

import com.terraformersmc.modmenu.util.mod.Mod;
import com.terraformersmc.modmenu.util.mod.ModBadgeRenderer;
import dev.deftu.modmenustylizer.client.ModConfig;
import dev.deftu.modmenustylizer.client.ModMenuStylizerClient;
import dev.deftu.textile.VanillaConverter;
import dev.deftu.textile.impl.SimpleText;
import net.minecraft.text.OrderedText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ModBadgeRenderer.class, remap = false)
public abstract class Mixin_CustomBadges {

    @Shadow(remap = false)
    protected Mod mod;

    @Inject(
            method = "draw",
            at = @At("TAIL"),
            remap = false
    )
    private void modmenustylizer$setupCustomBadges(
            //#if MC >= 1.20.1
            DrawContext ctx,
            //#else
            //$$ MatrixStack ctx,
            //#endif
            int mouseX,
            int mouseY,
            CallbackInfo ci
    ) {
        ModConfig config = ModMenuStylizerClient.getCustomConfig(this.mod);
        if (config == null) {
            return;
        }

        config.getBadges().forEach(badge -> drawBadge(
                ctx,
                VanillaConverter.toVanillaText(new SimpleText(badge.getText())).asOrderedText(),
                badge.getOutlineColor().getRGB(),
                badge.getColor().getRGB(),
                mouseX,
                mouseY
        ));
    }

    @Shadow(remap = false)
    public abstract void drawBadge(
            //#if MC >= 1.20.1
            DrawContext ctx,
            //#else
            //$$ MatrixStack ctx,
            //#endif
            OrderedText text,
            int outlineColor,
            int fillColor,
            int mouseX,
            int mouseY
    );

}
