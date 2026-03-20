package com.anyatech.workers_service.services;

import org.springframework.stereotype.Component;

@Component
public class DataStructureAlgorithms {


    /*
    * Linear Search has a time complexity of O(n) where n is the number of elements in the array
    *
    * */
    public int linearSearch(int[] nums, int target){

        int cnt = 0;
        for(int i = 0; i < nums.length; i++){
            cnt++;
            if(nums[i] == target){
                System.out.println("linear search took this many steps: " + cnt);
                return i;
            }
        }

        System.out.println("linear search took this many steps: " + cnt);
        return -1;
    }

    /*
     * Binary search has a time complexity of O(log n) where n is the number of elements in the array
     *
     * “Binary search” gets its name from the word binary meaning two.
     * The algorithm repeatedly splits the search space into two halves and decides which half to keep.
     * That’s the entire essence of why it’s called binary.
     *
     * */
    public int binarySearch(int[] nums, int target){



        int cnt = 0;
        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            cnt++;
            int mid = (left + right) / 2;

            if(nums[mid] == target){

                System.out.println("binary search took this many steps: " + cnt);
                return mid;
            }
            else if(nums[mid] < target){
                left = mid + 1;
            }
            else right = mid - 1;

        }

        System.out.println("binary search took this many steps: " + cnt);

        return -1;
    }

    /*
     * Binary search has a time complexity of O(log n) where n is the number of elements in the array
     *
     * “Binary search” gets its name from the word binary meaning two.
     * The algorithm repeatedly splits the search space into two halves and decides which half to keep.
     * That’s the entire essence of why it’s called binary.
     *
     * */
    public int binaryRecursiveSearch(int[] nums, int target){

//TODO implement this based on this video - https://www.youtube.com/watch?v=4_HOnhB64Dg
        return -1;
    }

}
