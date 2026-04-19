package 栈;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q0394字符串解码 {
    public static void main(String[] args) {
        String s = "3[a2[c]]";
        System.out.println(decodeString(s));
    }
    public static String decodeString(String s) {
        Deque<StringBuilder> stack = new ArrayDeque<>();
        Deque<Integer> kstack = new ArrayDeque<>();
        StringBuilder temp = new StringBuilder();
        int k =0;
        for(char c : s.toCharArray()){
            if(c >= '0' && c <= '9'){
                k = k*10 + c - '0';
            }else if(c == ']'){
                StringBuilder sb = stack.pop();
                int times = kstack.pop();
                for(int i = 0; i < times; i++){
                    sb.append(temp);
                }
                temp = sb;
            }else if(c == '['){
                kstack.push(k);
                stack.push(temp);
                temp = new StringBuilder();
                k=0;
            }else{
                temp.append(c);
            }
        }
        return temp.toString();
    }
}
