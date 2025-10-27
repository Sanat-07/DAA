import java.util.*;

public class Kruskal {
    private int operations = 0;

    public MSTResult run(List<String> nodes, List<Edge> edges) {
        long start = System.nanoTime();
        
        List<Edge> sortedEdges = new ArrayList<>(edges);
        Collections.sort(sortedEdges);
        operations += edges.size() * (int)(Math.log(edges.size()) + 1);

        UnionFind uf = new UnionFind();
        for (String node : nodes) {
            uf.makeSet(node);
        }

        List<Edge> mstEdges = new ArrayList<>();
        for (Edge edge : sortedEdges) {
            operations++;
            if (uf.union(edge.from, edge.to)) {
                mstEdges.add(edge);
                operations++;
            }
        }

        operations += uf.getOperations();

        long end = System.nanoTime();
        double totalCost = mstEdges.stream().mapToDouble(e -> e.weight).sum();
        double execTimeMs = (end - start) / 1_000_000.0;

        return new MSTResult("Kruskal", mstEdges, totalCost, nodes.size(), edges.size(), operations, execTimeMs);
    }
}
