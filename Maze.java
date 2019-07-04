import java.util.PriorityQueue;
import java.util.Stack;

public class Maze {
    Graph maze;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private int numberV;
    private int numberE;
    private final int SPACE_BETWEEN_V = 2;
    private final int BEGIN_INDENT = 3;
    Stack<Integer> path = new Stack();

    Maze(Graph maze, int numberV, int numberE) {
        this.maze = maze;
        this.marked = new boolean[numberE + 1];
        this.edgeTo = new int[numberE + 1];
        this.distTo = new int[numberE + 1];
        this.numberE = numberE;
        this.numberV = numberV;
        int target = (int)(Math.random() * numberV);
        System.out.println("target: " + target);
        bfs(maze, 0, target);
        drawPath();
    }

    private void bfs(Graph G, int s, int target) {
        PriorityQueue<Integer> q = new PriorityQueue();
        q.add(s);
        marked[s] = true;
        distTo[s] = 0;
        boolean finish = false;
        while (!q.isEmpty()) {
            int v = q.remove();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    if(w == target) {
                        finish = true;
                        break;
                    }
                    q.add(w);
                    marked[w] = true;
                    path.push(w);
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
            if (finish) break;
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        path.add(v);
    }

    private void drawPath() {
        int buffer;
        int counter = 0;
        while (!path.empty()) {
            buffer = path.remove(0);
            System.out.println(buffer);
        }
    }

    public static void main(String args[]) {
        In in = new In("D:\\maze1.txt");
        int numberV = in.readInt();
        int numberE = in.readInt();
        Graph maze = new Graph(numberV);
        int a;
        int b;
        for (int i = 0; i < numberE; i++) {
            a = in.readInt();
            b = in.readInt();
            maze.addEdge(a, b);
        }
        Maze m = new Maze(maze, numberV, numberE);
    }
}
