package assignment2;

// Produces the resulting feedback for one user guess
// This class is there so that the analysis of a guess process is easy, and since a specific format is required to be returned... black peg and white peg, that can be written using polymorphism
public class MastermindFeedback extends FeedbackTemplate{
 
    public MastermindFeedback(char[] secret_code, String user_input){ // no default constructor, as this class' purpose is to provide a feedback to a given input
        // no need to have local variables set... as all analysis can directly be done once object is instantiated, as purpose is relatively straight-forward
        super(secret_code, user_input);
    }

    // This is where things get interesting, as we want to return a String to the main to print to the user, so we can actually override the toString() that is inherited from the object class (Polymorphism)
    @Override 
    public String toString(){
        return getCorrectLetters() + "B_" + getPartialCorrectLetters() + "W";
    }
}