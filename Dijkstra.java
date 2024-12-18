import java.util.Scanner;

public class Dijkstra {
    public static void dijkstra(int[][] adjacencyMatrix) {
        int v = adjacencyMatrix.length;
        boolean visited[] = new boolean[v];
        int distance[] = new int[v];
        for (int i = 0; i < v; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[0] = 0;

        for (int i = 0; i < v - 1; i++) {
            int minVertex = findMinVertex(distance, visited); // vertex with min dist.
            visited[minVertex] = true;

            for (int j = 0; j < v; j++) { // for neighbors
                if (adjacencyMatrix[minVertex][j] != 0 && !visited[j]) {
                    int newDist = distance[minVertex] + adjacencyMatrix[minVertex][j];
                    if (newDist < distance[j]) {
                        distance[j] = newDist;
                    }
                }
            }
        }
        for (int i = 0; i < v; i++) {
            System.out.println("Vertex " + i + " shortest distance is " + distance[i]);
        }
    }

    public static int findMinVertex(int[] distance, boolean visited[]) {
        int minVertex = -1;
        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int v = sc.nextInt();
        System.out.print("Enter the number of edges: ");
        int e = sc.nextInt();
        int[][] adjacencyMatrix = new int[v][v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                adjacencyMatrix[i][j] = 0; // No edges initially
            }
        }
        System.out.println("Enter the edges in the format: vertex1 vertex2 distance");
        for (int i = 0; i < e; i++) {
            System.out.print("Edge " + (i + 1) + ": ");
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int dist = sc.nextInt();
            adjacencyMatrix[v1][v2] = dist;
            adjacencyMatrix[v2][v1] = dist;
        }
        dijkstra(adjacencyMatrix);
    }

}
