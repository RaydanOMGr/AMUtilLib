package me.andreasmelone.amutillib.registry;

import java.util.LinkedList;
import java.util.List;

public class Registry<T extends Registrable> {
    private final String id;
    private final List<RegistryObject<T>> registry = new LinkedList<>();

    private Registry(String id) {
        this.id = id;
    }

    public RegistryObject<T> create(String id, T object) {
        RegistryObject<T> registryObject = new RegistryObject<>(id, object);
        registry.add(registryObject);
        return registryObject;
    }

    public List<RegistryObject<T>> getRegistry() {
        return registry;
    }

    public String getId() {
        return id;
    }

    public static <T extends Registrable> Registry<T> create(String id) {
        return new Registry<>(id);
    }
}
