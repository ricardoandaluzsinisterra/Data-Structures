package utils;

public class HashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 23;
    private Entry<K, V>[] data;
    private int size;

    public HashMap(){
        data = new Entry[DEFAULT_CAPACITY];
    }

    public int size(){
        return size;
    }

    private int hashFunction(K K){
        int hash = K.hashCode();
        hash = Math.abs(hash) % data.length;
        return hash;
    }

    private boolean isKey(K K){
        for(Entry entry : data){
            if(entry.getKey() == K){
                return true;
            }
        }
        return false;
    }

    public V put(K K, V V){
        int slot = hashFunction(K);
        if (data[slot] == null){
            Entry entry = new Entry(K, V);
            data[slot] = entry;
            size++;
            return null;
        }
        else{
            V oldValue = data[slot].updateValue(V);
            return oldValue;
        }
    }

    



    private static class Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K K, V V){
            this.key = K;
            this.value = V;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V updateValue(V V) {
            V previousValue = value;
            this.value = V;
            return previousValue;
        }
    }
}
