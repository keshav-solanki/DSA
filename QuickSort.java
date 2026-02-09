class QuickSort{

    private static void swap(int arr[], int s, int e){
        int temp = arr[s];
        arr[s] = arr[e];
        arr[e] = temp;
    }

    private static int partition(int[] arr, int s, int e){
        int pivot = e;
        int i=s;

        for(int j=s; j<e; j++){
            if(arr[j]<=arr[pivot]){
                swap(arr, i , j);
                i++;
            }
        }
        swap(arr, i, pivot);
        return i;
    }

    private static void quickSort(int[] arr, int s, int e){
        if(s>=e){
            return;
        }

        int p = partition(arr,s,e);

        quickSort(arr, s, p-1);
        quickSort(arr, p+1, e);
    }
    public static void main(String[] args) {
        int arr[] = {4,0,8,3,9};
        quickSort(arr,0,arr.length-1);

        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]+ " ");
        }

        System.out.println();
    }
}