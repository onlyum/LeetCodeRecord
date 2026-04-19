package 贪心;

import java.util.*;

public class Q0763划分字母区间 {
    public static void main(String[] args){
        String input = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(input));
    }
    static List<Integer> partitionLabels(String s) {
        HashMap<Character, Integer> map = new HashMap<>();//map记录每个字母最后出现位置
        for(int i=0;i<s.length();i++){
            map.put(s.charAt(i), i);
        }
        int start=0,end=0;
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<s.length();i++){//更新end为当前字母最后出现的下标,成功走到end说明中间的字符都满足条件，则添加到结果
            end = Math.max(end, map.get(s.charAt(i)));
            if(i==end)  {
                list.add(end - start + 1);
                start = end + 1;
            }
        }
        return list;
    }
}
