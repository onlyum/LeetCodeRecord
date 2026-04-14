import java.util.Arrays;
//20260413
public class Q0031 {
    public static void main(String[] args){
        int[] in = {1,3,2};
        nextPermutation(in);
        for(int a:in){
            System.out.println(a);
        }
    }
    public static void nextPermutation(int[] nums) {
        //找第一个升序位置,重排该位置及之后部分||全降序则直接反转
        int len = nums.length;
        int startIndex = 0;

        boolean flag = false;
        for(int i =len-1;i>0;i--){
            if(nums[i-1] < nums[i]){
                flag = true;
                startIndex = i-1;
                break;
            }
        }
        if(flag){
            int rightIndex = len-1;
            //start右边降序，直接从右往左找,找到就退出
            for(int i = len-1;i>startIndex;i--){
                if(nums[i] > nums[startIndex]){
                    rightIndex = i;
                    break;
                }
            }
            int temp = nums[startIndex];
            nums[startIndex] = nums[rightIndex];
            nums[rightIndex] = temp;

            Arrays.sort(nums, startIndex+1, len);//左闭右开
        }else{
            Arrays.sort(nums);
        }
    }
}
