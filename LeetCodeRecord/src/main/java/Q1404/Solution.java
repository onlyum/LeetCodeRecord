package Q1404;

import java.util.Arrays;

class Solution {
    public int numSteps(String s) {
        int steps = 0;
        StringBuilder sb = new StringBuilder(s);
        while (!sb.toString().equals("1")) {
            int len = sb.length();
            steps++;
            if (sb.charAt(len - 1) == '0') {
                sb.deleteCharAt(len - 1);
            }else {
                for (int i = len-1; i >= 0; i--) {
                    if (sb.charAt(i) == '1') {
                        sb.setCharAt(i, '0');
                        if(i == 0){
                            sb.insert(i, '1');
                            break;
                        }
                    }else {
                        sb.setCharAt(i, '1');
                        break;
                    }
                }
            }
        }
        return steps;
    }
}
