package 携程.D20260412;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
public class T1答案 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String line = br.readLine();
        if (line == null) return;
        StringTokenizer st = new StringTokenizer(line);

        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            // 降维打击：O(1) 的数学判断
            if (n < 4) {
                pw.println("-1");
            } else {
                // x 永远取最小合数 4，y 就是 2*n - 4
                pw.println("4 " + (2 * n - 4));
            }
        }

        pw.flush();
        pw.close();
    }
}
