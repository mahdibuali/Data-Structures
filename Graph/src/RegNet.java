import java.util.ArrayList;
import java.util.Queue;

public class RegNet {
    //creates a regional network
    //G: the original graph
    //max: the budget
    public static Graph run(Graph G, int max) {
	//To be implemented
        // find MST
        Graph T = findMST(G);

        //remove largest edges until within budget
        while (T.totalWeight() > max) {
            T = removeLargestConnEdge(T);
        }
        T = T.connGraph();

        // add edges within budget to minimize stops
        ArrayList<Edge> s[] = getEdgeStops(G, T);
        for (int i = s.length - 1; i >= 0 ; i--) {
            for (int j = 0; j < s[i].size(); j++) {
                if (T.totalWeight() + s[i].get(j).w < max) {
                    T.addEdge(s[i].get(j));
                    if (T.totalWeight() == max) {
                        break;
                    }
                }
            }
        }
        return T;
    }

    public static Graph findMST(Graph G) {
        ArrayList<Edge> E = G.edges();
        int V = G.V();
        Graph T = new Graph(V);
        T.setCodes(G.getCodes());
        DistQueue q = new DistQueue(G.E());
        for (int i = 0; i < E.size(); i++) {
            q.insert(i, E.get(i).w);
        }
        UnionFind u = new UnionFind(V);
        while (T.E() < V - 1) {
            Edge e = E.get(q.delMin());
            if (u.find(e.ui()) != u.find(e.vi())) {
                T.addEdge(e);
                u.union(e.vi(), e.ui());
            }
        }
        return T;
    }

    public static Graph removeLargestConnEdge(Graph T) {
        ArrayList<Edge> E = T.sortedEdges();
        for (int i = E.size() - 1; i >= 0 ; i--) {
            Edge e = E.get(i);
            if (T.deg(e.u) == 1 || T.deg(e.v) == 1) {
                T.removeEdge(e);
                return T;
            }
        }
        return T;
    }

    public static ArrayList<Edge>[] getEdgeStops(Graph G, Graph T) {
        ArrayList<Edge> E = G.subgraph(T.getCodes()).sortedEdges();
        /*
        int[] stops = new int[E.size()];
        for (int i = 0; i < stops.length; i++) {
            stops[i] = bfs(T, E.get(i).ui(), E.get(i).vi());
        }
        ArrayList<Edge>[] r = new ArrayList[G.V() - 1];
        for (int i = 0; i < stops.length; i++) {
            r[stops[i] - 1].add(E.get(i));
        }
        return r;
        */
        ArrayList<Edge>[] r = new ArrayList[G.V() - 2];
        for (int i = 0; i < r.length; i++) {
            r[i] = new ArrayList<Edge>();
        }
        int[][] stops  = bfs(T);
        for (int i = 0; i < E.size(); i++) {
            if (stops[E.get(i).ui()][E.get(i).vi()] != 0) {
                r[stops[E.get(i).ui()][E.get(i).vi()] - 1].add(E.get(i));
            }
        }

        return r;
    }

    public static int[][] bfs(Graph T) {
        int[][] r = new int[T.V()][T.V()];
        for (int i = 0; i < T.V(); i++) {
            Queue1<Integer> q = new Queue1<>(T.V());
            int[] stops = new int[T.V()];
            int[] visited = new int[T.V()];
            q.enqueue(i);
            stops[i] = -1;
            while (!q.isEmpty()) {
                int v = 0;
                try {
                    v = q.dequeue();
                } catch (EmptyQueueException e) {
                    e.printStackTrace();
                }
                visited[v] = 1;
                for (int j = 0; j < T.adj(v).size(); j++) {
                    int w = T.adj(v).get(j);
                    if (visited[w] != 1) {
                        q.enqueue(w);
                        visited[w] = 1;
                        stops[w] = stops[v] + 1;
                    }
                }
            }
            r[i] = stops;
        }
        return r;
    }


    public static void main(String[] args) {
        Graph x = new Graph(5);
        String[] s = {"A", "B", "C", "D", "E"};
        x.setCodes(s);
        x.addEdge("A", "B", 4);
        x.addEdge("A", "C", 11);
        x.addEdge("A", "D", 7);
        x.addEdge("A", "E", 5);
        x.addEdge("B", "C", 3);
        x.addEdge("B", "D", 8);
        x.addEdge("B", "E", 9);
        x.addEdge("C", "D", 6);
        x.addEdge("C", "E", 10);
        x.addEdge("D", "E", 2);


        Graph y = run(x, 30);
        /*ArrayList<Edge> o[] = getEdgeStops(x, y);
        for (int i = o.length - 1; i >= 0; i--) {
            for (int j = 0; j < o[i].size(); j++) {
                System.out.println(o[i].get(j).u + " to "+o[i].get(j).v+": "+ (i + 1) + " stops (d = "+ o[i].get(j).w +")" );
            }
        }*/
        System.out.println(y.toString());





    }
}