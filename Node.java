/**
 * Created by kjh on 16. 11. 16.
 */
public class Node{
    char alpha;
    int freq;
    StringBuffer huffmanCode = new StringBuffer();
    Node leftChild;
    Node rightChild;

    public Node(){
    }

    public Node(char a, int f){
        this.alpha = a;
        this.freq = f;
        this.leftChild = null;
        this.rightChild = null;
    }
}
