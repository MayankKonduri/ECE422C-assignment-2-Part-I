package assignment2;

// Produces the resulting feedback for one user guess
// This class is there so that the analysis of a guess process is easy, and since a specific format is required to be returned... black peg and white peg, that can be written using polymorphism
public class Feedback{

    private final int blackPegs; // generated for the specific object, and this object is generated for each guess, so can have final, as won't be changed
    private final int whitePegs; // generated for the specific object, and this object is generated for each guess, so can have final, as won't be changed
    
    public Feedback(char[] secret_code, String user_input){ // no default constructor, as this class' purpose is to provide a feedback to a given input
        // no need to have local variables set... as all analysis can directly be done once object is instantiated, as purpose is relatively straight-forward

        boolean[] iterated_index = new boolean[secret_code.length]; // we need this because if exact match, we can still count the later character as a black peg, so we need to first search for black ('for exact'), and then iterate back to those we didn't search for an white

        int black = 0; // have non-'final' variables so we can increment along the process
        int white = 0; // have non-'final' variables so we can increment along the process

        // search for black pegs first (exact match)
        for(int i = 0; i < secret_code.length; i++){
            if(secret_code[i] == user_input.charAt(i)){
                black++;
                iterated_index[i] = true; // we scanned through this and assigned a peg
            }
        }

        for(int i = 0; i < secret_code.length; i++){
            if(secret_code[i] == user_input.charAt(i)){
                continue; // we have already declared this as a black peg
            }

            // now that we know this is NOT a black peg, to consider this as a white peg, it must match with another letter from the secret_code, but from an index that hasn't been used
            for(int j = 0; j < secret_code.length; j++){
                if(!iterated_index[j] && (secret_code[j] == user_input.charAt(i))){
                    // white peg found!
                    white++;
                    iterated_index[j] = true; // don't need to worry about the 'i', as we won't loop back
                    break; // we are done for this letter
                }
            }
        }

        // set the final feedback values
        this.blackPegs = black;
        this.whitePegs = white;
    }

    // (Don't necessarily need, but there for Encapsulation) Returns the number of black pegs.
    public int getBlackPegs(){
        return blackPegs;
    }


    // (Don't necessarily need, but there for Encapsulation) Returns the number of white pegs.
    public int getWhitePegs(){
        return whitePegs;
    }

    // This is where things get interesting, as we want to return a String to the main to print to the user, so we can actually override the toString() that is inherited from the object class (Polymorphism)
    @Override 
    public String toString(){
        return blackPegs + "B_" + whitePegs + "W";
    }
}