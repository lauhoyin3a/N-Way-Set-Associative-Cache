package org.SetAssociateCache;

import java.util.HashMap;

public class CacheSet<TKey, TValue> {
    int Capacity;

    HashMap<TKey, CacheItem<TKey, TValue>> Store;

    CacheItem<TKey, TValue> lru;
    CacheItem<TKey, TValue> mru;
    //LinkedList<TKey> UsageTracker;
    public int Count;
    public CacheSet(int capacity) {
        this.Capacity = capacity;
        //this.UsageTracker = new LinkedList<>();
        this.Store = new HashMap<>(capacity);
        this.mru =  new CacheItem<TKey, TValue>();
        this.lru =  new CacheItem<TKey, TValue>();
        this.lru.next = this.mru;
        this.mru.previous = this.lru;
    }

    public TValue get(TKey key) {
        // If the key is present, update the usage tracker
        if (this.containsKey(key)) {
            // update by remove the node and reinsert into our linkedlist
            // remove from linked list
            // add it as most recently used

            this.recordUsage(key);
        } else {
            throw new RuntimeException(String.format("The key '%s' was not found", key));
        }
        return this.Store.get(key).value;
    }

    public void set(TKey key, TValue value) {
        //int indexOfKey = this.findIndexOfKey(key);
        if (this.containsKey(key)) {
            this.Store.put(key, new CacheItem<TKey, TValue>(key, value));
        } else {
            //int indexToSet;
            // If the set is at it's capacity
            if (this.Count == this.Capacity) {
                // Choose the Least-Recently-Used (LRU) item to replace, which will be at the tail of the usage tracker
                // TODO: Factor this logic out to allow for custom replacement algos
                //TKey keyToReplace = this.UsageTracker.getLast();
                //indexToSet = this.findIndexOfKey(keyToReplace);
                //Remove lru (implement interface)
                CacheItem<TKey, TValue> lru = this.lru.next;
                this.removeFromLinkedList(lru);
                // remove from hashmap
                this.Store.remove(lru.key);
                // Remove the existing key
                //this.removeKey(keyToReplace);
            }
            this.Store.put(key, new CacheItem<>(key, value));
            this.addToLinkedList(this.Store.get(key));
            //this.Count++;
        }
        this.recordUsage(key);
    }
    public boolean containsKey(TKey key) {
        return this.Store.containsKey(key);
    }
    //    private void removeKey(TKey key) {
//      int indexOfKey = this.findIndexOfKey(key);
//      if (indexOfKey >= 0) {
//        this.UsageTracker.remove(key);
//        this.Store[indexOfKey] = null;
//        this.Count--;
//      }
//    }
//    private int findIndexOfKey(TKey key) {
//      for (int i = 0; i < this.Count; i++) {
//        if (this.Store[i] != null && this.Store[i].key.equals(key)) return i;
//      }
//      return -1;
//    }
    private void recordUsage(TKey key) {
        //this.UsageTracker.remove(key);
        //this.UsageTracker.addFirst(key);
        this.removeFromLinkedList(this.Store.get(key));
        this.addToLinkedList(this.Store.get(key));
    }
    private void removeFromLinkedList(CacheItem<TKey, TValue> cacheItem){
        CacheItem<TKey, TValue> prev = cacheItem.previous;
        CacheItem<TKey, TValue> next = cacheItem.next;
        prev.next = next;
        next.previous = prev;
    }
    private void addToLinkedList(CacheItem<TKey, TValue> cacheItem){
        CacheItem<TKey, TValue> prev = this.mru.previous;
        CacheItem<TKey, TValue> next = this.mru;
        prev.next = cacheItem;
        next.previous = cacheItem;

        cacheItem.next = next;
        cacheItem.previous = prev;
    }
}
