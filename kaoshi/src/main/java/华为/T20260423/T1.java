package 华为.T20260423;

/**
 * 描述
 * 爱丽丝在人偶制作的过程中，为了方便维护众多的“上海人偶”，为每个人偶标记了不同的版本号。随着人偶版本的更迭，版本号的排布变得十分混乱，她需要你帮忙将这些版本号按从小到大进行整理。
 * 每个版本号字符串由主版本号和可选的测试版本号组成：
 * 1. 主版本号：由 1 至 4 个非负整数组成，整数之间用 `.` 分隔（例如 `1.0.2`）。
 * 2. 测试版本号：若存在，则位于主版本号之后，以空格分隔，格式为 `betaX`，其中 X
 * X 为正整数（例如 `1.0.2 beta3`）。若不包含此部分，则该版本为正式版。
 * 排序规则如下：
 * 1. 比较主版本号：从左至右依次比较对应位置的整数。若在某个位置数字不同，则数字较小者对应的版本号较小；若一个主版本号是另一个的前缀且两者长度不同，则较短者较小（例如 `1.0` < `1.0.0`）。
 * 2. 当主版本号完全相同时：
 * - 测试版总是小于正式版（例如 `1.0.0 beta9` < `1.0.0`）。
 * - 若两者均为测试版，则比较 `beta` 后续的整数 X，X数字较小者对应的版本号较小。

 * 输入描述：
 * 第一行包含一个整数 n表示版本号的数量。
 * 接下来的 n行，每行包含一个符合上述格式的版本号字符串。
 * 主版本号的每个部分均为 [0,1000]
 * [0,1000] 范围内的整数，且不含多余的前导零（除了数字 `0` 本身）。若存在 `beta` 字段，其后的数字 X
 * X 亦在 [1,1000]范围内。

 * 输出描述：
 * 输出共 n 行，每行一个字符串，表示按升序排列后的版本号序列。
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

//版本整理
public class T1 {
    static class Version implements Comparable<Version> {
        String origin;  //原始数据
        int[] mainParts; //主版本号
        int betaNum;    //测试版本号
        public Version(String origin) {
            this.origin = origin;
            String[] splitSpace = origin.split(" ");//空格分割
            //主版本号提取
            String[] splitMainParts = splitSpace[0].split("\\.");
            mainParts = new int[splitMainParts.length];
            for(int i = 0; i < mainParts.length; i++) {
                mainParts[i] = Integer.parseInt(splitMainParts[i]);
            }
            //测试版本号提取
            if(splitSpace.length > 1){
                betaNum = Integer.parseInt(splitSpace[1].substring(4));
            }else{
                betaNum = 1111;
            }
        }
        @Override
        public int compareTo(Version other) {
            //1、先比较主版本号
            //前缀部分
            int len = Math.min(this.mainParts.length, other.mainParts.length);
            for(int i = 0; i < len; i++) {
                if(this.mainParts[i] != other.mainParts[i]) {
                    return Integer.compare(this.mainParts[i], other.mainParts[i]);
                }
            }
            //若前缀相同，长度不同则短的在前
            if(this.mainParts.length != other.mainParts.length) {
                return Integer.compare(this.mainParts.length, other.mainParts.length);
            }

            //2.若主版本号相同，则比较beta
            return  Integer.compare(this.betaNum, other.betaNum);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st =  new StringTokenizer(br.readLine().trim());
        if(!st.hasMoreTokens())  return;
        int n = Integer.parseInt(st.nextToken());
        Version[] input = new Version[n];
        for(int i = 0; i < n; i++) {
            input[i] =  new Version(br.readLine().trim()) ;
        }

        Arrays.sort(input);

        for(int j = 0; j < n; j++) {
            pw.println(input[j].origin);
        }

        pw.flush();
        pw.close();
    }
}
