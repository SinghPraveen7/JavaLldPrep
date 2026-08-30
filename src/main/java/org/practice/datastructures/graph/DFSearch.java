package org.practice.datastructures.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DFSearch {

    public static void main(String[] args) {
        List<List<Integer>> graph = getGraph();
        boolean[] visited = new boolean[graph.size()];
        System.out.println("DFS recursive:");
        DFS(graph, 0, visited);
        System.out.println();
        System.out.println("DFS iterative:");
        DFSIterative(graph, 0);
    }

    private static void DFSIterative(List<List<Integer>> adjacencyList, int node) {
        boolean[] visited = new boolean[adjacencyList.size()];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            int n = stack.pop();
            if (visited[n]) continue;
            visited[n] = true;
            System.out.print(n + " ");
            for (int neighbor : adjacencyList.get(n)) {
                if (!visited[neighbor]) {
                    stack.push(neighbor);
                }
            }
        }
    }

    private static void DFS(List<List<Integer>> adjacencyList, int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");
        for (int neighbor : adjacencyList.get(node)) {
            if (!visited[neighbor]) {
                DFS(adjacencyList, neighbor, visited);
            }
        }
    }

    private static List<List<Integer>> getGraph() {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        adjacencyList.get(0).add(1);
        adjacencyList.get(1).add(0);
        adjacencyList.get(1).add(2);
        adjacencyList.get(1).add(3);
        adjacencyList.get(2).add(1);
        adjacencyList.get(2).add(3);
        adjacencyList.get(3).add(1);
        adjacencyList.get(3).add(2);
        return adjacencyList;
    }

}
