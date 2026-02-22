package array;

class BinaryGap{

    public static int binaryGap(int n) {
        int maxDistance= 0;
        int lastIndex = -1;
        int currentIndex =0;

        while(n>0){
            if((n&1)==1){
                if(lastIndex != -1){
                    maxDistance = Math.max(maxDistance, currentIndex-lastIndex);
                }
                lastIndex = currentIndex;
            }
            n=n>>1;
            currentIndex++;
        }
        return maxDistance;
    }

    public static void main(String[] args) {
        int n =22;
        System.out.println(binaryGap(n));
    }
}
