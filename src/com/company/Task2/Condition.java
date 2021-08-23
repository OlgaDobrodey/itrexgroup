package com.company.Task2;

public class Condition {

    public static boolean nextLevelFreeSpace(char symbolThisLevel, char symbolNextLevel ){
        return ((symbolThisLevel == Symbols.FREE_SPACE|| symbolThisLevel == Symbols.PRINCE)
                && symbolNextLevel == Symbols.FREE_SPACE);
         }


}
