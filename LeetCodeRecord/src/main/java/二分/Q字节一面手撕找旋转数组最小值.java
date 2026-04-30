package 二分;

public class Q字节一面手撕找旋转数组最小值 {
    //注意一定是右边界为判断条件
    public int findMin(int[] nums) {
        // 如果数组为空，抛出异常或返回特定值（视具体需求而定）
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // 防止 (left + right) 溢出
            int mid = left + (right - left) / 2;
            // 如果中间值大于右边界值，说明出现了旋转（逆序），最小值肯定在右半部分
            if (nums[mid] > nums[right]) {
                left = mid + 1; // 最小值不可能是 mid，所以 left = mid + 1
            }else {
                right = mid; // 最小值可能是 mid，所以不能用 right = mid - 1
            }
        }

        // 当 left == right 时，循环结束，此时的指针就指向最小值
        return nums[left];
    }
}
