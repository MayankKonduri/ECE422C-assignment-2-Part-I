package assignment2;

// Parent class for any turn-based guessing game, so it holds things that EVERY guessing game can have:
// - a maximum number of guesses and whether the player has won.

public class GameTemplate {
    
    private final int MAX_GUESSES;
    
    // Keeps track of whether the player has won or lost (in order to prompt a query to start a new game).
    private boolean game_won; 

    public GameTemplate(int MAX_GUESSES){ // constructor for the parent class, so that any child class can use this to set the maximum number of guesses allowed for the game
        this.MAX_GUESSES = MAX_GUESSES;
        this.game_won = false;
    }

    // Getters and Setters for Private and Protected Variables.
    
    public int getMaxGuesses(){
        return MAX_GUESSES;
    }

    public boolean isWon(){
        return game_won;
    }

    // Only the specific game should be able to declare itself won (so not public).
    protected void setWon(boolean won){
        this.game_won = won;
    }

    // ---------- OVERRIDING METHODS ----------

    public char[] randomCode(){
        return null; // this is a placeholder, as the parent class does not know how to generate a random code, but the child class will override this method and implement it (needed for polymorphism)
    }

    // Shared behaviour for all guessing games: when starting a new game, the player has not won yet.
    public void startNewGame(){
        setWon(false);    
    }

    // These instructions depend on the specific game, so the child game overrides them.
    public String getInstructions(){
        return "";
    }

    // This is where the specific game can validate the user input, so the child game overrides this.
    public String validateGuess(String user_input){
        return null;
    }

    // This is where the specific game can score the user input, so the child game overrides this.
    public String scoreGuess(String user_input){
        return "";
    }

    // Every guessing game has some sort of code or number that the user is trying to guess, so the child game overrides this.
    public String getSecretCode(){
        return "";
    }


}