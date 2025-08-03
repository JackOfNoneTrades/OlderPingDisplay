package org.fentanylsolutions.olderpingdisplay;

import java.io.File;
import java.util.Arrays;

import net.minecraftforge.common.config.Configuration;

public class Config {

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

    private static Configuration config;

    // General settings
    public static boolean autoColorPingText;
    public static boolean autoColorPingBars;
    public static boolean renderText;
    public static boolean renderPingBars;
    public static int textColor;
    public static int barColor;
    public static int xOffset;
    public static String pingFormat;
    public static int[] blacklistedPings;
    public static String[] blacklistedRegexes;
    public static String[] blacklistedServerIps;

    public static class Categories {

        public static final String general = "general";
    }

    public static void loadConfig(File file) {
        config = new Configuration(file);

        try {
            config.load();

            autoColorPingText = config
                .get(Categories.general, "autoColorPingText", true, "Automatically color ping text")
                .getBoolean();
            autoColorPingBars = config
                .get(Categories.general, "autoColorPingBars", true, "Automatically color ping bars")
                .getBoolean();
            renderText = config.get(Categories.general, "renderText", true, "Render ping text on screen")
                .getBoolean();
            renderPingBars = config.get(Categories.general, "renderPingBars", false, "Render ping bars on screen")
                .getBoolean();

            textColor = Integer.decode(
                config.get(Categories.general, "textColor", "0xA0A0A0", "Hex color of ping text (e.g. 0xFF0000)")
                    .getString());
            barColor = Integer.decode(
                config.get(Categories.general, "barColor", "0x00E676", "Hex color of ping bars")
                    .getString());

            xOffset = config.get(Categories.general, "xOffset", 0, "Horizontal offset for ping display")
                .getInt();

            pingFormat = config.get(Categories.general, "pingFormat", "%dms", "Format string for ping display")
                .getString();

            blacklistedPings = parseIntArray(
                config.get(Categories.general, "blacklistedPings", new String[] {}, "List of ping values to ignore")
                    .getStringList());
            blacklistedRegexes = config
                .get(
                    Categories.general,
                    "blacklistedRegexes",
                    new String[] {},
                    "List of usernames to ignore (regex supported)")
                .getStringList();
            blacklistedServerIps = config
                .get(Categories.general, "blacklistedServerIps", new String[] {}, "List of server IPs to ignore")
                .getStringList();

        } catch (Exception e) {
            System.err.println("Error loading config: " + e.getMessage());
        } finally {
            config.save();
        }
    }

    private static int[] parseIntArray(String[] strings) {
        return Arrays.stream(strings)
            .map(String::trim)
            .mapToInt(s -> {
                try {
                    return Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    return -1;
                }
            })
            .filter(i -> i >= 0)
            .toArray();
    }

    public static Configuration getRawConfig() {
        return config;
    }
}
