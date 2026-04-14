//import java.util.Map;
//import java.util.HashMap;
//
//public class Q0076 {
//    public static void main(String[] args) {
//        String res = minWindow("a", "aa");
//        System.out.println(res);
//    }
//    public static Map<Character, Integer> par = new HashMap<Character, Integer>();    //窗口HashMap
//    public static Map<Character, Integer> sub = new HashMap<Character, Integer>();    //子串HashMap
//    public static String minWindow(String s, String t) {
//        // 初始化子串对应的HashMap:(字符，字符出现次数)
//        for(int i=0;i<t.length();i++){
//            char c = t.charAt(i);
//            sub.put(c, sub.getOrDefault(c, 0) + 1);
//        }
//
//        int l = 0, r = -1, len = Integer.MAX_VALUE, ansL = -1, ansR = -1, sLen = s.length();
//        while(r < sLen){
//            r++;
//            if(r < sLen && sub.containsKey(s.charAt(r))){
//                par.put(s.charAt(r), par.getOrDefault(s.charAt(r), 0) + 1); //只对子串中出现的字符，窗口对应的HashMap对应记录出现次数
//            }
//            while(check() && l<=r){ //检查子串中字符出现次数是否被窗口HashMap覆盖，如果覆盖，则记录当前窗口左右位置 + 更新窗口
//                if(r - l + 1 < len){
//                    len = r - l + 1;    //更新滑动窗口长度，以寻找最小值
//                    ansL = l;
//                    ansR = l + len;
//                }
//
//                //  向右滑动，更新窗口的HashMap
//                if(sub.containsKey(s.charAt(l))){
//                    par.put(s.charAt(l), par.getOrDefault(s.charAt(l), 0) - 1);
//                }
//                l++;
//            }
//        }
//        return ansL == -1 ? "" : s.substring(ansL, ansR);
//    }
//
//    public static boolean check(){
//        for(Map.Entry<Character, Integer> entry : sub.entrySet()){  //基于子串HashMap遍历，如果对应窗口的字符个数小于子串则false，反之true
//            Character c = entry.getKey();
//            Integer val = entry.getValue();
//            if(par.getOrDefault(c, 0) < val){
//                return false;
//            }
//        }
//        return true;
//    }
//}
class Q0076 {
    public String minWindow(String S, String t) {
        // ASCII 表中包含大小写字母的字符范围在 128 以内
        int[] cntS = new int[128];// cntS 记录当前滑动窗口（S 的子串）中各个字符出现的次数
        int[] cntT = new int[128];// cntT 记录目标字符串 t 中各个字符出现的次数，作为匹配的“基准”
        // 遍历字符串 t，统计每个字符的出现频率
        for (char c : t.toCharArray()) {
            cntT[c]++;
        }
        // 将字符串 S 转换为字符数组，提高后续通过索引访问字符的效率
        char[] s = S.toCharArray();
        int m = s.length;

        // 记录全局最短子串的左右边界。
        // ansLeft 初始为 -1 作为一个标记，如果遍历完后它还是 -1，说明没找到符合条件的子串。
        // ansRight 初始为 m（最大可能长度+1），方便在后续逻辑中不断用更小的值覆盖它。
        int ansLeft = -1;
        int ansRight = m;

        // 窗口的左边界
        int left = 0;

        // right 代表窗口的右边界，它会主动向右扩张
        for (int right = 0; right < m; right++) {
            // 1. 将当前右端点的字符加入窗口，更新其在 cntS 中的出现次数
            cntS[s[right]]++;
            // 2. 检查当前窗口内的字符是否已经完全涵盖了 t 中的所有字符
            // 如果涵盖了，就进入循环，尝试缩小窗口（寻找最优解）
            while (isCovered(cntS, cntT)) {
                // 2.1 记录当前的最优解：
                // 如果当前窗口的长度 (right - left) 比之前记录的最短长度 (ansRight - ansLeft) 还要小
                if (right - left < ansRight - ansLeft) {
                    ansLeft = left;   // 更新最短子串的起点
                    ansRight = right; // 更新最短子串的终点
                }

                // 2.2 尝试缩小窗口：
                // 将窗口左端点对应的字符移出窗口，其在 cntS 中的计数减 1
                cntS[s[left]]--;
                // 左指针右移，窗口物理缩小。
                // 缩小后，while 循环会再次检查 isCovered。如果依然涵盖，就继续缩小；
                // 如果不涵盖了，就会退出 while 循环，让 right 继续向右扩张去寻找下一个可行解。
                left++;
            }
        }

        // 3. 遍历结束后，根据 ansLeft 判断是否找到了有效子串
        // 如果 ansLeft 依然是 -1，说明 S 中不存在涵盖 t 的子串，返回空字符串。
        // 否则，截取并返回这个最短子串（注意 substring 的右边界是左闭右开，所以要 +1）。
        return ansLeft < 0 ? "" : S.substring(ansLeft, ansRight + 1);
    }

    /**
     * 辅助函数：判断当前窗口（cntS）是否完全涵盖了目标（cntT）
     * 即：对于 t 中的每一种字符，窗口内的对应字符数量是否都大于等于它。
     */
    private boolean isCovered(int[] cntS, int[] cntT) {
        // 题目限定了只包含英文字母，所以只需要检查大写字母 'A' 到 'Z'
        for (int i = 'A'; i <= 'Z'; i++) {
            if (cntS[i] < cntT[i]) {
                return false; // 只要有一个字符的数量不够，就说明还没涵盖全
            }
        }
        // 接着检查小写字母 'a' 到 'z'
        for (int i = 'a'; i <= 'z'; i++) {
            if (cntS[i] < cntT[i]) {
                return false;
            }
        }
        // 所有需要的字符数量都达标了，返回 true
        return true;
    }
}