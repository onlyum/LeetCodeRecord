package 网易互娱.D20260409;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
模拟SQL
GROUP BY user_name
HAVING COUNT(DISTINCT user_id)>=2
通过name分组，找同一name中不同id有2个及以上的
 */
public class T1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens()) return;
        int n = Integer.parseInt(st.nextToken());
        int res = 0;
        HashMap<String, String> map = new HashMap<>();//存user_name：HashSet<user_id>
        HashMap<String, Integer> count = new HashMap<>();//存user_name：不同user_id个数
        while(n-- > 0) {
            st = new StringTokenizer(br.readLine());
            String user_id = st.nextToken();
            String user_name = st.nextToken();

            if(count.containsKey(user_name)) {
                if(count.get(user_name) >= 2) {//不同id有两个以上了
                    continue;
                }else  {
                    String hs = map.get(user_name);//取出所有id
                    if(!user_id.equals(hs)) {//不重复就更新个数
                        count.put(user_name, count.get(user_name) + 1);
                        res++;//第一次更新记录
                    }
                }
            }else{
                count.put(user_name, 1);
                map.put(user_name, user_id);
            }
        }

        pw.print(res);
        pw.flush();
        pw.close();
    }
}
