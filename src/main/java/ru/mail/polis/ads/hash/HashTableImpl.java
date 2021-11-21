package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private static final int DEFAULT_SIZE = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private int size = DEFAULT_SIZE;
    private int placedCount;
    private List<Pair<Key, Value>>[] buckets = new List[DEFAULT_SIZE];

    public HashTableImpl() {
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        List<Pair<Key, Value>> keyBucket = buckets[getHashKey(key)];
        if (keyBucket == null) {
            return null;
        }

        Pair<Key, Value> pair = getPairFromBucket(key, keyBucket);
        if (pair != null) {
            return pair.second;
        }

        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if ((double) placedCount / size >= DEFAULT_LOAD_FACTOR) {
            resize();
        }

        int hKey = getHashKey(key);
        if (buckets[hKey] == null) {
            buckets[hKey] = new LinkedList<>();
        }

        Pair<Key, Value> currPair = getPairFromBucket(key, buckets[hKey]);
        if (currPair == null) {
            buckets[hKey].add(new Pair<>(key, value));
            placedCount++;
        } else {
            currPair.second = value;
        }
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        List<Pair<Key, Value>> keyBucket = buckets[getHashKey(key)];
        if (keyBucket == null) {
            return null;
        }

        Pair<Key, Value> pairToDelete = getPairFromBucket(key, keyBucket);
        if (pairToDelete != null) {
            keyBucket.remove(pairToDelete);
            placedCount--;
            return pairToDelete.second;
        }

        return null;
    }

    @Override
    public int size() {
        return placedCount;
    }

    @Override
    public boolean isEmpty() {
        return placedCount == 0;
    }

    private int getHashKey(Object key) {
        int h;
        return ((h = key.hashCode()) ^ (h >>> 16)) & (size - 1);
    }

    private void resize() {
        List<Pair<Key, Value>>[] oldBuckets = buckets;

        size <<= 1;
        buckets = new List[size];

        for (List<Pair<Key, Value>> bucket : oldBuckets) {
            if (bucket == null) {
                continue;
            }

            for (Pair<Key, Value> pair : bucket) {
                int hKey = getHashKey(pair.first);
                if (buckets[hKey] == null) {
                    buckets[hKey] = new LinkedList<>();
                }

                buckets[hKey].add(pair);
            }
        }
    }

    /**
     * Returns:
     * Pair if contains
     * null if !contains
     */
    private Pair<Key, Value> getPairFromBucket(@NotNull Key key, List<Pair<Key, Value>> keyBucket) {
        for (Pair<Key, Value> pair : keyBucket) {
            if (pair.first.equals(key)) {
                return pair;
            }
        }

        return null;
    }

    private static class Pair<K, V> {
        public K first;
        public V second;

        Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }
    }
}