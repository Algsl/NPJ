package com.zthx.npj.utils;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class TestDimenTool {
    public static void main(String[] args) {
        StringBuilder sw400 = new StringBuilder();
        //添加xml开始的标签
        String xmlStart = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<resources>\n";
        sw400.append(xmlStart);
        //添加内容
        for (int i = -200; i <=800; i++) {
//            此处name后的标签名可以自定义"margin_"随意更改
            if(i<0){
                String start = "<dimen name=\"dp_m_" + change(i) + "\">";
                String end = "dp</dimen>";
                sw400.append(start).append(new DecimalFormat("#0.00000").format(i*1.92000)).append(end).append("\n");
                //sw400.append(start).append(i).append(end).append("\n");
            }else{
                String start = "<dimen name=\"dp_" + change(i) + "\">";
                String end = "dp</dimen>";
                sw400.append(start).append(new DecimalFormat("#0.00000").format(i*1.92000)).append(end).append("\n");
                //sw400.append(start).append(i).append(end).append("\n");
            }
        }

        for(int i=6;i<=50;i++){
            String start = "<dimen name=\"sp_" + change(i) + "\">";
            String end = "sp</dimen>";
            sw400.append(start).append(new DecimalFormat("#0.00000").format(i*1.92000)).append(end).append("\n");
            //sw400.append(start).append(i).append(end).append("\n");
        }

        //添加xml的尾标签
        sw400.append("</resources>");
        String sw400file = "./app/src/main/res/values-sw720dp/dimens.xml";
        writeFile(sw400file, sw400.toString());
    }

    public static void writeFile(String file, String text) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

        out.close();
    }

    private static String change(int i) {
        if(i<0){
            return (Math.abs(i)*1)+"";
        }else{
            return (i*1) + "";
        }
    }
}