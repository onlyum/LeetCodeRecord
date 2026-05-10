package 回溯;

import java.util.ArrayList;
import java.util.List;

public class Q0017电话号码的组合 {
    public static void  main(String[] args){
        String digits = "23";
        System.out.println(letterCombinations(digits));
    }
    private static String[] reflect = {
            "","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"
    };
    static List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        DFS(ans ,digits, 0, new StringBuilder());
        return ans;
    }
    static void DFS(List<String> ans,String digits, int index, StringBuilder curPath){
        //中止条件：找完这个字符串就添加到结果,index为当前字符串长度
        if(curPath.length() == digits.length()){
            ans.add(curPath.toString());
            return;
        }
        String dig = reflect[digits.charAt(index)-'0'];
        for(int i=0;i<dig.length();i++){//遍历当前数字的字符串
            curPath.append(dig.charAt(i));
            DFS(ans, digits, index+1, curPath);//递归下一数字的字符串
            curPath.deleteCharAt(curPath.length()-1);
        }
    }
}
