package org.practice.datastructures.graph;

import java.util.*;

public class ShortestPath {

    public static void main(String[] args) {
        BFS(getGraph(), 0);
    }

    private static void BFS(List<List<Integer>> adjacencyList, int start) {
        Deque<Integer> queue = new ArrayDeque<>();
        int[] distance = new int[adjacencyList.size()];
        Arrays.fill(distance, -1);
        int[] parent = new int[adjacencyList.size()];
        Arrays.fill(parent, -1);
        boolean[] visited = new boolean[adjacencyList.size()];
        visited[start] = true;
        distance[start] = 0;
        queue.offer(start);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adjacencyList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distance[neighbor] = distance[node] + 1;
                    parent[neighbor] = node;
                    queue.offer(neighbor);
                }
            }
        }
        for (int i = 0; i < adjacencyList.size(); i++) {
            System.out.println(i + " distance from root is " + distance[i]);
        }
        List<Integer> shortestPath = new ArrayList<>();
        int pathEndNode = 3;
        while (pathEndNode != -1) {
            shortestPath.add(pathEndNode);
            pathEndNode = parent[pathEndNode];
        }
        shortestPath = shortestPath.reversed();
        System.out.println("Shortest Path to 3 Node");
        for (int i: shortestPath) {
            System.out.print(i + " ");
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
