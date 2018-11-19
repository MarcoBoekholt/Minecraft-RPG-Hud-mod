package me.mabo.mod;


import me.mabo.mod.gui.GuiExperienceBarInGame;
import me.mabo.mod.handlers.ConfigurationHandler;
import me.mabo.mod.proxy.CommonProxy;
import me.mabo.mod.util.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.logging.Level;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class Main {

    @Mod.Instance
    public static Main instance;

    public static boolean DEV_MODE = true;

    public static final Logger logger = LogManager.getLogger(Reference.MODID);

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        String configDir = event.getModConfigurationDirectory().toString();
        ConfigurationHandler.init(configDir);
        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
        logger.info("preInit Initialized");
    }
    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) { logger.info("init Initialized"); }
    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new GuiExperienceBarInGame());
        logger.info("postInit Initialized");
    }



}
