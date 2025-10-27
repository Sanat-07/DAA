import java.util.List;

public class MSTResult {
    String algorithm;
    List<Edge> edges;
    double totalCost;
    int vertices;
    int totalEdges;
    int operations;
    double timeMs;

    public MSTResult(String algorithm, List<Edge> edges, double totalCost, int vertices, int totalEdges, int operations, double timeMs) {
        this.algorithm = algorithm;
        this.edges = edges;
        this.totalCost = totalCost;
        this.vertices = vertices;
        this.totalEdges = totalEdges;
        this.operations = operations;
        this.timeMs = timeMs;
    }

    public String getAlgorithm() { return algorithm; }
    public List<Edge> getEdges() { return edges; }
    public double getTotalCost() { return totalCost; }
    public int getVertices() { return vertices; }
    public int getTotalEdges() { return totalEdges; }
    public int getOperations() { return operations; }
    public double getTimeMs() { return timeMs; }
}
