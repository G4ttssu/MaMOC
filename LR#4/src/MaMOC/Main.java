package MaMOC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите текст, который хотите сжать: ");
        String text = in.nextLine();

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

        double a = (encoded.length());
        double b = (text.getBytes().length * 8);
        int effective = (int)((1 -a/b)*100);

        System.out.println("Исходный текст: " + text);
        System.out.println("Частота символов: " + frequencies);
        System.out.println("Коды символов: " + codes);
        System.out.println("Размер исходной строки: " + text.getBytes().length * 8 + " бит");
        System.out.println("Размер сжатой строки: " + encoded.length() + " бит");
        System.out.println("Процент сжатия текста: " + effective + "%");
        System.out.println("Зашифровано: " + encoded);

        // декодируем сжатую информацию обратно
        String decoded = huffmanDecode(encoded.toString(), tree);

        System.out.println("Расшифровано: " + decoded);

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
