package Q0045;

public class Solution {
    public int jump(int[] nums) {
        int maxArrive = 0;//维护总的最大可达
        int nextStep = 0;//维护下一步最大可达
        int step = 0;//走的步数
        for(int i=0;i<nums.length-1;i++){//
            maxArrive = Math.max(maxArrive, i+nums[i]);
            /*
            当你走到 nextStep 时，你必须得“再跳一次”（step++），并把刚才路上捡到的最好弹药（maxArrive）换上，作为新的边界。
题目中假设你总是可以到达数组的最后一个位置，所以
如果你已经走到了倒数第二个格子，并且 nextStep 还没到终点，那你肯定得再跳一次。
一旦你这一跳的边界 nextStep 已经足够你到达最后一个格子，你就不需要站在最后一个格子上再开启新的一跳了。
            */
            if(i == nextStep){
                nextStep = maxArrive;
                step++;
            }
        }
        return step;
    }
}