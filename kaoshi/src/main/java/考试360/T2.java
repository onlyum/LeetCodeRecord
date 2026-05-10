package 考试360;

/*
 * ==============================================================================
 * 题目简介：
 * 小A的学校举办了运动会，需要根据各项比赛的成绩统计出所有班级的总积分排名。
 * 积分规则为：第一名得3分，第二名得2分，第三名得1分。
 * 排名规则为：按总分从高到低排名，分数相同的班级名次并列。如果出现并列，接下来的排名会跳过
 * 相应的名次（例如，有两个班级并列第一名，则下一个分数对应的名次直接是第三名）。
 * * 输入样例：
 * 5 3
 * 1 2 3
 * 2 3 1
 * 3 1 2
 * * 输出样例：
 * 3 1 2 3
 * 0
 * 0
 * * 解题思路：
 * 1. 使用数组 `score` 记录各班级得分，依次读取数据并累加。
 * 2. 将班级按 分数降序、编号升序 进行排序。
 * 3. 遍历排序后的班级，根据“名次 = 在此之前的人数 + 1”计算标准名次。
 * 4. 收集名次分别为 1、2、3 的班级编号，并按照题目要求的格式输出。
 * ==============================================================================
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class T2 {

    // 定义内部类用于绑定班级ID和总分，并实现排序规则
    static class ClassScore implements Comparable<ClassScore> {
        int id;
        int score;

        public ClassScore(int id, int score) {
            this.id = id;
            this.score = score;
        }

        @Override
        public int compareTo(ClassScore other) {
            // 总分不相同时，按总分降序排列
            if (this.score != other.score) {
                return Integer.compare(other.score, this.score);
            }
            // 总分相同时，按班级编号升序排列
            return Integer.compare(this.id, other.id);
        }
    }

    public static void main(String[] args) throws IOException {
        // 使用 BufferedReader 和 StringTokenizer 进行快速输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String line = br.readLine();
        // 处理可能存在的空行
        while (line != null && line.trim().isEmpty()) {
            line = br.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken()); // 班级数
        int m = Integer.parseInt(st.nextToken()); // 比赛场数

        int[] score = new int[n + 1];

        // 读入冠军（加3分）
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int classId = Integer.parseInt(st.nextToken());
            score[classId] += 3;
        }

        // 读入亚军（加2分）
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int classId = Integer.parseInt(st.nextToken());
            score[classId] += 2;
        }

        // 读入季军（加1分）
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int classId = Integer.parseInt(st.nextToken());
            score[classId] += 1;
        }

        // 封装班级信息并排序
        ClassScore[] classes = new ClassScore[n];
        for (int i = 1; i <= n; i++) {
            classes[i - 1] = new ClassScore(i, score[i]);
        }
        Arrays.sort(classes);

        // 用于保存前三名班级的编号
        List<Integer> rank1 = new ArrayList<>();
        List<Integer> rank2 = new ArrayList<>();
        List<Integer> rank3 = new ArrayList<>();

        int currentRank = 1;
        for (int i = 0; i < n; i++) {
            // 如果当前班级的分数比上一个班级低，则名次更新为 i + 1
            if (i > 0 && classes[i].score < classes[i - 1].score) {
                currentRank = i + 1;
            }

            // 根据名次分发到不同集合中
            if (currentRank == 1) {
                rank1.add(classes[i].id);
            } else if (currentRank == 2) {
                rank2.add(classes[i].id);
            } else if (currentRank == 3) {
                rank3.add(classes[i].id);
            }
        }

        // 输出结果
        printRankResult(pw, rank1);
        printRankResult(pw, rank2);
        printRankResult(pw, rank3);

        // 刷新缓冲区
        pw.flush();
        pw.close();
        br.close();
    }

    // 格式化输出名次对应结果的方法
    private static void printRankResult(PrintWriter pw, List<Integer> list) {
        // 先输出个数
        pw.print(list.size());
        // 如果个数不为0，则遍历输出编号（因为前面已经按ID升序排过，直接输出即可）
        for (int id : list) {
            pw.print(" " + id);
        }
        pw.println(); // 换行
    }
}