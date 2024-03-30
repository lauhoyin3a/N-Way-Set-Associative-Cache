package org.SetAssociateCache;

public class LRUReplacementAlgo<TKey, TValue> implements IReplacementAlgo<TKey, TValue> {

    public CacheItem<TKey, TValue> evictItem(CacheSet<TKey, TValue> set){
        // LRU remove
        CacheItem<TKey, TValue> evictItem = set.lru.next;
        return evictItem;
    }


}