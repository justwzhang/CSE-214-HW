//Justin Zhang 116215200
package applications.arithmetic;

/**
 * This class can determine if a certain input expression is a valid Dyck word.
 * This class makes use of the Brackets enum for stored bracket values.
 *
 * @author Ritwik Banerjee and Justin Zhang
 */
public class DyckWord {

    private final String word;

    private static int currentIndex;

    /**
     * A constructor that creates a DyckWord object if the input word is a valid Dyck word.
     *
     * @param word is the input string of an expression
     * @throws IllegalArgumentException if the input string is not a valid Dyck word
     */
    public DyckWord(String word) {
        if (isDyckWord(word))
            this.word = word;
        else
            throw new IllegalArgumentException(String.format("%s is not a valid Dyck word.", word));
    }

    /**
     * This evaluates whether an input string expression is a valid Dyck word.
     * This is mainly used for keeping track of the iteration of the string in conjunction
     * with the isDyckWordHelper method.
     *
     * @param word is the input string expression
     * @return <code>true</code> if and only if the input string expression is a Dyck word, <code>false</code> otherwise.
     */
    private static boolean isDyckWord(String word) {
        int numOfLeft = 0;
        int numOfRight = 0;
        boolean check = true;
        for(int i=0; i<word.length(); i++) {
            if (Brackets.isLeftBracket(word.charAt(i)))
                numOfLeft++;
            if (Brackets.isRightBracket(word.charAt(i)))
                numOfRight++;
        }
        for(int i=0; i<word.length(); i++){
            if(Brackets.isLeftBracket(word.charAt(i))){
                check = isDyckWordHelper(word.substring(i));
            }
            if(!check)
                break;
        }
        if(numOfLeft != numOfRight) {
            return false;
        }
        currentIndex = 0;
        return check;
    }

    /**
     * This is a helper method for the isDyckWord method. It's primary usage is
     * to determine whether or not a part of the original string is a valid Dyck word.
     * This method recursively calls itself when a left bracket is present.
     *
     * @param word the input string that can be a sub string of the previous recursive call
     * @return <code>true</code> if and only if the input string/sub string expression is a Dyck word, <code>false</code> otherwise.
     */
    private static boolean isDyckWordHelper(String word){
        boolean check = true;
        char left = ' ';
        char right = ' ';
        for(int i=0; i<word.length(); i++){
            if(Brackets.isLeftBracket(word.charAt(i))){
                if(left != ' ') {
                    check = isDyckWordHelper(word.substring(i));
                    i += currentIndex;
                    currentIndex = 0;
                    if(!check)
                        return check;
                    if(i<word.length()-1)
                        i++;
                    if(Brackets.isLeftBracket(word.charAt(i))){
                        i--;
                        continue;
                    }
                }
                else
                    left = word.charAt(i);
            }
            if(Brackets.isRightBracket(word.charAt(i))){
                right = word.charAt(i);
            }
            if(left != ' ' && right != ' '){
                if(Brackets.correspond(left, right)){
                    currentIndex = i;
                    return check;
                }else {
                    return false;
                }
            }
        }

        return check;
    }

    /**
     * A simple getter method that retrieves the expression.
     *
     * @return word
     */
    public String getWord() {
        return word;
    }

}
