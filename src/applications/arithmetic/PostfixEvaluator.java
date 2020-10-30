//Justin Zhang 116215200
package applications.arithmetic;
import datastructures.sequential.Stack;

/**
 * This class implements the Evaluator interface to evaluate postfix expressions.
 * This class can evaluate any given postfix expression given using the evaluate method.
 *
 * @author Justin Zhang
 */
public class PostfixEvaluator implements Evaluator{

    /**
     * This method can take the input postfix string and evaluate its end value.
     *
     * @param expressionString the postfix string
     * @return the end calculated value of the postfix expression which was the sole value left in a stack
     */
    @Override
    public Double evaluate(String expressionString) {
        Stack<Double> operandStack = new Stack<Double>();
        for(int i=0; i<expressionString.length(); i++){
            if(expressionString.charAt(i) == ' ')
                continue;
            if(isOperand(nextToken(expressionString.substring(i), 0))){
                operandStack.push(Double.parseDouble(nextToken(expressionString.substring(i), 0)));
                i+=nextToken(expressionString.substring(i), 0).length();
            }else{
                if(expressionString.charAt(i) == Operator.ADDITION.getSymbol()){
                    Double val1 = operandStack.pop();
                    Double val2 = operandStack.pop();
                    operandStack.push(val2 + val1);
                }
                if(expressionString.charAt(i) == Operator.SUBTRACTION.getSymbol()){
                    Double val1 = operandStack.pop();
                    Double val2 = operandStack.pop();
                    operandStack.push(val2 - val1);
                }
                if(expressionString.charAt(i) == Operator.MULTIPLICATION.getSymbol()){
                    Double val1 = operandStack.pop();
                    Double val2 = operandStack.pop();
                    operandStack.push(val2 * val1);
                }
                if(expressionString.charAt(i) == Operator.DIVISION.getSymbol()){
                    Double val1 = operandStack.pop();
                    Double val2 = operandStack.pop();
                    operandStack.push(val2 / val1);
                }
            }
        }
        return operandStack.pop();
    }

    /**
     * Given a string and a specific index, this method returns the next token starting at that index.
     * A token represents the simplest building block of the expression.
     *
     * @param s     an input string
     * @param start the starting index
     * @return the next token starting at the given index in the input string
     */
    @Override
    public String nextToken(String s, int start) {
        Evaluator.TokenBuilder builder = new TokenBuilder();
        String token = "";
        for(int i=start; i<s.length(); i++){
            if(s.charAt(i) == ' '){
                token = builder.build();
                return token;
            }
            if(!isOperand(s.substring(i)) && builder.build().equals("")){
                builder.append(s.charAt(i));
                token = builder.build();
                return token;
            }
            if(!isOperand(s.substring(i))){
                token = builder.build();
                return token;
            }else{
                builder.append(s.charAt(i));
            }
        }
        token = builder.build();
        return token;
    }

    /**
     * Determines whether or not the first character of the string is a valid operand.
     * This is mostly used as a helper method for the nextToken method.
     *
     * @param s the input string
     * @return <code>true</code> if the given string is a valid operand, and <code>false</code> otherwise
     */
    @Override
    public boolean isOperand(String s) {
        if(s.charAt(0) == Operator.LEFT_PARENTHESIS.getSymbol() || s.charAt(0) == Operator.RIGHT_PARENTHESIS.getSymbol()) {
            return false;
        }
        return !Operator.isOperator(s);
    }
}
