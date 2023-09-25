package DataStructureClasses;

/**
 *
 * @author andre
 */
public class Vertex {
    private String name;
    private int numVertex;
    
    public Vertex(String name){
        this.name = name;
        this.numVertex = -1;
    }
    
    // saber si dos vertices son iguales
    public boolean equality(Vertex vertice){
        return this.name.equals(vertice.getName());
    }
    
    // establecer el numero de vertices
    public void assignVertex(int num){
        setNumVertex(num);
    }
    
    // caracteristicas del vertice
    public String toString(){
        return this.name + " (" + this.numVertex + ")";
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
        return numVertex;
    }

    /**
     * @param numVertex the numVertex to set
     */
    public void setNumVertex(int numVertex) {
        this.numVertex = numVertex;
    }
    
    
}
