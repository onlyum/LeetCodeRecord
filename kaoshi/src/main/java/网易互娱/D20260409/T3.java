package 网易互娱.D20260409;

import java.io.*;
import java.util.Deque;
import java.util.StringTokenizer;
/*
模拟embedding返回top-k
 */
public class T3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens()) return;
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
//        pw.print("n"+n+"d"+d+"k"+k);
//        pw.println();

        double[][] doc = new double[n][d];
        double[] query = new double[n];

        double[] score = new double[n];
        int[] index = new int[n];
        for(int i=0;i<n;i++){
            index[i]=i;
        }

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<d; j++){
                doc[i][j] = Double.parseDouble(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<d;i++){
            query[i] = Double.parseDouble(st.nextToken());
        }

        for(int i=0;i<n;i++){
            double shang = 0;
            double xia1 =0,xia2=0;
            for(int j=0;j<d;j++){
                shang+= query[i] * doc[i][j];
                xia1 += query[i] * query[i];
                xia2 += doc[i][j] * doc[i][j];
            }
            score[i] = shang/(Math.sqrt(xia1*xia1)*Math.sqrt(xia2*xia2));
        }

        for(int i=0;i<d;i++){//小的往后
            for(int j=i;j<d;j++){
                if(score[i]<score[j]){
                    double temp = score[i];
                    score[i] = score[j];
                    score[j] = temp;

                    int in =  index[i];
                    index[i] = index[j];
                    index[j] = in;
                }
            }
        }

        for(int i=0;i<k;i++){
            if(i==k-1){
                pw.print(index[i]);
            }else{
                pw.print(index[i]+" ");
            }
        }

        pw.flush();
        pw.close();

    }
}
