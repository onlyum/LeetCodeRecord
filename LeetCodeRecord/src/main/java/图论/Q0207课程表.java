package 图论;

import java.util.*;

public class Q0207课程表 {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1,0},{0,1}};
        System.out.println(canFinish(numCourses, prerequisites));
    }
    static boolean canFinish(int numCourses, int[][] prerequisites) {
        //入度为0即可修
        int[] indegree = new int[numCourses];
        //邻接表，i位置存所有依赖i的后续课程
        List<List<Integer>> adjacency = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            adjacency.add(new ArrayList<>());
        }
        for(int[] prereq: prerequisites){
            int houXiu = prereq[0];
            int xianXiu = prereq[1];
            indegree[houXiu]++;
            adjacency.get(xianXiu).add(houXiu);
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        int count = 0;//记录已修多少
        while(!queue.isEmpty()){
            int pre = queue.poll();
            count++;
            for(int index:adjacency.get(pre)){//遍历邻接表
                indegree[index]--;
                if(indegree[index] == 0){
                    queue.add(index);
                }
            }
        }

        return count == numCourses;
    }
}
