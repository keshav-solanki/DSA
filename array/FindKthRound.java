package array;

public class FindKthRound {

    private static int findRound(int[] arr){
        int n = arr.length;

        for(int i=0; i<n-1; i++){
            if(arr[i]>arr[i+1]){
                return i+1;
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        int arr[] = {3,4,5,1,2};
        System.out.println(findRound(arr));
    }
    
}