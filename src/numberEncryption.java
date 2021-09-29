import com.sun.security.jgss.GSSUtil;

public class numberEncryption {
    public static void main(String[] args) {
        //Jeg starter med at definere alfabetet. (Man kan altså derfor selv vælge om sit alfabet indeholder danske bogstaver, tal, symboler osv.)
        String alphabet = "abcdefghijklmnopqrstuvwxyzæøå ";

        //Her laver jeg den string jeg gerne vil encode samt encoder den med metoden "encodeString".
        String stringToEncode = "Johannes";
        encodeString(stringToEncode, alphabet);


        //Her laver jeg et integer array som jeg decoder til et ord.
        int[] x = {10, 15, 8, 1, 14, 14, 5, 19};
        decodeString(x, alphabet);
    }

    //metode som decoder min string ved hjælp af et for-each-loop samt metoden "indexToCharacter".
    public static void decodeString(int[] arrayOfInt, String alphabet){
        System.out.println();
        for ( int x : arrayOfInt){
            System.out.printf("" + indexToCharacter(x, alphabet));
        }
    }

    //metode som encoder min string ved hjælp af et for-loop, samt metoden "characterToIndex".
    public static void encodeString(String stringToEncode, String alphabet){
        stringToEncode = stringToEncode.toLowerCase();
        System.out.println();
        for (int i = 0; i < stringToEncode.length(); i++) {
            int x = characterToIndex(stringToEncode.charAt(i), alphabet);
            System.out.printf(x + ";");
        }
    }

    //Metoden som tager en character og returnere den integer værdi som svarer til hvor i alfabetet det står.
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

    //Metode som tager en enkelt integer og returnere den char som svarer til det indeks i alfabetet.
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
