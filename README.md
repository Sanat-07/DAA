# DAA Assignment 3: Optimization of a City Transportation Network

**Authors:** Sanat (Prim's Algorithm), Nurkeldi (Kruskal's Algorithm)  
**Course:** Design and Analysis of Algorithms  
**Assignment:** Minimum Spanning Tree Implementation

## 📋 Project Description

This project implements both **Prim's** and **Kruskal's** algorithms to find the Minimum Spanning Tree (MST) for optimizing a city's transportation network. The goal is to determine the minimum set of roads that connect all city districts with the lowest possible total construction cost.

## 🎯 Objectives

- Apply Prim's algorithm to find MST
- Apply Kruskal's algorithm to find MST  
- Compare performance and efficiency of both algorithms
- Analyze execution time and operation counts
- Verify that both algorithms produce identical MST costs

## 👥 Team Members

- **Sanat** - Implemented Prim's Algorithm
- **Nurkeldi** - Implemented Kruskal's Algorithm

## 🏗️ Project Structure

```
src/
├── Main.java          # Main execution class with algorithm comparison
├── Edge.java          # Edge class representing graph edges
├── Prim.java          # Prim's algorithm implementation
├── Kruskal.java       # Kruskal's algorithm implementation
├── UnionFind.java     # Union-Find data structure for Kruskal
└── MSTResult.java     # Result storage class

Data Files:
├── input.json         # Input graph data (7 vertices, 14 edges)
├── input_example.json # Example input format
├── output_prim.json   # Prim's algorithm results
└── output_kruskal.json # Kruskal's algorithm results
```

## 🚀 How to Run

### Compilation
```bash
javac src/*.java
```

### Execution
```bash
java -cp src Main
```

## 📊 Algorithm Results

### Test Graph
- **Vertices:** 7 (A, B, C, D, E, F, G)
- **Edges:** 14
- **MST Edges:** 6

### Performance Comparison

| Algorithm | Total Cost | Operations | Execution Time | Speed |
|-----------|------------|------------|----------------|-------|
| **Prim**  | 57.0       | 6          | 4.69 ms        | -     |
| **Kruskal** | 57.0     | 175        | 0.56 ms        | 8.36x faster |

### MST Edges Found
Both algorithms find the same MST with edges:
- F → G (6.9)
- D → E (7.8) 
- B → C (8.7)
- C → D (9.5)
- C → F (11.8)
- A → C (12.3)

**Total MST Cost: 57.0**

## 🔍 Algorithm Analysis

### Prim's Algorithm
- **Approach:** Greedy algorithm starting from a vertex
- **Data Structure:** Priority Queue (Min-Heap)
- **Time Complexity:** O(E log V)
- **Space Complexity:** O(V)
- **Advantages:** Fewer operations, good for dense graphs
- **Disadvantages:** Slower execution time

### Kruskal's Algorithm  
- **Approach:** Greedy algorithm sorting all edges
- **Data Structure:** Union-Find (Disjoint Set)
- **Time Complexity:** O(E log E)
- **Space Complexity:** O(V)
- **Advantages:** Faster execution, good for sparse graphs
- **Disadvantages:** More operations due to sorting

## 📈 Key Findings

1. **✅ Correctness:** Both algorithms produce identical MST cost (57.0)
2. **⚡ Performance:** Kruskal is 8.36x faster in execution time
3. **🔢 Efficiency:** Prim uses significantly fewer operations (6 vs 175)
4. **🎯 Optimality:** Both find the globally optimal solution

## 🛠️ Technical Implementation

### Edge Class
- Public fields for direct access (`from`, `to`, `weight`)
- Implements `Comparable<Edge>` for sorting
- Supports both integer and double weights

### Union-Find Structure
- Path compression optimization
- Union by rank for efficiency
- Operation counting for analysis

### JSON Processing
- Custom JSON parser (no external dependencies)
- Supports both simple and complex input formats
- Detailed output with all required metrics

## 📝 Assignment Requirements Met

- ✅ Both Prim's and Kruskal's algorithms implemented
- ✅ JSON input/output processing
- ✅ Operation counting and timing analysis
- ✅ Algorithm comparison and verification
- ✅ Minimum 5 vertices requirement (7 vertices used)
- ✅ Detailed console output with results

## 🎓 Conclusion

This project successfully demonstrates the implementation and comparison of two fundamental MST algorithms. While both algorithms achieve the same optimal result, they differ significantly in their approach and performance characteristics, making them suitable for different graph types and scenarios.

---

**Repository:** https://github.com/Sanat-07/DAA.git  
**Authors:** Sanat (Prim's Algorithm), Nurkeldi (Kruskal's Algorithm)  
**Course:** Design and Analysis of Algorithms
