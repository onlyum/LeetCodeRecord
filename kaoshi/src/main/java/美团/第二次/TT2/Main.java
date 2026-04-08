package 美团.第二次.TT2;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            String sb = in.next();
            Queue<Character> stack = new ArrayDeque<>();
            for(char c : sb.toCharArray()){
                if(c == '('){
                    stack.offer(c);
                }else{
                    if(!stack.isEmpty()&&stack.peek() == '('){
                        stack.poll();
                    }else{
                        stack.offer(c);
                    }
                }
            }
            System.out.println(stack.size()/2);
        }
    }
}
/*
输入：
3
2
)(
4
()()
4
))((


输出：
1
0
3
 */