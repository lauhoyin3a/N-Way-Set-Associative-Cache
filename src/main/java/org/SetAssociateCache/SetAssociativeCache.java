package org.SetAssociateCache;

public class SetAssociativeCache<TKey, TValue> {
    int Capacity;
    int SetSize;
    int SetCount;
    CacheSet<TKey, TValue>[] Sets;
    public SetAssociativeCache(int setCount, int setSize) {
        this.SetCount = setCount;
        this.SetSize = setSize;
        this.Capacity = this.SetCount * this.SetSize;
        // Initialize the sets
        this.Sets = new CacheSet[this.SetCount];
        for (int i = 0; i < this.SetCount; i++) {
            Sets[i] = new CacheSet<>(setSize);
        }
    }

    public TValue get(TKey key) {
        int setIndex = this.getSetIndex(key);
        CacheSet<TKey, TValue> set = this.Sets[setIndex];
        return set.get(key);
    }

    public void set(TKey key, TValue value) {
        int setIndex = this.getSetIndex(key);
        CacheSet<TKey, TValue> set = this.Sets[setIndex];
        set.set(key, value);
    }
    /** Returns the count of items in the cache */
    public int getCount() {
        int count = 0;
        for (int i = 0; i < this.Sets.length; i++) {
            count += this.Sets[i].Store.size();
        }
        return count;
    }
    /** Returns true if the given key is present in the set; otherwise, false. */
    public boolean containsKey(TKey key) {
        int setIndex = this.getSetIndex(key);
        CacheSet<TKey, TValue> set = this.Sets[setIndex];
        return set.containsKey(key);
    }
    /** Maps a key to a set */
    private int getSetIndex(TKey key) {
        int c = Integer.MAX_VALUE;
        int s = -1;
        for (int i = 0; i < this.Sets.length; i++) {
            if (this.Sets[i].containsKey(key)) {
                return i;
            }
            if (this.Sets[i].Count < c) {
                c = this.Sets[i].Count;
                s = i;
            }
        }
        return s;
    }
}