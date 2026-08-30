package org.practice.datastructures.graph;

import java.util.ArrayList;
import java.util.List;

//0 -> [1,2]
//1 -> [0,2,3]
//2 -> [0,1,3]
//3 -> [1,2]
public class AdjacencyList {

    public static void main(String[] args) {
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
        System.out.println("Printing adjacency List representation of graph");
        for (int i = 0; i < 4; i++) {
            System.out.print(i + " -> [ ");
            for (int j: adjacencyList.get(i)) {
                System.out.print(j + " ");
            }
            System.out.print("]");
            System.out.println();
        }
    }

}
