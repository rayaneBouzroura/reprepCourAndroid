public class Main {
    public static void main(String[] args) {
        //creating a bunch of arrays

        //this one is even and the middle is even
        int A1[] = { 1,2,3,4,5,6,7,8};
        //this one is even and the middle is odd
        int A2[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,22};
        //this one is odd and the middle is even
        int A3[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
        //this one is odd and the middle is odd
        int A4[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};

        printBeforeAfter(A4);


    }

    private static void printBeforeAfter(int[] Array) {
        //print the original array
        System.out.println("Original Array: ");
        //print the first array
        for (int i = 0; i < Array.length; i++)
        {
            System.out.print(Array[i] + " ");
        }
        //reversify the first array
        reverse(Array);
        //print the first array
        System.out.println("\nReversed Array: ");
        for (int i = 0; i < Array.length; i++)
        {
            System.out.print(Array[i] + " ");
        }
    }



    public static void reverse(int[] A) {
        int length = A.length;
        int mid = length / 2;
        // For even-length arrays, the middle is also even
        if (length % 2 == 0 && mid % 2 == 0) {
            // Reverse the first half of the array
            for (int i = 0; i < mid; i += 2) {
                int temp = A[i];
                A[i] = A[i+1];
                A[i+1] = temp;
            }
            // Change the second element of each pair in the second half
            for (int i = mid; i < length - 1; i += 2) {
                A[i+1] = A[i] + A[i+1];
            }
        }
        // For even-length arrays, the middle is odd
        else if (length % 2 == 0) {
            // Reverse the first half of the array
            for (int i = 0; i < mid - 1; i += 2) {
                int temp = A[i];
                A[i] = A[i+1];
                A[i+1] = temp;
            }
            // Change the second element of each pair in the second half
            for (int i = mid; i < length - 1; i += 2) {
                A[i+1] = A[i] + A[i+1];
            }
        }
        // For odd-length arrays
        else {
            // Reverse the first half of the array
            for (int i = 0; i < mid; i += 2) {
                int temp = A[i];
                A[i] = A[i+1];
                A[i+1] = temp;
            }
            // Change the second element of each pair in the second half
            for (int i = mid + 1; i < length - 1; i += 2) {
                A[i+1] = A[i] + A[i+1];
            }
        }
    }



}