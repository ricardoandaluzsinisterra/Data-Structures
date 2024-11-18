package utils;
// Create a class called ArrayList (CapWords for class names)

import model.PostEngagement;

public class ArrayList {
    private PostEngagement [] engagements;
    private int count;

    public ArrayList(){
        engagements = new PostEngagement[10];
        count = 0;
    }

    public boolean add(PostEngagement engagement){
        if(engagements.length == count){
            grow();
        }
        engagements[count] = engagement;
        count++;
        return true;
    }

    public PostEngagement get(int index){
        validatePosition(index);
        return engagements[index];
    }

    public int size(){
        return count;
    }

    private void grow(){
        PostEngagement [] enlargedArray = new PostEngagement[engagements.length+15];

        for (int i = 0; i < engagements.length; i++) {
            enlargedArray[i] = engagements[i];
        }

        engagements = enlargedArray;
    }

    public PostEngagement shiftDelete(int pos){
        validatePosition(pos);

        PostEngagement deleted = engagements[pos];

        for (int i = pos; i < count-1; i++) {
            engagements[i] = engagements[i+1];
        }

        engagements[engagements.length-1] = null;
        count--;

        return deleted;
    }


    public PostEngagement update(int pos, PostEngagement newEngagement){
        PostEngagement deleted = engagements[pos];
        engagements[pos] = newEngagement;
        return deleted;
    }

    public void bubbleSort(){
        boolean swapped = true;
        int i = 0;
        while(swapped){
            swapped = false;
            for (int j = 0; j < count-i-1; j++) {
                if(engagements[j].compareTo(engagements[j+1]) > 0){
                    swap(j, j+1);
                    swapped = true;
                }
            }
            i++;
        }
    }

    private void swap(int pos1, int pos2) {
        PostEngagement temp = engagements[pos1];
        engagements[pos1] = engagements[pos2];
        engagements[pos2] = temp;
    }

    private void validatePosition(int pos) {
        if(pos < 0 || pos >= count){
            throw new ArrayIndexOutOfBoundsException("Supplied position must be within the boundary of the array");
        }
    }

}

