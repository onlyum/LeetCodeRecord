package Q0169;

/*
## **摩尔投票算法 (Boyer-Moore Voting Algorithm)**。

它的核心逻辑非常霸气，可以总结为一句话：**“不同的数同归于尽，最后活下来的就是多数元素。”**
 */
class Solution {
    public int majorityElement(int[] nums){
        int candidate = 0;
        int count = 0;
        for(int num:nums){
            if(count == 0){
                candidate = num;
            }
            if(candidate == num){
                count++;
            }else{
                count--;
            }
        }
        return candidate;
    }
}