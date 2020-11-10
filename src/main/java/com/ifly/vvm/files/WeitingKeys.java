package com.ifly.vvm.files;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class WeitingKeys {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\github\\pyspark\\weiting_86.txt")));
            String line = null;
            Set<String> set = new TreeSet<String>();
            while((line = br.readLine())!=null) {
                String[] lines = line.split(" ");
                if(lines.length > 2) {
                    set.add(lines[lines.length - 1]);
                }
            }
            System.out.println(set);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
