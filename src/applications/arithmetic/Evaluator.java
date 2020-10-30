//Justin Zhang 116215200
package applications.arithmetic;

/**
 * This interface provides the structure that can be used to evaluate any
 * form of expressions such as infix, postfix, and prefix depending on how it is implemented.
 *
 * @author Justin Zhang
 */
public interface Evaluator {
    /**
     * This is the fundamental method for any class that implements this interface.
     * This method evaluates the given arithmetic expression depending on its implementation.
     *
     * @param expressionString
     * @return
     */
    Double evaluate(String expressionString);

    /**
     * Given a string and a specific index, this method returns the next token starting at that index.
     *
     * @param s     an input string
     * @param start the starting index
     * @return the next token starting at the given index in the input string
     */
    String nextToken(String s, int start);

    /**
     * Determines whether or not a string is a valid operand.
     * This is mostly used as a helper method for the nextToken method.
     *
     * @param s the input string
     * @return <code>true</code> if the given string is a valid operand, and <code>false</code> otherwise
     */
    boolean isOperand(String s);

    /**
     * This class handles the parsing of tokens from a string. This is used in situations where a
     * single token,primarily a number in this case, may take up more than one character in the string.
     */
    class TokenBuilder {

        /**
         * The StringBuilder object is used because Strings in Java are immutable, while
         * we may want to build a token as we parse from left to right one
         * character at a time and in this case, the tokens can represent single
         * operators or multi-digit operands.
         */
        private StringBuilder tokenBuilder = new StringBuilder();

        /**
         * @see StringBuilder#append(char)
         */
        public void append(char c) {
            tokenBuilder.append(c);
        }

        /**
         * @return the final string object that represents a single token
         * @see StringBuilder#toString()
         */
        public String build() {
            return tokenBuilder.toString();
        }

    }
}
