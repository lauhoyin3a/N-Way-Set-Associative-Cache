package org.SetAssociateCache;

public  interface IReplacementAlgo<TKey, TValue> {
    // TODO: Define the interface for replacement algos...
    CacheItem<TKey, TValue> evictItem(CacheSet<TKey, TValue> set);

}
