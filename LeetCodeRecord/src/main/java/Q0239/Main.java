package Q0239;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        Solution sol = new Solution();
        int[] res = sol.maxSlidingWindow(nums, k);
        for(int i = 0; i < res.length; i++){
            System.out.println(res[i]);
        }
    }
}
