public class MyQueue implements QueueInterface {
	/* 
	* TODO 2: Implement "MyQueue"
	*/
	
    //We create an instance of MyLinkedList
	private MyLinkedList queueList;
	
	//In the constructor, we initialize it
	public MyQueue()
	{
		queueList = new MyLinkedList();
	}
	
	//In this method, we check if it has any elements by using MyLinkedList's isEmpty method
	//because if the underlying list is empty so is the queue
	
	public boolean isEmpty()
	{
		return queueList.isEmpty();
	}

	//In this method, we add an Object at the end of the queue
	public void enqueue(Object item)
	{
		queueList.add(queueList.size(), item);
	}
	
	// In this method, we remove an Object from the front of the queue 
	//as long as the queue is not empty, if it is throw an exception 
	public Object dequeue()
	{
		if(isEmpty() != true) 
		{
			Object objectInFront = queueList.get(0);
			queueList.remove(0);
			return objectInFront;
		}
		
		else
		{
			throw new QueueException("DEQUEUE: The queue is empty");
		}
	}

	//This method removes all things form the queue
	public void dequeueAll()
	{
		queueList.removeAll();
	}

	//This method allows you to look at the element at the front of the 
	//queue as long as the queue is not empty 
	//if it is, we throw an exception
	public Object peek()
	{
		if(isEmpty() != true)
		{
			return queueList.get(0);
		}
		
		else
		{
			throw new QueueException("PEEK: The queue is empty");
		}
		
	}
	
	//This method, prints out the queue in this format
	// (Object, Object, ... )
	public String toString()
	{
		String s = "(";
		for(int i = 0; i < queueList.size(); i++)
		{
			if (i != 0 ) 
			{
				s = s + ",";
			}
			s = s + queueList.get(i) ;
		}
		return s + ")";
	}
	/* 
	* TODO 2: Implement "MyQueue"
	*/

} 