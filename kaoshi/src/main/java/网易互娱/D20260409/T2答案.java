package 网易互娱.D20260409;
import java.io.*;

public class T2答案 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();

        // 修复 1 & 2：处理 null 或者完全为空的情况，必须保证输出 0
        if (line == null || line.trim().isEmpty()) {
            System.out.println(0);
            return;
        }

        int res = 0;
        int len = line.length();
        int i = 0;

        while (i < len) {
            char c = line.charAt(i);

            // 精确跳过所有类型的空白字符（包括空格、Tab等）
            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            if (isAlphanumeric(c)) {
                // 字母或数字：贪婪匹配，连续的算作 1 个 Token
                while (i < len && isAlphanumeric(line.charAt(i))) {
                    i++;
                }
                res++;
            } else {
                // 汉字、标点或符号：每个字符单独算作 1 个 Token
                i++;
                res++;
            }
        }

        // 输出最终结果
        System.out.println(res);
    }

    /**
     * 判断是否为英文字母或数字
     */
    private static boolean isAlphanumeric(char c) {
        return (c >= 'A' && c <= 'Z') ||
                (c >= 'a' && c <= 'z') ||
                (c >= '0' && c <= '9');
    }
}
