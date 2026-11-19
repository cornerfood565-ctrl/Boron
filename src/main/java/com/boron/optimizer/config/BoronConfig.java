package com.boron.optimizer.config;

import com.boron.optimizer.BoronClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class BoronConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH =
            FabricLoader.getInstance().getConfigDir().resolve("boron.json");

    private static boolean entityCulling = true;
    private static boolean blockEntityCulling = true;
    private static boolean particleOptimization = true;
    private static boolean chunkBuildScheduling = true;

    private BoronConfig() {
    }

    public static void load() {
        if (!Files.exists(CONFIG_PATH)) {
            save();
            return;
        }

        try (var reader = Files.newBufferedReader(CONFIG_PATH)) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            entityCulling = readBoolean(json, "entityCulling", entityCulling);
            blockEntityCulling = readBoolean(json, "blockEntityCulling", blockEntityCulling);
            particleOptimization = readBoolean(json, "particleOptimization", particleOptimization);
            chunkBuildScheduling = readBoolean(json, "chunkBuildScheduling", chunkBuildScheduling);
        } catch (Exception exception) {
            BoronClient.LOGGER.warn("Could not read BORON config; using safe defaults.", exception);
            save();
        }
    }

    public static void save() {
        JsonObject json = new JsonObject();
        json.addProperty("entityCulling", entityCulling);
        json.addProperty("blockEntityCulling", blockEntityCulling);
        json.addProperty("particleOptimization", particleOptimization);
        json.addProperty("chunkBuildScheduling", chunkBuildScheduling);

        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (var writer = Files.newBufferedWriter(CONFIG_PATH)) {
                GSON.toJson(json, writer);
            }
        } catch (IOException exception) {
            BoronClient.LOGGER.error("Could not save BORON config.", exception);
        }
    }

    private static boolean readBoolean(JsonObject json, String key, boolean fallback) {
        return json.has(key) && !json.get(key).isJsonNull()
                ? json.get(key).getAsBoolean()
                : fallback;
    }

    public static boolean isEntityCullingEnabled() {
        return entityCulling;
    }

    public static boolean isBlockEntityCullingEnabled() {
        return blockEntityCulling;
    }

    public static boolean isParticleOptimizationEnabled() {
        return particleOptimization;
    }

    public static boolean isChunkBuildSchedulingEnabled() {
        return chunkBuildScheduling;
    }

    public static String getVersionLabel() {
        return "0.1.0";
    }
}
