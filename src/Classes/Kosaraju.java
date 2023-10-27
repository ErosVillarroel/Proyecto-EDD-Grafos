package Classes;

import DataStructureClasses.TypeStack;

/**
 *
 * @author B-St
 */
public class Kosaraju {

    private static boolean[] visited;
    private final OurGraph graph;

    public Kosaraju(OurGraph graph) {
        
        System.out.println("KOSARAJU TESTTTTTT-------------------------------");
        
        this.graph = graph;
        this.findStronglyConnectedComponents(graph);
    }

    private void findStronglyConnectedComponents(OurGraph graph) {

        int numberOfVertexs = graph.getNumVertexs();

        this.visited = new boolean[numberOfVertexs];
        TypeStack<Integer> stack = new TypeStack();

        //Primera pasada
        for (int i = 0; i < numberOfVertexs; i++) {
            if (!this.visited[i]) {
                this.findOrder(i, stack);
            }
        }

        //Transponer el grafo 
        graph.transpose();

        //Segunda pasada
        this.visited = new boolean[numberOfVertexs];

        //Segunda pasada
        while (!stack.isEmpty()) {
            int vertex = stack.pop().getData();
            if (!this.visited[vertex]) {
                dfs(vertex);
                System.out.println(" ");
            }
        }
        
        //Devolver la matriz al original
        graph.transpose();

    }

    private void findOrder(int vertexNum, TypeStack<Integer> stack) {
        this.visited[vertexNum] = true;

//        SimpleList<BooleanNode> visited = new SimpleList();
//        visited.fillWith(vertexNum, true);

        for (int i = 0; i < this.graph.getVertexsListSize(); i++) {
            if (this.graph.checkEdge(vertexNum, i) == true && !visited[i]) {
                this.findOrder(i, stack);
            }
        }
        stack.push(vertexNum);
    }

    private void dfs(int vertex) {
        this.visited[vertex] = true;
        System.out.print(vertex + " ");
        
        for (int i = 0; i<this.graph.getNumVertexs(); i++){
            if (graph.checkEdge(vertex, i) && !this.visited[i]){
                dfs(i);
            }
        }
    }
}