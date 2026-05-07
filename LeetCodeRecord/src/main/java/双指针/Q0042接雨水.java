package 双指针;

public class Q0042接雨水 {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1, leftMax = 0, rightMax = 0, ans = 0;
        while(left < right){
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if(height[left] < height[right]){
                ans += leftMax - height[left];
                left++;
            }else{
                ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }
    //动规方法
//    public int trap(int[] height) {
//        int ans = 0, n = height.length;
//        if(n == 0) return 0;
//
//        int[] leftMax = new int[n];
//        leftMax[0] = height[0];
//        for(int i = 1; i < n; i++){
//            leftMax[i] = Math.max(leftMax[i-1], height[i]);
//        }
//
//        int[] rightMax = new int[n];
//        rightMax[n-1] = height[n-1];
//        for(int i = n-2; i >= 0; i--){
//            rightMax[i] = Math.max(rightMax[i+1], height[i]);
//        }
//
//        for(int i = 0; i < n; i++){
//            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
//        }
//        return ans;
//    }
}
