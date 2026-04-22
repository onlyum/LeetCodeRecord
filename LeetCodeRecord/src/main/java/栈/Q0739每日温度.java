package 栈;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q0739每日温度 {
    public static void main(String[] args) {
        int[] temperatures = {73,74,75,71,69,72,76,73};
    }
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();//单减栈
        int[] result = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {
            int t = temperatures[i];
            if(stack.isEmpty()) {
                stack.push(i);
            }else{
                while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                    int preIndex = stack.pop();
                    result[preIndex] = i - preIndex;
                }
                stack.push(i);
            }
        }
        return result;
    }
}
