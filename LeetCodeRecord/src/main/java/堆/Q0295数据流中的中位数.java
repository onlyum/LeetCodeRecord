package 堆;

import java.util.PriorityQueue;

//维护大顶堆lpq和小顶堆rpq,小数入lqp,大数入rqp,优先入lpq
class MedianFinder {
    public static void main(String[] args){
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);    // arr = [1]
        medianFinder.addNum(2);    // arr = [1, 2]
        System.out.println(medianFinder.findMedian()); // 返回 1.5 ((1 + 2) / 2)
        medianFinder.addNum(3);    // arr[1, 2, 3]
        System.out.println(medianFinder.findMedian()); // return 2.0
    }
    PriorityQueue<Integer> lpq;
    PriorityQueue<Integer> rpq;
    public MedianFinder() {
        lpq = new PriorityQueue<>((a, b)->b-a);
        rpq = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if(lpq.size() == rpq.size()){//一样多最终加到lqp
            rpq.offer(num);
            lpq.offer(rpq.poll());
        }else{//lpq多，则最终加到rqp
            lpq.offer(num);
            rpq.offer(lpq.poll());
        }

        if(lpq.isEmpty()){
            lpq.offer(num);
            return;
        }
        if(num > lpq.peek()){//入rpq
            if(lpq.size() == rpq.size()){
                rpq.offer(num);
                lpq.offer(rpq.poll());
            }else{
                rpq.offer(num);
            }
        }else{//入lpq
            if(lpq.size() == rpq.size()){
                lpq.offer(num);
            }else{
                lpq.offer(num);
                rpq.offer(lpq.poll());
            }
        }
    }

    public double findMedian() {
        if(lpq.size() > rpq.size()){
            return lpq.peek();
        }else{
            return (double)(lpq.peek()+ rpq.peek())/2.0;
        }
    }
}
