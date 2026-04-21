package 技巧;/*
## **摩尔投票算法 (Boyer-Moore Voting Algorithm)**。

它的核心逻辑非常霸气，可以总结为一句话：**“不同的数同归于尽，最后活下来的就是多数元素。”**
 */
//一刷
//class 技巧.Q0169 {
//    public int majorityElement(int[] nums){
//        int candidate = 0;
//        int count = 0;
//        for(int num:nums){
//            if(count == 0){
//                candidate = num;
//            }
//            if(candidate == num){
//                count++;
//            }else{
//                count--;
//            }
//        }
//        return candidate;
//    }
//}

//20260413
class Q0169多数元素 {
    public static void main(String[] args){
        int[] in = {2, 3, 4, 1, 2, 1, 1, 1, 1, 5};
        System.out.println(majorityElement(in));
    }
    //题目前提，存在众数
    public static int majorityElement(int[] nums){
        int leiZhu = nums[0];// 初始擂主
        int remain = 1;// 初始血量
        for(int i=1;i<nums.length;i++){
            //当血量为0时，说明上一波同归于尽了，赶紧换新擂主，重置血量
            if(remain == 0){
                leiZhu = nums[i];
                remain = 1;
                continue;
            }
            // 如果擂台还有人，判断是敌是友
            if(nums[i] != leiZhu){
                remain--;
            }else{
                remain++;
            }
        }
        return leiZhu;
    }
}