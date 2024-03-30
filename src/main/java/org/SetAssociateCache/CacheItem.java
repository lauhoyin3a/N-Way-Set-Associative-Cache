package org.SetAssociateCache;

public  class CacheItem<TKey, TValue> {
    public TKey key;
    public TValue value;

    public CacheItem<TKey, TValue> previous;
    public CacheItem<TKey, TValue> next;
    public CacheItem(){

    }
    public CacheItem(TKey key, TValue value) {
        this.key = key;
        this.value = value;
    }
}
