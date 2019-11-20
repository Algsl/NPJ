package com.zthx.npj.utils;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class TestDimenTool {

    private static Double[] beilvs={
            0.853330,0.960000,1.024000,1.045330,1.066670,
            1.093330,1.096000,1.152000,1.280000,1.421330,
            1.578670,1.600000,1.706670,1.765330,1.920000
    };
    private static String[] dim_types={
            "values-sw320dp","values-sw360dp","values-sw384dp","values-sw392dp","values-sw400dp",
            "values-sw410dp","values-sw411dp","values-sw432dp","values-sw480dp","values-sw533dp",
            "values-sw592dp","values-sw600dp","values-sw640dp","values-sw662dp","values-sw720dp"};

    public static void main(String[] args) {
        for(int i=0;i<beilvs.length;i++){
            generateDim(beilvs[i],dim_types[i]);
        }
    }

    public static void generateDim(Double beilv,String path){
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
                sw400.append(start).append(new DecimalFormat("#0.00000").format(i*beilv)).append(end).append("\n");
                //sw400.append(start).append(i).append(end).append("\n");
            }else{
                String start = "<dimen name=\"dp_" + change(i) + "\">";
                String end = "dp</dimen>";
                sw400.append(start).append(new DecimalFormat("#0.00000").format(i*beilv)).append(end).append("\n");
                //sw400.append(start).append(i).append(end).append("\n");
            }
        }

        for(int i=6;i<=50;i++){
            String start = "<dimen name=\"sp_" + change(i) + "\">";
            String end = "sp</dimen>";
            sw400.append(start).append(new DecimalFormat("#0.00000").format(i*beilv)).append(end).append("\n");
            //sw400.append(start).append(i).append(end).append("\n");
        }

        //添加xml的尾标签
        sw400.append("</resources>");
        String sw400file = "./app/src/main/res/"+path+"/dimens.xml";
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