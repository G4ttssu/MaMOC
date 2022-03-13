package MaMOC;

import java.util.HashMap;
import java.util.Map;

public class Main {

    static Map<Character,Integer> getDictionary(String input)
    {
        Map<Character,Integer> dictionary = new HashMap<Character,Integer>();
        for(int index = 0; index < input.length(); ++index)
            dictionary.putIfAbsent(input.charAt(index),getCharacters(input,input.charAt(index)));
        return dictionary;
    }

    static int getCharacters(String input, Character symbol)
    {
        int count = 0;
        for(int index = 0; index < input.length(); ++index)
            if(symbol == input.charAt(index))
                count++;
        return count;
    }

    static void outDictionary(Map<Character,Integer> dictionary)
    {
        dictionary.entrySet().stream().sorted(Map.Entry.<Character,Integer>comparingByValue().reversed()).forEach(System.out::println);
    }

    public static void main(String[] args)
    {
        String a = "abracadabra";
        Map<Character,Integer> dictionary = getDictionary(a);
        outDictionary(dictionary);
    }
}
