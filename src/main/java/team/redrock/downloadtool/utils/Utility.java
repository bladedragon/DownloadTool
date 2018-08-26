package team.redrock.downloadtool.utils;

import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@NoArgsConstructor
public class Utility {
    public static void sleep(int nSecond) {
        try {
            Thread.sleep(nSecond);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void log(String sMsg) {
        System.err.println(sMsg);
    }

    public static void log(int sMsg) {
        System.err.println(sMsg);
    }

    public boolean isEnglish(String charaString) {

        return charaString.matches("^[a-zA-Z]*");
    }

    public boolean isChinese(String str) {
        String regEx = "[\\u4e00-\\u9fa5]+";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        if (m.find())
            return true;
        else
            return false;
    }

    public static boolean IsNull(String str) {
        return str == null || str.trim().equals("") || str.isEmpty();
    }


    public static String getFileName(String filepath) {

        String name = filepath.trim();
        String temp[] = name.split("\\\\");
        String ftemp = temp[temp.length-1];
        String fname[] = ftemp.split("/");
        return fname[fname.length-1];
    }

    public static String getsuffix(String filename){
        String filname = filename.trim();
        String suffix = filname.substring(filename.lastIndexOf(".")+1);

        return suffix;
    }

    public static String getfn(String name){
        String[] temp = name.split(">");
        String filename = temp[1];
        System.out.println(temp[1]);
        return  filename;
    }


    public static String isVedio(String suffix) {

        String[] vediolist = new String[]{".mp4", ".avi", ".mov", ".wmv", ".flv", ".rmvb"};
        String[] audiolist = new String[]{".mp3",".wav",".flac",".aac"};
        String[] textlist = new String[]{".txt",".doc",".md",".pdf","equb"};
        String torrent = ".torrent";
        for (String suff1 : vediolist) {
            if (suff1.equals(suffix)) {
                return "vedio";
            }
        }
        for (String suff2 : audiolist) {
            if (suff2.equals(suffix)) {
                return "audio";
            }
        }
        for (String suff3 : textlist) {
            if (suff3.equals(suffix)) {
                return "text";
            }
        }
        if(torrent.equals(suffix)){
            return "torrent";
        }

        return "other";
    }
//
//    public static void main(String[] args) {
////        String path = "C:\\Users\\11566\\Desktop\\images\\images\\1.jpg";
////        String path2 = "https://blog.csdn.net/u012377333/article/details/50561437.mp4";
////        String name = getFileName(path);
////        System.out.println(name);
////        System.out.println(getsuffix(name));
//        String fn = "删除-->2";
//        System.out.println(getfn(fn));
//    }
}