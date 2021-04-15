package implementation;

import java.util.NoSuchElementException;

public class MyBinarySearchTree<E extends Comparable<E>> {
    private Node<E> root; //root is the head of linkedList.

    public Node<E> getRoot() {
        return root;
    }

    //insert
    public void insert(E data) {
        // creating new node
        Node<E> node = new Node<>(data); //it is known as diamond syntax
        if (isEmpty()) {
            //clean code
            root = node;
        } else {
            //traversing to reach the right position to add
            //new node , as well as keep track of parent
            Node<E> temp = root;
            Node<E> parent = null;
            while (temp != null) {
                parent = temp;
                if (data.compareTo(temp.getData()) <= 0) {
                    //traversing left subTree
                    temp = temp.getLeft();
                } else {
                    //traversing right subTree
                    temp = temp.getRight();
                }
            }
            if (data.compareTo(parent.getData()) <= 0) {
                parent.setLeft(node);

            } else {
                parent.setRight(node);
            }
        }
    }

    public void preOrder(Node<E> node) {
        //base condition
        if (node == null) {
            return;
        } else {
            //process the root
            System.out.print(node.getData() + ",");
            //process the left subTree
            preOrder(node.getLeft());
            //process the right subTree
            preOrder(node.getRight());
        }
        System.out.println();
    }

    public void inOrder(Node<E> node) {
        if (node != null) {
            //Process the left subTree
            inOrder(node.getLeft());
            //process the root
            System.out.println(node.getData() + ",");
            //process the right subTree
            inOrder(node.getRight());
        }
    }


    private boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;

    }
    public boolean search(E searchElement){
        boolean response= false;
//        traverse
        Node<E> temp = root;
        while ( temp != null){
            //equality
            if (searchElement.compareTo(temp.getData())==0){
                response= true;
                break;
            }
            // less than
            else if (searchElement.compareTo(temp.getData())< 0){
                temp = temp.getLeft();
            }
            //greater than
            else {

                temp = temp.getRight();
            }
        }
        return response;
    }
    //try recursive


    //delete
    public  void delete (E deletingElement){
        //searching the deleting element and keep track of parent
        Node<E> temp = root;
        Node<E> parent = null;
        while (temp != null){
            if (compare(deletingElement,temp)==0){
                break;
            }
            else {
                parent = temp;
                if(compare(deletingElement,temp)<0){
                    temp = temp.getLeft();
                }
                else{
                    temp=temp.getRight();
                }
            }
        }
        if(temp!=null){
            //delete
            //case 1-leaf node
                 //root node
            if(isLeaf(temp)){
                if(parent== null){
                    root = null;
                }
                else{
                    if (compare(deletingElement,parent)<0){
                        parent.setLeft(null);
                    }
                    else{
                        parent.setRight(null);
                    }
                }

            }
            //case 2 -on child
                 //root node

            else if (hasLeftChild(temp)){
                //root node
                if(parent== null){
                    root = root.getLeft();
                }
                else{
                    if (compare(deletingElement,parent)<0){
                        parent.setLeft(temp.getLeft());
                    }
                    else{
                        parent.setRight(temp.getLeft());
                    }
                }

            }
            //case 3 -two children
                 //root node
        }
        else{
            throw new NoSuchElementException("Element not Found");
        }
    }

    private boolean hasLeftChild(Node<E> temp) {
        if(temp.getRight()!= null && temp.getRight()==null){
            return true;
        }
        return false;
    }

    private boolean isLeaf(Node<E> temp) {
        if (temp.getLeft() == null && temp.getRight()==null){
            return true;
        }
        return  false;
    }

    private int compare(E deletingElement, Node<E> temp) {
        return deletingElement.compareTo(temp.getData());
    }
}
//Big O means worst case
