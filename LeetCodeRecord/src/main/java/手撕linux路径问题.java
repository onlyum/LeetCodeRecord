import java.util.*;

public class 手撕linux路径问题 {
    // /root/abc/.././ffdc/../cd
    public static void Main(String args){
        String s = "/root/abc/.././ffdc/../cd";
        String res = quchong(s);
        System.out.println(res);
    }
    public static String quchong(String input){
        StringBuilder sb = new StringBuilder();
        Queue<List<Character>> stack = new LinkedList<>();
        List<Character> temp = new ArrayList<>();

        int count = 0;

        for(int i =1;i<input.length();i++){
            char c =      input.charAt(i);
            if(c=='/'){
                if(temp == null){
                    continue;
                }
                stack.add(temp);
            }else if(c=='.'){
                count++;
                if(count==2){//'..'时
                    stack.poll();
                    count = 0;
                }
            }else{
                temp.add(c);
                temp = new ArrayList<>();
            }
        }

        while(!stack.isEmpty()){
//            sb.append(stack.);
        }
        return sb.toString();
    }
}
