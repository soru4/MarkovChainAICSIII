import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
public class MarkovChainGen2 extends MarkovChain
{
    //INSTANCE VARIABLES
    /**
     *  This holds relationships between pairs-of-words -> next-word
     */
    private OrderWordGraph wgPairs;
    String lastWord;
    String lastLastWord;

    //CONSTRUCTOR
    public MarkovChainGen2() 
    {
        //Initialize Instance Variables
        wgPairs = new OrderWordGraph();
        lastWord = null;
        lastLastWord = null;
    }
    
    //METHODS
    
    @Override
    public void train(String filename)
    {
        super.train(filename); //tell the MarkovChain super class to train() like it normally does
        wgPairs.processFile(filename, 2);
        //TODO: do some magic to store 2nd order markov chain data
        
    }
    
        @Override
    public List<String> getNextWords()
    {
        //TODO: return List<String> of words that are neighbors of lastWord, weighted appropriatly
        WeightedGraph<String> w = wgPairs.getGraph();
        
        
        Map<String, Integer> s ;
        if(lastWord == null || lastWord.equals("[END]"))
           s=  w.getNeighborWeights("[START]");
        else{
            s = w.getNeighborWeights(lastLastWord+ " " + lastWord);
        }
        List<String> list = new ArrayList<String>();
        if(s!=null){
            for(Map.Entry<String,Integer> y : s.entrySet()){
    
                for(int i = 0; i < y.getValue(); i++){
                
                    list.add(y.getKey());
    
                }
            }
         }
        return list;
    }
        @Override
    public String getNextWord()
    {
        //TODO: return random word with an edge from lastWord
        List<String> list = getNextWords();
        if(list.size() <=0)
            return null;
        String r = list.get((int)(Math.random() *list.size()));
        lastWord = r;
        
        lastLastWord = lastWord;

        return r;
    }
        @Override
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
        lastLastWord = null;
        return total;
    }
    //TODO: override any other other methods needed to generate 2nd Degree Markov Chains
    
}