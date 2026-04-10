package 网易互娱.D20260409;

import java.io.*;
import java.util.StringTokenizer;
/*
实现Token计数器
 */
public class T2 {
    public static int charType(char c) {
        //A65 Z90   a97 z122
        int val = (int)c;
        if((val>=65 && val<=90) || (val>=97&&val<=122) || (val>=(int)'0'&&val<=(int)'9')){//1是字母或数字
            return 1;
        }else if(val<=255){//3是标点符号
            return 3;
        }else{//2是汉字
            return 2;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens()) return;

        int res = 0;
        while(st.hasMoreTokens()){//去空格
            String strIn = st.nextToken();
            int i = 0;
            int len = strIn.length();
//            pw.print(strIn+"长度"+len+":");
            while(i<len){
                char c = strIn.charAt(i);
//                pw.print("字符"+c+" "+(int)c+"|");

                if(charType(c) == 1){//字母或数字
                    while(i<len&&charType(strIn.charAt(i))==1){
                        i++;
                    }
                    res++;
                }else if(charType(c) == 2){//汉字
                    i++;
                    res++;
                }else if(charType(c) == 3){//符号
                    i++;
                    res++;
                }
            }
//            pw.println(strIn+":"+res);
        }
        pw.print(res);
        pw.flush();
        pw.close();

    }
}
