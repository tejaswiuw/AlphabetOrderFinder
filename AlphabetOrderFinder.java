import java.util.*;

/**
 * Implements a function to find the order of the alphabets 
 * based on a list of sorted words provided as input. These words 
 * are constructed using the alphabet characters whose order 
 * is to be determined.
 *
 */
class AlphabetOrderFinder {
    
    /**
     * Function to find the order of the alphabets given a list of
     * ordered words.
     * 
     * @param words Ordered list of words
     * 
     * @return List of characters ordered alphabetically
    */
  public static List <Character> findOrder(String[] words) {
    
    // List to store the final ordered list of characters
    List <Character> orderedList = new ArrayList <Character>();
    
    // Input validation!
    if (words == null){
      return orderedList;
    }
      
    // Map to track indegree
    HashMap<Character,Integer> indegree = new HashMap<>();
    
    // Map that serves as an adjacency List
    HashMap<Character, List<Character>> adjList = new HashMap<>();
      
    // Intialize the data structures
    for (int i = 0; i < words.length; i++) {
      if (words[i] == null){
        // Input validation - one of input words is null!
        return orderedList;
      }
      char[] ch = words[i].toCharArray();
      for (int j = 0; j < ch.length; j++) {
        indegree.put(ch[j], 0);
        adjList.put(ch[j], new ArrayList<Character>());
      }
    }

    // Step 1
    // Traverse the given list of words to 
    // - construct the adjacency list (tracking the order between two individual characters)
    // - update the indegree map (tracking characters that appear before current character)
    for (int i = 0; i < words.length - 1; i++) {
      String word1 = words[i];
      String word2 = words[i + 1];

      // Check for an invalid input case - 
      // Input ordered list is invalid if word2 is a prefix of word1
      // example - "foo", "fo" -> not a valid order, so return empty list!
      if (word1.length() > word2.length() && word1.startsWith(word2)) {
        return orderedList;
      }

      // Update adjacencyList & indegree map
      for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
        // Check until characters are different
        if (word1.charAt(j) != word2.charAt(j)) {
          adjList.get(word1.charAt(j)).add(word2.charAt(j));
          indegree.put(word2.charAt(j), indegree.get(word2.charAt(j)) + 1);
          break;
        }
      }
    }
    
    // Step 2
    // Traverse the adjacency list for characters to determine the order
    
    // Queue for Breadth First Search (BFS)
    Queue<Character> queue = new LinkedList<Character>();

    // Adding all the characters with indegree 0 into the queue
    for (Character item: indegree.keySet()) {
      if (indegree.get(item) == 0) {
        queue.add(item);
      }
    }

    while (!queue.isEmpty()) {
      char c = queue.poll();
      // Add the character to the result (intially with indegree equals 0)
      orderedList.add(c);

      for (Character m: adjList.get(c)) {
        indegree.put(m, indegree.get(m) - 1);
        if (indegree.get(m) == 0) {
          // add character to the queue when there indegree equals 0
          // happens when all the characters before current character 
          // are already processed
          queue.add(m);
        }
      }

    }
    
    // Check for an invalid input case - 
    // word list provided contains characters that did not
    // show up in the final list of ordered characters
    if (orderedList.size() < indegree.size()) {
      return new ArrayList<>();
    }
    
    return orderedList;
  }

  // main method used for local testing. Uncomment it to run a simple testcase!
  /*
  public static void main(String[] args) {
    String[] words = { "bca", "aaa", "acb" };
    System.out.println(Arrays.asList(AlphabetOrderFinder.findOrder(words)));
  }
  */
}
