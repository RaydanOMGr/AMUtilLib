package me.andreasmelone.amutillib.registry;

public class RegisteredObject<T extends Registrable> {
    private final T object;
    protected RegisteredObject(T object) {
        this.object = object;
    }

    public T get() {
        return object;
    }
}
