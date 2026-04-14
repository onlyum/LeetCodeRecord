//20260413
public class Q0136 {
    public static void main(String[] args){
        int[] in = {4,1,2,1,2};
        System.out.print(singleNumber(in));
    }
    public static int singleNumber(int[] nums) {
        int res = nums[0];
        for(int i=1;i<nums.length;i++){
            res = res^nums[i];
        }
        return res;
    }
}
