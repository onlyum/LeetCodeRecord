package Q0238;

public class Main {
    public static void main(String[] args){
        int[] nums = {1,2,3,4};
        Solution solution = new Solution();
        int[] output = solution.productExceptSelf(nums);
        for (int i = 0; i < output.length; i++){
            System.out.print(output[i] + " ");
        }
    }
}
