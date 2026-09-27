package assignment2;

// Parent class for any turn-based guessing game, so it holds things that EVERY guessing game can have:
// - a maximum number of guesses and whether the player has won.

public abstract class GameTemplate {
    
    private final int MAX_GUESSES;

    // The secret word that the player is trying to guess (this is defined dynamically on instatiation of each object of  each new game)
    protected char[] secret_word;
    
    // Keeps track of whether the player has won or lost (in order to prompt a query to start a new game).
    private boolean game_won; 

    public GameTemplate(int MAX_GUESSES){ // constructor for the parent class, so that any child class can use this to set the maximum number of guesses allowed for the game
        this.MAX_GUESSES = MAX_GUESSES;
        this.game_won = false;
    }

    public void startNewGame(){
        setWon(false);
        secret_word = randomCode();
    }

    // Getters and Setters for Private and Protected Variables.
    
    public int getMaxGuesses(){
        return MAX_GUESSES;
    }
    
    // A testing mode must make it possible to reveal or deterministically control the secret for testing.
    public String getSecretCode(){
        return new String(secret_word); // convert char[] to String (could use .clone() if the secret word was already in the String format)
    }

    public boolean isWon(){
        return game_won;
    }

    // Only the specific game should be able to declare itself won (so not public).
    protected void setWon(boolean won){
        this.game_won = won;
    }

    // ---------- OVERRIDING METHODS (Abstract Methods :P)----------

    public abstract char[] randomCode(); // this is a placeholder, as the parent class does not know how to generate a random code, but the child class will override this method and implement it (needed for polymorphism)
    public abstract String getInstructions(); // These instructions depend on the specific game, so the child game overrides them.
    public abstract String validateGuess(String user_input); // This is where the specific game can validate the user input, so the child game overrides this.
    public abstract String scoreGuess(String user_input); // This is where the specific game can score the user input, so the child game overrides this.

}