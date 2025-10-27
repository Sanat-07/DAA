import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private final Map<String, String> parent = new HashMap<>();
    private int operations = 0;

    public void makeSet(String node) {
        parent.put(node, node);
        operations++;
    }

    public String find(String node) {
        operations++;
        if (!parent.get(node).equals(node)) {
            parent.put(node, find(parent.get(node)));
            operations++;
        }
        return parent.get(node);
    }

    public boolean union(String node1, String node2) {
        String root1 = find(node1);
        String root2 = find(node2);
        operations += 2;
        
        if (!root1.equals(root2)) {
            parent.put(root1, root2);
            operations++;
            return true;
        }
        return false;
    }

    public int getOperations() {
        return operations;
    }
}
