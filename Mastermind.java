package assignment2;

import java.util.Random;

public class Mastermind extends GameTemplate {

    // Stores the configuration for this specific game (the user defines what type of game he wants to play in the main file)
    private final GameConfiguration config;

    // The secret code that the player is trying to guess (this is defined dynamically on instatiation of each object of Mastermind [so each new game])
    private char[] secret_code;

    // values from 'GameConfiguration' to set locally for easy retrieval
    private final int NUMBER_PEGS;
    private final char[] COLORS_IN_GAME;

    // this is like starting 'play game,' as you have defined settings and started to actually play
    public Mastermind(GameConfiguration config) {
        // for easy access, attain values upon instantiation

        super(config.getMaxGuesses()); // parent stores the maximum guesses and win status

        NUMBER_PEGS = config.getNumberPegs();
        COLORS_IN_GAME = config.getColors();

        this.config = config; // the user tunes the configuration for this mastermind game
        this.secret_code = randomCode(); // upon instantation of game object, a secret code is created randomly
    }

    
    // ---------- OVERRIDING METHODS ----------

    // Generates a random secret code using the colors allowed by this game configuration
    // specific to Mastermind, (randomCode() or this logic is specific to Mastermind, but other games might still have a secret code, so they can implement their own randomCode() method) -> but still overridden for polymorphism...
    @Override
    public char[] randomCode(){
        Random r = new Random(); // creates a new random object to help generate random values (using methods)
        char[] generating_secret_code = new char[NUMBER_PEGS];

        for(int i = 0; i < NUMBER_PEGS; i++){
            int random_index = r.nextInt(0, COLORS_IN_GAME.length); // from index 0 to length - 1, so all the indices
            generating_secret_code[i] = COLORS_IN_GAME[random_index]; // Colors may repeat (as mentioned in spec).
        }

        return generating_secret_code;
    }

    // to start a new game, we don't have to make a new object... we just use same game config, and generate a new code and reset in-game stats
    @Override 
    public void startNewGame(){
        super.startNewGame(); // reset the parent class's game_won status (if we want to keep what the parent class has, as all game types start with win is false, and then we can add along)
        secret_code = randomCode(); // generate new code
    }

    // Returns the instructions for the current Mastermind game object (the main can print this to the users so they know what configs they are playing under)
    @Override
    public String getInstructions(){
        return "Guess the " + NUMBER_PEGS+ "-peg secret code using the colors {" + new String(COLORS_IN_GAME)+ "}. Colors can repeat."; // JAVA said to convert char array to String
    }

    // before we can validate, we can quickly check whether the guess was valid
    // We return a String because invalid user input is expected and should let the player try again, whereas invalid configuration should throw an error because it is a programming/setup mistake.
    @Override 
    public String validateGuess(String user_input){

        // Empty input is not a valid Mastermind guess, but this is not in parent class as empty input might be 'skip' in another game
        if(user_input.isEmpty()){
            return "Please Try Again. Reason: Your guess cannot be empty.";
        }

        // first check is making sure that the user's input is the same length as the secret code (if not, automatically false)
        if(user_input.length() != NUMBER_PEGS){
            return "Please Try Again. Reason: Guess must contain exactly " + NUMBER_PEGS + " colors.";
        }

        // now that we know the input length is valid, the next step is making sure that the actual input are of valid colors
        for(int i = 0; i < user_input.length(); i++){
            char guessed_color = user_input.charAt(i); // get the character at a specific position of the String

            // it is nice that the 'GameConfiguration' class has a "isLegalColor" method, and we actually have a config object of the same class, so we can use that algorithm here!
            if(!config.isLegalColor(guessed_color)){ // we don't need an input array, as config is the object itself we got the valid colors from, so it can check internally
                return "Please Try Again. Reason: Input color {" + guessed_color + "} is not a legal color for this game configuration.";
            }  
        }

        return null; // if all good, return null, and then the code that called this method can check if the return value is null, and if so, can conclude that the guess is valid
    }

    // I was debating to let validateGuess call this, but that would interfere with the fact that "invalid guesses do not consume an attempt"
    @Override
    public String scoreGuess(String user_input){
        Feedback f = new Feedback(secret_code, user_input); // char[] and String as inputs

        if(f.getBlackPegs() == NUMBER_PEGS){
            // Game Won!
            setWon(true); // set the parent class's game_won status to true, as the user has guessed the entire code correctly
        }

        return f.toString(); // overriding the toString() method inherited from Object Class to return a custom String
    }

    // A testing mode must make it possible to reveal or deterministically control the secret for testing.
    @Override 
    public String getSecretCode(){
        return new String(secret_code); // convert char[] to String
    }

}