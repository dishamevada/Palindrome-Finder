public class MyStack implements StackInterface {
	/* 
	* TODO 1: Implement "MyStack"
	*/
	//We create an instance of MyLinkedList
	private MyLinkedList stackList; 
	
	//In the constructor, we initialize it
	public MyStack()
	{
		stackList = new MyLinkedList();
	}
	
	//In this method, we check if it has any elements by using MyLinkedList's isEmpty method
		//because if the underlying list is empty so is the stack
	public boolean isEmpty()
	{
		return stackList.isEmpty();
	}
	
	//In this method, we add an Object at the top of the stack
	public void push(Object o)
	{
		stackList.add(0, o);
	}
	
	// In this method, we remove an Object from the top of the stack
	//as long as the stack is not empty, if it is throw an exception 
	public Object pop() 
	{
		if(isEmpty() != true) 
		{
			Object objectAtTop = stackList.get(0);
			stackList.remove(0);
			return objectAtTop;
		}
		
		else 
		{
			throw new StackException("POP: The stack is empty");
		}
	}
	
	//This method allows you to look at the element at the top of the
	//stack as long as the stack is not empty 
	//if it is, we throw an exception
	public Object peek()
	{
		if(isEmpty() != true)
		{
			return stackList.get(0);
		}
		
		else 
		{
			throw new StackException("PEEK: The stack is empty");
		}
	}

	//This method removes all things form the stack
	public void popAll()
	{
		stackList.removeAll();
	}
	
	//This method, prints out the stack in this format
	// (Object, Object, ... )
	public String toString()
	{
		String s = "(";
		for(int i = 0; i < stackList.size(); i++)
		{
			if (i != 0 )
			{
				s = s + ",";
			}
			s = s + stackList.get(i) ;
		}
		return s + ")";
	}
	
	/* 
	* TODO 1: Implement "MyStack"
	*/
}