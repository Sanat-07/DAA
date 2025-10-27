import com.google.gson.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonObject input = gson.fromJson(new FileReader("input_example.json"), JsonObject.class);
        JsonArray graphs = input.getAsJsonArray("graphs");

        JsonArray results = new JsonArray();

        for (JsonElement g : graphs) {
            JsonObject graphObj = g.getAsJsonObject();
            JsonArray nodesArr = graphObj.getAsJsonArray("nodes");
            JsonArray edgesArr = graphObj.getAsJsonArray("edges");

            List<String> nodes = new ArrayList<>();
            for (JsonElement n : nodesArr) nodes.add(n.getAsString());

            List<Edge> edges = new ArrayList<>();
            for (JsonElement e : edgesArr) {
                JsonObject obj = e.getAsJsonObject();
                edges.add(new Edge(
                        obj.get("from").getAsString(),
                        obj.get("to").getAsString(),
                        obj.get("weight").getAsInt()
                ));
            }

            long start = System.nanoTime();
            Kruskal kruskal = new Kruskal();
            List<Edge> mst = kruskal.findMST(nodes, edges);
            long end = System.nanoTime();

            int totalCost = mst.stream().mapToInt(e -> e.weight).sum();

            JsonObject result = new JsonObject();
            result.addProperty("graph_id", graphObj.get("id").getAsInt());
            result.addProperty("vertices", nodes.size());
            result.addProperty("edges", edges.size());
            result.addProperty("total_cost", totalCost);
            result.addProperty("operations_count", kruskal.getOperationsCount());
            result.addProperty("execution_time_ms", (end - start) / 1_000_000.0);

            JsonArray mstEdges = new JsonArray();
            for (Edge e : mst) {
                JsonObject edgeObj = new JsonObject();
                edgeObj.addProperty("from", e.from);
                edgeObj.addProperty("to", e.to);
                edgeObj.addProperty("weight", e.weight);
                mstEdges.add(edgeObj);
            }
            result.add("mst_edges", mstEdges);
            results.add(result);
        }

        JsonObject output = new JsonObject();
        output.add("results", results);

        try (FileWriter writer = new FileWriter("output_example.json")) {
            gson.toJson(output, writer);
        }

        System.out.println("✅ Kruskal’s algorithm executed successfully! Check output_example.json");
    }
}
