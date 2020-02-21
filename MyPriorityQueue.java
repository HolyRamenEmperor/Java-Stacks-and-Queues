//Name: J4-18
//Date: 1/27/20
//extension to LunchRoom 
//implements java.util.PriorityQueue
//   uses an ArrayList in sorted order
//LunchRoom is the driver class

import java.util.*;
public class MyPriorityQueue<E extends Comparable>
{
   private List<E> pq;
   public MyPriorityQueue()
   {
      pq = new ArrayList<E>();
   }
   public boolean add(E obj)
   {
      int index = 0;
      boolean stop = false;
      for(int i = 0; i < pq.size(); i++)
      {
         if(obj.compareTo(pq.get(i)) >= 0 && stop == false)
         {
            index = i;
            stop = true;
         }
      }
      pq.add(index, obj);
      return true;
   }
   public E remove()
   {
      E obj = pq.remove(pq.size() - 1);
      return obj;
   }
   public E peek()
   {
      E obj = pq.get(pq.size() - 1);
      return obj;
   }
   public boolean isEmpty()
   {
      return pq.size() == 0;
   }       
}