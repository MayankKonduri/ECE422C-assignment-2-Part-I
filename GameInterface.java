package assignment2;

import java.util.Scanner; // so that the user can actually interact with the game and provide their input
import java.util.ArrayList; // so that we can have our history 'memory' data structure

public class GameInterface{

    private GameTemplate gameObject; // input

    // IMPORTANT: right now we are using GameTemplate as the type

    private final boolean testMode; // user-defined input (cannot change after inputted)
    private final Scanner sc; // scanner object in order to collect user input

    public GameInterface(GameTemplate gameObject, boolean testMode){
        this.gameObject = gameObject;
        this.testMode = testMode;
        this.sc = new Scanner(System.in); // initializing the scanner object
    }

    public void runGame(){
        // loop for letting users play as many games as possible
        boolean does_user_want_to_play = true; // the user started the program, so I would assume they want to play
        while(does_user_want_to_play){
            // play game
            playOneIteration();
            does_user_want_to_play = ask_user_to_play_again();
            // if user says no... the while loop will discontinue
        }

        System.out.println("Thank you for playing!");
    }

    private boolean ask_user_to_play_again(){
        System.out.println();
        System.out.println("Thank you for joining us for a game! Would you like to play another game (Please Enter 'Y' for Yes)?");
    
        if(!sc.hasNextLine()){
            return false;
        }

        String user_answer = sc.nextLine();
        if(user_answer.equals("Y") || user_answer.equals("y")){
            return true;
        } else{
            return false;
        }
    }

    private void playOneIteration(){
        gameObject.startNewGame(); // generates new code and resets winning status...

        // stores the valid guesses and their feedback for this specific game iteration
        ArrayList<String> history_valid_guesses = new ArrayList<>();

        System.out.println(gameObject.getInstructions());
        System.out.println("NOTE: Type HISTORY at any time to see your past guesses.");
        
        if(testMode){
            // Hopefully not a user trying to use shorcuts!!!!!
            System.out.println("[TEST MODE] Secret code generated for this game is: " + gameObject.getSecretCode());
        }

        int guesses_left = gameObject.getMaxGuesses(); // how many guesses the user has left

        while(guesses_left > 0){ // user can only win inside this loop
            System.out.println();
            System.out.println("WARNING: You have " + guesses_left + " guess(es) left.");
            System.out.print("Please enter your guess: "); // no 'ln' so prints on the same line

            if(!sc.hasNextLine()){
                return;
            }

            String user_guess = sc.nextLine(); // user types in input to console

            if(user_guess.equals("HISTORY")){ // if user wants to see history, we will display all their vlaid guesses, and not use an attempt
                if (history_valid_guesses.isEmpty()) {
                    System.out.println("You have not made any valid guesses yet."); // if the history is empty... means users didn't provide a valid guess yet
                }
                for (String valid_guess : history_valid_guesses) {
                    System.out.println(valid_guess);
                }

                continue; // let the loop continue without using up an attmept
            }
            
            // next step after entering the stage where know the user actually provided an actual attempt is to see if they made a valid guess
            String guess_result = gameObject.validateGuess(user_guess); // returns null if all good
            if(guess_result != null){
                // means there is an error
                System.out.println(guess_result);
                continue; // "Invalid guesses do not consume an attempt," and let the user guess again with no penalty on guesses left
            }

            // after detemining they WANTED to make a guess, and MADE a valid guess, the next step is consequential, and we need to evaluate their guess
            String feedback_from_logic = gameObject.scoreGuess(user_guess);
            System.out.println(feedback_from_logic); // specific format for output

            // this is the feedback received in the format (feedback_from_logic)... now we store it in 'memory'
            history_valid_guesses.add(user_guess + ": " + feedback_from_logic);

            // Check whether the player guessed the entire secret code correctly
            if(gameObject.isWon()){
                System.out.println("You win!");
                return;
            }

            // if game is still continuing
            guesses_left--;
        }

        // The player used all of their valid guesses without winning
        System.out.println("Sorry, you lose! The secret code was " + gameObject.getSecretCode() + ".");

    }
}