package 贪心;

/*
121. 买卖股票的最佳时机
贪心：
记录当天前的最低价格，假设最低买入，今天卖出
 */
public class Q0121买卖股票的最佳时机 {
    public static void main(String[] args){
        int[] input = {7,1,5,3,6,4};
        int res = maxProfit(input);
        System.out.println(res);
    }
    //2026/04/17二刷
    static int maxProfit(int[] prices){
        int minBefore = Integer.MAX_VALUE;
        int maxPro = 0;
        for(int p:prices){
            if(minBefore > p){
                minBefore = p;
            }else{
                maxPro = Math.max(maxPro, (p-minBefore));
            }
        }
        return maxPro;
    }
//    static int maxProfit(int[] prices){
//        int minprice = Integer.MAX_VALUE;
//        int maxprofit = 0;
//        for(int price:prices){
//            if(price  < minprice){
//                minprice = price;
//            }else{
//                if(price - minprice > maxprofit){
//                    maxprofit = price - minprice;
//                }
//            }
//        }
//        return maxprofit;
//    }
}