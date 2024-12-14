package application;

import model.PostEngagement;
import utils.ArrayList;
import utils.FileHandlingUtilities;


    public class PostEngagementApp {

        public static void displayAndSort(ArrayList engagements){
            engagements.bubbleSort();
            engagements.toString();
        }

        public static void main(String[] args) {
            System.out.println(System.getProperty("user.dir"));
            PostEngagement[] engagementsArray = FileHandlingUtilities.readPostEngagementFile("C:\\Users\\code\\'OneDrive - Dundalk Institute of Technology'\\Documents\\Coding\\'elkhonduras java'\\data-structures\\SocialMediaAnalysis\\engagements_1.txt");
            ArrayList engagements = new ArrayList();

            for (PostEngagement engagement : engagementsArray){
                engagements.add(engagement);
            }

            displayAndSort(engagements);
            


        }

        
}
