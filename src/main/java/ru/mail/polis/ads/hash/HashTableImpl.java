package ru.mail.polis.ads.hash;

import java.util.LinkedList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private static class Pair<K, V> {
        public K key;
        public V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final int[] sizes = {223, 457, 907, 1811, 3643, 7309, 14653, 29231,
            58657, 117269, 220009, 440023, 880057};

    private byte sizeIndex = 0;
    private int size = 109;

    private int currSize = 0;
    @SuppressWarnings("unchecked")
    private List<Pair<Key, Value>>[] table = new List[size];

    public HashTableImpl() {
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        List<Pair<Key, Value>> pairs = table[getHash(key)];
        if(pairs == null || pairs.isEmpty()) {
            return null;
        }
        for(Pair<Key, Value> pair : pairs) {
            if(pair.key.equals(key)) {
                return pair.value;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if(currSize >= size * 0.75) {
            rehash();
        }
        int place = getHash(key);
        List<Pair<Key, Value>> pairs = table[place];
        if(pairs == null) {
            pairs = new LinkedList<>();
            table[place] = pairs;
        }
        for(Pair<Key, Value> pair : pairs) {
            if(pair.key.equals(key)) {
                pair.value = value;
                return;
            }
        }
        currSize++;
        pairs.add(new Pair<>(key, value));
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        List<Pair<Key, Value>> pairs = table[getHash(key)];
        if(pairs == null) {
            return null;
        }
        for(Pair<Key, Value> pair : pairs) {
            if(pair.key.equals(key)) {
                currSize--;
                Value value = pair.value;
                pairs.remove(pair);
                return value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return currSize;
    }

    @Override
    public boolean isEmpty() {
        return currSize == 0;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        List<Pair<Key, Value>>[] oldTable = table;
        if(sizeIndex < sizes.length) {
            table = new List[sizes[sizeIndex]];
            size = sizes[sizeIndex];
            sizeIndex++;
        } else {
            table = new List[size * 2];
            size = size * 2;
        }

        for(List<Pair<Key, Value>> list : oldTable) {
            if(list != null) {
                for(Pair<Key, Value> pair : list) {
                    optimizedPut(pair);
                }
            }
        }

    }

    private void optimizedPut(Pair<Key, Value> pair) {
        int place = getHash(pair.key);
        List<Pair<Key, Value>> pairs = table[place];
        if(pairs == null) {
            pairs = new LinkedList<>();
            table[place] = pairs;
        }
        pairs.add(pair);
    }

    private int getHash(Key key) {
        return Math.abs(key.hashCode() % size);
    }
}
