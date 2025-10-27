import java.util.*;

public class Kruskal {
    private int operationsCount = 0;

    public List<Edge> findMST(List<String> nodes, List<Edge> edges) {
        List<Edge> result = new ArrayList<>();
        UnionFind uf = new UnionFind();

        for (String node : nodes) {
            uf.makeSet(node);
            operationsCount++;
        }

        Collections.sort(edges);
        operationsCount += edges.size();

        for (Edge edge : edges) {
            String root1 = uf.find(edge.from);
            String root2 = uf.find(edge.to);
            operationsCount += 2;

            if (!root1.equals(root2)) {
                result.add(edge);
                uf.union(root1, root2);
                operationsCount++;
            }
        }

        return result;
    }

    public int getOperationsCount() {
        return operationsCount;
    }
}
