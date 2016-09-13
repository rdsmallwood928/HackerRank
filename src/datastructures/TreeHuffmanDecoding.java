package datastructures;

/**
 * Created by robert.smallwood on 9/6/16.
 */
public class TreeHuffmanDecoding {

    public static void main(String[] args) {

    }

/*    void decode(String S, Node root) {
        int index = 0;
        Node currentNode = root;
        while(index < S.length()) {
            if(currentNode.left == null && currentNode.right == null) {
                System.out.print(currentNode.data);
                currentNode=root;
            } else {
                Character indexChar = S.charAt(index);
                if(indexChar.equals('1')) {
                    currentNode = currentNode.right;
                } else {
                    currentNode = currentNode.left;
                }
                index= index+1;
            }
        }
        System.out.print(currentNode.data);
    }*/
}
