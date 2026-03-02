package org.example;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        if(s.length() < p.length()){
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        int[] sArr = new int[26];
        int[] pArr = new int[26];
        for (int i = 0; i < p.length(); i++) {
            sArr[s.charAt(i) - 'a']++;
            pArr[p.charAt(i) - 'a']++;
        }
        if (Arrays.equals(sArr, pArr)){
            ans.add(0);
        }
        for(int i = 0; i < s.length() - p.length(); i++){
            sArr[s.charAt(i) - 'a']--;
            sArr[s.charAt(i + p.length()) - 'a']++;
            if(Arrays.equals(sArr, pArr)){
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
