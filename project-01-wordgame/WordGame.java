import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WordGame {
    
    /** 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        System.out.println("Welcome to Word Game! Spell letters from scrambled letters. Good Luck!");

        int score = 0;
    
        System.out.println("You're starting score is " + score);

        int wordCount = countWords("words.txt");

        String[] words = readWords("words.txt", wordCount);

        String gameWord = getGameWord(words);

        System.out.println(gameWord);

        getShuffledLetters(gameWord);

        Scanner scanner = new Scanner(System.in);

        List<String> userInputWords = new ArrayList<>();

        
        while (true) {

            String input = scanner.nextLine();

            if (input.equals("mix")) {
                
                getShuffledLetters(gameWord);
                System.out.println("Your current score is: " + score);

            }
            else if (input.equals("ls")) {

                System.out.println("Valid words you've entered so far: " + userInputWords);
                System.out.println("Your current score is: " + score);

            } else if (input.equals("bye")) {

                System.out.println("Your final score was: " + score);
                break;

            } else if (input.length() == 4) { 

                if (isWordValid(input, gameWord)){
                    userInputWords.add(input);
                    ++score;
                    System.out.println("Your current score is: " + score);
                }
                else{
                    System.out.println("You can only use letters from specific word.");
                }
                
            } 
            else if ((input.length() > 3) && (input.length() <= 6) ){
                
                if (isWordValid(input, gameWord)){
                    userInputWords.add(input);
                    score += 7;
                    System.out.println("Your current score is: " + score);}
                else{
                    System.out.println("You can only use letters from specific word.");
                }

            }
            else if (userInputWords.contains(input)){

                System.out.println("You've already used that word");
                System.out.println("Your current score is: " + score);

            }
            else {

                System.out.println("Invalid word. Try again!");
                System.out.println("Your current score is: " + score);

            }
        }
        scanner.close();
    }

    
    /** 
     * method counts words in text file
     * @param filename allows for the words.txt to be read by this method
     * @return int count of words in file
     * @throws FileNotFoundException
     */
    public static int countWords(String filename) throws FileNotFoundException {

        File f = new File(filename);
        Scanner fileIn = new Scanner(f);

        int count = 0;

        while (fileIn.hasNext()) {

            fileIn.next();
            count++;
        }

        fileIn.close();

        return count;
    }

    
    /** 
     * method returns shuffled word from whatever word is stored into gameWord String
     * @param gameWord brings in the stored word in gameWord
     * @return prints out shuffled word
     */
    public static void getShuffledLetters(String gameWord){

        char[] letters = gameWord.toCharArray();

        Character[] letters2 = new Character[letters.length];
        for (int i = 0; i < letters.length; i++) {
            letters2[i] = letters[i];
        }

        Collections.shuffle(Arrays.asList(letters2));

        System.out.println();
        for (int i = 0; i < letters2.length; i++) {
            System.out.print(letters2[i] + " ");
        }
        System.out.println();

    }

    
    /** 
     * reads the strings from the words.txt file
     * @param filename words.txt file 
     * @param wordCount amount of strings in file
     * @return words array
     * @throws FileNotFoundException was not able to find designated file
     */
    public static String[] readWords(String filename, int wordCount) throws FileNotFoundException {

        File f = new File(filename);
        Scanner fileIn = new Scanner(f);

        String[] words = new String[wordCount];

        int count = 0;

        while (fileIn.hasNext()) {
            words[count] = fileIn.next();
            count++;
        }

        fileIn.close();

        return words;
    }

    
    /** 
     * gets a 7 unique letter word from string array
     * @param words bring in string array of words
     * @return a 7 unique letter word from list of array 
     */
    public static String getGameWord(String[] words) {

        Collections.shuffle(Arrays.asList(words)); 

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == 7) {
                if (checkUniqueLetters(words[i])) {
                    return words[i];
                }
            }
        }

        return ""; // There are no 7 unique letter words

    }

    
    /** 
     * checks the word for unique letters
     * @param word from getgameword method
     * @return boolean true or false
     */
    public static boolean checkUniqueLetters(String word){

        char[] letters = word.toCharArray();

        for(int i = 0; i < word.length(); i++){
            char currentLetter = letters[i];
            for(int j = 0; j < word.length(); j++){
                if(i != j && currentLetter == letters[j]){
                    return false;
                }
            }
        }
        return true;
    }

    
    /** 
     * makes sure all letters input by user are letters in the designated word
     * @param input user input
     * @param gameWord stored word in gameword
     * @return false or true to call
     */
    public static boolean isWordValid (String input, String gameWord){

        for (int i = 0; i < input.length(); i++) {

            if (!gameWord.contains(String.valueOf(input.charAt(i)))) {
                return false;
            }

        }
        return true;

    }
}
