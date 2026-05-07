package WPS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//过64.29%，剩余答案不对
//截取一段连续子串，使得子串表示的正整数小于k，求截取方案数量
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
        for(int i = 0; i < s.length(); i++){
            for(int j = i ; j < s.length(); j++){
                String sub = s.substring(i, j+1);
                if(compare(sub, Integer.toString(k))){
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
            for(int i = 0; i < s.length(); i++){
                int sint = s.charAt(i) - '0';
                int tint = tar.charAt(i) - '0';
                if(sint>=tint) return false;
            }
            return true;
        }
    }
}
