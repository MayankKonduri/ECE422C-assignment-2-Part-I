package assignment2;

public abstract class FeedbackTemplate {

    private final char[] results_per_letter; // gives the result per position (C, P, A), as required by the spec


    private final int correctLetters; // helps the win logic, right symbol in the right spot -> 'C' (Mastermind calls this a black peg)
    private final int partially_correctLetters; // right symbol in the wrong spot -> 'P' (Mastermind calls this a white peg)

    public FeedbackTemplate(char[] secret_code, String user_input){
        // no need to have a default constructor, as the whole point of this class is to be an object where we check 2 inputs, almost acting as a method, but more complicated as it does multiple functions together

        boolean[] iterated_index = new boolean[secret_code.length]; // we need this because if exact match, we can still count the later character as a black peg, so we need to first search for black ('for exact'), and then iterate back to those we didn't search for an white

        char[] results_in_loop = new char[secret_code.length]; // uses a character array... since we basically need a result per position, and that can change as we iterate through the loop, like one character might not match the final guess, but that character could actually be in another position, so A can change to P
    
        for(int i = 0; i < results_in_loop.length; i++){ // the worst case scenario is that all the letters are absent, so we start with this, and then build on
            results_in_loop[i] = 'A';
        }

        int correct_in_loop = 0;
        int present_in_loop = 0;

        // search for C (correct letters) first (exact match)
        // search for black pegs first (exact match)
        for(int i = 0; i < secret_code.length; i++){
            if(secret_code[i] == user_input.charAt(i)){
                results_in_loop[i] = 'C';
                correct_in_loop++;
                iterated_index[i] = true; // we scanned through this and assigned a C, meaning case closed, don't worry about it again
            }
        }

        // now search for all P (present letters), and this is a little more complex like we mentioned as a index could change from A to P based on future looping
        // search for white pegs first (partial match)
        for(int i = 0; i < secret_code.length; i++){
            if(secret_code[i] == user_input.charAt(i)){
                continue; // we already marked as 'C'
            }

            // now... if the letter is not an exact match...
            for(int j = 0; j < secret_code.length; j++){
                if(!iterated_index[j] && (secret_code[j] == user_input.charAt(i))){
                    // although we are currently further in the indices of the guess word, a letter here matches with one of the words in the user's guess, so we can change that index for the user's guess from 'A' to 'P'
                    results_in_loop[i] = 'P';
                    present_in_loop++;
                    iterated_index[j] = true; // we already matched this letter from the final guess
                    break; // we are done for this letter
                }
            }
        }

         // set the final feedback values
        this.results_per_letter = results_in_loop;
        this.correctLetters = correct_in_loop;
        this.partially_correctLetters = present_in_loop;

    }

    // Returns the number of letters that were exactly correct (useful for win logic when checking).
    public int getCorrectLetters() {
        return correctLetters;
    }

    // (Don't necessarily need, but there for Encapsulation) Returns the number of white pegs.
    public int getPartialCorrectLetters(){
        return partially_correctLetters;
    }

    // (Don't necessarily need, but there for Encapsulation) Returns a copy of the results for this guess
    public char[] getLetterResults(){
        return results_per_letter.clone(); // want to return the reference to a cloned object... so another class cannot change the 'Truth'
    }

    // ---------- OVERRIDING METHODS (Abstract Methods :P)----------

    @Override 
    public abstract String toString(); // This is where things get interesting, as we want to return a String to the main to print to the user, so we can actually override the toString() that is inherited from the object class (Polymorphism)

}
