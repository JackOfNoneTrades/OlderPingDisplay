package org.fentanylsolutions.olderpingdisplay.mixins;

import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraftforge.client.GuiIngameForge;

import org.fentanylsolutions.olderpingdisplay.ClientVars;
import org.fentanylsolutions.olderpingdisplay.Config;
import org.fentanylsolutions.olderpingdisplay.OlderPingDisplay;
import org.fentanylsolutions.olderpingdisplay.PingColors;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("unused")
@Mixin(GuiIngameForge.class)
public class GuiInGameForgeMixin {

    @Shadow(remap = false)
    private FontRenderer fontrenderer;

    /*
     * Quick note:
     * Inside renderPlayerList is a line that goes:
     * if (pre(PLAYER_LIST)) return;
     * That means if a "pre" event is registered, this won't render at all
     * Normal Forge behavior, but still worth nothing in my opinion, especially
     * if anyone tries to debug this without much knowledge
     */

    private GuiPlayerInfo currentPlayer;

    @Redirect(
        method = "renderPlayerList",
        at = @At(value = "INVOKE", target = "Ljava/util/List;get(I)Ljava/lang/Object;"),
        remap = false)
    protected Object grabCurrentPlayerInLoop(List<GuiPlayerInfo> instance, int i) {
        this.currentPlayer = instance.get(i);
        return this.currentPlayer;
    }

    @Redirect(
        method = "renderPlayerList",
        at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/GuiIngameForge;drawTexturedModalRect(IIIIII)V"))
    protected void drawPlayerPing(GuiIngameForge instance, int x, int y, int textureX, int textureY, int width,
        int height) {
        int ping = currentPlayer.responseTime;
        for (int p : Config.blacklistedPings) {
            if (ping == p) {
                return;
            }
        }
        for (String regex : Config.blacklistedRegexes) {
            System.out.println(regex + " - " + currentPlayer.name);
            if (currentPlayer.name.matches(regex)) {
                return;
            }
        }
        String currentIp = ClientVars.mc.func_147104_D().serverIP;
        for (String ip : Config.blacklistedServerIps) {
            if (currentIp.equals(ip)) {
                return;
            }
        }
        int pingColor = 0;
        if (Config.autoColorPingText && Config.renderText || Config.autoColorPingBars && Config.renderPingBars) {
            pingColor = PingColors.getColor(ping);
        }

        int pingIndex;
        if (ping < 0) {
            pingIndex = 5;
        } else if (ping < 150) {
            pingIndex = 0;
        } else if (ping < 300) {
            pingIndex = 1;
        } else if (ping < 600) {
            pingIndex = 2;
        } else if (ping < 1000) {
            pingIndex = 3;
        } else {
            pingIndex = 4;
        }

        GL11.glColor4f(1f, 1f, 1f, 1f);
        int xOffset = 0;
        if (Config.renderPingBars) {
            xOffset -= 12;
            ClientVars.mc.getTextureManager()
                .bindTexture(OlderPingDisplay.pingBarsRl);
            // background
            ((GuiIngameForge) (Object) this).drawTexturedModalRect(x, y, 0, 48, 10, 8);

            if (pingIndex < 5) {
                float[] rgb = PingColors.hexToGLColor(Config.autoColorPingBars ? pingColor : Config.barColor);
                GL11.glColor4f(rgb[0], rgb[1], rgb[2], 1.0F);
                ((GuiIngameForge) (Object) this).drawTexturedModalRect(x + Config.xOffset, y, 0, pingIndex * 8, 10, 8);
                GL11.glColor4f(1f, 1f, 1f, 1f);
            }
        }
        if (Config.renderText) {
            String drawString = Config.pingFormat.replace("%d", String.valueOf(ping));
            xOffset += -fontrenderer.getStringWidth(drawString) + 10 + Config.xOffset;
            fontrenderer.drawStringWithShadow(
                drawString,
                x + xOffset,
                y,
                Config.autoColorPingText ? pingColor : Config.textColor);
        }
    }
}
