//==================================================================================================================\\
//=10/09/2015                                                                                       Alexander Alava=\\
//=TeamProject.java                                                                                   Isaac Roberts=\\
//=                                                                                                                =\\
//=   This program lets the user choose between manually entering a DNA sequence or selecting a text file with a   =\\
//=   sequence already on it and then it analyzes the amount of Cytosine and Guanine present in the acidic bases   =\\
//==================================================================================================================\\ 

import java.util.*;
import java.text.*;
import java.io.*;

public class TeamProject 
{
    public static void main(String args[]) throws IOException
    {
        //Declaring variables\\
        String dnaInput, userChoice, fileName, quit = "";
        int totalChar, gCount, cCount, junkCount;
        boolean loop, manualTag;

        //Declaring and initializing Scanner object and DecimalFormat format\\
        Scanner in = new Scanner(System.in);
        DecimalFormat dfmt = new DecimalFormat("0.###");

        //Establishing a while loop to let the user exit the program\\
        while (!quit.equals("q")) 
        {
            //File name references\\
            String ACTB = "ACTB.txt";
            String BMP5 = "BMP5.txt";
            String G6PD = "G6PD.txt";
            String GABRA1 = "GABRA1.txt";
            String HBB = "HBB.txt";
            String HSP90 = "HSP90.txt";

            //Definining and reseting variables for each run\\
            manualTag = false;
            gCount = 0;
            cCount = 0;
            junkCount = 0;
            fileName = "";
            dnaInput = "";

            //Printing header\\
            System.out.println("\n---DNA CONTENT CALCULATOR---");

            //Prompting for and reading in user's choice for manual or existing sequence\\
            System.out.println("\nWould you like to enter the sequence manually or use the local text files? ");
            System.out.print("Please select \"m\" for manual or \"t\" for local text files: ");
            userChoice = in.nextLine();
            userChoice = userChoice.toLowerCase();

            //Establishing a do-while loop to read in user input for userChoice\\
            do 
            {
                //Establishing a switch case for manual entry, text file or invalid entry\\
                switch (userChoice)
                {
                    //Case for manual entry\\
                    case "m" :
                        System.out.println("\nPlease enter a sequence: ");
                        dnaInput = in.nextLine();
                        loop = false;
                        manualTag = true;
                        break;

                    //Case for text file\\
                    case "t" :
                        //Establishing a do-while loop to make the user select a file\\
                        do 
                        {
                            //Declaring string textFileChoice\\
                            String textFileChoice;

                            //Prompting for and reading in user's file choice\\
                            System.out.println("\nSelect a numbered file from the list below.");
                            System.out.println("\n(1) ACTB.txt");
                            System.out.println("(2) BMP5.txt");
                            System.out.println("(3) G6PD.txt");
                            System.out.println("(4) GABRA1.txt");
                            System.out.println("(5) HBB.txt");
                            System.out.println("(6) HSP90.txt");
                            System.out.print("\nNumber: ");

                            textFileChoice = in.nextLine();

                            //Assigning file reference to fileName and falling through if no cases match\\
                            switch (textFileChoice) 
                            {
                                case "1":
                                    fileName = ACTB;
                                    break;
                                case "2":
                                    fileName = BMP5;
                                    break;
                                case "3":
                                    fileName = G6PD;
                                    break;
                                case "4":
                                    fileName = GABRA1;
                                    break;
                                case "5":
                                    fileName = HBB;
                                    break;
                                case "6":
                                    fileName = HSP90;
                                    break;
                                default:
                                    //Printing when the entry is invalid\\
                                    System.out.println("\nInvalid entry. Please enter a whole number from 1 to 6.");
                            }
                        } 
                        while (fileName.equals(""));

                        //Declaring and initializing File Scanner object\\
                        Scanner fileScan = new Scanner(new File(fileName));

                        //Reading the file line by line and pulling out strings\\
                        while (fileScan.hasNext()) 
                        {
                            dnaInput = dnaInput + fileScan.nextLine();
                        }
                        fileScan.close();
                        //Setting a flag to false since the conditions have been met\\
                        loop = false;
                        break;

                    default :
                        //Validating user entry\\
                        System.out.println("\nYou have made an invalid selection");
                        System.out.print("Please try again by selecting either \"m\" or \"t\": ");
                        userChoice = in.nextLine();

                        //Setting the loop to run again due to an invalid entry\\
                        loop = true;
                        break;
                }
            } 
            while (loop);

            //Determining total character length\\
            totalChar = dnaInput.length();

            //Determining C, G or junk in each index by counting down through each of them\\
            for (int i = (totalChar - 1); i >= 0; i--) 
            {
                //Counting Guanine\\
                if (dnaInput.charAt(i) == 'G') 
                {
                    gCount++;
                }
                //Counting Cytosine\\
                else if (dnaInput.charAt(i) == 'C') 
                {
                    cCount++;
                }
                //Counting entries not related to the DNA sequence\\
                else if (!(dnaInput.charAt(i) == 'A' || dnaInput.charAt(i) == 'T'))
                {
                    junkCount++;
                }
            }
            //Printing final results\\
            System.out.println();
            if (fileName.equals(ACTB))
            {
                System.out.println("---Beta-actin (ACTB)---");
            }
            else if (fileName.equals(BMP5))
            {
                System.out.println("---Bone Morphogenetic Protein 5 (BMP5)---");
            }
            else if (fileName.equals(G6PD))
            {
                System.out.println("---Glucose-6-phosphate Dehydrogenase (G6PD)---");
            }
            else if (fileName.equals(GABRA1))
            {
                System.out.println("---GABA Receptor A1 (GABRA1)---");
            }
            else if (fileName.equals(HBB))
            {
                System.out.println("---Hemoglobin Beta (HBB)---");
            }
            else if (fileName.equals(HSP90))
            {
                System.out.println("---Heat Shock Protein 90 (HSP90)---");
            }
            else if (manualTag)
            {
                System.out.println("---Manual Entry---");
            }
            System.out.println("Total number of known bases in series: " + (totalChar - junkCount));
            System.out.println("Total number of unknown parts in series: " + junkCount);
            System.out.println("Total instances of G's (guanine): " + gCount);
            System.out.println("Total instances of C's (cytosine): " + cCount);
            System.out.println("%G~C count: " + dfmt.format(((gCount + cCount) / (double) (totalChar - junkCount)) * 100) + "%");

            //Prompting for and reading in user input for quit string\\
            System.out.print("\nEnter \"q\" to quit or any other key to return to the menu: ");
            quit = in.nextLine();
            quit = quit.toLowerCase();

            if (quit.equals("q"))
            {
                System.out.println("\nDNA Content Calculator Closing...");
                System.out.println("Program created by Alexander Alava and Isaac Roberts");
                in.close();
            }
        }
    }
}