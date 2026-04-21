package 子串;

import java.util.HashMap;
import java.util.Map;

class Q0076最小覆盖子串 {
    public static void main(String[] args) {
        String res = minWindow("a", "a");
        System.out.println(res);
    }

    public static String minWindow(String s, String t) {
        //hashmap存已出现字符：次数
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> sub = new HashMap<>();
        int sLen = s.length();
        //初始化子hash
        for(char c:t.toCharArray()){
            sub.put(c, sub.getOrDefault(c, 0) + 1);
        }
        int left = 0,right = 0;//滑动窗口左右边界
        int resL = -1, resR = -1, minLen = Integer.MAX_VALUE;
        while(right < sLen){//从right为0开始，右边界扩张
            if(sub.containsKey(s.charAt(right))){//子串有改字符，则加入滑动窗口
                window.put(s.charAt(right), window.getOrDefault(s.charAt(right), 0) + 1);
            }
            //注意while循环，父串涵盖子串的状态，左边界收缩直到不包含
            while(check(window, sub) && left<=right){
                if(right-left+1 < minLen){
                    resL = left;
                    resR = right;
                    minLen = right-left+1;
                }
                if(sub.containsKey(s.charAt(left))){//滑动窗口有则减一，没有可以不用管
                    window.put(s.charAt(left), window.getOrDefault(s.charAt(left), 0) - 1);
                }
                left++;
            }
            right++;
        }
        return resL==-1?"":s.substring(resL, resR+1);
    }
    public static boolean check(Map<Character, Integer> window, Map<Character, Integer> sub) {
        for(Map.Entry<Character, Integer> entry:sub.entrySet()){//遍历子串key,滑动窗口覆盖不了则返回false
            Character key = entry.getKey();
            Integer val = entry.getValue();
            if(window.getOrDefault(key, 0) < val){
                return false;
            }
        }
        return true;
    }
}