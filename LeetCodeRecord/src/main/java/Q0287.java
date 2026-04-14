/*
287. Find the Duplicate Number
 */

import java.util.Arrays;

//20260413
public class Q0287 {
    public static void main(String[] args){
        int[] nums = {1,3,4,2,2};
        int output = findDuplicate(nums);
        System.out.print(output);
    }
    public static int findDuplicate(int[] nums) {
        //注意0为起点，此处已经走过一步
        int slow = nums[0];
        int fast = nums[nums[0]];
        //1、找相遇点
        while(slow!=fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        //2、找环入口
        slow = 0;
        while(slow!=fast){
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
