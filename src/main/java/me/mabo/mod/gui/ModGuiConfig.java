package me.mabo.mod.gui;

import me.mabo.mod.handlers.ConfigurationHandler;
import me.mabo.mod.util.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ModGuiConfig extends GuiConfig {

    public ModGuiConfig(GuiScreen parentScreen) {
        super(parentScreen, new ConfigElement(ConfigurationHandler.getConfiguration().getCategory(ConfigurationHandler.CATEGORY_MOD)).getChildElements(),
                Reference.MODID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigurationHandler.getConfiguration().toString()));
    }
}
