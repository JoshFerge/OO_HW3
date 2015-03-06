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
  private int id;

  public Submission()
  {
    // Give this submission a unique(ish) id
    id = rand.nextInt(10000000);
  }

  public int getId() {
  	return id;
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

   public static synchronized GenQueueSingleton getInstance() {
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

		System.out.println("running getInstance");
		GenQueueSingleton queue = GenQueueSingleton.getInstance();
		System.out.println("running getInstance");
		queue = GenQueueSingleton.getInstance();
		System.out.println("adding submission: "+g1.getId());
		queue.add(g1);
		System.out.println("adding submission: "+g2.getId());
		queue.add(g2);
		System.out.println("running getInstance");
		queue = GenQueueSingleton.getInstance();
		System.out.println("adding submission: "+g3.getId());
		queue.add(g3);
		System.out.println("adding submission: "+g4.getId());
		queue.add(g4);
		System.out.println("adding submission: "+g5.getId());
		queue.add(g5);
		System.out.println("length of queue: "+queue.size());
		System.out.println("popping "+queue.process().getId());
		System.out.println("length of queue: "+queue.size());
		System.out.println("popping "+queue.process().getId());
		System.out.println("length of queue: "+queue.size());
		System.out.println("running getInstance");
		queue = GenQueueSingleton.getInstance();
		System.out.println("popping "+queue.process().getId());
		System.out.println("length of queue: "+queue.size());
		System.out.println("popping "+queue.process().getId());
		System.out.println("length of queue: "+queue.size());

	}
}









