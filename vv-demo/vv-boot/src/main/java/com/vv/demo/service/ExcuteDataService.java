package com.vv.demo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcuteDataService {
    public readFile(){
        String filename = "";

        while(){

        }

    }

    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        System.out.println("以行为单位读取文件内容，一次读一整行：");
        Map tempMap = new HashMap();
        List<String> tempList = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(file));

            String tempString = null;
            int line = 1;
            String content = "";
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                //System.out.println("line " + line + ": " + tempString);
                System.out.println("line " + line);
                tempList.add(tempString);
                line++;

                if(line % 1000 == 0){
                    tempMap.put(line, tempList);
                    tempList = new ArrayList<String>();
                    /*wirteFile(line, content);
                    content = "";*/
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

}
