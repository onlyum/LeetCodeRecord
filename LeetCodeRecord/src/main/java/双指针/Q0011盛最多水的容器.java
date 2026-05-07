package 双指针;

public class Q0011盛最多水的容器 {
    public int maxArea(int[] height) {
        int len = height.length, left = 0, right = len-1, max = 0;
        while(left < right){
            int curArea = (right - left)*Math.min(height[left], height[right]);
            max = Math.max(curArea, max);
            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }
        }
        return max;
    }
}
