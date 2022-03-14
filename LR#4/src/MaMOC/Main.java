package MaMOC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        String text = "abracnigabra";

        // вычисляем частоты символов в тексте
        TreeMap<Character, Integer> frequencies = countFrequency(text);

        // генерируем список листов дерева
        ArrayList<CodeTreeNode> codeTreeNodes = new ArrayList<>();
        for(Character c: frequencies.keySet()) {
            codeTreeNodes.add(new CodeTreeNode(c, frequencies.get(c)));
        }

        // строим кодовое дерево с помощью алгоритма Хаффмана
        CodeTreeNode tree = huffman(codeTreeNodes);

        // генерируем таблицу префиксных кодов для кодируемых символов с помощью кодового дерева
        TreeMap<Character, String> codes = new TreeMap<>();
        for(Character c: frequencies.keySet()) {
            codes.put(c, tree.getCodeForCharacter(c, ""));
        }

        // кодируем текст, заменяем сиволы соответствующими кодами
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            encoded.append(codes.get(text.charAt(i)));
        }

        System.out.println("Исходный текст: " + text);
        System.out.println("Частота символов: " + frequencies);
        System.out.println("Коды символов: " + codes);
        System.out.println("Размер исходной строки: " + text.getBytes().length * 8 + " бит");
        System.out.println("Размер сжатой строки: " + encoded.length() + " бит");
        System.out.println("Зашифровано: " + encoded);

        // декодируем сжатую информацию обратно
        String decoded = huffmanDecode(encoded.toString(), tree);

        System.out.println("Расшифровано: " + decoded);

        // тест сжатия файла
        //fileCompressTest();
    }

    private static TreeMap<Character, Integer> countFrequency(String text) {
        TreeMap<Character, Integer> freqMap = new TreeMap<>();
        for (int i = 0; i < text.length(); i++) {
            Character c = text.charAt(i);
            Integer count = freqMap.get(c);
            freqMap.put(c, count != null ? count + 1 : 1);
        }
        return freqMap;
    }

    private static CodeTreeNode huffman(ArrayList<CodeTreeNode> codeTreeNodes) {
        while (codeTreeNodes.size() > 1) {
            Collections.sort(codeTreeNodes);
            CodeTreeNode left = codeTreeNodes.remove(codeTreeNodes.size() - 1);
            CodeTreeNode right = codeTreeNodes.remove(codeTreeNodes.size() - 1);

            CodeTreeNode parent = new CodeTreeNode(null, right.weight + left.weight, left, right);
            codeTreeNodes.add(parent);
        }
        return  codeTreeNodes.get(0);
    }

    private static String huffmanDecode(String encoded, CodeTreeNode tree) {
        StringBuilder decoded = new StringBuilder();

        CodeTreeNode node = tree;
        for (int i = 0; i < encoded.length(); i++) {
            node = encoded.charAt(i) == '0' ? node.left : node.right;
            if (node.content != null) {
                decoded.append(node.content);
                node = tree;
            }
        }
        return decoded.toString();
    }

    // класс для представления кодового дерева
    private static class CodeTreeNode implements Comparable<CodeTreeNode> {

        Character content;
        int weight;
        CodeTreeNode left;
        CodeTreeNode right;

        public CodeTreeNode(Character content, int weight) {
            this.content = content;
            this.weight = weight;
        }

        public CodeTreeNode(Character content, int weight, CodeTreeNode left, CodeTreeNode right) {
            this.content = content;
            this.weight = weight;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(CodeTreeNode o) {
            return o.weight - weight;
        }

        // извлечение кода для символа
        public String getCodeForCharacter(Character ch, String parentPath) {
            if (content == ch) {
                return  parentPath;
            } else {
                if (left != null) {
                    String path = left.getCodeForCharacter(ch, parentPath + 0);
                    if (path != null) {
                        return path;
                    }
                }
                if (right != null) {
                    return right.getCodeForCharacter(ch, parentPath + 1);
                }
            }
            return null;
        }
    }
}



/*
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
        String text = "adadfffdfdfd";
        TreeMap<Character,Integer> dictionary = createDictionary(text);
        ArrayList<Node> tree = new ArrayList<>();

        for(Character character: dictionary.keySet())
            tree.add(new Node(character, dictionary.get(character)));

        Node treeNode = huffmanCode(tree);

        TreeMap<Character,String> codes = new TreeMap<>();
        for(Character character: dictionary.keySet())
            codes.put(character, treeNode.getCode(character,""));

        System.out.println(dictionary.toString());
        System.out.println("Таблица префиксныз кодов: " + codes.toString());
    }

    private static Node huffmanCode(ArrayList<Node> tree)
    {
        while(tree.size() > 1)
        {
            Collections.sort(tree);
            Node left = tree.remove(tree.size() - 1);
            Node right = tree.remove(tree.size() - 1);

            Node parent = new Node(null, right.weight + left.weight, left, right);
            tree.add(parent);
        }

        return tree.get(0);
    }

    private static String huffmanDecode(String encoded, Node tree)
    {
        StringBuilder decoded = new StringBuilder();

        Node node = tree;
        for(int index = 0; index < encoded.length(); ++index)
        {
            node = encoded.charAt(index) == '0' ? node.left : node.right;
            if(node.symbol != null)
            {
                decoded.append(node.symbol);
                node = tree;
            }
        }

        return decoded.toString();
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

        public Node(Character symbol, Integer weight, Node left, Node right) {
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

        //Обход дерева и возвращение кода нужного символа
        public String getCode(Character character, String parent)
        {
            if(symbol == character)
                return parent;
            else
            {
                if(left != null)
                {
                    String path = left.getCode(character,parent + 0);
                    if(path != null)
                        return path;
                }
                if(right != null)
                {
                    String path = left.getCode(character,parent + 1);
                    if(path != null)
                        return path;
                }
            }
            return null;
        }
    }
}

 */
