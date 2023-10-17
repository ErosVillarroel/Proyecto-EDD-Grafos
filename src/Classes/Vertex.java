package Classes;

/**
 *
 * @author andre
 */
public class Vertex {
    private String name;
    private int vertexNum;
    
    public Vertex(String name){
        this.name = name;
        this.vertexNum = -1;
    }

    public Vertex(String name, int vertexNum) {
        this.name = name;
        this.vertexNum = vertexNum;
    }
    
    // saber si dos vertices son iguales
    public boolean isEqualTo(Vertex vertice){
        return this.name.equals(vertice.getName());
    }
    
    // establecer el numero de vertices
    public void assignVertexNum(int num){
        setNumVertex(num);
    }
    
    // caracteristicas del vertice


    public String vertexToString(){
        return this.name + " (" + this.vertexNum + ")";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the numVertex
     */
    public int getNumVertex() {
        return vertexNum;
    }

    /**
     * @param numVertex the numVertex to set
     */
    public void setNumVertex(int numVertex) {
        this.vertexNum = numVertex;
    }
    
    
}
