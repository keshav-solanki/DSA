public class Pattern {
    public static void main(String[] args) {
        int n=10;
        int odd =1;
        int even =2;

        for(int i=1; i<=n; i++){

            for(int j=n-i; j>0; j--){
                System.out.print("  ");
            }

            for(int j=1; j<2*i; j++){
                if(i%2==0){
                    System.out.print(even+ " ");
                    even += 2;
                }
                else{
                    System.out.print(odd+ " ");
                    odd += 2;
                }
            }

            for(int j=n-i; j>0; j--){
                System.out.print("  ");
            }

            System.out.println();
        }
    }
}
