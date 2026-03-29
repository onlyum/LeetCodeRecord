package Q0017;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private String[] map = {
            "","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"
    };
    private List<String> res = new ArrayList<>();   //记录结果集
    private StringBuilder path = new StringBuilder();   //记录当前字符串路径
    public List<String> letterCombinations(String digits){
        if(digits==null||digits.length()==0)  return res;
        backtracking(digits, 0);
        return res;
    }
    private void backtracking(String digits, int index){
        //1.中止条件：找完这个字符串就添加到结果
        if(index == digits.length()){
            res.add(path.toString());
            return;
        }
        String letters = map[digits.charAt(index) - '0'];
        //2.遍历当前层
        for(int i=0;i<letters.length();i++){
            path.append(letters.charAt(i));//3.加入遍历的字母
            backtracking(digits, index+1);//4.递归
            path.deleteCharAt(path.length()-1);//5.回溯删除刚遍历的字母，以进行同层遍历其他字母
        }
    }
}