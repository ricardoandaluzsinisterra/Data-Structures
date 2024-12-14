package utils;

import java.util.ArrayList;

public class CollisionResistantHashMap<K, V> {
    protected java.util.ArrayList<Entry<K,V>>[] map = new java.util.ArrayList[103];
    private int count = 0;
    private static final double LOAD_FACTOR = 0.75;
    private static final int EXPANSION_FACTOR = 3;

    public CollisionResistantHashMap() {

    }

    private boolean hasCapacity(){
        return count < map.length*LOAD_FACTOR;
    }

    protected int hash(K key){
        return Math.abs(key.hashCode()) % map.length;
    }

    private void updateMap(){
        java.util.ArrayList<Entry<K,V>>[] oldMap = map;
        map = new java.util.ArrayList[map.length*EXPANSION_FACTOR];

        for (int i = 0; i < oldMap.length; i++) {
            if(oldMap[i] == null) {
                continue;
            }

            for (int j = 0; j < oldMap[i].size(); j++) {
                Entry<K, V> currentEntry = oldMap[i].get(j);
                K key = currentEntry.key;
                V value = currentEntry.value;

                put(key, value);
            }
        }
    }

    public V put(K key, V value){
        // Validation
        if(key == null){
            throw new IllegalArgumentException("Key cannot be null");
        }
        if(!hasCapacity()){
            updateMap();
        }
        // Calculate appropriate slot/bucket for use based on key
        int pos = hash(key);

        // If slot/bucket is null, it has never been used.
        // Add a new list and add new Entry
        if(map[pos] == null) {
            map[pos] = new ArrayList<>();
        }

        Entry<K, V> newEntry = new Entry<>(key, value);
        int index = map[pos].indexOf(newEntry);
        if(index != -1){
            Entry<K, V> toBeUpdated = map[pos].get(index);
            V replaced = toBeUpdated.value;
            toBeUpdated.value = value;
            return replaced;
        }

        map[pos].add(newEntry);
        count++;
        return null;
    }

    public ArrayList<Entry<K, V>> get(K key){
        // Validation
        if(key == null){
            throw new IllegalArgumentException("Key cannot be null");
        }

        int pos = hash(key);
        if(map[pos] == null){
            return null;
        }

        int index = map[pos].indexOf(new Entry(key, null));
        if(index == -1){
            return null;
        }
        
        return map[pos];
    }

    public void display(){
        for (int i = 0; i < map.length; i++) {
            if(map[i] == null) {
                System.out.println("SLot " + i + ") Null");
                continue;
            }

            System.out.println("Slot " + i + ")");
            for (int j = 0; j < map[i].size(); j++) {
                Entry<K, V> currentEntry = map[i].get(j);
                System.out.println("Entry " + j + " -> " + currentEntry);
            }
        }
    }

    /* public PostEngagement mostUniqueUsers(){
        int [] sizeArray = new int[count];
        for (int e = 0; e < count; e++) {
            int sizeOfList = map[e].size();
            sizeArray[e] = sizeOfList;
        }

        int duplicates = 0;
        int maxSize = 0;
        ArrayList<Integer> sizeIndexes = new ArrayList<Integer>();
        for (int i=0; i < count; i++){
            if (sizeArray[i] > maxSize){
                maxSize = sizeArray[i];
                sizeIndexes.add(i);
            }
            if (sizeArray[i] == maxSize){
                duplicates++;
                sizeIndexes.add(i);
            }
        }

        if (duplicates > 0){
            java.util.ArrayList<Entry<K,V>>[] newMap = new java.util.ArrayList[sizeIndexes.size()];
            for(int i=0; i < sizeIndexes.size(); i++){
                newMap[i] = map[sizeIndexes.get(i)];
            }
        boolean swapped = true;
        int i = 0;
        while(swapped){
            swapped = false;
            for (int j = 0; j < sizeIndexes.size()-i-1; j++) {
                if(newMap[j].getValue() > newMap[j+1].getValue()){
                    swap(j, j+1);
                    swapped = true;
                }
            }
            i++;
        }
        }
    }
     */
    
    

    private void swap(int pos1, int pos2) {
        ArrayList<Entry<K, V>> temp = map[pos1];
        map[pos1] = map[pos2];
        map[pos2] = temp;
    }

    private static class Entry<K, V>{
        K key;
        V value;

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Entry<?, ?> entry = (Entry<?, ?>) o;
            return key.equals(entry.key);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        public V getValue(){
            return this.value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
