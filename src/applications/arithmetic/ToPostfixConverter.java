//Justin Zhang 116215200
package applications.arithmetic;
import datastructures.sequential.Stack;

/**
 * This class implements the Converter interface to convert to postfix expressions.
 * This class takes in an infix expression and converts it to a postfix expression
 *
 * @author Justin Zhang
 */
public class ToPostfixConverter implements Converter{
    Operator operate;

    /**
     * The fundamental method of any class implementing this interface. This method converts the given
     * infix arithmetic expression to a postfix expression.
     *
     * @param expression  the given infix arithmetic expression
     * @return converted the converted postfix expression
     */
    @Override
    public String convert(ArithmeticExpression expression) {
        String converted = "";
        String str = expression.getExpression();
        TokenBuilder builder = new TokenBuilder();
        Stack<Character> operatorStack = new Stack<Character>();
        for(int i=0; i<str.length(); i++){
            if(isOperand(nextToken(str.substring(i), 0))){
                String tempToken = nextToken(str.substring(i), 0);
                i += tempToken.length()-1;
                for(int j=0; j<tempToken.length(); j++){
                    builder.append(tempToken.charAt(j));
                }
                builder.append(' ');
            }
            if(!isOperand(nextToken(str.substring(i), 0))){
                if(operatorStack.isEmpty() || operatorStack.peek().equals(Operator.LEFT_PARENTHESIS.getSymbol())){
                    operatorStack.push(nextToken(str.substring(i), 0).charAt(0));
                    continue;
                }
                if(str.charAt(i) == (Operator.RIGHT_PARENTHESIS.getSymbol())){
                    while(!operatorStack.peek().equals(Operator.LEFT_PARENTHESIS.getSymbol())){
                        builder.append(operatorStack.pop());
                        builder.append(' ');
                    }
                    operatorStack.pop();
                    continue;
                }
                if(!operatorStack.isEmpty() && Operator.of(operatorStack.peek()).getRank() == Operator.of(str.charAt(i)).getRank()){
                    while(!operatorStack.isEmpty() && Operator.of(operatorStack.peek()).getRank() == Operator.of(str.charAt(i)).getRank()){
                        builder.append(operatorStack.pop());
                        builder.append(' ');
                    }
                    operatorStack.push(str.charAt(i));
                    continue;
                }
                if(!operatorStack.isEmpty() && str.charAt(i) != (Operator.LEFT_PARENTHESIS.getSymbol()) && Operator.of(operatorStack.peek()).getRank() < Operator.of(str.charAt(i)).getRank()){
                    while(!operatorStack.isEmpty() && Operator.of(operatorStack.peek()).getRank() < Operator.of(nextToken(str.substring(i), 0)).getRank()){
                        builder.append(operatorStack.pop());
                        builder.append(' ');
                    }
                    operatorStack.push(str.charAt(i));
                }else{
                    operatorStack.push(str.charAt(i));
                }
                continue;
            }

        }
        if(!operatorStack.isEmpty()){
            while(!operatorStack.isEmpty()){
                builder.append(operatorStack.pop());
                builder.append(' ');
            }
        }
        converted = builder.build();
        return converted;
    }

    /**
     * Given a string and a specific index, this method returns the next token starting at that index.
     * A token represents the simplest building block of the expression.
     *
     * @param s     the given string
     * @param start the given index
     * @return token the end string which represents the next token
     */
    @Override
    public String nextToken(String s, int start) {
        TokenBuilder builder = new TokenBuilder();
        String token = "";
        for(int i=start; i<s.length(); i++){
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
