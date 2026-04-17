package 云智;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class T2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens()){
            return;
        }

        String str = st.nextToken();

        char[] yuanYin = "aeiou".toCharArray();
        int len = str.length();
        int count=len;
        Set<Character> set = new HashSet<>();
        for(char c : yuanYin){
            set.add(c);
        }
        for (int i = 0; i < len; i++) {
            if(set.contains(str.charAt(i))){
                count--;
            }
        }
        int res = 2*count;
        pw.println(res);
        pw.flush();
        pw.close();
    }
}
