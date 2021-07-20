import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Pendu {

        public static final String[] WORDS = {
                "chat","lama","louer","perdre","chercher","fleuriste","accueil","explore","reliure","nouveau","petit","plant","blond","roux"
        };

        public static final Random RANDOM = new Random();
        public static final int MAX_ERRORS = 9;
        private String wordToFind;
        private char[] wordFound;
        private int nbErrors;
        static ArrayList < String > letters = new ArrayList < > ();
        private String nextWordToFind() {
            return WORDS[RANDOM.nextInt(WORDS.length)];
        }

        public void newGame() {
            nbErrors = 0;
            letters.clear();
            wordToFind = nextWordToFind();


            wordFound = new char[wordToFind.length()];

            Arrays.fill(wordFound, '.');
        }


        public boolean wordFound() {
            return wordToFind.contentEquals(new String(wordFound));
        }


        private void enter(String c) {

            if (!letters.contains(c)) {

                if (wordToFind.contains(c)) {

                    int index = wordToFind.indexOf(c);

                    while (index >= 0) {
                        wordFound[index] = c.charAt(0);
                        index = wordToFind.indexOf(c, index + 1);
                    }
                } else {

                    nbErrors++;
                }

                letters.add(c);
            }
        }

        private String wordFoundContent() {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < wordFound.length; i++) {
                builder.append(wordFound[i]);

                if (i < wordFound.length - 1) {
                    builder.append(" ");
                }
            }

            return builder.toString();
        }


        public void play() {
            try (Scanner input = new Scanner(System.in)) {

                while (nbErrors < MAX_ERRORS) {
                    System.out.println("\nEnter a letter : ");

                    String str = input.next();


                    if (str.length() > 1) {
                        str = str.substring(0, 1);
                    }

                    enter(str);


                    System.out.println("\n" + wordFoundContent());

                    if (wordFound()) {
                        System.out.println("\nYou win!");
                        break;
                    } else {

                        System.out.println("\n=> Nb tries remaining : " + (MAX_ERRORS - nbErrors));
                    }
                }

                if (nbErrors == MAX_ERRORS) {

                    System.out.println("\nYou lose!");
                    System.out.println("=> Word to find was : " + wordToFind);
                }
            }
        }

        public static void main(String[] args) {
            Pendu Pendu = new Pendu();
            Pendu.newGame();
            Pendu.play();
        }

    }
