package Classes;

import DataStructureClasses.SimpleList;
import DataStructureClasses.TypeStack;

//Implementa el algoritmo de kosaraju para encontrar elementos fuertemente relacionados
public class Kosaraju {

    private static boolean[] visited;
    private final OurGraph graph;
    private SimpleList<SimpleList> components;

    //Crea un un nuevo objeto kosaraju que almacena la informacion. Toma como argumento un objeto OurGraph
    public Kosaraju(OurGraph graph) {

        System.out.println("KOSARAJU TEST-------------------------------");

        this.graph = graph;
        this.components = new SimpleList();
        this.findStronglyConnectedComponents(graph);
    }

    
    //Encuentra los componentes fuertemente relacionados de el grafo
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
                SimpleList<Integer> component = new SimpleList();
                dfs(vertex, component);
                System.out.println(" ");
                components.addAtTheEnd(component);
            }

        }

        //Devolver la matriz al original
        graph.transpose();

        System.out.println("Cada print (Lista) es un componente fuertemente relacionado.");
        for (int i = 0; i < this.components.getSize(); i++) {

            this.components.getValueByIndex(i).printList();

        }

    }

    private void findOrder(int vertexNum, TypeStack<Integer> stack) {
        this.visited[vertexNum] = true;

        for (int i = 0; i < this.graph.getVertexsListSize(); i++) {
            if (this.graph.checkEdge(vertexNum, i) == true && !visited[i]) {
                this.findOrder(i, stack);
            }
        }
        stack.push(vertexNum);
    }

    private void dfs(int vertex, SimpleList component) {

        this.visited[vertex] = true;
        component.addAtTheEnd(vertex);
        System.out.print(vertex + " ");

        for (int i = 0; i < this.graph.getNumVertexs(); i++) {
            if (graph.checkEdge(vertex, i) && !this.visited[i]) {
                dfs(i, component);
            }
        }
    }

    //devuelve una lista de listas en la que cada indice tiene una lista con los indices que forman parte de un elemento fuertemente conectado
    public SimpleList<SimpleList> getComponents() {
        return components;
    }

}
