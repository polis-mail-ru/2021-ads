package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private static final double LOAD_FACTOR = 0.75;
    private static final int INITIAL_CAPACITY = 29;

    private List<List<Pair<Key, Value>>> hashTable = new ArrayList<>(INITIAL_CAPACITY);
    private int currentSize;
    private int tableSize = INITIAL_CAPACITY;


    public HashTableImpl() {
        initArray();
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        int hash = key.hashCode() & Integer.MAX_VALUE;
        List<Pair<Key, Value>> list = hashTable.get(hash % tableSize);
        if (list != null) {
            Pair<Key, Value> get = getByHash(list, hash);
            while (get != null) {
                if (get.key.equals(key)) {
                    return get.value;
                }
                get = get.cloneByHash;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        putImpl(key, value, true);
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        int hash = key.hashCode() & Integer.MAX_VALUE;
        int index = hash % tableSize;
        List<Pair<Key, Value>> list = hashTable.get(index);
        Pair<Key, Value> get = (list != null) ? getByHash(list, hash) : null;
        Pair<Key, Value> parent = get;
        while (get != null && !get.key.equals(key)) {
            parent = get;
            if (get.key.equals(key)) {
                return get.value;
            }
            get = get.cloneByHash;
        }
        if (get == null) {
            return null;
        }
        if (parent == get) {
            if (get.cloneByHash == null) {
                Pair<Key, Value> deleted = removeByHash(list, hash);
                currentSize--;
                return deleted.value;
            }
            Value deleted = get.value;
            get.value = get.cloneByHash.value;
            get.key = get.cloneByHash.key;
            get.cloneByHash = get.cloneByHash.cloneByHash;
            currentSize--;
            return deleted;
        }
        parent.cloneByHash = get.cloneByHash;
        currentSize--;
        return get.value;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    private void putImpl(@NotNull Key key, @NotNull Value value, boolean countSize) {
        int hash = key.hashCode() & Integer.MAX_VALUE;
        int index = hash % tableSize;
        List<Pair<Key, Value>> list = hashTable.get(index);
        if (list == null) {
            hashTable.set(index, new LinkedList<>());
            hashTable.get(index).add(new Pair<>(key, value, hash));
            if (countSize) {
                currentSize++;
            }
            return;
        }
        Pair<Key, Value> get = getByHash(list, hash);
        if (get != null) {
            if (get.key.equals(key)) {
                get.value = value;
                return;
            }
            //find equals in clones
            while (get.cloneByHash != null) {
                if (get.key.equals(key)) {
                    get.value = value;
                    return;
                }
                get = get.cloneByHash;
            }
            get.cloneByHash = new Pair<>(key, value, hash);
            if (countSize) {
                currentSize++;
            }
            return;
        }
        list.add(new Pair<>(key, value, hash));
        if (countSize) {
            currentSize++;
        }
        resize();
    }

    private double getCurrentLoadFactor() {
        return (double) currentSize / tableSize;
    }

    private void resize() {
        if (getCurrentLoadFactor() < LOAD_FACTOR) {
            return;
        }
        int oldSize = tableSize;
        tableSize = oldSize * 2;
        List<List<Pair<Key, Value>>> oldHash = hashTable;
        hashTable = new ArrayList<>(tableSize);
        initArray();

        for (List<Pair<Key, Value>> list : oldHash) {
            if (list == null) {
                continue;
            }
            for (Pair<Key, Value> element : list) {
                Pair<Key, Value> get = element;
                putImpl(element.key, element.value, false);
                while (get.cloneByHash != null) {
                    get = get.cloneByHash;
                    putImpl(get.key, get.value, false);
                }
            }
        }
    }

    private void initArray() {
        for (int i = 0; i < tableSize; i++) {
            hashTable.add(null);
        }
    }

    private Pair<Key, Value> getByHash(List<Pair<Key, Value>> list, int hash){
        for (Pair<Key, Value> pair: list) {
            if (pair.valueHashcode == hash) {
                return pair;
            }
        }
        return null;
    }

    private Pair<Key, Value> removeByHash(List<Pair<Key, Value>> list, int hash){
        for (Pair<Key, Value> pair: list) {
            if (pair.valueHashcode == hash) {
                list.remove(pair);
                return pair;
            }
        }
        return null;
    }

    private static class Pair<Key, Value> {
        Key key;
        Value value;
        Pair<Key, Value> cloneByHash;
        int valueHashcode;

        Pair(Key key, Value value, int hashcode) {
            this.key = key;
            this.value = value;
            valueHashcode = hashcode;
        }
    }
}
