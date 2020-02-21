// Name: J4-18
// Date: 1/9/20
 
import java.util.*;
 
public class Postfix
{
  public static void main(String[] args)
  {
     System.out.println("Postfix  -->  Evaluate");
     ArrayList<String> postfixExp = new ArrayList<String>();
     /*  build your list of expressions here  */
     postfixExp.add("3 4 5 * +");
     postfixExp.add("3 4 * 5 +");
     postfixExp.add("10 20 + -6 6 * +");
     postfixExp.add("3 4 + 5 6 + *");
     postfixExp.add("3 4 5 + * 2 - 5 /");
     postfixExp.add("8 1 2 * + 9 3 / -");
     postfixExp.add("2 3 ^");
     postfixExp.add("20 3 %");
     postfixExp.add("21 3 %");
     postfixExp.add("22 3 %");
     postfixExp.add("23 3 %");
     postfixExp.add("5 !");
     postfixExp.add("1 1 1 1 1 + + + + !");
    
     for( String pf : postfixExp )
     {
        System.out.println(pf + "\t\t" + eval(pf));
     }
  }
 
  public static int eval(String pf)
  {
     int fresult = 1;
     Stack<Integer> operands = new Stack<Integer>();
     List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
     /*  enter your code here  */
     for(int i = 0; i < postfixParts.size(); i++)
     {
       if(!isOperator(postfixParts.get(i)) && !postfixParts.get(i).equals("!"))
       {
         operands.push(Integer.parseInt(postfixParts.get(i)));
       }
       else if(isOperator(postfixParts.get(i)))
       {
         int b = operands.pop();
         int a = operands.pop();
         operands.push(eval(a, b, postfixParts.get(i)));
       }
       else
       {
         int value = operands.pop();
         for(int j = 1; j <= value; j++)
         {
           fresult = fresult * j;
         }
         operands.push(fresult);
       }
     }
     return operands.peek();
  }
 
  public static int eval(int a, int b, String ch)
  {
    if(ch.equals("+"))
    {
      return a + b;
    }
    else if(ch.equals("-"))
    {
      return a - b;
    }
    else if(ch.equals("*"))
    {
      return a * b;
    }
    else if(ch.equals("/"))
    {
      return a/b;
    }
    else if(ch.equals("^"))
    {
      return (int)Math.pow(a, b);
    }
    return a%b;
  }
 
  public static boolean isOperator(String op)
  {
     String operators = "+-*/^%";
     if(operators.contains(op))
     {
       return true;
     }
     return false;
  }
}
 
/**********************************************
Postfix  -->  Evaluate
3 4 5 * +    23
3 4 * 5 +    17
10 20 + -6 6 * +   -6
3 4 + 5 6 + *    77
3 4 5 + * 2 - 5 /    5
8 1 2 * + 9 3 / -    7
2 3 ^    8
20 3 %   2
21 3 %   0
22 3 %   1
23 3 %   2
5 !    120
1 1 1 1 1 + + + + !    120
*************************************/
 

