package DataStructureClasses;

/**
 *
 * @author B-St
 */
public class BooleanNode extends SimpleNode {
    
    private BooleanNode pNext;
    private boolean data;
    
    public BooleanNode(boolean data) {
        super(data);
        this.data = data;
        this.pNext = null;   
    }

    public boolean getValue(){
        return this.data;
    }
    
    public BooleanNode getpNext() {
        return pNext;
    }

    public void setpNext(BooleanNode pNext) {
        this.pNext = pNext;
    }
    
    
    
}
