package 华为.T20260422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class T1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int count = 2;
        while(count-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            //List顺序结构，记录原始顺序
            List<String[]> record = new ArrayList<>();
            //图用hashmap结构存储，key为当前节点，value为下一节点
            HashMap<Integer,List<Integer>> graph = new HashMap<>();
            //加一个版本记录，节点：版本
            HashMap<Integer,Integer> maxVersion = new HashMap<>();
            while(n-->0){
                String str = br.readLine();
                String[] strSplit = str.split(",");
                record.add(strSplit);//记录顺序
                int u = Integer.parseInt(strSplit[0]);
                int v = Integer.parseInt(strSplit[1]);
                int w = Integer.parseInt(strSplit[2]);

                // 保证节点均不空
                graph.putIfAbsent(u, new ArrayList<>());
                graph.putIfAbsent(v, new ArrayList<>());
                graph.get(u).add(v);

                maxVersion.put(v, Math.max(w, maxVersion.getOrDefault(v, 0)));
            }

            if(hasCycle(graph)){
                pw.println("false");
            }else{
                for(String[] r:record){
                    pw.println(r[0]+','+r[1]+','+maxVersion.get(Integer.parseInt(r[1])));
                }
            }
        }

        pw.flush();
        pw.close();
    }
    /**
     * 判断有向图中是否存在环（DFS + 三色标记法）
     * state: 0 = 未访问, 1 = 访问中(在当前路径栈上), 2 = 已访问且安全
     */
    static boolean hasCycle(HashMap<Integer,List<Integer>> graph){
        HashMap<Integer, Integer> state = new HashMap<>();//记录状态，0默认未访问，1访问中，2已访问且安全
        for(int key:graph.keySet()){
            state.put(key, 0);
        }

        //遍历每一个起点
        for(int key:graph.keySet()){
            if(state.get(key)==0){//如果节点未访问则进行递归
                if(dfs(graph, state, key)){//有环
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 深度优先搜索，遇到正在访问中的节点即为发现环
     */
    static boolean dfs(HashMap<Integer,List<Integer>> graph, HashMap<Integer, Integer> state, int curNode){
        int curState = state.get(curNode);
        if(curState == 1)   return true;//找到环
        if(curState == 2)   return false;//它所有的子孙节点都探查完毕，无环，直接返回

        state.put(curNode, 1);//访问中

        for(int nex:graph.get(curNode)){//递归下一个节点
            if(dfs(graph, state, nex)){
                return true;
            }
        }

        //已安全访问（它所有的子孙节点都探查完毕，无环）
        state.put(curNode, 2);
        return false;
    }
}
