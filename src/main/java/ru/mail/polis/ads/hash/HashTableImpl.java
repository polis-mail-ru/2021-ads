package ru.mail.polis.ads.hash;

import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private HashMap<Key, Value> map;

    public HashTableImpl() {
        map = new HashMap<>();
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        return map.get(key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        map.put(key,value);
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        map.remove(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }
}
