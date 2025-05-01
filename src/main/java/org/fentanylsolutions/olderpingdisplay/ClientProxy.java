package org.fentanylsolutions.olderpingdisplay;

import net.minecraft.client.Minecraft;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {

    public void init(FMLInitializationEvent event) {
        super.init(event);
        Config.registerConfig();
    }

    public void postInit(FMLPostInitializationEvent event) {
        ClientVars.mc = Minecraft.getMinecraft();
        super.postInit(event);
    }
}
