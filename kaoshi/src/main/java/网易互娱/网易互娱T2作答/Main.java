package 网易互娱.网易互娱T2作答;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    static int[] dr = new int[]{-1, 0, 1,  0};//上-1，右0，下1，左 0
    static int[] dc = new int[]{0 , 1, 0, -1};//上 0，右1，下0，左-1
    //组件改方向
    static int[] change2 = new int[]{3, 2, 1, 0};//字符'/'上0->左3,右1->下2，下2->右1，左3->上0
    static int[] change3 = new int[]{1, 0, 3, 2};//字符'\'上0->右1，右1->上0,下2->左3,左3->下2

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), M = in.nextInt();//N行，M列
        int K = in.nextInt();//组件数量
        int Sr = in.nextInt(), Sc = in.nextInt();//出生点行[1,M]列[1,N]
        int[] source = new int[]{Sr, Sc};
        int Er =  in.nextInt(), Ec = in.nextInt();//皇冠行列
        int[] target = new int[]{Er, Ec};
        int[][] flag = new int[N+1][M+1];//默认0为普通，1为#，2为/，3为\, -1表示走过了,10表示目标
        flag[Er][Ec] = 10;
        int res = 0;

        for(int i = 0; i < K; i++){
            int R = in.nextInt(), C = in.nextInt();
            char Type = in.next().charAt(0);
            if(Type == '#'){
                flag[R][C] = 1;
            }else if(Type == '/'){
                flag[R][C] = 2;
            }else {
                flag[R][C] = 3;
            }
        }

        res = BFS(flag, source, target, res, N, M);
        //深度优先
        System.out.println(1);
    }
    public static int BFS(int[][] flag, int[] cur, int[] target, int count,int N,int M){//开始位置，目标位置
        int row = cur[0], col = cur[1];
        if(row == target[0] && col == target[1]){//当前为目标直接返回
            return 0;
        }

        for(int i = 0;i < 4;i++){
            int nextR = row + dr[i];
            int nextC = col + dc[i];
            //如果出界则停住
            if(nextR <= 0 || nextC <= 0 || nextR >= N+1 || nextC >= M+1){
                continue;
            }
            //没出界则一直跑到停止
            while(nextR >=1 && nextR <= N && nextC>=1 && nextC <= M){
                //下一步如果走过了就不走了
                if(flag[nextR][nextC] == -1){
                    continue;
                }else{
                    if(flag[nextR][nextC] == 0){
                        //可以走，没走过就上去，置为-1
                        flag[nextR][nextC] = -1;
                        nextR = nextR + dr[i];
                        nextC = nextC + dc[i];
                    }else if(flag[nextR][nextC] == 1){
                        //撞到#即停止
                        //停住了就广度遍历
                        count += BFS(flag, new int[]{nextR-dr[i],nextC-dc[i]}, target, count, N, M);
                    }else if(flag[nextR][nextC] == 2){
                        //撞到/换方向
                        dr[i] = change2[dr[i]];
                        dc[i] = change2[dc[i]];
                    }else if(flag[nextR][nextC] == 3){
                        dr[i] = change3[dr[i]];
                        dc[i] = change3[dc[i]];
                    }else if(flag[nextR][nextC] == 10){
                        break;
                    }
                }
            }

            count++;
        }

        //回溯
        count--;

        return 1;
    }
}

/*
3 5 3
2 1 2 3
1 4 #
1 5 \
2 5 /


 */
//        for(int i = 0; i < N; i++){
//            for(int j = 0; j < M; j++){
//                if(flag[i][j] == 0){
//                    System.out.print(".");
//                }else if(flag[i][j] == 1){
//                    System.out.print("#");
//                }else if(flag[i][j] == 2){
//                    System.out.print("/");
//                }else if(flag[i][j] == 3){
//                    System.out.print(']');
//                }
//            }
//            System.out.println();
//        }