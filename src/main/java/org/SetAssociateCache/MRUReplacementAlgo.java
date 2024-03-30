package org.SetAssociateCache;

public class MRUReplacementAlgo<TKey, TValue> implements IReplacementAlgo<TKey, TValue> {

    public CacheItem<TKey, TValue> evictItem(CacheSet<TKey, TValue> set){
        // MRU remove
        CacheItem<TKey, TValue> evictItem = set.mru.previous;
        return evictItem;
    }
}