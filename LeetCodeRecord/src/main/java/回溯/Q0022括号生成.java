package 回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q0022括号生成 {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(generateParenthesis(n));
    }
    static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        DFS(res, new StringBuilder(), n, n);
        return res;
    }
    static void DFS(List<String> res, StringBuilder curPath, int left, int right) {
        if(left==0&&right==0){
            res.add(curPath.toString());
            return;
        }
        if(left>0){//左括号入
            curPath.append('(');
            DFS(res, curPath, left-1, right);
            curPath.deleteCharAt(curPath.length()-1);
        }
        if(right>0 && right>left){//右括号入
            curPath.append(')');
            DFS(res, curPath, left, right-1);
            curPath.deleteCharAt(curPath.length()-1);
        }
    }
}
