import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Item{
    int value;
    int index;

    Item(int value, int index){
        this.value = value;
        this.index = index;
    }
}

public class PerformOpration {

    public static void update(List<List<Integer>> operation, List<Integer> intial_flow, int i){
        int pos = operation.get(i).get(1);
        int val = operation.get(i).get(2);

        intial_flow.add(pos, val);
    }

    public static long query(List<List<Integer>> operation, List<Integer> intial_flow, int i, int d){
        int l = operation.get(i).get(1);
        int r = operation.get(i).get(2);
        int k = operation.get(i).get(3);
        int sync = operation.get(i).get(4);
        long sum =0;

        PriorityQueue<Item> pq = new PriorityQueue<>((a,b) -> (b.value-a.value));

        for(int idx=l; idx>=r; idx++){
            int v = intial_flow.get(idx);
            if(intial_flow.get(idx)>=sync){
                v = v*5;
            }
            pq.add(new Item(v, idx));
        }

        int count =0;

        while (count ==0 || Math.abs(count)) {
            
        }

    }

    public static long helper(List<List<Integer>> operation, List<Integer> intial_flow, int n, int d){
        long sum=0;
        for(int i=0; i<operation.size() ; i++){
            if(operation.get(i).get(0)==1){
                update(operation, intial_flow,i);
            }
            else{
               sum += query(operation, intial_flow,i , d);
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        int n = 4;
        int d = 2;
        
        List<Integer> intial_flow = new ArrayList<>();
        intial_flow.add(5);
        intial_flow.add(5);
        intial_flow.add(5);

        List<List<Integer>> operation = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        temp.add(2);
        temp.add(0);
        temp.add(2);
        temp.add(1);
        temp.add(0);
        operation.add(0, temp);

        long result = helper(operation, intial_flow, n, d);

        System.out.println(result);
    }
}
