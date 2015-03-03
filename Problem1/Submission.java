/**
* Submission.java
*
* A basic dummy Submission object for use with the auto-grader
*/
import java.util.LinkedList;

import java.util.Random;

public class Submission
{
  private static Random rand = new Random();
  public int id;

  public Submission()
  {
    // Give this submission a unique(ish) id
    id = rand.nextInt(10000000);
  }


}


class GenQueueSingleton {
   private static GenQueueSingleton single;
   private LinkedList<Submission> list;
   private GenQueueSingleton() {
		list = new LinkedList<Submission>();
	}

   public void add(Submission item) {
      list.addLast(item);
   }
   public Submission process() {
      return list.poll();
   }

   public int size() {
      return list.size();
   }

   public boolean hasItems() {
      return !list.isEmpty();
   }



   public static GenQueueSingleton getInstance() {
		if (single == null) {
			single = new GenQueueSingleton();
		}
		return single;
	}



}

class Test {
	public static void main(String[] args) {
		Submission g1 = new Submission();
		Submission g2 = new Submission();
		Submission g3 = new Submission();
		Submission g4 = new Submission();
		Submission g5 = new Submission();

		
		// GenQueueSingleton queue = GenQueueSingleton.getInstance();
		
		GenQueueSingleton queue = GenQueueSingleton.getInstance();
		queue = GenQueueSingleton.getInstance();

		queue.add(g1);
		queue.add(g2);
		queue = GenQueueSingleton.getInstance();
		queue.add(g3);
		queue.add(g4);
		queue.add(g5);
		System.out.println(queue.size());
		System.out.println(queue.process().id);
		System.out.println(queue.size());
		System.out.println(queue.process().id);
		System.out.println(queue.size());
		queue = GenQueueSingleton.getInstance();
		System.out.println(queue.process().id);
		System.out.println(queue.size());
		System.out.println(queue.process().id);
		System.out.println(queue.size());


	}
}









