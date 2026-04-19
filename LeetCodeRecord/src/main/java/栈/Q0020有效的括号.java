package 栈;

import java.util.ArrayDeque;
import java.util.Queue;

public class Q0020有效的括号 {
    public static void main(String[] args) {
        String input = "()]{}";
        System.out.println(isValid(input));
    }
    public static boolean isValid(String s) {
        Queue<Character> queue = new ArrayDeque<>();
        for(char c:s.toCharArray()){
            if(c=='('||c=='['||c=='{'){
                queue.offer(c);
            }else{
                if(queue.isEmpty()) return false;
                if(c==')'&&queue.peek()!='(')   return false;
                if(c==']'&&queue.peek()!='[')   return false;
                if(c=='}'&&queue.peek()!='{')   return false;
                queue.poll();
            }
        }
        return queue.isEmpty();
    }
}
