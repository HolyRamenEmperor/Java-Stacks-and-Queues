// Name: J4-18
// Date: 1/13/20

import java.util.*;

public class Infix
{
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
     /*build your list of Infix expressions here  */
      ArrayList<String> infixExp = new ArrayList<String>();
      infixExp.add("3 - 4 + 5");
      infixExp.add("3 + 4 * 5");
      infixExp.add("3 * 4 + 5");
      infixExp.add("( -5 + 15 ) - 6 / 3");
      infixExp.add("( 3 + 4 ) * ( 5 + 6 )");
      infixExp.add("( 3 * ( 4 + 5 ) - 2 ) / 5");
      infixExp.add("8 + -1 * 2 - 9 / 3");
      infixExp.add("3 * ( 4 * 5 + 6 )");
      infixExp.add("3 * ( 4 * 5 - 6 + 2 )");
   
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);  //get this to work first
         //System.out.println(infix + "\t\t\t" + pf); 
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + Postfix.eval(pf));  //Postfix must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   { 
      List<String> infixParts = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      /* enter your code here  */
      Stack<String> stk = new Stack<String>();
      String highest = "*/";
      String lowest = "+-";
      String postfix = "";
      boolean done = false;
      int x = 1; 
      for(int i = 0; i < infixParts.size(); i++)
      {
         done = false;
         String str = infixParts.get(i);
         if(!Postfix.isOperator(str) && (!str.equals("(") && !str.equals(")")))
         {
            if(x == 1)
            {
               postfix += str;
               x++;
            }
            else
            {
               postfix += " " + str;
            }
         }
         else if(str.equals("("))
         {
            stk.push(str);
         }
         else if(str.equals(")"))
         {
            while(!stk.peek().equals("("))
            {
               postfix += " " + stk.pop();
            }
            stk.pop();
         }
         else if(Postfix.isOperator(str))
         {
            if(stk.isEmpty())
            {
               stk.push(str);
            }
            else if(!isLowerOrEqual(str.charAt(0), stk.peek().charAt(0)))
            {
               stk.push(str);
            }
            else if(stk.peek().equals(")"))
            {
               stk.push(str);
            }
            else
            {
               while(!stk.isEmpty() && done == false)
               {
                  if(!isLowerOrEqual(str.charAt(0),stk.peek().charAt(0)) && !stk.peek().equals(")") && !stk.peek().equals("("))
                  {
                     postfix += " " + stk.pop();
                  }
                  else if(!stk.peek().equals(")") && !stk.peek().equals("("))
                  {
                     postfix += " " + stk.pop();
                  }
                  else
                  {
                     done = true;
                  }
               }
               stk.push(str);
            }
         }
      }
      while(!stk.isEmpty())
      {
         postfix += " " + stk.pop();
      }
      return postfix;
   }
   
	//returns true if c1 has lower or equal precedence than c2
   public static boolean isLowerOrEqual(char c1, char c2)
   {
      String highest = "*/";
      String lowest = "+-";
      String one = "" + c1;
      String two = "" + c2;
      if((lowest.contains(one) && lowest.contains(two)) || (highest.contains(one) && highest.contains(two)) || (lowest.contains(one) && highest.contains(two)))
      {
         return true;
      }
      return false; 
   }
}
	
/********************************************

 Infix  	-->	Postfix		-->	Evaluate
 3 - 4 + 5			3 4 - 5 +			4
 3 + 4 * 5			3 4 5 * +			23
 3 * 4 + 5			3 4 * 5 +			17
 ( -5 + 15 ) - 6 / 3			-5 15 + 6 3 / -			8
 ( 3 + 4 ) * ( 5 + 6 )			3 4 + 5 6 + *			77
 ( 3 * ( 4 + 5 ) - 2 ) / 5			3 4 5 + * 2 - 5 /			5
 8 + -1 * 2 - 9 / 3			8 -1 2 * + 9 3 / -			3
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78
 3 * ( 4 * 5 - 6 + 2 )			3 4 5 * 6 - 2 + *			48
  
***********************************************/