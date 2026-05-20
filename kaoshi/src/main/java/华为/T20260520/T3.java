package 华为.T20260520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class T3 {
    public static class Skill{
        int id  ;
        int time ;
        int power;
        int k   ;
        int qz ;
        Skill(int id, int time, int power, int k){
            this.id = id;
            this.time = time;
            this.power = power;
            this.k = k;
            this.qz = -1;//没有前置技能
        }
        Skill(int id, int time, int power, int k, int qz){
            this.id = id;
            this.time = time;
            this.power = power;
            this.k = k;
            this.qz = qz;
        }

//        public int compare(Skill other){
//            return Integer.compare(this.id, other.id);
//        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens()) return;

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        List<Skill> skills = new ArrayList<Skill>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int power = Integer.parseInt(st.nextToken());
            int k =  Integer.parseInt(st.nextToken());
            if(st.hasMoreTokens()){
                int qz = Integer.parseInt(st.nextToken());
                skills.add(new Skill(id, time, power, k, qz));
            }else{
                skills.add(new Skill(id, time, power, k));
            }
        }
        //战力降序，时间升序
        skills.sort(new Comparator<Skill>() {
            @Override
            public int compare(Skill o1, Skill o2) {
                if (o1.power < o2.power) return o2.power - o1.power;
                else return o2.time - o1.time;
            }
        });


    }
}
