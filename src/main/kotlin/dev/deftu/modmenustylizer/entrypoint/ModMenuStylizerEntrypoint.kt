package dev.deftu.modmenustylizer.entrypoint

//#if FABRIC
import net.fabricmc.api.ClientModInitializer
//#elseif FORGE
//#if MC >= 1.16.5
//$$ import net.minecraftforge.eventbus.api.IEventBus
//$$ import net.minecraftforge.fml.common.Mod
//$$ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
//$$ import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
//#else
//$$ import net.minecraftforge.fml.common.Mod
//$$ import net.minecraftforge.fml.common.Mod.EventHandler
//$$ import net.minecraftforge.fml.common.event.FMLInitializationEvent
//#endif
//#elseif NEOFORGE
//$$ import net.neoforged.bus.api.IEventBus
//$$ import net.neoforged.fml.common.Mod
//$$ import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
//#endif

import dev.deftu.modmenustylizer.client.ModMenuStylizerClient

//#if FORGE-LIKE
//$$ import dev.deftu.modmenustylizer.ModMenuStylizerConstants
//#if MC >= 1.16.5
//$$ @Mod(ModTemplateConstants.ID)
//#else
//$$ @Mod(modid = ModTemplateConstants.ID, version = ModTemplateConstants.VERSION)
//#endif
//#endif
class ModMenuStylizerEntrypoint
    //#if FABRIC
    : ClientModInitializer
    //#endif
{

    //#if FORGE && MC >= 1.16.5
    //$$ init {
    //$$     setupForgeEvents(FMLJavaModLoadingContext.get().modEventBus)
    //$$ }
    //#elseif NEOFORGE
    //$$ constructor(modEventBus: IEventBus) {
    //$$     setupForgeEvents(modEventBus)
    //$$ }
    //#endif

    //#if FABRIC
    override
    //#endif
    fun onInitializeClient(
        //#if FORGE-LIKE
        //#if MC >= 1.16.5
        //$$ event: FMLClientSetupEvent
        //#else
        //$$ event: FMLInitializationEvent
        //#endif
        //#endif
    ) {
        //#if MC <= 1.12.2
        //$$ if (!event.side.isClient) return
        //#endif

        ModMenuStylizerClient.onInitializeClient()
    }

    //#if FORGE-LIKE && MC >= 1.16.5
    //$$ fun setupForgeEvents(modEventBus: IEventBus) {
    //$$     modEventBus.addListener(this::onInitializeClient)
    //$$ }
    //#endif

}
