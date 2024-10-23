package utils;

public class HashMap<K, V> {

    private Entry<K, V>[] map;
    private int count;

    //calculates the hash of the key and based on that decides what goes where
    public HashMap(){
        //Casting object array to an Entry array
        map = (Entry<K, V>[]) new Object [10];
    }

    private static class Entry<K, V>{
        K key;
        V value;

        public  Entry(K key, V value){
            this.key = key;
            this.value = value;
        }
    }



}
