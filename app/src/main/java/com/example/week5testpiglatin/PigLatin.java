package com.example.week5testpiglatin;

import android.util.Log;

public class PigLatin {
    static String vowels = "aeiou";
    public static void main(String[] args){
        String sentence = "Hello lets speak pig latin";
        String [] sentenceArray = sentence.split(" ");
        convertToPig(sentenceArray);
    }

    public static void convertToPig(String [] givenSentence){
        for(int i = 0; i<givenSentence.length;i++){
            for(int j= 0; j<givenSentence[i].length(); j++){
                if(vowels.indexOf(givenSentence[i].charAt(j)) >= 0){
                    //Log.d("TAG", "convertToPig: "  + givenSentence[i].charAt(j));
                    String temp = Character.toString(givenSentence[i].charAt(j));
                    String [] tempArray = givenSentence[i].split(temp);
                    givenSentence[i] = temp + tempArray[1] + tempArray[0] + "ay";

                    //Log.d("TAG", "convertToPig: "  + givenSentence[i]);
                    System.out.println("convertToPig: "  + givenSentence[i]);
                    break;
                }
            }

        }
    }
}
