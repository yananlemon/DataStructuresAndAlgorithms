package com.lemon.stack;  
  
import java.util.ArrayList;  
import java.util.List;  
import java.util.Stack;  
import java.util.StringTokenizer;  
import java.util.regex.Pattern;  
  
public class PostfixConverter {  
      
    public static void main(String[] args) {  
        //String infixExpression="10+3-6/2+4*5";  
        String infixExpression="(((10+3)-(6/2))+(4*5))";  
        buildPostfixExpression(infixExpression);  
    }  
      
    public static List<String> buildPostfixExpression(String infixExpression){  
        List<String> result=new ArrayList<String>();  
        Stack<String> operatorStack=new Stack<String>();  
        StringTokenizer strTokenizer = new StringTokenizer(infixExpression, "+-*/()", true);  
        String currentEle;  
        while (strTokenizer.hasMoreTokens()) {    
            currentEle = strTokenizer.nextToken().trim();  
            if(currentEle!=null && !currentEle.equals("")){  
                if(isDigital(currentEle)){  
                    result.add(currentEle);  
                }else if(currentEle.equals("(")){  
                    operatorStack.push(currentEle);  
                }else if(currentEle.equals(")")){  
                    while(!operatorStack.isEmpty() && !operatorStack.peek().equals("(")){  
                        result.add(operatorStack.pop());  
                    }  
                    operatorStack.pop();  
                }else if(currentEle.equals("+")||currentEle.equals("-")||  
                        currentEle.equals("*")||currentEle.equals("/")||  
                        currentEle.equals("(")||currentEle.equals(")")){  
                    while(!operatorStack.isEmpty()   
                            &&  
                            (!currentEle.equals(")") && !operatorStack.peek().equals("("))&&  
                            getPriority(operatorStack.peek())>=getPriority(currentEle)){  
                        result.add(operatorStack.pop());  
                    }  
                    operatorStack.push(currentEle);  
                }  
            }  
              
        }  
        while(!operatorStack.isEmpty()){  
            result.add(operatorStack.pop());  
        }  
        System.out.println(result.toString());  
        return result;  
    }  
      
    public static int getPriority(String operator){  
        if(operator.equals("+")||operator.equals("-")){  
            return 1;  
        }else if(operator.equals("*")||operator.equals("/")){  
            return 2;  
        }else if(operator.equals("(")){  
            return 3;  
        }  
        return 0;  
    }  
      
    /**  
     * 判断字符串是否是数字类型  
     * @param str  
     * @return true:数字;false:非数字 
     */    
    public static boolean isDigital(String str) {    
        String numRegex = "^\\d+(\\.\\d+)?$";   
        return Pattern.matches(numRegex, str);    
    }    
}  
