package dev.thewlabs.schoolar.core.interfaces;

public interface Serializable<T, S> {
    T serialize();

    S serializeWithDetails();
}
