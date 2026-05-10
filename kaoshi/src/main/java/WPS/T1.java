package WPS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//过64.29%，剩余答案不对
//截取一段连续子串，使得子串表示的正整数小于k，求截取方案数量
//注意点，长度相同可以String.compareTo()比较字典序，compareTo 返回负数说明字典序更小（即数值更小）

public class T1 {
    /*
1234
23
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        if(!st.hasMoreTokens()) return;
        String s = st.nextToken();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        if(s.isEmpty()) return;

        pw.print(solution(s,n));

        pw.flush();
        pw.close();
    }
    static int solution(String s, int k){
        int count = 0;
        String tar = Integer.toString(k);// 提取到外层，避免重复转换
        for(int i = 0; i < s.length(); i++){
            //正整数不能以0开头
            if(s.charAt(i) == '0') continue;
            for(int j = i ; j < s.length(); j++){
                String sub = s.substring(i, j+1);
                if(compare(sub, tar)){
                    count++;
                }else{
                    break;
                }
            }
        }
        return count;
    }
    static boolean compare(String s, String tar){
        if(s.length() > tar.length()) return false;
        else if(s.length() < tar.length()) return true;
        else{
            // 【核心修复1】长度相同时，直接利用字典序比较代替原来的逐位绝对大小判定
            // compareTo 返回负数说明字典序更小（即数值更小）
            return s.compareTo(tar) < 0;
        }
    }
//    static boolean compare(String s, String tar){
//        if(s.length() > tar.length()) return false;
//        else if(s.length() < tar.length()) return true;
//        else{
//            for(int i = 0; i < s.length(); i++){
//                int sint = s.charAt(i) - '0';
//                int tint = tar.charAt(i) - '0';
//                if(sint>=tint) return false;
//            }
//            return true;
//        }
//    }
}
