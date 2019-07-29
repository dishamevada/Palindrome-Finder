
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException; 
import java.util.Arrays;
import java.util.ArrayList;

public class Palindrome {

	static WordDictionary dictionary = new WordDictionary(); 

	
	// Get all words that can be formed starting at this index
	public static String[] getWords(String text, int index) {
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i <= text.length() - index; i++) {
			String maybeWord = text.substring(index, index + i);
			if (dictionary.isWord(text.substring(index, index + i))) {
				words.add(maybeWord);
			}
		}

		return words.toArray(new String[0]);
	}

    /*
     * In this method, we take in a stack and return the reversed
     * version of what was in that stack using a temporary stack so 
     * that later in explorePalindrome() we can print out 
     * the reversed version of what was in the stack 
     */
	public static String stackToReverseString(MyStack stack) 
	{
		/* 
		* TODO 3
		*/
		
		//Make an empty String that we add words to later 
		String reverseString = "";
		
		//Make a temporary stack so that we can do the work here 
		//and keep the original unchanged  
		StackInterface temporaryStack = new MyStack();
		
		Object transfer;
		
		//We pop words from the original stack 
		//and push those words onto the temporary
		//stack as long as the original stack is not empty
		while(stack.isEmpty() != true)
		{
			transfer = stack.pop();
			temporaryStack.push(transfer);
		}

        //We pop the words from the temporary stack and 
		//push them back onto the original stack while
		//adding those words to the empty String we created earlier
		while(temporaryStack.isEmpty() != true )
		{
			transfer = temporaryStack.pop();
			reverseString = reverseString + " " + transfer;
			stack.push(transfer);
		}
		
		return reverseString;
		/* 
		* TODO 3
		*/
	}

	/*
	 * 	In this method, we taken in a String and return the reversed version of 
	 *  that String without punctuation and other symbols in order to use it when 
	 *  checking if the String is a palindrome, as we do not care about the case and 
	 *  punctuation/other symbols when checking
	 */
	public static String reverseStringAndRemoveNonAlpha(String text) 
	{
		/* 
		* TODO 4
		*/
		
		String lowerCaseText = text.toLowerCase();
		
		//Create an empty String to add the words to 
		String reverseString2 = ""; 
		
		//Create a new stack that we will use to push values on and later pop off
		StackInterface stringStack = new MyStack();
		Object stringTransfer; 
		
		//Here we iterate through the String and push each character
		//onto the stack only if it is alphabetic
		//The method isAlphabetic() takes care of that for us and ignores the punctuation 
		for(int i = 0; i < lowerCaseText.length(); i++)
		{
			if(Character.isAlphabetic(lowerCaseText.charAt(i)) == true)
			{
				stringStack.push((Character)lowerCaseText.charAt(i));
			}
		}
		
		//We pop words from the original stack 
		//and push those words onto the temporary
		//stack as long as the original stack is not empty
		while(stringStack.isEmpty() != true)
		{
			stringTransfer = stringStack.pop();
			reverseString2 = reverseString2 + stringTransfer; 
		}
		
		return reverseString2;
		/* 
		* TODO 4
		*/
	}


	/*
	 * This method returns true if the text is a palindrome, false if not.
	 * We will use this to determine if the word passed is a palindrome 
	 */
	public static boolean isPalindrome(String text) 
	{
		/* 
		* TODO 5: Implement "explorePalindrome"
		*/
		
		//This stores the lower cased and reversed version of the String in variable input
		String input = reverseStringAndRemoveNonAlpha(text.toLowerCase());
		
		//These Strings will later be compared to see if they are palindromes
		String stackString = "";
		String queueString = ""; 
	
		StackInterface stack = new MyStack();
		QueueInterface queue = new MyQueue();
		
		//We iterate through the characters of the input and add them to the stack and queue
		for(int i = 0; i< input.length(); i++)
		{
			stack.push((Character)input.charAt(i));
			queue.enqueue((Character)input.charAt(i));
		}
		
		//We add the characters to the Strings created earlier while 
		//removing them from the stack and queue as long as the stack 
		//or queue is not empty 
		while(stack.isEmpty() != true || queue.isEmpty() != true)
		{
			stackString = stackString + stack.pop();
			queueString  = queueString + queue.dequeue();
		}
		
		//Returns true if they are equal to each other or false otherwise 
		return stackString.equals(queueString);
		
		/* 
		* TODO 5: Implement "explorePalindrome"
		*/
	}
	
	/*
	 * This method discovers possible palindromes that can be made out 
	 * of the text using the helper function
	 */
	public static void explorePalindrome(String text) 
	{

	/* 
	* TODO 6: Implement "explorePalindrome" & helper function
	*/
		
	//This stores the lower cased and reversed version of the String in variable stringToDecompose
	String stringToDecompose = new String(reverseStringAndRemoveNonAlpha(text));
	stringToDecompose = stringToDecompose.toLowerCase();
	
	MyStack decomposeStack = new MyStack();
	decomposeText(text, stringToDecompose, 0, decomposeStack);
		
	/* 
	* TODO 6
	*/

	}
	
	
	/*
	 * In this method, we decompose the text passed in and print out all the possible 
	 * palindrome phrases in order to display them to the user 
	 */
	public static void decomposeText (String originalText, String textToDecompose, int index, MyStack decomposition)
	{ 
		
		//Stores all the possible words found in an array of Strings using the function getWords()
		String[] possibleWords = getWords(textToDecompose, index);
		
		//Since we are finished when the index is at the end of the word
		//we print out the original and reversed text to show the user 
		if(index == textToDecompose.length())
		{
			System.out.println(originalText + ":" + stackToReverseString(decomposition));
		}
		
		//In this case, we have not reached the end so we continue to 
		//iterate through all the array of possible words 
		else
		{
			for(int i = 0; i < possibleWords.length; i++) 
			{
				//We add each word onto the the stack 
				decomposition.push(possibleWords[i]);
				//The recursively call it at the index of the current index plus the length of the word every time
				decomposeText(originalText, textToDecompose, index + possibleWords[i].length(), decomposition);
				//We pop it from the stack
				decomposition.pop();
			}
		}
	}


	// This function looks at the arguments that are passed 
	// and decides whether to test palindromes or expand them
	public static void main(String[] args) throws IOException {

		if (args.length == 0) {
			System.out.println("ERROR: Remember to set the mode with an argument: 'test' or 'expand'");
		} else {
			String mode = args[0];

			// Default palindromes to use if none are provided
			String[] testPalindromes = {"A", "ABBA", "oh no an oboe", "salami?", "I'm alas, a salami"};
			if (args.length > 1)
				testPalindromes = Arrays.copyOfRange(args, 1, args.length);

			// Test whether the provided strings are palindromes
			if (mode.equals("test")) {

				for (int i = 0; i < testPalindromes.length; i++) {
					String text = testPalindromes[i];
					boolean result = isPalindrome(text);
					System.out.println("'" + text + "': " + result);
				}

			} else if (mode.equals("expand")) {
				for (int i = 0; i < testPalindromes.length; i++) {

					explorePalindrome(testPalindromes[i]);
				}	
			}
			else {
				System.out.println("unknown mode: " + mode);
			}
		}
	}
}