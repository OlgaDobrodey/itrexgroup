package com.company;

import java.io.*;

public class Task2 {
    public static void main(String[] args) {
        String path = getPath(Task2.class) + "INPUT.TXT";
        long count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String firstLine = reader.readLine();
            String[] s = firstLine.split(" "); //The first line of INPUT.TXT file contains digits H, M, N - count of levels and rectangle size of each level.
            int h = Integer.parseInt(s[0]); //H blocks
            int m = Integer.parseInt(s[1]);// M rows
            int n = Integer.parseInt(s[2]); //N columns
            String line = reader.readLine();
            for (int i = 0; i < h; i++) {
                line = reader.readLine();
                for (int i1 = 0; i1 < m; i1++) {
                    if (!line.replaceAll("\\s", "").isEmpty()) {
                        if (line.length() == n && line.matches("[.o21]{3}")) {
                            count = count + line.chars().filter(ch -> ch == '.').count();
                        } else {
                            throw new IllegalArgumentException("file contains lines that do not fit the conditional:\n" +
                                    " H- blocks, M -rows,N columns");
                        }
                    }
                    line = reader.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(count * 5);

    }

    private static String getPath(Class<?> aClass) {
        String packageName = aClass
                .getPackage()
                .getName()
                .replace(".", File.separator)
                .concat(File.separator);
        String root = System.getProperty("user.dir");
        return root + File.separator + "src" + File.separator + packageName;
    }
}
