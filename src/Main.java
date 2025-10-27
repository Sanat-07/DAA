import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String inputContent = readFile("input.json");
        
        List<String> nodes = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        
        parseInputJson(inputContent, nodes, edges);
        
        Prim prim = new Prim(nodes, edges);
        MSTResult primResult = prim.run();
        
        writeOutputJson(primResult);
        
        System.out.println("========== Prim's Algorithm Results ==========");
        System.out.println("Algorithm: " + primResult.getAlgorithm());
        System.out.println("Total Cost: " + primResult.getTotalCost());
        System.out.println("Vertices: " + primResult.getVertices());
        System.out.println("Original Edges: " + primResult.getTotalEdges());
        System.out.println("MST Edges: " + primResult.getEdges().size());
        System.out.println("Operations: " + primResult.getOperations());
        System.out.println("Execution Time: " + primResult.getTimeMs() + " ms");
        System.out.println("\nMST Edges:");
        for (Edge edge : primResult.getEdges()) {
            System.out.println("  " + edge.getFrom() + " -> " + edge.getTo() + " (weight: " + edge.getWeight() + ")");
        }
        System.out.println("=============================================");
        System.out.println("✅ Results also saved to output.json");
    }
    
    private static String readFile(String filename) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    
    private static void parseInputJson(String content, List<String> nodes, List<Edge> edges) {
        content = content.replaceAll("\\s+", "");
        
        int nodesStart = content.indexOf("\"nodes\":[") + 9;
        int nodesEnd = content.indexOf("]", nodesStart);
        String nodesStr = content.substring(nodesStart, nodesEnd);
        String[] nodeArray = nodesStr.split(",");
        for (String node : nodeArray) {
            nodes.add(node.replaceAll("\"", ""));
        }
        
        int edgesStart = content.indexOf("\"edges\":[") + 9;
        int edgesEnd = content.lastIndexOf("]");
        String edgesStr = content.substring(edgesStart, edgesEnd);
        
        String[] edgeStrings = edgesStr.split("\\},\\{");
        for (int i = 0; i < edgeStrings.length; i++) {
            String edgeStr = edgeStrings[i];
            if (i == 0) edgeStr = edgeStr.substring(1);
            if (i == edgeStrings.length - 1) edgeStr = edgeStr.substring(0, edgeStr.lastIndexOf("}"));
            
            String from = extractField(edgeStr, "from");
            String to = extractField(edgeStr, "to");
            double weight = Double.parseDouble(extractField(edgeStr, "weight"));
            
            edges.add(new Edge(from, to, weight));
        }
    }
    
    private static String extractField(String json, String field) {
        String searchStr = "\"" + field + "\":\"";
        int start = json.indexOf(searchStr);
        if (start == -1) {
            searchStr = "\"" + field + "\":";
            start = json.indexOf(searchStr);
            return json.substring(start + searchStr.length()).split("[,\\}]")[0];
        }
        int end = json.indexOf("\"", start + searchStr.length());
        return json.substring(start + searchStr.length(), end);
    }
    
    private static void writeOutputJson(MSTResult result) throws IOException {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"algorithm\": \"").append(result.algorithm).append("\",\n");
        json.append("  \"mst_edges\": [\n");
        
        for (int i = 0; i < result.edges.size(); i++) {
            Edge edge = result.edges.get(i);
            json.append("    {\n");
            json.append("      \"from\": \"").append(edge.getFrom()).append("\",\n");
            json.append("      \"to\": \"").append(edge.getTo()).append("\",\n");
            json.append("      \"weight\": ").append(edge.getWeight()).append("\n");
            json.append("    }");
            if (i < result.edges.size() - 1) json.append(",");
            json.append("\n");
        }
        
        json.append("  ],\n");
        json.append("  \"total_cost\": ").append(result.totalCost).append(",\n");
        json.append("  \"vertices\": ").append(result.vertices).append(",\n");
        json.append("  \"edges\": ").append(result.totalEdges).append(",\n");
        json.append("  \"operations\": ").append(result.operations).append(",\n");
        json.append("  \"execution_time_ms\": ").append(result.timeMs).append("\n");
        json.append("}");
        
        try (FileWriter writer = new FileWriter("output.json")) {
            writer.write(json.toString());
        }
    }
}
