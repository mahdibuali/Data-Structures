import java.util.Arrays;

public class SortMerge {

    public static void sort(int[] arr) {
	//TO BE IMPLEMENTED
        int first = 0;
        Queue<int[]> queue = new Queue<>();
        //create subarrays queue
        for (int i = 0; i < arr.length; i++) {
            if ((i == arr.length - 1) || (arr[i + 1] < arr[i])) {
                int[] pair = new int[2];
                pair[0] = first;
                pair[1] = i;
                first = i + 1;
                queue.enqueue(pair);
            }
        }

        // while queue is not empty
        while (!queue.isEmpty()) {
            int[] arr2 = arr.clone();
            //merge the two peaks
            int[] sub1 = queue.dequeue();
            if (sub1[1] == arr.length - 1) {
                if (!queue.isEmpty()) {
                    queue.enqueue(sub1);
                }
            }
            else {
                int[] sub2 = queue.dequeue();
                first = sub1[0];
                int[] pair = new int[2];
                pair[0] = first;
                pair[1] = sub2[1];

                int i = sub1[0];
                int j = sub2[0];
                while (first <= pair[1]) {
                    if (j > sub2[1] || (i <= sub1[1] && arr2[i] <= arr2[j])) {
                        arr[first] = arr2[i];
                        i++;
                    } else {
                        arr[first] = arr2[j];
                        j++;
                    }
                    first++;
                }
                if (!queue.isEmpty()) {
                    queue.enqueue(pair);
                }
            }
            // add the new subarray
        }
    }

}
	