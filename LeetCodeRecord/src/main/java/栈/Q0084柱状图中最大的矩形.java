package 栈;

import 链表.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class Q0084柱状图中最大的矩形 {
    public static void main(String[] args) {
        int[] input = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(input));
    }
    public static int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();//存下标,注意是下标
        //寻找左右两侧第一个比当前值小的边界（边界值不算入），就可以推出然后计算以它为高的最大矩形
        int n = heights.length;
        int[] newHeight = new int[n+2];//哨兵
        newHeight[0] = 0;
        newHeight[n+1] = 0;
        int maxArea = 0;
        System.arraycopy(heights, 0, newHeight, 1, n);
        for(int i=0;i<newHeight.length;i++){
            while(!stack.isEmpty() && newHeight[stack.peek()] > newHeight[i]){
                int h = newHeight[stack.pop()], leftIndex = stack.peek(), rightIndex = i;
                maxArea = Math.max(maxArea, (rightIndex-leftIndex-1)*h);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
