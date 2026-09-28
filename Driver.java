package assignment2;

public class Driver{
    // this is what the user will run... so we must have a main (only uses GameTemplate ats the reference type, with minimal customization per game, so all good)
    public static void main(String[] args){
        // "java assignment2.Driver mastermind [test]"
        // so after running the program... these commands are accepted by the String[] args... so we have to make sure our algorithm acknowledges this

        if (args.length < 1) { // let the user know they have to tell us what game they want to play
            throw new IllegalArgumentException("Please specify a game and debug mode.");
        }
        
        // if there is an input, but the input is not the Mastermind game (atleast for now), throw an error
        if (!(args[0].equals("mastermind") || args[0].equals("Mastermind") || args[0].equals("Wordle") || args[0].equals("wordle"))) {
            throw new IllegalArgumentException("Unknown game: " + args[0]);
        } else{

            boolean testMode = false;

            if (args.length > 1) {
                if ((args[1].equals("test") || args[1].equals("Test"))) {
                    testMode = true;
                } else {
                    throw new IllegalArgumentException("Unknown debug: " + args[1]);
                }
            }
            
            boolean game_Mastermind = false;
            boolean game_Wordle = false;

            if(args[0].equals("mastermind") || args[0].equals("Mastermind")){
                game_Mastermind = true;
            } else if(args[0].equals("Wordle") || args[0].equals("wordle")){
                game_Wordle = true;
            }

            GameTemplate game;

            if(game_Mastermind){
                System.out.println("Launching Mastermind Game [Test Mode: " + testMode + "]...");
                // Example of polymorphism, as we are declaring as a GameTemplate type, but the actual object is a Mastermind type (so we can use the parent class's methods, but the child class's methods will override them since that is the reference or dynamic binding)
                game = new MastermindGame(new MastermindConfiguration()); // begin a new game object, or an actual game instance with the default configs
            } else {
                System.out.println("Launching Wordle Game [Test Mode: " + testMode + "]...");
                // Example of polymorphism, as we are declaring as a GameTemplate type, but the actual object is a Mastermind type (so we can use the parent class's methods, but the child class's methods will override them since that is the reference or dynamic binding)
                game = new WordleGame(new WordleConfiguration(6, 6, "assignment2/Dictionary.txt")); // begin a new game object, or an actual game instance with the default configs
            }
            
            GameInterface g_Interface = new GameInterface(game, testMode);
            g_Interface.runGame(); // hand over the baton, and let the user interface loop
    
            
        }
        
        }
}