package de.trc.TRC_JoinLeaveMessage;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TRC_JoinLeaveMessage implements ModInitializer {

    public static final String MOD_ID = "TRC_JoinLeaveMessage";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        Config.load();
        LOGGER.info("TRC CustomJoinLeaveMessage aktiviert!");
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
            LOGGER.info("TRC CustomJoinLeaveMessage deaktiviert!");
        });
    }
}
