package com.wukou.algorithm.tree;

import com.wukou.algorithm.Util;

/**
 * Definition of RedBlackBinarySearchTree: from wikipedia
 * 1, Each node is either red or black.
 * 2, The root is black. This rule is sometimes omitted. Since the root can always be changed from red to black,
 *    but not necessarily vice versa, this rule has little effect on analysis.
 * 3, All leaves (NIL) are black.
 * 4, If a node is red, then both its children are black.
 * 5, Every path from a given node to any of its descendant NIL nodes goes through the same number of black nodes.
 * @param <K>
 * @param <V>
 */
public class RedBlackTree<K extends Comparable, V> {
    private static Boolean RED = true;
    private static Boolean BLACK = true;
    private Node root;
    private int size;
    public RedBlackTree(){
        this.root = null;
        this.size = 0;
    }
    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }
    private void changeColor(Node node) {
        node.color = RED;
        if(node.left != null){
            node.left.color = BLACK;
        }
        if(node.right != null){
            node.right.color = BLACK;
        }
    }

    /**
     * Left Rotate
     * //      node                                             R
     * //     /    \              left rotate                /    \
     * //    n1     R           -------------->            node    n3
     * //         /   \                                   /    \
     * //       n2     n3                               n1    n2
     *
     */
    private Node leftRotate(Node node){
        Node x = node.right;
        //left rotate
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }
    /**
     * 右旋转
     * //      node                                              R
     * //     /    \              right rotate                /    \
     * //    R     n3           ------------->              n1     node
     * //  /  \                                                  /    \
     * // n1   n2                                               n2    n3
     *
     */
    private Node rightRotate(Node node){
        Node x = node.left;
        //right rotate
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK; //The Root Node's color is black;
    }
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value); //The color of the newly added node is red
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            // key.compareTo(node.key) == 0
            node.value = value;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        if (isRed(node.left) && node.left!=null && isRed(node.left.left)) {
            node = rightRotate(node);
        }

        if (isRed(node.left) && isRed(node.right)) {
            changeColor(node);
        }
        return node;
    }
    private Node get(Node node, K key){
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        } else { // if key.compareTo(node.key) > 0
            return get(node.right, key);
        }
    }
    private Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }
    private V delete(K key){
        Node node = get(root, key);
        if (node != null) {
            root = delete(root, key);
            return (V)node.value;
        }
        return null;
    }
    private Node delete(Node node, K key){
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = delete(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = delete(node.right, key);
            return node;
        } else {//key.compareTo(node.key) == 0

            //The left subtree of the node to be deleted is empty
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            //The right subtree of the node to be deleted is empty
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            /*The right and left subtree of node to be delete is empty
              Find the smallest node bigger then to be deleted node.
              this is the smallest node of the right subtree of the node to be deleted
              use the node replace the node to be deleted.
              Replace the position of the node to be deleted with this node.
            */
            Node successor = minimum(node.right);
            successor.right = deleteMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;

            return successor;
        }
    }
    private Node deleteMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = deleteMin(node.left);
        return node;
    }


    public boolean contains(K key) {
        return get(root, key) != null;
    }
    public V get(K key) {
        Node node = get(root, key);
        return node == null ? null : (V)node.value;
    }
    public void set(K key, V value) {
        Node node = get(root, key);
        if(node == null){
            throw new IllegalArgumentException("Node doesn't exists");
        }
        node.value = value;
    }
    public int getSize() {
        return size;
    }
    public boolean isEmply(){
        return size == 0;
    }

    public static void main(String[] args) {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        tree.add(1, "one");
        tree.add(2, "two");
        Util.println(tree.get(1));
    }

    private class Node<K, V>{
        K key;
        V value;
        Node left, right;
        boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = RED;
        }
    }
}
