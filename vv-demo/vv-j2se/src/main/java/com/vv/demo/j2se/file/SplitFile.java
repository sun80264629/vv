package com.vv.demo.j2se.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

public class SplitFile {
    public static void main(String[] args) {
        //先将源文件读取到内存中
        /*int eachSize=100*1024;
        File srcFile =new File("d:/jst-mysql-db.sql");//创建一个文件对象
        splitFile(srcFile,eachSize);*/
        String[] content = new String[]{"长春市","A0T887","徐世波","str_to_date('2018-12-31 00:00:00', '%Y-%m-%d %H:%i:%s')","13596151521","LVGBV87E1EG014897","广州丰田"};
        String sqlStr = String.format("INSERT INTO j_car (area, carno, name, regdate, carCode, tel, band)"
                + " VALUES ('%s', '%s', '%s', %s, '%s', '%s', '%s')", content);
        String ddd = "长白山\tKD0600\t孙振\t2017-07-31 00:00:00\t15043379222\tLVGCJE733FG068152\t广州丰田";
        System.out.println(ddd.split("\t").length);
    }

    public static void splitFile(File srcFile,int eachSize){
        //判断文件是否符合拆分要求
        if(srcFile.length()==0){
            throw new RuntimeException("文件不符合拆分要求");
        }
        byte[] fileContent= new byte[(int) srcFile.length()];
        try {
            //将文件内容读取到内存中
            FileInputStream fis=new FileInputStream(srcFile);
            fis.read(fileContent);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //计算要次要拆分为多少份
        int fileNumber;
        if(fileContent.length%eachSize==0){
            fileNumber = fileContent.length/eachSize;
        }else{
            fileNumber = fileContent.length/eachSize+1;
        }

        for(int i=0;i<fileNumber;i++){
            String fileName = srcFile.getName()+"-"+i+".txt";
            File fi = new File(srcFile.getParent(), fileName);//在当前文件路径下创建拆分的文件
            byte[] eachContent;

            //将源文件内容复制到拆分的文件中
            if(i!=fileNumber-1){
                eachContent = Arrays.copyOfRange(fileContent, eachSize*i, eachSize*(i+1));
            }else{
                eachContent = Arrays.copyOfRange(fileContent, eachSize*i, fileContent.length);
            }

            try {
                FileOutputStream fos = new FileOutputStream(fi);
                fos.write(eachContent);
                fos.close();
                System.out.printf("输出子文件 %s,其大小是 %d,每个的大小是%d\n",fi.getAbsoluteFile(),fi.length(),eachContent.length);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        }

    }
}
