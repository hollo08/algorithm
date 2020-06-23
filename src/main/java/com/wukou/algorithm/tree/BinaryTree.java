package com.wukou.algorithm.tree;

public class BinaryTree<K extends Comparable, V> {
    public static void main(String[] args) {
        Node<Integer, Integer> root = new Node<Integer, Integer>(22, 33);
        BinaryTree<Integer, Integer> tree =
                new BinaryTree<Integer, Integer>(root);
        tree.put(1, 11);
        tree.put(1, 22);
        System.out.println(tree);
    }


    public BinaryTree(Node<K, V> root){
        super();
        this.root = root;
    }
    private Node<K, V> root;

    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + root +
                '}';
    }

    public V get(K key){
        return get(root, key);
    }
    private V get(Node<K, V> node, K key){
        if(node == null){
            return null;
        }
        int compareTo = key.compareTo(node.getKey());
        if(compareTo == 0){
            return node.getValue();
        }else if(compareTo < 0){
            return get(node.getLeft(), key);
        }else {
            return get(node.getRight(), key);
        }
    }

    private void put(K key, V value){
        put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> node, K key, V value){
        if(node == null){
            return new Node<K, V>(key, value);
        }
        int compareTo = key.compareTo(node.getKey());
        if(compareTo == 0){
            node.setValue(value);
        }else if(compareTo < 0){
            node.setLeft(put(node.getLeft(), key, value));
        }else {
            node.setRight(put(node.getRight(), key, value));
        }
        return node;
    }



    static class Node<k, v>{
        private Node<k, v> left;
        private Node<k, v> right;
        private k key;
        private v value;

        public Node(k key, v value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" +
                    "left:" + (left==null?"":left.toString()) +
                    ", right:" + (right==null?"":right.toString()) +
                    ", key:" + key +
                    ", value:" + value +
                    '}';
        }

        public Node<k, v> getLeft() {
            return left;
        }

        public void setLeft(Node<k, v> left) {
            this.left = left;
        }

        public Node<k, v> getRight() {
            return right;
        }

        public void setRight(Node<k, v> right) {
            this.right = right;
        }

        public k getKey() {
            return key;
        }

        public void setKey(k key) {
            this.key = key;
        }

        public v getValue() {
            return value;
        }

        public void setValue(v value) {
            this.value = value;
        }
    }

}
