package org.example;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class Solution1 {
    public int[] sortByBits(int[] arr) {
        List<Integer> list = new ArrayList<Integer>();
        int[] bitOfNum = new int[10001];

        for(int i :arr){
            list.add(i);
            bitOfNum[i]=getBit(i);
        }

        Collections.sort(list, new Comparator<Integer>(){
            public int compare(Integer x, Integer y){
                if(bitOfNum[x]!=bitOfNum[y]){
                    return bitOfNum[x]-bitOfNum[y];
                }else{
                    return x-y;
                }
            }
        });

        for(int i=0;i<arr.length;i++){
            arr[i]=list.get(i);
        }

        return arr;
    }
    public int getBit(int num) {
        int res = 0;
        while(num!=0){
            res += num%2;
            num/=2;
        }
        return res;
    }
}
