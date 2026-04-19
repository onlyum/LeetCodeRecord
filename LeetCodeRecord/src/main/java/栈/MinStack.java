package 栈;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        minStack.pop();
        minStack.top();
        minStack.getMin();
    }
    Deque<Integer> minQueue;
    Deque<Integer> queue;
    public MinStack() {
        minQueue = new ArrayDeque<>();
        minQueue.push(Integer.MAX_VALUE);
        queue = new ArrayDeque<>();
    }

    public void push(int val) {
        queue.push(val);
        minQueue.push(Math.min(val, minQueue.peek()));
    }

    public void pop() {
        minQueue.pop();
        queue.pop();
    }

    public int top() {
        return queue.peek();
    }

    public int getMin() {
        return minQueue.peek();
    }
}
