import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private final Map<String, String> parent = new HashMap<>();

    public void makeSet(String node) {
        parent.put(node, node);
    }

    public String find(String node) {
        if (!parent.get(node).equals(node)) {
            parent.put(node, find(parent.get(node))); 
        }
        return parent.get(node);
    }

    public void union(String node1, String node2) {
        String root1 = find(node1);
        String root2 = find(node2);
        if (!root1.equals(root2)) {
            parent.put(root1, root2);
        }
    }
}
