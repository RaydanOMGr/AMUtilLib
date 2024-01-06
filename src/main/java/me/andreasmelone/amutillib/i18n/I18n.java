package me.andreasmelone.amutillib.i18n;

import me.andreasmelone.amutillib.utils.Util;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class I18n {
    private final JavaPlugin plugin;
    public I18n(JavaPlugin plugin) {
        this.plugin = plugin;
    }


    private String language = "en_us";

    public String get(String key) {
        File file = new File(plugin.getDataFolder(), "langs/" + language + ".yml");
        if(!file.exists()) {
            plugin.saveResource("langs/" + language + ".yml", false);
            return get(key);
        }

        if(!file.exists()) {
            plugin.getLogger().warning("Language file " + language + ".yml does not exist.");
            language = "en_us";
            return get(key);
        }

        FileConfiguration langFile = YamlConfiguration.loadConfiguration(file);
        if(!langFile.isSet(key)) {
            plugin.getLogger().warning("Key " + key + " does not exist in language file " + language + ".yml");
            language = "en_us";
            return key;
        }

        return langFile.getString(key);
    }

    public String get(String key, TranslationKey... keys) {
        String value = get(key);
        for(TranslationKey translationKey : keys) {
            value = value.replace(translationKey.key(), translationKey.value());
        }
        return value;
    }

    public String getTransformed(String key, TranslationKey... keys) {
        return Util.transform(get(key, keys));
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
