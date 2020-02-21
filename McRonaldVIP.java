// Name: J4-18
// Date: 1/16/20
 
import java.util.*;
 
public class McRonaldVIP
{
  public static final int TIME = 1080;  //18 hrs * 60 min
  public static int served = 0;
  public static double avg = 0;
  public static int maxtime = 0;
  public static int maxqueue = 0;
  public static boolean sa = false;
  public static boolean VIPhere = false;
  public static int VIPserved = 0; 
  public static int saVIPtime = -1;
  public static int saVIP = -1;
  public static int sacust = -1;
  public static int saservetime = -1;
  public static Queue<Integer> queVIP = new LinkedList<Integer>();
  public static Queue<Integer> questk = new LinkedList<Integer>();
  public static Queue<Integer> VIPque = new LinkedList<Integer>();
  public static Stack<Integer> stkque = new Stack<Integer>();
  public static Queue<Integer> que = new LinkedList<Integer>();
  public static void main(String[] args)
  {
    //opening hours of restaurant
    for(int i = 0; i < TIME; i++)
    {
      display(que, i);
    }
    //serving VIP and customers in service area after restaurant "closes"
    int minOT = TIME;
    if(VIPhere == true)
    {
      while(saVIPtime > 1)
      {
         saVIPtime--;
         System.out.println(minOT + ": VIP" + VIPque.toString());
         System.out.println(minOT + ":" + que.toString());
         System.out.println("ServiceArea#1 " + saVIP + ":" + saVIPtime);
         System.out.println();
         minOT++;
      }
      System.out.println("VIP " + saVIP + " leaves and his total wait time is " + (minOT - saVIP));
      queVIP.add(minOT - saVIP);
      VIPhere = false;
    }
    if(!(sacust == -1))
    {
      while(saservetime > 1)
      {
         saservetime--;
         System.out.println(minOT + ": VIP" + VIPque.toString());
         System.out.println(minOT + ":" + que.toString());
         System.out.println("ServiceArea#1 " + sacust + ":" + saservetime);
         System.out.println();
         minOT++;
      }
      System.out.println("Customer " + sacust + " leaves and his total wait time is " + (minOT - sacust));
      questk.add(minOT - sacust);
    }
    //serving people in queue after restaurant "closes"
    while(!VIPque.isEmpty())
    {
      saVIP = VIPque.remove();
      saVIPtime = (int)((Math.random() * 6) + 2);
      while(saVIPtime > 0)
      {
         System.out.println(minOT + ": VIP" + VIPque.toString());
         System.out.println(minOT + ":" + que.toString());
         System.out.println("ServiceArea#1 " + saVIP + ":" + saVIPtime);
         System.out.println();
         saVIPtime--;
         minOT++;
      }
      System.out.println("VIP " + saVIP + " leaves and his total wait time is " + (minOT - saVIP));
      queVIP.add(minOT - saVIP);
    }
    while(!que.isEmpty())
    {
      sacust = que.remove(); 
      saservetime = (int)((Math.random() * 6) + 2);
      while(saservetime > 0)
      {
         System.out.println(minOT + ": VIP" + VIPque.toString());
         System.out.println(minOT + ":" + que.toString());
         System.out.println("ServiceArea#1 " + sacust + ":" + saservetime);
         System.out.println();
         saservetime--;
         minOT++;
      }
      System.out.println("Customer " + sacust + " leaves and his total wait time is " + (minOT - sacust));
    }
    System.out.println(minOT + ": VIP" + VIPque.toString());
    System.out.println(minOT + ":" + que.toString());
    System.out.println("ServiceArea#1 " + -1 + ":" + -1);
    questk.add(minOT - sacust);
    System.out.println();
    //statistics
    System.out.println("Total customers served = " + served);
    Queue<Integer> quetemp = new LinkedList<Integer>(questk);
    double sum = 0; 
    int count = 0;
    while(!quetemp.isEmpty())
    {
      sum += quetemp.remove();
      count++;
    }
    avg = sum / count;
    System.out.println("Average wait time = " + avg);
    while(!questk.isEmpty())
    {
      int maxy = questk.remove();
      if(maxy > maxtime)
      {
         maxtime = maxy;
      }
    }
    System.out.println("Longest wait time = " + maxtime);
    while(!stkque.isEmpty())
    {
      int queue = stkque.pop();
      if(queue > maxqueue)
      {
         maxqueue = queue;
      }
    }
    System.out.println("Longest queue = " + maxqueue);
    System.out.println("Total VIP served = " + VIPserved);
    double VIPsum = 0;
    int VIPcount = 0;
    while(!queVIP.isEmpty())
    {
      VIPsum += queVIP.remove();
      VIPcount++;
    }
    double VIPavg = VIPsum / VIPcount;
    System.out.println("Average VIP wait time = " + VIPavg);
  }
 
