package 贪心;

public class Q0045跳跃游戏二 {
    public static void main(String[] args){
        int[] input = {7,1,5,3,6,4};
        System.out.println(jump(input));
    }
    //2026/04/17二刷
    //贪心：题干默认一定可达，遍历无需判断是否可达
    //每次遍历到上一步最大可达位置，步数就加一（即要求每一步都要走到最远）
    static int jump(int[] nums) {
        int len = nums.length;
        int step = 0;
        int maxArrive = 0;//全局最大可达位置下标
        int nextStep = 0;//维护下一步最大可达
        for(int i=0;i<len;i++){//
            maxArrive = Math.max(maxArrive, i+nums[i]);//更新最大可达
            if(i==nextStep){//更新下一步最大可达
                step++;
                nextStep = maxArrive;
            }
        }
        return --step;//到达
    }
//    static int jump(int[] nums) {
//        int maxArrive = 0;//维护总的最大可达
//        int nextStep = 0;//维护下一步最大可达
//        int step = 0;//走的步数
//        for(int i=0;i<nums.length-1;i++){//
//            maxArrive = Math.max(maxArrive, i+nums[i]);
//            /*
//            当你走到 nextStep 时，你必须得“再跳一次”（step++），并把刚才路上捡到的最好弹药（maxArrive）换上，作为新的边界。
//题目中假设你总是可以到达数组的最后一个位置，所以
//如果你已经走到了倒数第二个格子，并且 nextStep 还没到终点，那你肯定得再跳一次。
//一旦你这一跳的边界 nextStep 已经足够你到达最后一个格子，你就不需要站在最后一个格子上再开启新的一跳了。
//            */
//            if(i == nextStep){
//                nextStep = maxArrive;
//                step++;
//            }
//        }
//        return step;
//    }
}