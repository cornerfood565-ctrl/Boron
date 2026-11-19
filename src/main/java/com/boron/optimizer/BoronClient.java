package com.boron.optimizer;

import com.boron.optimizer.config.BoronConfig;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BoronClient implements ClientModInitializer {
    public static final String MOD_ID = "boron";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        BoronConfig.load();
        LOGGER.info("BORON {} initialized. Performance modules are being brought online in stages.",
                BoronConfig.getVersionLabel());
    }
}
