package me.mabo.mod.gui;

import me.mabo.mod.Main;
import me.mabo.mod.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class GuiExperienceBarInGame extends Gui {

    Minecraft mc = Minecraft.getMinecraft();
    private final ResourceLocation bar = new ResourceLocation(Reference.MODID, "textures/gui/expbar2.png");
    private final int bar_width = 137, bar_height = 29, xpbar_width = 107, xpbar_height = 13, hpbar_width = 107, hpbar_height = 13;

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE || event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
            if(event.getType().equals(RenderGameOverlayEvent.ElementType.HEALTH) || event.getType().equals(RenderGameOverlayEvent.ElementType.EXPERIENCE) && event.isCancelable()) {
                event.setCanceled(true);
            }
            //Get the XP from the player and calculate the width based on current xp amount
            float xpBar = (float)xpbar_width / 1.0f;
            int currentXPBarWidth = (int)(xpBar * mc.player.experience);

            //Get the hp from the player and calculate the width based on current health
            float hpBar = (float)hpbar_width / mc.player.getMaxHealth();
            int currentHPBarWidth = (int)(hpBar * mc.player.getHealth());

            //Draw the XP bar background and filled part
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            mc.renderEngine.bindTexture(bar);
            drawTexturedModalRect(10, 5, 0, 0, bar_width, bar_height); //bar background
            drawTexturedModalRect(39, 6, 29, 29, currentHPBarWidth, hpbar_height); //bar health
            drawTexturedModalRect(39, 20, 29, 42, currentXPBarWidth, xpbar_height); //bar xp
            GL11.glDisable(GL11.GL_BLEND);

        }
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            //RENDER TEXT
            int nextLVL = mc.player.experienceLevel + 1;
            int xpMax = 0;
            for (int i = 0; i < nextLVL; i++) {
                xpMax =
                        i >= 31 ? (9 * i - 158) :
                                i >= 16 ? (5 * i - 38) :
                                        2 * i + 7;
            }
            //Get current xp by multiplying the next XP amount with the experience (double)
            double currentXP = xpMax * mc.player.experience;

            //Draw XP strings (info)
            //drawString(FMLClientHandler.instance().getClient().fontRenderer, Integer.toString(mc.player.experienceLevel), 5, 12, 0xffffff); //Current XP LEVEL
            drawString(FMLClientHandler.instance().getClient().fontRenderer, Integer.toString((int) currentXP) + " / " + Integer.toString(xpMax), 68, 23, 0xffffff); //XP bar text
            drawString(FMLClientHandler.instance().getClient().fontRenderer, Integer.toString((int) mc.player.getHealth()) + " / " + Integer.toString((int) mc.player.getMaxHealth()), 68, 9, 0xffffff); //HP bar text

            //drawString(FMLClientHandler.instance().getClient().fontRenderer, String.valueOf(mc.player.getTotalArmorValue()), 200, 200, 0xffffff); //Draw armor level
        }
    }
}

