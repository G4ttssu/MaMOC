package MaMOC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.lang.Comparable;

public class Main {

    private static TreeMap<Character,Integer> createDictionary(String text)
    {
        TreeMap<Character,Integer> dictionary = new TreeMap<>();
        for(int index = 0; index < text.length(); ++index)
            dictionary.put(text.charAt(index), dictionary.get(text.charAt(index)) != null ? dictionary.get(text.charAt(index)) + 1 : 1 );
        return dictionary;
    }

    public static void main(String[] args)
    {
        String text = "abracadabra 'dadasad' adasdadas' dasdasdadasdasdas eeqhfgf";
        TreeMap<Character,Integer> dictionary = createDictionary(text);
        ArrayList<Node> tree = new ArrayList<>();

        for(Character character: dictionary.keySet())
            tree.add(new Node(character, dictionary.get(character)));
    }

    private static Node huffmanCdde(ArrayList<Node> tree)
    {
        while(tree.size() > 1)
        {
            Collections.sort(tree);
            Node left = tree.remove(tree.size() -1 );
            Node right = tree.remove(tree.size() - 1);

            Node parent = new Node(null, right.weight + left.weight, right, left);
            tree.add(parent);
        }

        return tree.get(0);
    }

    private static class Node implements Comparable<Node>
    {

        Character symbol;
        Integer weight;

        Node right;
        Node left;

        public Node(Character symbol, Integer weight) {
            this.symbol = symbol;
            this.weight = weight;
        }

        public Node(Character symbol, Integer weight, Node right, Node left) {
            this.symbol = symbol;
            this.weight = weight;
            this.right = right;
            this.left = left;
        }

        public Node(Character symbol) {
            this.symbol = symbol;
        }

        @Override
        public int compareTo(Node object) {
            return object.weight - weight;
        }
    }
}
