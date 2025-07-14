package com.example.sortingVisualizer.SortingVizualizer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/sort")
public class SortingController {

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/bubble")
    public List<int[]> bubbleSort(@RequestBody int[] array) {
        List<int[]> steps = new ArrayList<>();
        steps.add(array.clone());
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
                steps.add(array.clone());
            }
        }
        return steps;
    }

    @PostMapping("/insertion")
    public List<int[]> insertionSort(@RequestBody int[] array) {
        List<int[]> steps = new ArrayList<>();
        steps.add(array.clone());
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
            steps.add(array.clone());
        }
        return steps;
    }

    @PostMapping("/selection")
    public List<int[]> selectionSort(@RequestBody int[] array) {
        List<int[]> steps = new ArrayList<>();
        steps.add(array.clone());
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;

            steps.add(array.clone());
        }
        return steps;
    }

    @PostMapping("/heap")
    public List<int[]> heapSort(@RequestBody int[] array) {
        int n = array.length;

        List<int[]> steps = new ArrayList<>();
        steps.add(array.clone());
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
            steps.add(array.clone());
        }

        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0);

            steps.add(array.clone());
        }
        return steps;
    }

    private void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            heapify(array, n, largest);
        }
    }


    List<int[]> MergeSortsteps = new ArrayList<>();

    @PostMapping("/merge")
    public List<int[]> mergeSort(@RequestBody int[] array) {
        MergeSortsteps.add(array.clone());
        mergeSortHelper(array);
        return MergeSortsteps;
    }

    private void mergeSortHelper(int[] array) {
        if (array.length < 2) {
            return;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        mergeSortHelper(left);
        mergeSortHelper(right);

        merge(left, right, array);
    }

    private void merge(int[] left, int[] right, int[] array) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        while (i < left.length) {
            array[k++] = left[i++];
        }

        while (j < right.length) {
            array[k++] = right[j++];
        }

        MergeSortsteps.add(array.clone());
    }

    @PostMapping("/quick")
    public List<int[]> quickSort(@RequestBody int[] array) {
        List<int[]> steps = new ArrayList<>();
        steps.add(array.clone());
        quickSortHelper(array, 0, array.length - 1, steps);
        return steps;
    }

    private void quickSortHelper(int[] array, int low, int high, List<int[]> steps) {
        if (low < high) {
            int pi = partition(array, low, high, steps);
            quickSortHelper(array, low, pi - 1, steps);
            quickSortHelper(array, pi + 1, high, steps);
        }
    }

    private int partition(int[] array, int low, int high, List<int[]> steps) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        steps.add(array.clone());
        return i + 1;
    }

    @PostMapping("/bucket")
    public List<float[]> bucketSort(@RequestBody float[] arr) {
        int n = arr.length;
        List<float[]> steps = new ArrayList<>();
        steps.add(arr.clone());
        @SuppressWarnings("unchecked")
        ArrayList<Float>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) buckets[i] = new ArrayList<>();
        for (float num : arr) {
            int idx = (int) (num * n);
            buckets[idx].add(num);
        }
        for (ArrayList<Float> bucket : buckets) {
            Collections.sort(bucket);
        }
        int index = 0;
        for (ArrayList<Float> bucket : buckets) {
            for (float num : bucket) {
                arr[index++] = num;
                steps.add(arr.clone());
            }
        }
        return steps;
    }

    @PostMapping("/counting")
    public List<int[]> countingSort(@RequestBody int[] array) {
        List<int[]> steps = new ArrayList<>();
        steps.add(array.clone());

        if (array.length == 0) return steps;

        int max = Arrays.stream(array).max().orElse(0);
        int min = Arrays.stream(array).min().orElse(0);

        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[array.length];

        for (int num : array) {
            count[num - min]++;
            steps.add(array.clone());
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            output[count[array[i] - min] - 1] = array[i];
            count[array[i] - min]--;
            steps.add(output.clone());
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = output[i];
        }
        steps.add(array.clone());

        return steps;
    }




}


