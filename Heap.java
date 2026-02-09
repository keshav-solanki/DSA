
public class Heap {
    static int arr[] = new int[10];
    static int count=0;
    int v=0;
    void create(int d){
        if(count==arr.length-1){
            return;
        }
        arr[0]=-1;
        count++;
        arr[count]=d;
        int i =count;
        while (i>1) {
            int parent = i/2;
            if(parent!=0 && arr[i]>arr[parent]){
                int temp= arr[parent];
                arr[parent]=arr[i];
                arr[i]=temp;
                i=parent;
            }
            else{
                return;
            }
        }
    }
    void inOrder(){
        for(int i=1; i<=count;i++){
            System.out.print(arr[i]+ " ");
        }
        System.out.println();
    }
    void delete(){
        int temp = arr[1];
        arr[1] = arr[count];
        count--;

        int i=1;
        while(i<=count){
            int lc =2*i;
            int rc= 2*i+1;
            int smallest = i;

            if(lc<=count && arr[lc]>arr[smallest]){
                smallest=lc;
            }
            if(rc<=smallest && arr[rc]>arr[smallest]){
                smallest=rc;
            }
            if(i!=smallest){
                temp = arr[smallest];
                arr[smallest]= arr[i];
                arr[i]=temp;
                i=smallest;
            }
            else{
                break;
            }
        }
    }
    void heapSort(){
        int size =count;
        while (size>1) {
            int temp=arr[1];
            arr[1]=arr[size];
            arr[size]=temp;
            size--;
            heapify(size,1);
        }    
    }
    void heapify(int n,int i){
        int largest=i;
        int lc = 2*i;
        int rc= 2*i+1;
        if(lc<=n && arr[lc]>arr[largest]){
            largest=lc;
        }
        if(rc<=n && arr[rc]>arr[largest]){
            largest=rc;
        }
        if(largest!=i){
            int temp = arr[largest];
            arr[largest]= arr[i];
            arr[i]=temp;
            heapify(n,largest);
        }
    }
    public static void main(String[] args) {
        Heap h = new Heap();
        h.create(50);
        h.create(40);
        h.create(30);
        h.create(10);
        h.create(8);
        h.create(19);
        h.create(7);
        System.out.println("Heap: ");
        h.inOrder();
        System.out.println("Sorted: ");
        h.heapSort();
        h.inOrder();
        h.delete();
        h.heapSort();
        h.inOrder();
    }
}
