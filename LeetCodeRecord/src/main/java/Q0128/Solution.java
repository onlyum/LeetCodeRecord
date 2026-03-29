package Q0128;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int maxLength = 0;
        for (int num:set){
            if (!set.contains(num-1)){  //只有为序列首个数字才进循环
                int curNum = num;
                int curLength = 1;
                while(set.contains(curNum+1)){
                    curNum += 1;
                    curLength += 1;
                }
                maxLength = Math.max(maxLength,curLength);
            }
        }
        return maxLength;
    }
}
