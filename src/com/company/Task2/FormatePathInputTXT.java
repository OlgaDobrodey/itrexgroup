package com.company.Task2;

import java.io.File;

public class FormatePathInputTXT {

    public static String getPath(Class<?> aClass) {
        String packageName = aClass
                .getPackage()
                .getName()
                .replace(".", File.separator)
                .concat(File.separator);
        String root = System.getProperty("user.dir");
        return root + File.separator + "src" + File.separator + packageName;
    }

}
