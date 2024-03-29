package edu.whu.cs.nlp.mts.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.whu.cs.nlp.mts.domain.Word;
import edu.whu.cs.nlp.mts.sys.SystemConstant;

/**
 * 公共工具
 * @author Apache_xiaochao
 *
 */
public class CommonUtil implements SystemConstant{

    public static final Logger log = Logger.getLogger(CommonUtil.class);

    /**
     * 去除文本最后的面的换行符
     * @param text
     * @return
     */
    public static String cutLastLineSpliter(String text){
        String value = text;
        if(text != null){
            int index = text.lastIndexOf(LINE_SPLITER);
            if(index > 0){
                value = text.substring(0, index);
            }
        }
        return value;
    }

    /**
     * 将多层List转化成字符串
     * @param <T>
     * @param lists
     * @return
     */
    public static <T> String lists2String(List<List<T>> lists){
        String result = null;
        StringBuilder sb_out = new StringBuilder();
        for (List<T> list : lists) {
            StringBuilder sb_in = new StringBuilder();
            for (T t : list) {
                sb_in.append(t.toString() + " ");
            }
            sb_out.append(sb_in.toString().trim() + LINE_SPLITER);
        }
        result = cutLastLineSpliter(sb_out.toString());
        return result;
    }

    /**
     * 将List转化成字符串
     * @param <T>
     * @param lists
     * @return
     */
    public static <T> String list2String(List<T> list){
        String result = null;
        StringBuilder sb = new StringBuilder();
        for (T t : list) {
            sb.append(t.toString() + " ");
        }
        result = sb.toString().trim();
        return result;
    }

    /**
     * 将输入的字符封装成对象
     * @param input
     * @param pattern
     * @return
     */
    public static Word str2Word(String str){
        Word word = null;
        if(str != null && !"".equals(str.trim())){
            String[] attrs = str.split(WORD_ATTRBUTE_CONNECTOR);
            word = new Word();
            word.setName(attrs[0]);
            word.setLemma(attrs[1]);
            word.setPos(attrs[2]);
            word.setNer(attrs[3]);
            word.setSentenceNum(Integer.parseInt(attrs[4]));
            word.setNumInLine(Integer.parseInt(attrs[5]));
        }
        return word;
    }

    /**
     * 对输入文本按行切分，然后存入List集合
     * @param input
     * @return
     */
    public static List<String> str2List(String input){
        List<String> list = null;
        if(input != null){
            list = new ArrayList<String>();
            String[] lines = input.split(LINE_SPLITER);
            for (String line : lines) {
                list.add(line);
            }
        }
        return list;
    }

    /**
     * 将字符串形式的详细词信息，转化成对象进行存储
     * @param wordStr
     * @return
     */
    public static Word string2Word(String wordStr){
        Word word = null;
        if(wordStr != null && !"".equals(wordStr.trim())){
            try{
                String[] attrs = wordStr.split(WORD_ATTRBUTE_CONNECTOR);
                word = new Word();
                word.setName(attrs[0]);
                word.setLemma(attrs[1]);
                word.setPos(attrs[2]);
                word.setNer(attrs[3]);
                word.setSentenceNum(Integer.parseInt(attrs[4]));
                word.setNumInLine(Integer.parseInt(attrs[5]));
            }catch(Exception e){
                log.error("解析词失败：" + wordStr, e);
                word = new Word();
                word.setName("");
                word.setLemma("");
                word.setPos("");
                word.setNer("O");
                word.setSentenceNum(-1);
                word.setNumInLine(-1);
            }

        }
        return word;
    }

    /**
     * 计算两个字符串之间的距离<br>
     * 如果两个字符串在长度上不相同则距离为无穷大
     * @param str1
     * @param str2
     * @return
     */
    public static int strDistance(String str1, String str2) {
        int dis = Integer.MAX_VALUE;
        if(str1 == null || str2 == null) {
            return dis;
        }
        if(str1.length() != str2.length()) {
            return dis;
        }

        if(str1.charAt(0) == str2.charAt(0)) {
            dis = 0;
        } else {
            dis = 1;
        }

        for(int i = 1; i < str1.length(); i++) {
            if(str1.charAt(i) != str2.charAt(i)) {
                dis += 2;
            }
        }

        return dis;
    }

}
