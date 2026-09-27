package assignment2;

// Produces the resulting feedback for one user guess in Wordle
// Same idea as Feedback.java for Mastermind (in fact I have it open in split view when writing code now)
public class WordleFeedback extends FeedbackTemplate {

    public WordleFeedback(char[] secret_code, String user_input){
        // no need to have a default constructor, as the whole point of this class is to be an object where we check 2 inputs, almost acting as a method, but more complicated as it does multiple functions together
        super(secret_code, user_input);
    }


    // Returns the Wordle feedback in the required format as per the spec sheet
    @Override
    public String toString(){ // overrides the Object class' toString() method
        String results_feedback = "";

        for(char letter_result : getLetterResults()){
            results_feedback += letter_result + " ";
        }

        return results_feedback.trim(); // help from AI on how to remove the last " " as our loop always ends with it, even for last letter
    }

}
