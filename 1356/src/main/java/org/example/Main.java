package org.example;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1024,512,256,128,64,32,16,8,4,2,1};
        Solution2 solution2 = new Solution2();
        arr = solution2.sortByBits(arr);
        for (int i : arr) {
            System.out.println(i + ",");
        }
    }
}