  public static void display(Queue<Integer> q, int min)   //if you are storing arrival times
  //public static void display(Queue<Customer> q, int min) //if you have a Customer class
  {
    int cust = (int)(Math.random() * 5);
    int time = (int)((Math.random() * 6) + 2);
    int VIP = (int)(Math.random() * 20);
    int VIPtime = (int)((Math.random() * 6) + 2);
    if(VIP == 0 || (VIPhere == true))
    {
      if(VIP == 0)
      {
         System.out.println("VIP arrives at minute " + min);
         VIPserved++;
         if(VIPhere == false)
         {
            VIPhere = true;
            saVIP = min;
            saVIPtime = VIPtime;
            System.out.println(min + ": VIP" + VIPque.toString());
            System.out.println(min + ":" + que.toString());
            System.out.println("ServiceArea#1 VIP" + saVIP + ":" + saVIPtime);
            System.out.println();
         }
         else if(VIPhere == true && saVIPtime > 1)
         {
            VIPque.add(min);
            saVIPtime = saVIPtime - 1;
            System.out.println(min + ": VIP" + VIPque.toString());
            System.out.println(min + ":" + que.toString());
            System.out.println("ServiceArea#1 VIP" + saVIP + ":" + saVIPtime);
            System.out.println();
         }
         else if(VIPhere == true && saVIPtime == 1)
         {
            VIPhere = true;
            saVIP = min;
            saVIPtime = VIPtime;
            System.out.println(min + ": VIP" + VIPque.toString());
            System.out.println(min + ":" + que.toString());
            System.out.println("ServiceArea#1 VIP" + saVIP + ":" + saVIPtime);
            System.out.println();
         }
      }
      else if(VIPhere == true)
      {
         if(saVIPtime == 1)
         {
            System.out.println("VIP " + saVIP + " leaves and his total wait time is " + (min - saVIP));
            queVIP.add(min - saVIP);
            if(VIPque.isEmpty())
            {
               VIPhere = false;
               saVIP = -1;
               saVIPtime = -1;
            }
            else
            {
               VIPhere = true;
               saVIP = VIPque.remove();
               saVIPtime = VIPtime;
               System.out.println(min + ": VIP" + VIPque.toString());
               System.out.println(min + ":" + que.toString());
               System.out.println("ServiceArea#1 VIP" + saVIP + ":" + saVIPtime);
               System.out.println();
            }
         }
         else
         { 
            saVIPtime = saVIPtime - 1;
            System.out.println(min + ": VIP" + VIPque.toString());
            System.out.println(min + ":" + que.toString());
            System.out.println("ServiceArea#1 VIP" + saVIP + ":" + saVIPtime);
            System.out.println();
         }
      }
      if(cust == 0 && VIPhere == true && (VIP != 0))
      {
         System.out.println("Customer arrives at minute " + min);
         served++;
         que.add(min);
      }
    } 
    else
      if(cust != 1)
      {
         if(sa == true && saservetime > 1)
         {
            saservetime = saservetime - 1;
         }
         else if(saservetime == 1 || sa == false)
         {
            if(saservetime == 1)
            {
               questk.add(min - sacust);
               System.out.println("Customer " + sacust + " leaves and his total wait time is " + (min - sacust));
            }
            if(que.isEmpty())
            {
               sacust = -1;
               saservetime = -1;
               sa = false;
            }
            else
            {
               sacust = que.remove();
               saservetime = time;
               sa = true;
            }
         }
         System.out.println(min + ":" + que.toString());
         System.out.println("ServiceArea#1 " + sacust + ":" + saservetime);
         System.out.println();
         Queue<Integer> temp = new LinkedList<Integer>(que);
         while(!temp.isEmpty())
         {
            maxqueue++;
            temp.remove();
         }
         stkque.push(maxqueue);
         maxqueue = 0;
   
         return;
      }
      else
      {
        System.out.println("Customer arrives at minute " + min);
        served++;
        if(sa == true && saservetime > 1)
        {
          saservetime = saservetime - 1;
        }
        else if(saservetime == 1 || sa == false)
        {
          if(saservetime == 1)
          {
           questk.add(min - sacust);
           System.out.println("Customer " + sacust + " leaves and his total wait time is " + (min - sacust));
          }
          if(que.isEmpty())
          {
           sacust = -1;
           saservetime = -1;
           sa = false;
          }
          else
          {
           sacust = que.remove();
           saservetime = time;
           sa = true;
          }
        }
        if(sa == true)
        {
           que.add(min);
        }
        else
        {
           sacust = min;
           saservetime = time;
           sa = true;  
        }
        System.out.println(min + ":" + que.toString());
        System.out.println("ServiceArea#1 " + sacust + ":" + saservetime);
        System.out.println();
        Queue<Integer> temp = new LinkedList<Integer>(que);
        while(!temp.isEmpty())
        {
           maxqueue++;
           temp.remove();
        }
        stkque.push(maxqueue);
        maxqueue = 0;
        return;
      }
         
  }
}
 
// class Customer      // if you want a Customer class
// {
//
// }


