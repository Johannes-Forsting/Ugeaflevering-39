import com.sun.security.jgss.GSSUtil;

public class numberEncryption {
    public static void main(String[] args) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzæøå";


        String stringToEncode = "hej";
        encodeString(stringToEncode, alphabet);

        int[] x = {8, 5, 10};
        decodeString(x, alphabet);
    }

    public static void decodeString(int[] arrayOfInt, String alphabet){
        System.out.println();
        for ( int x : arrayOfInt){
            System.out.printf("" + indexToCharacter(x, alphabet));
        }
    }

    public static void encodeString(String stringToEncode, String alphabet){
        System.out.println();
        for (int i = 0; i < stringToEncode.length(); i++) {
            int x = characterToIndex(stringToEncode.charAt(i), alphabet);
            System.out.printf(x + ";");
        }
    }


    public static int characterToIndex(char character, String alphabet) {
        int letterCounter = 1;
        int letterAt = 0;
        char[] arrayOfAlphabet = alphabet.toCharArray();
        for (char x : arrayOfAlphabet) {
            if (x == character) {
                letterAt = letterCounter;
            }
            letterCounter++;
        }
        return letterAt;
    }

    public static char indexToCharacter(int index, String alphabet) {
        while (index < 0) {
            index = index + alphabet.length();
        }
        index = index % alphabet.length();
        char[] arrayOfAlphabet = alphabet.toCharArray();
        char characterToReturn = arrayOfAlphabet[index - 1];
        return characterToReturn;
    }
}
