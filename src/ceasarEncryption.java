
import java.util.InputMismatchException;
import java.util.Scanner;


public class ceasarEncryption {
    public static void main(String[] args) {
        //Jeg starter med at lave en scanner og definere alfabetet. Jeg kan på den måde selv bestemme hvad mit alfabet skal indeholde.
        Scanner scanner = new Scanner(System.in);
        String alphabet = "abcdefghijklmnopqrstuvwxyzæøå ,.-_;:0123456789?!'";

        //Her bruger jeg en metode til at spørger om vi skal encode eller decode.
        boolean encode = encodeOrDecode();

        //Her Spørger jeg hvilken string der skal encodes/decodes.
        String stringToCode = stringToCodeMethod(encode);

        //Hvis jeg gerne vil encode bruger jeg en metode til at spørger hvor meget der skal shiftes i encodingen. Derefter kører jeg min "ceasarStringEncoder" metode.
        //Ellers kører jeg min "ceasarStringDecoder" metode. Her skal shift ikke bruges da den kører samtlige muligheder for hvad der kan shiftes så det er nemmere at decode.
        //Oftest vil der kun være 1 mulighed der ikke er volapyk.
        if (encode) {
            int shift = getShiftNumber();
            cesarStringEncoder(stringToCode, shift, alphabet);
        } else {
            cesarStringDecoder(stringToCode, alphabet);
        }
    }

    //Metode som spørger om string til at decode/encode.
    public static String stringToCodeMethod(boolean encode){
        Scanner scanner = new Scanner(System.in);
        String messageToPrint = "And what string would you like to ";
        messageToPrint = encode ? messageToPrint + "encode." : messageToPrint + "decode.";
        System.out.println(messageToPrint);
        String stringToCode = scanner.nextLine();
        stringToCode = stringToCode.toLowerCase();
        return stringToCode;
    }

    //Metode som spørger efter shift tal til encoding. Kan kun tage imod integers men vil ikke crashe hvis man ikke inputter integer.
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

    //Metode som spørger om jeg vil decode eller encode.
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

    //Metode som decoder den indtastede string. Teknisk set encoder den faktisk bare den encodede string igen.
    //Den gør det bare med samtlige mulige shifts der kan laves sådan så den rigtige decoding vil være der et sted.
    public static void cesarStringDecoder(String stringToEncode, String alphabet) {
        System.out.println("Here are all possible decodes for that string:");
        for (int i = 0; i < alphabet.length() - 1; i++) {
            cesarStringEncoder(stringToEncode, i, alphabet);
            System.out.println();
        }
    }

    //Metode som som deler den indtastede string op i et array, så vi kan sende en karaktér igennem "ceasarCharEncoder" ad gangen.
    public static void cesarStringEncoder(String stringToEncode, int shift, String alphabet) {
        char[] arrayOfString = stringToEncode.toCharArray();
        for (char x : arrayOfString) {
            char v = ceasarCharEncoder(x, shift, alphabet);
            System.out.printf(v + "");
        }
    }

    //metode som tager en karakter ind, laver det om til en integer værdi, lægger shift til, for derefter at lave det tilbage om til en karakter.
    public static char ceasarCharEncoder(char character, int shift, String alphabet) {
        int index = characterToIndex(character, alphabet);
        index = index + shift;
        char charShifted = indexToCharacter(index, alphabet);
        return charShifted;
    }

    //Metode som tager en karaktér og laver det om til indeks. Det gør den ved at sammenligne karaktéren med samtlige karaktere i alfabetet indtil den finder et match.
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

    //Metode som tager karakterens (indeks + shift) og sender karakteren tilbage som tilhører den plads i alfabetet.
    //Der er et while loop inde i metoden som lægger alfabetets længde til indeks hvis det er mindre end 0.
    //Dette gør jeg i tilfælde af at man har indsat et minustal i shift så vi når uden for alfabetets længde.
    //Derefter moduluser jeg med alfabetets længde i tilfælde af at indeks er kommet til at være større end alfabetets længde.
    //Derfor man man sætte shift til at være 10312312 eller -312312 og det vil ikke gøre nogen forskel.
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
