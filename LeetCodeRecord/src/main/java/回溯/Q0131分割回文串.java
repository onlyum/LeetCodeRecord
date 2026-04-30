package 回溯;

import java.util.ArrayList;
import java.util.List;

public class Q0131分割回文串 {
    public static void main(String[] args) {
        String s = "aab";
        System.out.println(partition(s));
    }
    static boolean[][] dp;
    static List<List<String>> res;
    static List<String> path;
    //公用path则需要回溯
    static List<List<String>> partition(String s) {
        res = new ArrayList<>();
        path = new ArrayList<>();

        int n = s.length();
        dp = new boolean[n][n];//记录[i,j]是否回文

        //注意内外层遍历顺序
        //1、dp[i][j]依赖dp[i+1][j-1]，最外层循环按行来则让i从大到小
        for(int i = n-1; i >= 0; i--){
            for(int j = i; j < n; j++){
                if(s.charAt(i) == s.charAt(j)&&(j-i<=2||dp[i+1][j-1])){
                    dp[i][j] = true;
                }
            }
        }
        DFS(s, 0);
//        DFS(s, 0, new ArrayList<String>());
        return res;
        //2、dp[i][j]依赖dp[i+1][j-1]，最外层循环按列来则让j从小到大
//        for(int j = 0;j<n;j++){
//            for(int i = 0;i<=j;i++){
//                if(s.charAt(i) == s.charAt(j) && (j-i<=2 || dp[i+1][j-1])){
//                    dp[i][j] = true;
//                }
//            }
//        }
    }
    static void DFS(String s, int startIndex){//以startIndex为开始字母索引，进行递归遍历
        if(startIndex == s.length()){
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i = startIndex; i < s.length(); i++){
            if(dp[startIndex][i]){//如果[startIndex,i]是回文则认为是子串，切分，然后递归i+1后的子串
                path.add(s.substring(startIndex,i+1));//当前回文加入
                DFS(s, i+1);//DFS剩余子串
                path.remove(path.size()-1);
            }
        }
    }

    //2、不共用就不需要回溯
//    static void DFS(String s, int startIndex, ArrayList<String> path){//以startIndex为开始字母索引，进行递归遍历
//        if(startIndex == s.length()){
//            res.add(new ArrayList<>(path));
//            return;
//        }
//
//        for(int i = startIndex; i < s.length(); i++){
//            if(dp[startIndex][i]){
//                // 在递归调用时新建一个列表，包含当前path的所有元素 + 新子串
//                ArrayList<String> newPath = new ArrayList<>(path);
//                newPath.add(s.substring(startIndex,i+1));
//                DFS(s, i+1, newPath);
//            }
//        }
//    }
}
