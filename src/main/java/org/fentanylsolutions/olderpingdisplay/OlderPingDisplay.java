package org.fentanylsolutions.olderpingdisplay;

import net.minecraft.util.ResourceLocation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod(
    modid = OlderPingDisplay.MODID,
    version = Tags.VERSION,
    name = "Numerical Tab Ping",
    acceptedMinecraftVersions = "[1.7.10]",
    acceptableRemoteVersions = "*",
    dependencies = "required-after:unimixins;required-after:carbonconfig;")
public class OlderPingDisplay {

    public static final String MODID = "olderpingdisplay";
    public static final Logger LOG = LogManager.getLogger(MODID);

    public static ResourceLocation pingBarsRl;

    @SidedProxy(
        clientSide = "org.fentanylsolutions.olderpingdisplay.ClientProxy",
        serverSide = "org.fentanylsolutions.olderpingdisplay.CommonProxy")
    public static CommonProxy proxy;

    @SuppressWarnings("unused")
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @SuppressWarnings("unused")
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
