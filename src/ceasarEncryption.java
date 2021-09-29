
import java.util.InputMismatchException;
import java.util.Scanner;


public class ceasarEncryption {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String alphabet = "abcdefghijklmnopqrstuvwxyzæøå ,.-_;:0123456789?!'";
        ceasarEncoder(alphabet);

    }

    public static void ceasarEncoder(String alphabet) {
        Scanner scanner = new Scanner(System.in);
        boolean encode = encodeOrDecode();
        String stringToCode = stringToCodeMethod(encode);


        if (encode) {
            int shift = getShiftNumber();
            cesarStringEncoder(stringToCode, shift, alphabet);
        } else {
            cesarStringDecoder(stringToCode, alphabet);
        }
    }

    public static String stringToCodeMethod(boolean encode){
        Scanner scanner = new Scanner(System.in);
        String messageToPrint = "And what string would you like to ";
        messageToPrint = encode ? messageToPrint + "encode." : messageToPrint + "decode.";
        System.out.println(messageToPrint);
        String stringToCode = scanner.nextLine();
        stringToCode = stringToCode.toLowerCase();
        return stringToCode;
    }

    public static int getShiftNumber(){
        Scanner scanner = new Scanner(System.in);
        int shift = 0;
        while (true) {
            try {
                System.out.println("how much would you like to shift.");
                shift = scanner.nextInt();
                break;
            } catch (InputMismatchException inputNotAInteger) {
                scanner.nextLine();
                System.out.println("Please only write integers");
            }
        }
        return shift;
    }


    public static boolean encodeOrDecode(){
        Scanner scanner = new Scanner(System.in);
        boolean encode = false;
        System.out.println("Hello would you like to encode or decode?");
        while (true) {
            String encodeOrDecode = scanner.nextLine();
            if (encodeOrDecode.equalsIgnoreCase("encode")) {
                encode = true;
                break;
            } else if (encodeOrDecode.equalsIgnoreCase("decode")) {
                break;
            } else {
                System.out.println("I dont understand. Write \"encode\" or \"decode\".");
            }
        }
        return encode;
    }

    public static void cesarStringDecoder(String stringToEncode, String alphabet) {
        System.out.println("Here are all possible decodes for that string:");
        for (int i = 0; i < alphabet.length() - 1; i++) {
            cesarStringEncoder(stringToEncode, i, alphabet);
            System.out.println();
        }
    }


    public static void cesarStringEncoder(String stringToEncode, int shift, String alphabet) {
        char[] arrayOfString = stringToEncode.toCharArray();
        for (char x : arrayOfString) {
            char v = ceasarCharEncoder(x, shift, alphabet);
            System.out.printf(v + "");
        }
    }


    public static char ceasarCharEncoder(char character, int shift, String alphabet) {
        int index = characterToIndex(character, alphabet);
        index = index + shift;
        char charShifted = indexToCharacter(index, alphabet);
        return charShifted;


    }


    public static int characterToIndex(char character, String alphabet) {
        int letterCounter = 0;
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
        char characterToReturn = arrayOfAlphabet[index];
        return characterToReturn;
    }

}
