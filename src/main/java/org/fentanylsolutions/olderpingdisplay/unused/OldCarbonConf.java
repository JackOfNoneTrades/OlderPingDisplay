package org.fentanylsolutions.olderpingdisplay.unused;

@Deprecated
public class OldCarbonConf {
    /*
     * public static ConfigHandler config;
     * private static class Defaults {
     * public static final boolean autoColorPingText = true;
     * public static final boolean autoColorPingBars = true;
     * public static final boolean renderText = true;
     * public static final boolean renderPingBars = false;
     * public static final int textColor = 0xA0A0A0;
     * public static final int barColor = 0x00E676;
     * public static final int xOffset = 0;
     * public static final String pingFormat = "%dms";
     * public static final int[] blacklistedPings = {};
     * public static final String[] blacklistedRegexes = {};
     * public static final String[] blacklistedServerIps = {};
     * }
     * public static class Categories {
     * public static final String general = "general";
     * }
     * public static boolean autoColorPingText = Config.Defaults.autoColorPingText;
     * public static ConfigEntry.BoolValue autoColorPingTextCE;
     * public static boolean autoColorPingBars = Config.Defaults.autoColorPingBars;
     * public static ConfigEntry.BoolValue autoColorPingBarsCE;
     * public static boolean renderText = Config.Defaults.renderText;
     * public static ConfigEntry.BoolValue renderTextCE;
     * public static boolean renderPingBars = Config.Defaults.renderPingBars;
     * public static ConfigEntry.BoolValue renderPingBarsCE;
     * public static int textColor = Config.Defaults.textColor;
     * public static ColorValue textColorCE;
     * public static int barColor = Config.Defaults.barColor;
     * public static ColorValue barColorCE;
     * public static int xOffset = Config.Defaults.xOffset;
     * public static ConfigEntry.IntValue xOffsetCE;
     * public static String pingFormat = Config.Defaults.pingFormat;
     * public static ConfigEntry.StringValue pingFormatCE;
     * public static int[] blacklistedPings = Config.Defaults.blacklistedPings;
     * public static ConfigEntry.ArrayValue blacklistedPingsCE;
     * public static String[] blacklistedRegexes = Config.Defaults.blacklistedRegexes;
     * public static ConfigEntry.ArrayValue blacklistedRegexesCE;
     * public static String[] blacklistedServerIps = Config.Defaults.blacklistedServerIps;
     * public static ConfigEntry.ArrayValue blacklistedServerIpsCE;
     * public static void registerConfig() {
     * carbonconfiglib.config.Config conf = new carbonconfiglib.config.Config(OlderPingDisplay.MODID);
     * @SuppressWarnings("unused")
     * ConfigSection generalSection = conf.add(Config.Categories.general);
     * autoColorPingTextCE = generalSection
     * .addBool("autoColorPingText", Config.Defaults.autoColorPingText, "Automatically color ping text");
     * autoColorPingBarsCE = generalSection
     * .addBool("autoColorPingBars", Config.Defaults.autoColorPingBars, "Automatically color ping bars");
     * renderTextCE = generalSection.addBool("renderText", Config.Defaults.renderText, "Render ping text on screen");
     * renderPingBarsCE = generalSection
     * .addBool("renderPingBars", Config.Defaults.renderPingBars, "Render ping bars on screen");
     * textColorCE = generalSection
     * .add(new ColorValue("textColor", Config.Defaults.textColor, "Color of the ping text (hex RGB)"));
     * barColorCE = generalSection
     * .add(new ColorValue("barColor", Config.Defaults.barColor, "Color of the ping bars (hex RGB)"));
     * xOffsetCE = generalSection.addInt("xOffset", Config.Defaults.xOffset, "Horizontal offset for ping display");
     * pingFormatCE = generalSection.addString(
     * "pingFormat",
     * Config.Defaults.pingFormat,
     * "Format string for displayed ping. %d gets replaced by the ping value");
     * String[] blacklistedPingsAsStrings = Arrays.stream(Config.Defaults.blacklistedPings)
     * .mapToObj(String::valueOf)
     * .toArray(String[]::new);
     * blacklistedPingsCE = generalSection
     * .addArray("blacklistedPings", blacklistedPingsAsStrings, "List of ping values to ignore");
     * blacklistedRegexesCE = generalSection.addArray(
     * "blacklistedRegexes",
     * Config.Defaults.blacklistedRegexes,
     * "List of usernames to ignore (supports regex)");
     * blacklistedServerIpsCE = generalSection
     * .addArray("blacklistedServerIps", Config.Defaults.blacklistedServerIps, "Server IPs to ignore for ping display");
     * config = CarbonConfig.CONFIGS.createConfig(conf);
     * config.addLoadedListener(() -> {
     * OlderPingDisplay.LOG.debug("Carbon config callback, dumping vars.");
     * dumpConf();
     * });
     * config.register();
     * }
     * private static void dumpConf() {
     * autoColorPingText = autoColorPingTextCE.get();
     * autoColorPingBars = autoColorPingBarsCE.get();
     * renderText = renderTextCE.get();
     * renderPingBars = renderPingBarsCE.get();
     * if (renderPingBars) {
     * OlderPingDisplay.pingBarsRl = new ResourceLocation(OlderPingDisplay.MODID, "textures/ping_bars.png");
     * }
     * textColor = textColorCE.get();
     * barColor = barColorCE.get();
     * xOffset = xOffsetCE.get();
     * pingFormat = pingFormatCE.get();
     * blacklistedPings = Arrays.stream(blacklistedPingsCE.get())
     * .mapToInt(Integer::parseInt)
     * .toArray();
     * // TODO: it actually just comma splits a string that looks like this: [a, b]...
     * blacklistedRegexes = blacklistedRegexesCE.get();
     * blacklistedServerIps = blacklistedServerIpsCE.get();
     * }
     */
}
