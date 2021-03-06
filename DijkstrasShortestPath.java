// A Java program for Dijkstra's single source shortest path algorithm.
// The program is for adjacency matrix representation of the graph
//This code is contributed by Aakash Hasija

import java.util.*;
import java.lang.*;
import java.io.*;
 
class DijkstrasShortestPath
{
    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    static final int V= 13;
    int minDistance(double dist[], Boolean sptSet[])
    {
        // Initialize min value
        Double min = Double.MAX_VALUE;
        int min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    // A utility function to print the constructed distance array
    void printSolution(double dist[], double n)
    {
        System.out.println("Vertex   Distance from Source");
        for (int  i = 0; i < V; i++)
            System.out.println(i+ "           "+dist[i]);
    }
    
    
    //A utility function that takes the parent array of the shortest path tree 
   //and target vertex then it prints out the shortest path.
    void printShortestPath(int[] parent, int target){


        //Base condition
        if(parent[target] == -1){
            System.out.println(" " + target);
            return;
        }

        //print the current vertex
        System.out.print(" " + target);

        //Make a recursive call
        printShortestPath(parent, parent[target]);

      
   }
 
    // Funtion that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    void dijkstra(double graph[][], int src)
    {
        double dist[] = new double[V]; // The output array. dist[i] will hold
                                 // the shortest distance from src to i
 
        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];
        
        int[] parent = new int[V];
 
        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++)
        {
            dist[i] = Double.MAX_VALUE;
            sptSet[i] = false;
        }
        
        parent[0] = -1; // First node is always root
 
        // Distance of source vertex from itself is always 0
        dist[src] = 0.0;
 
        // Find shortest path for all vertices
        for (int count = 0; count < V-1; count++)
        {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);
 
            // Mark the picked vertex as processed
            sptSet[u] = true;
 
            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)
 
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v]!=0 &&
                        dist[u] != Double.MAX_VALUE &&
                        dist[u]+graph[u][v] < dist[v]){

                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u;
                }

        }
 
        // print the constructed distance array
        printSolution(dist, V);
        printShortestPath(parent, 10);

        for(int i = 0; i < parent.length; i++){
            System.out.print(" " + parent[i]);
        }
    }
 
    // Driver method
    public static void main (String[] args)
    {
        /* Let us create the example graph discussed above */
       double graph[][] = new double[][]{{0, 19.8, 0, 0, 0, 0, 0, 0, 0,0,0,0,0},
                                              {4, 0, 8, 0, 0, 75.1, 76.9, 0, 0,0,0,0,0},
                                              {0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0},
                                              {0, 0, 7, 0, 9, 14, 0, 0, 0,0,0,0,0},
                                              {0, 0, 0, 0, 0, 59.6, 0, 0, 0,0,0,0,0},
                                              {0, 0, 0, 0, 0, 0, 183.5, 0, 0,0,0,0,0},
                                              {0, 0, 0, 0, 0, 0, 0, 138.8, 126.1,0,0,0,0},
                                              {0, 0, 0, 0, 0, 0, 0, 0, 134.3,0,0,0,0},
                                              {0, 0, 2, 0, 0, 0, 6, 7, 0,0,0,0,0},
                                              {0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                              {0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                              {0,0,0,0,0,0,0,0,0,0,0,0,0},
                                              {0,0,0,0,0,0,0,0,0,0,0,0,0}
                                             };
        DijkstrasShortestPath t = new DijkstrasShortestPath();
        t.dijkstra(graph, 0);
    }
}
