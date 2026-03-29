class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        // 关键：先排序，为了后续的“剪枝”优化
        Arrays.sort(candidates);

        backtracking(candidates, target, 0, path, res);
        return res;
    }

    private void backtracking(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        // 1. 终止条件：如果目标和减到了 0，说明找到了一组解
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 2. 横向遍历：从 start 开始，防止产生重复组合（比如 [2,3] 和 [3,2]）
        for (int i = start; i < candidates.length; i++) {

            // --- 核心优化：剪枝 ---
            // 如果当前数字已经大于目标值，因为数组是有序的，后面的数字肯定也大
            // 直接 break 结束本层循环，节省大量计算空间
            if (target - candidates[i] < 0) {
                break;
            }

            // 3. 做选择
            path.add(candidates[i]);

            // 4. 递归纵向探索
            // 关键点：传入 i 而不是 i + 1，因为题目允许“同一个数字重复选取”
            backtracking(candidates, target - candidates[i], i, path, res);

            // 5. 回溯：撤销选择，回到上一层状态
            path.remove(path.size() - 1);
        }
    }
}