package Classes;
/**
 *
 * @author B-St
 */
public class Graph {
    
    private int[][] matrix;
    
    public Graph(int size){
        matrix = new int[size][size];
    }
    
    public void addVertex(Vertex vertex){
        
    }
    
    public void addEdge(int srcVertex, int dstVertex){
        matrix[srcVertex][dstVertex] = 1;
    } 
    
    public boolean checkEdge(int srcVertex, int dstVertex){
         return matrix[srcVertex][dstVertex] == 1;
    }
    
    public void print(){
        for (int i = 0; i<matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        } 
    }
    
    }
