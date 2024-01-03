package me.andreasmelone.amutillib.registry;

public class RegistryObject<T extends Registrable> {
    private final String id;
    private final T object;

    protected RegistryObject(String id, T object) {
        this.id = id;
        this.object = object;
    }

    public T get() {
        return object;
    }
}