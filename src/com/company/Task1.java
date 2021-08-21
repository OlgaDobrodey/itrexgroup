package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Task1 {
/*
    Examples:
    Input:
            cacao and coffee
            success
    Output:
            kakao and kofi
            sukses
     */

    public static void main(String[] args){
        System.out.println("Input:");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();

        text = removeLetterC(text);
        text = removeDoubleLetter(text);
        text = removeEInEndWord(text);
        text = removeArticles(text);

        System.out.println("Output:");
        System.out.println(text);
    }

    /*
     1) Remove "c" from the text:
     If the text contains “ci” and “ce”, change it to “si” and “se”.
     If “ck” then change it to “k”.
     In the other case replace “c” with “k”.
     All the changes should be made in a strong order left-to-right.
     For example:
     The word “success” first of all will be the word “sukcess”. Then “suksess”.
     */

    public static String removeLetterC(String s) {
        return s.replaceAll("ci", "si").replaceAll("ce", "se")
                .replaceAll("ck", "k").replace('c', 'k');
    }

    /*
         2) Remove a double letter
                If the text contains “ee” then replace it by simple “i”.
                If “oo” then change it by “u”.
                In the other case any double letter should be changed by one letter.
                For example:
                    “ooo” will be “uo”
                    “oou” will be “u”
                    “iee” will be “i”
     */

    public static String removeDoubleLetter(String s) {
        s = s.replaceAll("ee", "i").replaceAll("oo", "u");
        Pattern pattern = Pattern.compile("([a-zA-Z])\\1+");
        Matcher matcher = pattern.matcher(s);
        int count = 0;
        while (matcher.find()) {
            int index = matcher.start() - count;
            int lengthWord = matcher.group().length() - 1;
            s = s.substring(0, index) + s.substring(index + lengthWord);
            count = count + lengthWord;
        }
        return s;
    }
    /*
          3) Remove the letter “e” at the end of each word
        Remove the letter “e” at the end of each word if the word length > 1.
        For example:
        “The” will be “Th”.

     */

    public static String removeEInEndWord(String string) {
        String[] split = string.split(" ");
        StringBuilder a = new StringBuilder();
        for (String s : split) {
            if (s.length() > 1 && s.endsWith("e")) {
                String b = s.substring(0, s.length() - 1);
                a.append(b).append(" ");
            } else a.append(s).append(" ");
        }
        return a.substring(0, a.toString().length() - 1);
    }

/*
    4) Remove articles
    Remove the articles “a”, “an” or “the” from the text.

 */


    private static String removeArticles(String aticls) {

        String[] s = aticls.split(" ");
        StringBuilder a = new StringBuilder();
        for (String s1 : s) {
            if (!(s1.equalsIgnoreCase("a") || s1.equalsIgnoreCase("an") || s1.equalsIgnoreCase("the") || s1.equalsIgnoreCase("th"))) {
                a.append(s1 + " ");
            }
        }
        return a.substring(0, a.length() - 1);
    }


}
