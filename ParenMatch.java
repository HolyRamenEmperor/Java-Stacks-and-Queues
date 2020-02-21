// Name: J4-18
// Date: 1/7/20

import java.util.*;

public class ParenMatch
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      /* enter test cases here */
      parenExp.add("5+7");
      parenExp.add("(5+7)");
      parenExp.add(")5+7(");
      parenExp.add("((5+7)*3)");
      parenExp.add("<{5+7}*3>");
      parenExp.add("[(5+7)*]3");
      parenExp.add("(5+7)*3");
      parenExp.add("5+(7*3)");
      parenExp.add("((5+7)*3");
      parenExp.add("[(5+7]*3)");
      parenExp.add("[(5+7)*3])");
      parenExp.add("([(5+7)*3]");
   
      for( String s : parenExp )
      {
         boolean good = checkParen(s);
         if(good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
      }
   }
   
   public static boolean checkParen(String exp)
   {
      Stack<String> stk = new Stack<String>();
      for(int i = 0; i < exp.length(); i++)
      {
         String str = exp.substring(i, i+1);
         if(LEFT.contains(str))
         {
            stk.push(str);
         }
         else if(RIGHT.contains(str) && stk.isEmpty())
         {
            return false; 
         }
         else if(!stk.isEmpty() && (RIGHT.contains(str) || LEFT.contains(str)))
         {
            if(RIGHT.indexOf(str) != LEFT.indexOf(stk.peek()))
            {
               return false;
            }
            else if(RIGHT.indexOf(str) == LEFT.indexOf(stk.peek()))
            {
               stk.pop();
            }
         }
      }
      if(!stk.isEmpty())
      {
         return false;
      }
      return true;
   }
}

/*
 Parentheses Match
 5+7	 good!
 (5+7)	 good!
 )5+7(	 BAD
 ((5+7)*3)	 good!
 <{5+7}*3>	 good!
 [(5+7)*]3	 good!
 (5+7)*3	 good!
 5+(7*3)	 good!
 ((5+7)*3	 BAD
 [(5+7]*3)	 BAD
 [(5+7)*3])	 BAD
 ([(5+7)*3]	 BAD
 */
