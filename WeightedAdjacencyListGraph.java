import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class WeightedAdjacencyListGraph<E> extends AdjacencyListGraph<E> implements WeightedGraph<E>
{
    //INSTANCE VARIABLES
    /**
     *	A mapping between a Node and a map of Neighbor->Weight
     */
    private Map<E, Map<E, Integer>> weights;

    //CONSTRUCTORS
    public WeightedAdjacencyListGraph() 
    {
        //initialize instance variables
        weights = new HashMap<E, Map<E, Integer>>();
    }

    //METHODS
    /**
     *	Add a node to the graph
     *
     *	Add a node to the graph exactly like an AdjacencyGraph,
     *	then create a Map to keep track of weight associations between neighbors
     *
     *	@param node the data to add to the graph. Duplicate data will not be added.
     *	@return true if the data was successfully added to graph
     */
    @Override
    public boolean add(E node)
    {
        boolean added = super.add(node);
        if(added){

            weights.put(node, new HashMap<E, Integer>());
            return true;
        }
        //TODO: call add() on the parent class to add this node to the graph

        //TODO: if the node was successfully added, create a mapping between the node and a new HashMap<E, Integer>

        //TODO: return whether the node was successfully added to the graph

        return false;
    }

    /**
     *	Set the weight between two nodes
     *
     *	@param from the node to set the weight from
     *	@param to the node to set the weight to
     *	@param the weight to set
     */
    public void setWeight(E from, E to, int weight) {
        // Check if 'from' node exists in the weights map

        // Retrieve neighbors and weight map for 'from' node
        Map<E, Integer> neighbors = weights.get(from);
        if(neighbors !=null)
            neighbors.put(to, weight); // Update weight
    }

    /**
     *	Set the weight between two nodes
     *
     *	If there is no edge between the nodes, this will return 0.
     *	If no weight has been assigned to these nodes, then this will return 0.
     *	
     *	@param from the node to get the weight from
     *	@param to the node to get the weight to
     *	@return the weight between from and to or 0 if no edge or weight exists.
     */
    public int getWeight(E from, E to)
    {
        //TODO: check if there is an edge between from and to. If not, return 0

        //TODO: check if a weight has been set between from and to, If not, return 0
        //		HINT: Integer objects can be null...
        if(!hasEdge(from, to))
            return 0;
        Integer n = weights.get(from).get((to));

        if(n!=null &&n!=0){
            return n;
        }
        return 0;

        //TODO: return the weight between from and to
    }

    public Map<E, Integer> getNeighborWeights(E node) {
       
        

        // Return a copy of the neighborWeights map to prevent external modifications
        return weights.get(node) == null ? null : weights.get(node);
    }

    /**
     *	Return a string representation of the weighted graph data
     *
     *	@return a string representation of the weighted graph
     */
    @Override
    public String toString()
    {
        List<String> ret = new ArrayList<String>();

        for(E node: getNodes())
        {
            List<String> neighborStrings = new ArrayList<String>();
            for(E neighbor : getNeighbors(node))
                neighborStrings.add(neighbor + "(" + getWeight(node, neighbor) + ")");

            ret.add(node + "->" + neighborStrings.toString());
        }

        return ret.toString();
    }
}