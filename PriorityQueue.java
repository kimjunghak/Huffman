import java.util.ArrayList;

/**
 * Created by KJH on 2016-11-14.
 */
public class PriorityQueue {

    ArrayList<Node> arrayList = new ArrayList<>();

    public void add(Node n){
        arrayList.add(n);
        build_min_heap();
    }

    private void min_heapify(int index){
        int leftChildIndex = index*2+1;
        int rightChildIndex = index*2+2;
        int smallest = index;

        if(leftChildIndex <= arrayList.size()-1 && arrayList.get(leftChildIndex).freq < arrayList.get(index).freq)
            smallest = leftChildIndex;

        if(rightChildIndex <= arrayList.size()-1 && arrayList.get(rightChildIndex).freq < arrayList.get(index).freq)
            smallest = rightChildIndex;

        if(smallest != index) {
            swapNode(index, smallest);
            min_heapify(smallest);
        }
    }

    public Node peek(){
        return arrayList.get(0);
    }

    public int size(){
        return arrayList.size();
    }

    public void build_min_heap(){
        for(int i = (arrayList.size()-1)/2; i>=0 ; i--)
            min_heapify(i);
    }

    private void swapNode(int index, int smallest) {
        Node temp = arrayList.get(index);
        arrayList.set(index, arrayList.get(smallest));
        arrayList.set(smallest, temp);
    }

    public Node extract_min(){
        Node ret = arrayList.remove(0);
        build_min_heap();
        return ret;
    }

    public boolean isEmpty(){
        return arrayList.size() == 0;
    }
}