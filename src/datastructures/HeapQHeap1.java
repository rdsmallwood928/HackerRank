import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by robert.smallwood on 8/28/16.
 */
public class HeapQHeap1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BinaryHeap heap = new BinaryHeap();
        Integer size = input.nextInt();
        while(input.hasNext()) {
            Integer operation = input.nextInt();
            switch (operation) {
                case 1:
                    heap.add(input.nextInt());
                    break;
                case 2:
                    heap.delete(input.nextInt());
                    break;
                case 3:
                    System.out.println(heap.getMin());
                    break;
            }
        }
        System.exit(0);
    }
}

class BinaryHeap {

    private List<Integer> heap = new ArrayList<Integer>();

    public void add(Integer addMe) {
        heap.add(addMe);
        heapifyUp(heap.size() - 1);
    }

    public void delete(Integer deleteMe) {
        int deleteIndex = -1;
        for(int i=0; i<heap.size(); i++)  {
            if(heap.get(i).equals(deleteMe)) {
                deleteIndex = i;
                break;
            }
        }
        if(deleteIndex != -1) {
            deleteItemAt(deleteIndex);
        }
    }

    public Integer deleteItemAt(Integer deleteIndex) {
        int keyItem = heap.get(deleteIndex);
        heap.set(deleteIndex, heap.get(heap.size() - 1));
        heap.remove(heap.size()-1);
        heapifyDown(deleteIndex);
        return keyItem;
    }

    public Integer getMin() {
        return heap.get(0);
    }

    private void heapifyUp(Integer childIndex) {
        int tmp = heap.get(childIndex);
        while (childIndex > 0 && tmp < getParent(childIndex)) {
            heap.set(childIndex, getParent(childIndex));
            childIndex = getParentIndex(childIndex);
        }
        heap.set(childIndex, tmp);
    }

    private void heapifyDown(int index)
    {
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

    private Integer kthChild(int index, int child) {
        return 2 * index + child;
    }

    private Integer getParent(Integer childIndex) {
        return heap.get((childIndex-1)/2);
    }

    private Integer getParentIndex(Integer childIndex) {
        return (childIndex-1)/2;
    }

    private Integer minChild(Integer index) {
        int bestChild = kthChild(index, 1);
        int k = 2;
        int pos = kthChild(index, k);
        while ((k <= 2) && (pos < heap.size())) {
            if (heap.get(pos) < heap.get(bestChild)) {
                bestChild = pos;
            }
            pos = kthChild(index, k++);
        }
        return bestChild;
    }
}
