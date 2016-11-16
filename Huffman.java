
/**
 * Created by kjh on 16. 11. 16.
 */
public class Huffman {

    PriorityQueue pQueue;
    Node[] character;
    StringBuffer[] huffmanCode;
    Node tree = new Node();

    public Huffman(){
        pQueue = new PriorityQueue();
        character = new Node[27];
        huffmanCode = new StringBuffer[27];
    }

    public StringBuffer[] table(){
        return huffmanCode;
    }

    public String encode(String word){
        encode_init(word);

        String print = "";
        for(int i=0 ; i<word.length() ; i++) {
            if(huffmanCode[word.charAt(i)-96] != null | huffmanCode[0] != null) {
                if (word.charAt(i) != ' ')
                    print += huffmanCode[word.charAt(i) - 96];
                else
                    print += huffmanCode[0];
            }
        }
        return print;
    }

    private void encode_init(String word){

        for(int i=0 ; i<character.length ; i++){
            if(i == 0)
                character[i] = new Node(' ', 0);
            else
                character[i] = new Node((char)(i+96), 0);
        }

        for(int i=0 ; i<word.length()-1 ; i++){
            if(word.charAt(i) == ' ')
                character[0].freq++;
            else
                character[word.charAt(i) - 96].freq++;
        }
        huffmanTree();
    }

    public void huffmanTree(){
        putQueue();

        while(pQueue.size() != 1) {
            Node root = new Node();
            root.leftChild = pQueue.extract_min();
            root.rightChild = pQueue.extract_min();
            root.freq = root.leftChild.freq + root.rightChild.freq;
            pQueue.add(root);
        }
        makeHuffmanCode(pQueue.peek());
    }

    private void makeHuffmanCode(Node node) {

        if(node == null)
            return;
        if(node.leftChild != null)
            node.leftChild.huffmanCode.append(node.huffmanCode + "0");
        if(node.rightChild != null)
            node.rightChild.huffmanCode.append(node.huffmanCode + "1");

        if(isAlphabet(node.alpha)){
            if(node.alpha != ' ')
                huffmanCode[node.alpha-96] = node.huffmanCode;
            else
                huffmanCode[0] = node.huffmanCode;
        }
        makeHuffmanCode(node.leftChild);
        makeHuffmanCode(node.rightChild);
    }

    public void decodeTree(StringBuffer line){
        char alpha = line.charAt(0);
        Node ptr = tree;
        for(int i=1 ; i<line.length() ; i++){
            if(isAlphabet(line.charAt(i)) || i == line.length()-1){
                ptr.alpha = alpha;
                ptr = tree;
                alpha = line.charAt(i);
            }
            else{
                if(ptr.leftChild == null)
                    ptr.leftChild = new Node();
                if(ptr.rightChild == null)
                    ptr.rightChild = new Node();

                if(line.charAt(i) == '0')
                   ptr = ptr.leftChild;
                else if(line.charAt(i) == '1')
                    ptr = ptr.rightChild;
            }
        }
    }

    public String decode(String encoded){
        Node ptr = tree;
        StringBuffer decode_print = new StringBuffer();
        for(int i=0 ; i<encoded.length() ; i++){
            char char_ptr = encoded.charAt(i);
            if(char_ptr == '0')
                ptr = ptr.leftChild;
            else
                ptr = ptr.rightChild;

            if(isAlphabet(ptr.alpha)) {
                decode_print.append(ptr.alpha);
                ptr = tree;
            }
        }

        return decode_print.toString();
    }


    private void putQueue() {
        for(int i=0 ; i<character.length ; i++){
            if(character[i].freq > 0)
                pQueue.add(character[i]);
        }
    }


    private boolean isAlphabet(char alpha) {
        return ((alpha >= 'a' && alpha <= 'z') || alpha == ' ');
    }
}
