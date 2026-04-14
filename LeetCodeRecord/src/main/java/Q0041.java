public class Q0041 {
    public static void main(String args[]){
        int[] nums = {3,4,-1,1};
        System.out.println(firstMissingPositive(nums));
    }
    public static int firstMissingPositive(int[] nums) {
        //  结果必在[1, n+1]中
        //  首先，讨论[1,n]
        //  1、所有负值都置为n+1,则全为正整数
        int n = nums.length;
        for(int i=0;i < n;i++){
            if(nums[i] <= 0){
                nums[i] = n+1;
            }
        }
        //  2、所有[1, n]范围的数对应下标位置的数字置为负
        for(int i=0;i < n;i++){
            int num = Math.abs(nums[i]);
            if(num <= n){
                nums[num-1] = -Math.abs(nums[num-1]);
            }
        }
        //  3、剩下最左边的非负[1,n]中的数对应位置下标为答案
        for(int i=0;i < n;i++){
            if(nums[i] > 0){
                return i+1;
            }
        }
        //  最后，加入n+1
        return n+1;
    }
}
