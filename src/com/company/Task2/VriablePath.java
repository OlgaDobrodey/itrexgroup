package com.company.Task2;

import java.util.HashSet;
import java.util.Set;

public class VriablePath {

        private final static VriablePath instance = new VriablePath();

        private final Set<Integer> listVariablePath = new HashSet<>();


        public Set<Integer>get() {
            return this.listVariablePath;
        }


        public void addSet(Integer list) {
            listVariablePath.add(list);
        }

        public static VriablePath getInstance() {
            return instance;
        }
    }
