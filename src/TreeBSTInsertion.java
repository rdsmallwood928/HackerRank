/**
 * Created by robert.smallwood on 9/6/16.
 */
public class TreeBSTInsertion {

    public static void main() {

    }

    static Node Insert(Node root,int value)
    {
        if(root == null) {
            root = new Node();
            root.data = value;
            root.right = null;
            root.left = null;
        }else if(value > root.data) {
            if (root.right != null) {
                Insert(root.right, value);
            } else {
                root.right = new Node();
                root.right.data = value;
                root.right.right = null;
                root.right.left = null;
            }
        } else {
            if(root.left != null) {
                Insert(root.left, value);
            } else {
                root.left = new Node();
                root.left.data = value;
                root.left.right = null;
                root.left.left = null;
            }
        }
        return root;
    }

}
