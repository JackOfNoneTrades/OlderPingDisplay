package org.fentanylsolutions.olderpingdisplay.gui;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;

import org.fentanylsolutions.olderpingdisplay.Config;
import org.fentanylsolutions.olderpingdisplay.OlderPingDisplay;

import com.google.common.collect.ImmutableList;

import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

@SuppressWarnings("unused")
public class GuiFactory implements IModGuiFactory {

    @Override
    public void initialize(Minecraft minecraftInstance) {}

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return ConfigGui.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
        return null;
    }

    public static class ConfigGui extends GuiConfig {

        private static IConfigElement ceGeneral = new ConfigElement(
            Config.getRawConfig()
                .getCategory(Config.Categories.general));

        public ConfigGui(GuiScreen parentScreen) {
            super(
                parentScreen,
                ImmutableList.of(ceGeneral),
                OlderPingDisplay.MODID,
                OlderPingDisplay.MODID,
                false,
                false,
                I18n.format(OlderPingDisplay.MODID + ".configgui.title"));
        }

        @Override
        public void initGui() {
            // You can add buttons and initialize fields here
            super.initGui();
            OlderPingDisplay.LOG.debug("Initializing config gui");
        }

        @Override
        public void drawScreen(int mouseX, int mouseY, float partialTicks) {
            // You can do things like create animations, draw additional elements, etc. here
            super.drawScreen(mouseX, mouseY, partialTicks);
        }

        @Override
        protected void actionPerformed(GuiButton b) {
            OlderPingDisplay.LOG.debug("Config button id " + b.id + " pressed");
            super.actionPerformed(b);
            /* "Done" button */
            /* For this to work, we need to go from category GUI to category selection GUI */
            if (b.id == 2000) {
                /* Syncing config */
                OlderPingDisplay.LOG.debug("Saving config");
                Config.getRawConfig()
                    .save();
                Config.loadConfig(OlderPingDisplay.confFile);
            }
        }
    }
}
