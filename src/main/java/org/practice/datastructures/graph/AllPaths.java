package org.practice.datastructures.graph;

import java.util.*;

public class AllPaths {

    public static void main(String[] args) {
        //V = 4,  edges[ ][ ] = [[0, 3], [0, 1], [1, 3], [2, 1], [2, 0]], src = 2, dest = 3
        int v = 4;
        int[][] edges = {
                {0, 3}, {0, 1}, {1, 3}, {2, 1}, {2, 0}
        };
        int src = 2;
        int dest = 3;
        List<List<Integer>> allPaths = findAllPaths(v, edges, src, dest);
        for (List list : allPaths) {
            System.out.println(list);
        }
    }

    private static List<List<Integer>> findAllPaths(int v, int[][] edges, int src, int dest) {
        List<List<Integer>> answer = new ArrayList<>();
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
        }
        Queue<List<Integer>> path = new ArrayDeque<>();
        List<Integer> start = new ArrayList<>();
        start.add(src);
        path.add(start);
        while (!path.isEmpty()) {
            List<Integer> p = path.poll();
            int node = p.get(p.size() - 1);
            if (node == dest) {
                answer.add(new ArrayList<>(p));
            }
            for (int neighbor : adjList.get(node)) {
                List<Integer> newPath = new ArrayList<>(p);
                newPath.add(neighbor);
                path.add(newPath);
            }
        }
        return answer;
    }

}
