package de.trc.TRC_JoinLeaveMessage;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TRC_JoinLeaveMessage.MOD_ID)
public class TRC_JoinLeaveMessage {

    public static final String MOD_ID = "trc_joinleavemessage";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public TRC_JoinLeaveMessage(FMLJavaModLoadingContext context) {

        Config.load();
        LOGGER.info("TRC CustomJoinLeaveMessage aktiviert!");

        var modBusGroup = context.getModBusGroup();

        FMLCommonSetupEvent
                .getBus(modBusGroup)
                .addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }
}
