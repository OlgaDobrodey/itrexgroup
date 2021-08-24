package com.company.Task21;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VriablePath {

        private final static VriablePath instance = new VriablePath();

        private final Set<List<Point>> setVariablePath = new HashSet<>();


        public Set<List<Point>>get() {
            return this.setVariablePath;
        }


        public void addSet(List<Point> list) {
            setVariablePath.add(list);
        }

        public static VriablePath getInstance() {
            return instance;
        }
    }
