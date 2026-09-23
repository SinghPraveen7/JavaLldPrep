package org.practice.datastructures.graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a square chessboard of size n × n, the initial position knightPos[] and target position targetPos[] of a Knight are given.
 * Find the minimum number of moves required for the Knight to reach targetPos.
 * A Knight moves in an L-shape, covering 2 cells in one direction and 1 cell perpendicular to it. From (x, y), it can move to:
 * (x ± 2, y ± 1)
 * (x ± 1, y ± 2)
 */
public class KnightMoves {

    public static void main(String[] args) {
        int n = 8;
        int[] knightPos = {7, 0};
        int[] targetPos = {0, 7};
        System.out.println(minStepToReachTarget(knightPos, targetPos, n));
    }

    private static int minStepToReachTarget(int[] knightPos, int[] targetPos, int n) {
        int[][] dir = {{1, 2}, {2, 1}, {-1, 2}, {-2, 1}, {1, -2}, {2, -1}, {-1, -2}, {-2, -1}};
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{knightPos[0], knightPos[1], 0});
        int[][] visited = new int[n][n];
        visited[knightPos[0]][knightPos[1]] = 1;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (current[0] == targetPos[0] && current[1] == targetPos[1]) {
                return current[2];
            }
            for (int i = 0; i < 8; i++) {
                int nx = current[0] + dir[i][0];
                int ny = current[1] + dir[i][1];
                if (nx >=0 && nx < n && ny >= 0 && ny < n && visited[nx][ny] != 1) {
                    visited[nx][ny] = 1;
                    queue.add(new int[] {nx, ny, current[2] + 1});
                }
            }
        }
        return -1;
    }

}
