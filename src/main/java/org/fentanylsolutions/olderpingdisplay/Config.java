package org.fentanylsolutions.olderpingdisplay;

import java.util.Arrays;

import net.minecraft.util.ResourceLocation;

import carbonconfiglib.CarbonConfig;
import carbonconfiglib.config.ConfigEntry;
import carbonconfiglib.config.ConfigHandler;
import carbonconfiglib.config.ConfigSection;
import carbonconfiglib.impl.entries.ColorValue;

public class Config {

    public static ConfigHandler config;

    private static class Defaults {

        public static final boolean autoColorPingText = true;
        public static final boolean autoColorPingBars = true;
        public static final boolean renderText = true;
        public static final boolean renderPingBars = false;
        public static final int textColor = 0xA0A0A0;
        public static final int barColor = 0x00E676;
        public static final int xOffset = 0;
        public static final String pingFormat = "%dms";
        public static final int[] blacklistedPings = {};
        public static final String[] blacklistedRegexes = {};
        public static final String[] blacklistedServerIps = {};
    }

    public static class Categories {

        public static final String general = "general";
    }

    /* General */
    public static boolean autoColorPingText = Defaults.autoColorPingText;
    public static ConfigEntry.BoolValue autoColorPingTextCE;
    public static boolean autoColorPingBars = Defaults.autoColorPingBars;
    public static ConfigEntry.BoolValue autoColorPingBarsCE;
    public static boolean renderText = Defaults.renderText;
    public static ConfigEntry.BoolValue renderTextCE;
    public static boolean renderPingBars = Defaults.renderPingBars;
    public static ConfigEntry.BoolValue renderPingBarsCE;
    public static int textColor = Defaults.textColor;
    public static ColorValue textColorCE;
    public static int barColor = Defaults.barColor;
    public static ColorValue barColorCE;
    public static int xOffset = Defaults.xOffset;
    public static ConfigEntry.IntValue xOffsetCE;
    public static String pingFormat = Defaults.pingFormat;
    public static ConfigEntry.StringValue pingFormatCE;
    public static int[] blacklistedPings = Defaults.blacklistedPings;
    public static ConfigEntry.ArrayValue blacklistedPingsCE;
    public static String[] blacklistedRegexes = Defaults.blacklistedRegexes;
    public static ConfigEntry.ArrayValue blacklistedRegexesCE;
    public static String[] blacklistedServerIps = Defaults.blacklistedServerIps;
    public static ConfigEntry.ArrayValue blacklistedServerIpsCE;

    public static void registerConfig() {
        carbonconfiglib.config.Config conf = new carbonconfiglib.config.Config(OlderPingDisplay.MODID);

        /* General */
        @SuppressWarnings("unused")
        ConfigSection generalSection = conf.add(Categories.general);

        autoColorPingTextCE = generalSection
            .addBool("autoColorPingText", Defaults.autoColorPingText, "Automatically color ping text");
        autoColorPingBarsCE = generalSection
            .addBool("autoColorPingBars", Defaults.autoColorPingBars, "Automatically color ping bars");
        renderTextCE = generalSection.addBool("renderText", Defaults.renderText, "Render ping text on screen");
        renderPingBarsCE = generalSection
            .addBool("renderPingBars", Defaults.renderPingBars, "Render ping bars on screen");

        textColorCE = generalSection
            .add(new ColorValue("textColor", Defaults.textColor, "Color of the ping text (hex RGB)"));
        barColorCE = generalSection
            .add(new ColorValue("barColor", Defaults.barColor, "Color of the ping bars (hex RGB)"));
        xOffsetCE = generalSection.addInt("xOffset", Defaults.xOffset, "Horizontal offset for ping display");

        pingFormatCE = generalSection
            .addString("pingFormat", Defaults.pingFormat, "Format string for displayed ping (e.g. \"%dms\")");

        String[] blacklistedPingsAsStrings = Arrays.stream(Defaults.blacklistedPings)
            .mapToObj(String::valueOf)
            .toArray(String[]::new);

        blacklistedPingsCE = generalSection
            .addArray("blacklistedPings", blacklistedPingsAsStrings, "List of ping values to ignore");
        blacklistedRegexesCE = generalSection.addArray(
            "blacklistedRegexes",
            Defaults.blacklistedRegexes,
            "List of usernames to ignore (supports regex)");
        blacklistedServerIpsCE = generalSection
            .addArray("blacklistedServerIps", Defaults.blacklistedServerIps, "Server IPs to ignore for ping display");

        config = CarbonConfig.CONFIGS.createConfig(conf);
        config.addLoadedListener(() -> {
            OlderPingDisplay.LOG.debug("Carbon config callback, dumping vars.");
            dumpConf();
        });
        config.register();
    }

    private static void dumpConf() {
        /* General */
        autoColorPingText = autoColorPingTextCE.get();
        autoColorPingBars = autoColorPingBarsCE.get();
        renderText = renderTextCE.get();
        renderPingBars = renderPingBarsCE.get();
        if (renderPingBars) {
            OlderPingDisplay.pingBarsRl = new ResourceLocation(OlderPingDisplay.MODID, "textures/ping_bars.png");
        }
        textColor = textColorCE.get();
        barColor = barColorCE.get();
        xOffset = xOffsetCE.get();
        pingFormat = pingFormatCE.get();
        blacklistedPings = Arrays.stream(blacklistedPingsCE.get())
            .mapToInt(Integer::parseInt)
            .toArray();
        blacklistedRegexes = blacklistedRegexesCE.get();
        blacklistedServerIps = blacklistedServerIpsCE.get();
    }
}
