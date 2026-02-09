public class MergeSort {

    public static void merge(int[] arr, int s, int e, int mid){

        int i=s;
        int j=mid+1;
        int k=0;
        int temp[] = new int[e+1];

        while(i<=mid && j<=e){
             if(arr[i]<=arr[j]){
                temp[k]=arr[i];
                i++;
                k++;
            }
            else{
                temp[k]=arr[j];
                j++;
                k++;
            }
        }
       

        if(i>mid) {
            while(j<=e){
                temp[k]=arr[j];
                j++;
                k++;
            }
        }
        if(j>e){
            while(i<=mid){
                temp[k]=arr[i];
                i++;
                k++;
            }
        }
        k=0;
        for(i=s; i<=e; i++){
            arr[i]=temp[k];
            k++;
        }
    }
    public static void mergeSort(int[] arr, int s,int e){
        if(s>=e){
            return;
        }
        int mid=s+(e-s)/2;
        mergeSort(arr, s, mid);
        mergeSort(arr, mid+1, e);
        merge(arr,s,e,mid);
    }
    public static void main(String[] args) { 
        int arr[] = {13,12,45,67,19};
        int n = arr.length-1;
        mergeSort(arr,0,n);
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
    
}