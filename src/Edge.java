public class Edge implements Comparable<Edge> {
    private String from;
    private String to;
    private double weight;

    public Edge(String from, String to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return from + "-" + to;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge edge = (Edge) obj;
        return (from.equals(edge.from) && to.equals(edge.to)) ||
               (from.equals(edge.to) && to.equals(edge.from));
    }

    @Override
    public int hashCode() {
        return from.hashCode() + to.hashCode();
    }
}


