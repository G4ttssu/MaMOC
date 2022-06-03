package MaMOC;

public class Main {

    public static void main(String[] args) {

        final String[] G = {"1110000","1001100","0101010","1101001"};

        String[] table = new String[(int)Math.pow(2,G.length)];

        for(int index = 0; index < Math.pow(2,G.length); ++index)
        {
            table[index] = Integer.toBinaryString(index);
            while(table[index].length() < G.length)
                table[index] = '0' + table[index];
        }

        String[] codeWords = new String[(int)Math.pow(2,G.length)];
        System.out.println("Итоговые кодовые слова: ");
        for(int index = 0; index < Math.pow(2, G.length); ++index)
        {
            String buffer = "";
            for(int jndex = 0; jndex < G[0].length(); ++jndex)
            {
                int temp = 0;
                for(int k = 0; k < G.length; ++k)
                    temp = (temp + table[index].charAt(k) * G[k].charAt(jndex))%2;
                 buffer += Integer.toString(temp);
            }
            codeWords[index] = buffer;
            System.out.println(index + ":" + codeWords[index]);
        }
        int length = (int)Math.pow(2, G.length);
        for(int index = 0; index < (int)Math.pow(2, G.length); ++index)
            for(int jndex = index + 1; jndex < (int)Math.pow(2, G.length); ++jndex)
            {
                int temp = 0;
                for(int k = 0; k < codeWords[0].length(); ++k)
                    if(codeWords[index].charAt(k) != codeWords[jndex].charAt(k))
                        temp += 1;
                if(temp < length)
                    length = temp;
            }
        System.out.println("Кодовое расстояние: " + length);
    }
}
