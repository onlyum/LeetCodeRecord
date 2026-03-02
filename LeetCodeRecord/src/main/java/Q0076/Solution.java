package Q0076;
import java.util.Map;
import java.util.HashMap;

public class Solution {
    Map<Character, Integer> par = new HashMap<Character, Integer>();    //窗口HashMap
    Map<Character, Integer> sub = new HashMap<Character, Integer>();    //子串HashMap
    public String minWindow(String s, String t) {
        // 初始化子串对应的HashMap:(字符，字符出现次数)
        for(int i=0;i<t.length();i++){
            char c = t.charAt(i);
            sub.put(c, sub.getOrDefault(c, 0) + 1);
        }

        int l = 0, r = -1, len = Integer.MAX_VALUE, ansL = -1, ansR = -1, sLen = s.length();
        while(r < sLen){
            r++;
            if(r < sLen && sub.containsKey(s.charAt(r))){
                par.put(s.charAt(r), par.getOrDefault(s.charAt(r), 0) + 1); //只对子串中出现的字符，窗口对应的HashMap对应记录出现次数
            }
            while(check() && l<=r){ //检查子串中字符出现次数是否被窗口HashMap覆盖，如果覆盖，则记录当前窗口左右位置 + 更新窗口
                if(r - l + 1 < len){
                    len = r - l + 1;    //更新滑动窗口长度，以寻找最小值
                    ansL = l;
                    ansR = l + len;
                }

                //  向右滑动，更新窗口的HashMap
                if(sub.containsKey(s.charAt(l))){
                    par.put(s.charAt(l), par.getOrDefault(s.charAt(l), 0) - 1);
                }
                l++;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public boolean check(){
        for(Map.Entry<Character, Integer> entry : sub.entrySet()){  //基于子串HashMap遍历，如果对应窗口的字符个数小于子串则false，反之true
            Character c = entry.getKey();
            Integer val = entry.getValue();
            if(par.getOrDefault(c, 0) < val){
                return false;
            }
        }
        return true;
    }
}
