/*
55.跳跃游戏
 */


public class Q0055 {
    public static void main(String[] args){
        int[] input = {7,1,5,3,6,4};
        System.out.println(canJump(input));
    }
    //2026/04/17二刷
    //贪心：遍历每个格子，记录从当前格作起点，最远到达位置，不断更新最远到达位置
    static boolean canJump(int[] nums) {
        int len = nums.length;
        int maxArrive = 0;//最大可达下标
        for(int i=0;i<len;i++){
            //注意条件，当前格在上一步最大可达范围内，才可以更新
            if(i <= maxArrive)   maxArrive = Math.max(i+nums[i], maxArrive);
            if(maxArrive >= len)    return true;
        }
        return false;
    }
//    public boolean canJump(int[] nums) {
//        int maxArrive = 0;
//        for(int i=0;i<nums.length;i++){
//            if(i<=maxArrive){
//                maxArrive = Math.max(maxArrive, i+nums[i]);
//            }
//            if(maxArrive >= nums.length - 1){
//                return true;
//            }
//        }
//        return false;
//    }
}