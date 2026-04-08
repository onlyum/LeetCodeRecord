package Q0004;
/*
4. 寻找两个正序数组的中位数

给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
算法的时间复杂度应该为 O(log (m+n)) 。

示例 1：

输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2

示例 2：

输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 */
public class Main {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //以左边数组开始个数少为例，i较小可保证j不越界
        if(nums1.length > nums2.length){
            return findMedianSortedArrays(nums2, nums1);
        }
        //1、找左右两部分，左右数量相等或左边多一个
        //左半部分全部小于右半部分,要求nums1[i-1] <= nums2[j] && nums2[j-1] <= nums1[i]
        //左：nums1[0,i-1],nums2[0,j-1]
        //右：nums1[i,m-1],nums2[j,n-1]
        int m = nums1.length, n = nums2.length, half = (m + n + 1) / 2;

        //2、二分遍历，注意是切割位置，遍历[0, m]共m+1个切割位置
        int left = 0, right = m;
        while (left <= right) {
            int i = (left + right) / 2, j = half - i;
            if(left < i && nums1[i-1] > nums2[j]){
                right = i - 1;
            }else if(i < right && nums2[j-1] > nums1[i]){
                left = i + 1;
            }else{
                //3、奇数：返回左部分最大值；偶数：返回（左最大+右最小）/2.0
                //求左部分最大值
                int leftMax = 0;
                if(i==0){
                    leftMax = nums2[j-1];
                }else if(j==0){
                    leftMax = nums1[i-1];
                }else{
                    leftMax = Math.max(nums1[i-1], nums2[j-1]);
                }

                if((m+n)%2==1){
                    return leftMax;
                }else{
                    //求右部分最小
                    int rightMin = 0;
                    if(i==m){
                        rightMin = nums2[j];
                    }else if(j==n){
                        rightMin = nums1[i];
                    }else{
                        rightMin = Math.min(nums1[i], nums2[j]);
                    }
                    return (leftMax+rightMin)/2.0;
                }
            }
        }
        return 0.0;
    }
    public static void main(String[] args) {

        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4, 5, 6};

        double result = findMedianSortedArrays(nums1, nums2);

        System.out.println("数组 1: " + java.util.Arrays.toString(nums1));
        System.out.println("数组 2: " + java.util.Arrays.toString(nums2));
        System.out.println("计算出的中位数: " + result);
    }
}
