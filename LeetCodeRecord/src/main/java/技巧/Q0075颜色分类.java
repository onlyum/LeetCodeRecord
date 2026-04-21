package 技巧;/*
75.Sort Colors
 */

//20260413
public class Q0075颜色分类 {
    public static void main(String[] args){
        int[] in = {2,0,1};
        sortColors(in);
        for(int i=0;i<in.length;i++){
            System.out.print(in[i] + (i==in.length-1?"":" "));
        }
    }
    public static void sortColors(int[] nums) {
        //在遍历过程中，数组被分为四个部分
        //[0, left - 1]：全是 0
        //[left, i - 1]：全是 1
        //[i, right]：未扫描的区域
        //[right + 1, len - 1]：全是 2

        int len = nums.length;
        int left=0,right=len-1,i=0;
        //----注意必须有二次检查，因为从左到右遍历，所以二次检查放右边
        while(i<=right){
            if(nums[i]==0){
                swap(nums, left, i);
                left++;
                i++;//--注意在==2时做二次检查，此处不做，因为左边交换的只有可能是0或1
            }else if(nums[i]==1){
                i++;
            }else if(nums[i]==2){
                swap(nums, right, i);
                right--;
                //--不更新i，做二次检查，因为交换过来的可能是0或1
            }
        }
    }
    public static void swap(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
