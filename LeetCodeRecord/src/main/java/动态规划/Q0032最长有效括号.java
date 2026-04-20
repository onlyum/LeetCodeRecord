package 动态规划;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q0032最长有效括号 {
    public static void main(String[] args) {
        String s = "(()";
        System.out.println(longestValidParentheses(s));
    }
    //方法2：栈
    public static int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();//栈只记录左括号下标，和多出的右括号的下标
        stack.push(-1);//初始化参考点，防空
        int maxLen = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='(')  stack.push(i);
            else{
                stack.pop();
                if(stack.isEmpty()) stack.push(i);
                else maxLen = Math.max(maxLen, i-stack.peek());
            }
        }
        return maxLen;
    }
}
