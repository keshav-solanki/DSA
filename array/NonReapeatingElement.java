 package array;
 
 class NonReapeatingElement {
    public static void main(String[] args) {
        int arr[] ={3,4,5,6,6,4,3,2,8,1,8};
        int n = arr.length;

        for(int i=0; i<n; i++){
            boolean flag = false;
            for(int j=0; j<n; j++){
                if(i!=j && arr[j] == arr[i]){
                    flag=true;
                    break;
                }
            }
            if(flag == false){
                System.out.println(arr[i]);
            }
        }
    }
}
