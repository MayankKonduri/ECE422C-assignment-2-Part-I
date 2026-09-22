package assignment2;

public class GameConfiguration{

    // STATIC as these are the default that all the objects get and can default to

    // Brown, Grey, Orange, Pink, Red, Yellow
    private static final char DEFAULT_COLORS[] = {'B', 'G', 'O', 'P', 'R', 'Y'};
    
    private static final int DEFAULT_NUMBER_PEGS = 4;
    private static final int DEFAULT_MAX_GUESSES = 12;

    // Extra: Cyan, Magenta, Teal, White
    private static final char[] DEFAULT_MAX_COLORS = {'B', 'G', 'O', 'P', 'R', 'Y', 'C', 'M', 'T', 'W'};

    // NON-STATIC because they belong to a specific object, and can be configured per GameConfiguration object (if user is picky)

    private final int NUMBER_PEGS;
    private final int MAX_GUESSES;
    private final char[] COLORS_IN_GAME;

    // DEFAULT CONSTRUCTOR which creates a default Mastermind game as per instruction sheet specs.
    public GameConfiguration() {
        this.NUMBER_PEGS = DEFAULT_NUMBER_PEGS;
        this.MAX_GUESSES = DEFAULT_MAX_GUESSES;
        this.COLORS_IN_GAME = DEFAULT_COLORS.clone();
    }

    public GameConfiguration(int NUMBER_PEGS, int MAX_GUESSES, char[] COLORS_IN_GAME){
        if(NUMBER_PEGS < 1){
            throw new IllegalArgumentException("Need at least 1 peg"); // tell user the code must have atleast 1 peg to guess
        } else{
            this.NUMBER_PEGS = NUMBER_PEGS;
        }

        if(MAX_GUESSES < 1){
            throw new IllegalArgumentException("Need at least 1 guess"); // tell user the player must atleast 1 guess to play the game
        } else{
            this.MAX_GUESSES = MAX_GUESSES;
        }

        if (COLORS_IN_GAME == null || COLORS_IN_GAME.length == 0) {
            throw new IllegalArgumentException("Need at least 1 color"); // tell user that they need atleast 1 colors to guess from
        } else if (COLORS_IN_GAME.length > DEFAULT_MAX_COLORS.length){ // tell the user they chose an invalid set of colors
            throw new IllegalArgumentException("Requested list of colors is more than configured (hint: you might have duplicated or used colors out of the set)");
        } else {
            for (int i = 0; i < COLORS_IN_GAME.length; i++) { // go through all the colors the user has requested, and make sure they are part of the max_default_colors configured for this game
                if(!contains(DEFAULT_MAX_COLORS, COLORS_IN_GAME[i])){
                    throw new IllegalArgumentException("Color {" + COLORS_IN_GAME[i] + "} not in set of allowed choices"); // let the user know they chose an invalid colors
                }

                for(int j = 0; j < COLORS_IN_GAME.length; j++){
                    if(!(i==j)){
                        if(COLORS_IN_GAME[i] == COLORS_IN_GAME[j]){
                            throw new IllegalArgumentException("Color {" + COLORS_IN_GAME[i] + "} has been duplicated");
                        }
                    }
                }
            }

            this.COLORS_IN_GAME = COLORS_IN_GAME.clone(); // if all good, set the current object's default list of colors as listed by the user.
        }

    }
    
    // Checks whether a specific color is legal for this particular game configuration.
    public boolean isLegalColor(char color) {
        return contains(COLORS_IN_GAME, color);
    }

    // customized function to check whether a specific color exists inside the max colors allowed for the game configuration
    private static boolean contains(char[] input_array, char color_to_be_checked){
        for(char elements: input_array){
            if(elements == color_to_be_checked){
                return true;
            }
        }

        return false;
    }

    // Getters for Private Variables

    // Returns the number of pegs for this configuration.
    public int getNumberPegs() {
        return NUMBER_PEGS;
    }

    // Returns the maximum number of guesses for this configuration.
    public int getMaxGuesses() {
        return MAX_GUESSES;
    }

    // Returns the colors used by this configuration.
    public char[] getColors() {
        return COLORS_IN_GAME.clone();
    }


}