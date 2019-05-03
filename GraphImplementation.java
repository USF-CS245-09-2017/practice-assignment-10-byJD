import java.util.List;
import java.util.ArrayList;

public class GraphImplementation implements Graph {

    public int size;
    public int[][] adjMatrix;


    public GraphImplementation(int vertices){

        size = vertices;
        adjMatrix = new int[vertices][vertices];

    }

    //adds edge between two vertices
    public void addEdge(int v1, int v2){

        adjMatrix[v1][v2]=1;
    }

    public int[] neighbors(int vertex){

        List<Integer> neighbourList = new ArrayList<>();

        for(int i = 0; i < adjMatrix.length; i++){
            if(adjMatrix[vertex][i]>0){
                neighbourList.add(i);
            }
        }

        int[] vertexID = new int[neighbourList.size()];

        for(int i = 0; i < vertexID.length; i++){
            vertexID[i] = neighbourList.get(i);
        }
        return vertexID;
    }


    public List<Integer> topologicalSort(){

        List<Integer> sortedList = new ArrayList<>();

        int[] incident = new int[size];

        //set array to all zero
        for(int i = 0; i < size; i++){
            incident[i] = 0;
        }

        //update value
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                incident[j] += adjMatrix[i][j];
            }
        }

        //find index
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){

                if(incident[j] == 0){
                    int[] neighbourlist = neighbors(j);

                    for(int k = 0; k < neighbourlist.length; k++){
                        incident[neighbourlist[k]] -= 1;
                    }
                    sortedList.add(j);
                    incident[j]=-1; //flag
                }
            }
        }

        System.out.println(sortedList);

        return sortedList;
    }



}