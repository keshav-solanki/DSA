package array;

import java.util.Scanner;

public class SwiftingZero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the length of Array");
        int n = sc.nextInt();
        
        int arr[] = new int[n];
        
        System.out.println("Enter the values of Array");
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        
        int count =0;
        for(int i=0; i<n; i++){
            if(arr[i]!=0){
                arr[count] = arr[i];
                count++;
            }
        }
        
        for(int i=count; i<n; i++){
            arr[i] = 0;
        }
        
        for(int i=0; i<n; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
