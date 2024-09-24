import java.io.File;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
/**
 * Write a description of class OrderWordGraph here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class OrderWordGraph extends WordGraph
{
    // instance variables - replace the example below with your own
    private Queue<String > lastWords; 
    private WeightedGraph<String> graph;
    private String lastWord;
    /**
     * Constructor for objects of class OrderWordGraph
     */
    public OrderWordGraph()
    {
        // initialise instance variables
        lastWords = new LinkedList<String>();
        graph = new WeightedAdjacencyListGraph<String>();
        lastWord =null;
        graph.add("[START]");
        graph.add("[END]");
    }
        public void processString(String str, int order)
    {
        //TODO: add each word from str to the graph instance variable
        for(String s : str.split(" ")){
            addWord(s,order);
     
        }
    }
    
       //METHODS
    /**
     *    "Sanatize" newWord by trimming extra spaces from the endges (use the trim() method)
     *    Add the specified word to the graph.
     *    Add an edge between lastWord and the newWord
     *    Increment the weight between these nodes by 1
     *    Set lastWord to point to newWord
     */
    public void addWord(String newWord)
    {
        if(lastWord == null){
            lastWord = "[START]";
        }
        newWord = newWord.trim();
        graph.add(newWord);
        graph.addEdge(lastWord, newWord);
        graph.setWeight(lastWord,newWord, graph.getWeight(lastWord,newWord)+1);
        lastWord = newWord;
        if(isEndWord(newWord)){
            graph.addEdge(newWord, "[END]");
            lastWord = null;
        }
        //TODO: add a word to the graph instance variable
    }
        public void addWord(String newWord, int order)
    {
        newWord = newWord.trim();
        if (lastWords.isEmpty())
            lastWords.add("[START]");
        if(lastWords.size() < order){
            
             if (!newWord.equals(""))
            {
              
                lastWords.add(newWord);
            }
        }else{

            
            addWord(lastWords.toString() );
          
            if (!newWord.equals(""))
            {
                lastWords.remove();
                lastWords.add(newWord);
            }

            if(super.isEndWord(newWord)){
                graph.addEdge(newWord, "[END]");
                lastWords.clear();
                
            }
        //TODO: add a word to the graph instance variable
        }
    }
      public void processFile(String filename, int order)
    {
        try
        {
            //open the specified file
            File file = new File(filename);
            Scanner in = new Scanner(file);
            
            //loop through each line of the file and process it
            while(in.hasNextLine())
                this.processString(in.nextLine(),  order);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
       public WeightedGraph<String> getGraph()
    {
        return graph;
    }
}
