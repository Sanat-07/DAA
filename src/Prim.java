import java.util.*;

public class Prim {
    private List<Edge> edges;
    private List<String> nodes;
    private int operations = 0;

    public Prim(List<String> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public MSTResult run() {
        long start = System.nanoTime();

        Map<String, List<Edge>> adj = new HashMap<>();
        for (String node : nodes) adj.put(node, new ArrayList<>());
        for (Edge e : edges) {
            adj.get(e.from).add(e);
            adj.get(e.to).add(new Edge(e.to, e.from, e.weight));
        }

        Set<String> visited = new HashSet<>();
        List<Edge> mstEdges = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(e -> e.weight));

        String startNode = nodes.get(0);
        visited.add(startNode);
        pq.addAll(adj.get(startNode));

        while (!pq.isEmpty() && visited.size() < nodes.size()) {
            Edge edge = pq.poll();
            operations++;
            if (visited.contains(edge.to)) continue;
            visited.add(edge.to);
            mstEdges.add(edge);
            for (Edge next : adj.get(edge.to)) {
                if (!visited.contains(next.to)) pq.offer(next);
            }
        }

        long end = System.nanoTime();
        double totalCost = mstEdges.stream().mapToDouble(e -> e.weight).sum();
        double execTimeMs = (end - start) / 1_000_000.0;

        return new MSTResult("Prim", mstEdges, totalCost, nodes.size(), edges.size(), operations, execTimeMs);
    }
}
