import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

public class MarkovChain 
{
    //INSTANCE VARIABLES
    /**
     *    This holds all of the word relationships
     */
    private WordGraph wg;
    
    /**
     *    This remembers the lastWord that this Markov Chain generated
     */
    private String lastWord;

    //CONSTRUCTOR
    public MarkovChain() 
    {
        //initialize instance variables
        wg = new WordGraph();
        lastWord = null;
    }
    
    //METHODS
    /**
     *    Add word relationships from the specified file
     */
    public void train(String filename)
    {
        wg.processFile(filename);
    }
    
    /**
     *    Get a list of words that follow lastWord.
     *    words with more *weight* will appear more times in the list.
     *  if lastWord is null, then return the words that are neighbors of [START]
     */
    public List<String> getNextWords()
    {
        //TODO: return List<String> of words that are neighbors of lastWord, weighted appropriatly
        WeightedGraph<String> w = wg.getGraph();
       
        Map<String, Integer> s ;
        if(lastWord == null || lastWord.equals("[END]"))
           s=  w.getNeighborWeights("[START]" );
        else{
            s = w.getNeighborWeights(lastWord);
        }
        List<String> list = new ArrayList<String>();
        //System.out.println(list.size());

        for(Map.Entry<String,Integer> y : s.entrySet()){

            for(int i = 0; i < y.getValue(); i++){
            
                list.add(y.getKey());
                
            }
        }

        return list;
    }
    
    /**
     *    Get a word that follows lastWord
     *    Use a weighted random number to pick the next word from the list of all of lastWord's neighbors in wordGraph
     *    If lastWord is null or [END], this should generate a word that *starts* a sentence
     *    Remember the word that is about to be returned in the appropriate instance variable
     */
    public String getNextWord()
    {
        //TODO: return random word with an edge from lastWord
        List<String> list = getNextWords();
        if(list.size() <=0)
            return null;
        String r = list.get((int)(Math.random() *list.size()));
        lastWord = r;
        
        return r;
    }
    
    /**
     *    Generate a sentence using the data in the wordGraph. 
     */

    public String generateSentence()
    {
        //TODO: generate a sentence from [START] to [END]
        String total= "";
        String currString = getNextWord();
        
        while(currString !=null && !currString.equals("[END]") ){
            total +=  currString + " ";
            currString = getNextWord();
        }
        lastWord = null;
        return total;
    }
    
    
}