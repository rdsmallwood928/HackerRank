package datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by bigwood928 on 9/5/2016.
 */
public class HeapJessieAndCookies {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCookies = input.nextInt();
        int minSweetness = input.nextInt();
        CookieHeap cookieHeap = new CookieHeap();
        for(int i=0;i<numCookies;i++) {
            cookieHeap.add(input.nextInt());
        }
        int numOperations = 0;
        while(cookieHeap.peek() < minSweetness && cookieHeap.size() >= 2) {
            int leastSweet = cookieHeap.removeCookie();
            int secondLeastSweet = cookieHeap.removeCookie();
            int mixedCookie =  (leastSweet + (2*secondLeastSweet));
            cookieHeap.add(mixedCookie);
            numOperations++;
        }
        if(cookieHeap.peek() >= minSweetness) {
            System.out.println(numOperations);
        } else {
            System.out.println(-1);
        }
    }

}

class CookieHeap {
    private List<Integer> heap = new ArrayList<Integer>();

    public void add(Integer addMe) {
        heap.add(addMe);
        heapifyUp(heap.size() - 1);
    }

    private void heapifyUp(Integer childIndex) {
        int tmp = heap.get(childIndex);
        while (childIndex > 0 && tmp < getParent(childIndex)) {
            heap.set(childIndex, getParent(childIndex));
            childIndex = getParentIndex(childIndex);
        }
        heap.set(childIndex, tmp);
    }

    private void heapifyDown(int index) {
        if(index < heap.size()) {
            int child;
            int tmp = heap.get(index);
            while (kthChild(index, 1) < heap.size()) {
                child = minChild(index);
                if (heap.get(child) < tmp) {
                    heap.set(index, heap.get(child));
                } else {
                    break;
                }
                index = child;
            }
            heap.set(index, tmp);
        }
    }

    private int minChild(int index) {
        //must be last entry...return
        if(kthChild(index, 1) == heap.size()-1) {
            return kthChild(index, 1);
        }
        return (heap.get(kthChild(index, 1)) < heap.get(kthChild(index, 2))) ? kthChild(index, 1) : kthChild(index, 2);
    }

    private Integer kthChild(int index, int child) {
        return 2 * index + child;
    }

    private Integer getParent(Integer childIndex) {
        return heap.get(getParentIndex(childIndex));
    }

    private Integer getParentIndex(Integer childIndex) {
        return (childIndex - 1) / 2;
    }

    public int peek() {
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public int removeCookie() {
        int minCookie = heap.get(0);
        heap.set(0, heap.get(heap.size()-1));
        heap.remove(heap.size()-1);
        heapifyDown(0);
        return minCookie;
    }
}