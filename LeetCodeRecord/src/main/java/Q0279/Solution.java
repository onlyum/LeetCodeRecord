package Q0279;

public class Solution {
    public int numSqure(int n){
        int[] f = new int[n+1];
        f[0] = 0;
        for(int i = 1;i<=n;i++){
            int min = Integer.MAX_VALUE;
            for(int j = 1;j*j<=i;j++){
                min = Math.min(f[i-j*j], min);
            }
            f[i] = min + 1;
        }
        return f[n];
    }
}
