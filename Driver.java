package assignment2;

public class Driver{
    // this is what the user will run... so we must have a main
    public static void main(String[] args){
        // "java assignment2.Driver mastermind [test]"
        // so after running the program... these commands are accepted by the String[] args... so we have to make sure our algorithm acknowledges this

        if (args.length < 1) { // let the user know they have to tell us what game they want to play
            throw new IllegalArgumentException("Please specify a game and debug mode.");
        }
        
        // if there is an input, but the input is not the Mastermind game (atleast for now), throw an error
        if (!(args[0].equals("mastermind") || args[0].equals("Mastermind"))) {
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

            System.out.println("Launching Mastermind Game [Test Mode: " + testMode + "]...");
            // Example of polymorphism, as we are declaring as a GameTemplate type, but the actual object is a Mastermind type (so we can use the parent class's methods, but the child class's methods will override them since that is the reference or dynamic binding)
            GameTemplate game = new Mastermind(new GameConfiguration()); // begin a new game object, or an actual game instance with the default configs
        
            // as of right now, the only game is Mastermind, so we can only check for that
        
            // so far, this file's purpose is to determine what specific game to launch, and then launch the actual game settings, and then it must also launch the user interface along with the data bank
            // (1) determine game to launch (Driver.java)
            // (2) set up game bank + inside logic (GameTemplate.java + Mastermind.java + Feedback.java)
            // (3) set up user interface TODO (GameInterface.java)

            GameInterface g_Interface = new GameInterface(game, testMode);
            g_Interface.runGame(); // hand over the baton, and let the user interface loop
    
            
        }
        
        }
}