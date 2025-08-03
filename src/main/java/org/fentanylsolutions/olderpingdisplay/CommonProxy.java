package org.fentanylsolutions.olderpingdisplay;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        OlderPingDisplay.LOG.info(OlderPingDisplay.MODID + " version " + Tags.VERSION);
        Config.loadConfig(OlderPingDisplay.confFile);
    }

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {}
}
