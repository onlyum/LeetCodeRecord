package 美团.第一次.T1;
// 小美的因子数量
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int a = in.nextInt();
        int b = in.nextInt();
        System.out.println(Solution(a,b));
    }
    public static int Solution(int left, int right) {
        int res = 0;
        int start = (int) Math.sqrt(left)-1;
        for(int i=left; i<=right;){
            i = start * start;
            if(start * start>=left && start * start<= right){
                res++;
            }
            start++;
        }
        return res;
    }
}

/*
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> input = new ArrayList<>();
        // 注意 hasNext 和 hasNextLine 的区别
        int a = in.nextInt();
        int b = in.nextInt();

        System.out.println(Solution(a, b));

    }
    public static int Solution(int left, int right) {
        int res = 0;
        for (int i = left; i <= right; i++) {
            if (ifSingle(i)) {
                res++;
            }
        }
        return res;
    }
    public static boolean ifSingle(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }
}
 */