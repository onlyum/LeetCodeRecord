package 华为;
//找二叉树中“平衡路径”的数量
//45%
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/*
问题：
源码核心 Bug 分析
1、建树逻辑（队列反序列化）错误：
在遇到 "None" 时，你的代码创建了一个值为 MIN_VALUE 的节点并将其加入到了队列 queue 中。这是一个致命错误。二叉树在层序遍历序列化时，空节点是没有子节点的。如果把空节点也加入队列，下一层循环会尝试为这个空节点寻找左右孩子，这会导致数组 index 越界错位，建出来的树完全是错的。

2、ifPH 回溯寻找路径的逻辑错误：
你在 solve 中调用了 ifPH(temp, root)。但在 ifPH 方法内部，你的递归写的是 ifPH(start, end.parent)。因为此时 end 是 root，root.parent 是 null，所以它只对比了 temp 和 root 的前缀和就直接结束了。它根本没有遍历从当前节点向上到根节点的中间所有祖先节点。

3、提前返回导致漏算（Under-counting）：
ifPH 中写了 if(start.preSum == end.preSum){return 1;}。如果一条路径上有多个子段和为 0（比如遇到值为 0 的节点），直接 return 1 会导致漏掉其他的合法路径，没有穷尽所有可能性。

4、隐藏的空指针异常 (NullPointerException)：
在 solve 方法中：

Java
if(temp.left!=null) {
    stack.push(temp.left);
    stack.push(temp.right); // 这里 temp.right 可能是 null！
}
如果 temp.right 是 null，它被压入了栈。下一次循环 temp = stack.pop() 取出 null，接着调用 temp.left 就会直接抛出 NPE。
 */

public class T2 {
    static class Node{
        Node left;
        Node right;
        Node parent;
        int data;
        int preSum;
        public Node(){}
        public Node(int data){
            this.data = data;
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter pw = new PrintWriter(System.out);
        String s = sc.nextLine().trim();
        if(s.isEmpty()){
            pw.println(0);
            pw.flush();
            pw.close();
            return;
        }
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        if(!st.hasMoreTokens()) return;
//        String s = st.toString();
        StringBuffer sb = new StringBuffer();
        if(s.charAt(0) != '['){
            pw.println(0);
            pw.flush();
            pw.close();
            return;
        }
        s = s.substring(1,s.length()-1);
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i)!=' ')
                sb.append(s.charAt(i));
        }
        s = sb.toString();
//        pw.println(sb.toString());
//        pw.flush();
//        pw.close();

        String[] strs = s.split(",");
        int len = strs.length;

        int index = 0, first =0;
        Node root = new Node(Integer.MIN_VALUE);
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(Integer.parseInt(strs[0])));
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){//取出i层
                if(first==0){
                    root = queue.peek();
                    first = 1;
                    root.preSum = root.data;
                }
                for(int j=0;j<2;j++){
                    index++;
                    Node node;
                    if(strs[index].equals("None")){node = new Node(Integer.MIN_VALUE);}
                    else {node = new Node(Integer.parseInt(strs[index]));}
                    if(j==0) queue.peek().left = node;
                    else queue.peek().right = node;
                    node.parent = queue.peek();
                    node.preSum = node.data + node.parent.preSum;
//                    [10,-5,-5,2,-2,3,-3]
                    queue.offer(node);
                }
                queue.poll();
            }
            if(index == len-1)  break;
        }
        //[10, -5, -5,2,- 2,3,-3]
        int res = solve(root);
//        pw.println(s);
        pw.println(res);
        pw.flush();
        pw.close();
    }
    //[1,-1,2,-2,None,3,-3]
    /*
10(10)
-5(5) -5(5)
2(7) -2(3) 3(8) -3(2)

1(1)
-1(0) 2(3)
-2(-2) None 3(6) -3(0)
 */
    static int solve(Node root){
        if(root == null) return 0;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        //算每个节点的preSum-链路上的某个preSum等于0即可
        int count = 0;
        while(!stack.isEmpty()){
            Node temp = stack.pop();
            count += ifPH(temp, root);
            if(temp.left!=null) {
                stack.push(temp.left);
                stack.push(temp.right);
            }
        }
        return count;
    }
    static int ifPH(Node start, Node end){
        if(start == end) return 0;
        if(end==null){return 0;}
        if(start.preSum == 0){return 1;}//整条路和为0
        if(start.preSum == end.preSum){return 1;}//去头之后的，某个端和为0
        return ifPH(start,end.parent);
    }
}
