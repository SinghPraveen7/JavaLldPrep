package org.practice.datastructures.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BFSearch {

    public static void main(String[] args) {
        List<List<Integer>> adjacencyList = getGraph();
        BFS(adjacencyList, 0);
    }

    private static void BFS(List<List<Integer>> adjacencyList, int start) {
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[adjacencyList.size()];
        visited[start] = true;
        queue.offer(start);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int neighbor : adjacencyList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }

    private static List<List<Integer>> getGraph() {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        adjacencyList.get(0).add(1);
        adjacencyList.get(0).add(2);
        adjacencyList.get(1).add(0);
        adjacencyList.get(1).add(2);
        adjacencyList.get(1).add(3);
        adjacencyList.get(2).add(0);
        adjacencyList.get(2).add(1);
        adjacencyList.get(2).add(3);
        adjacencyList.get(3).add(1);
        adjacencyList.get(3).add(2);
        return adjacencyList;
    }
}
