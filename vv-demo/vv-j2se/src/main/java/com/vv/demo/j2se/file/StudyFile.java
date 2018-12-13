package com.vv.demo.j2se.file;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyFile {
    public static void main(String[] args) {
        String fileName="d:/jst-mysql-db.sql";
        readFileByLines(fileName);
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

    public static void wirteFile(int line, String content) {
        String filename = "data_"+line+".sql";
        try {  //追加文件
            FileWriter writer = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(content);
            bw.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

