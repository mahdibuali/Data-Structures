public class GlobalNet {
    //creates a global network 
    //O : the original graph
    //regions: the regional graphs
    public static Graph run(Graph O, Graph[] regions) {
	//To be implemented
        // copy graph
        Graph G = new Graph(O.V());
        G.setCodes(O.getCodes());
        for (int i = 0; i < regions.length; i++) {
            for (int j = 0; j < regions[i].E(); j++) {
                G.addEdge(regions[i].edges().get(j));
            }
        }

        // find smallest edge between regions
        for (int i = 0; i < regions.length; i++) {
            for (int j = i + 1; j < regions.length; j++) {
                G = addShortestPathEdges(O, regions[i], regions[j], G);
            }
        }

        return G;
    }

    public static Graph addShortestPathEdges(Graph O, Graph s, Graph d, Graph G) {
        int[] dist = new int[O.V()];
        int[] prev = new int[O.V()];
        boolean[] internal = isInternal(O, s, d);
        DistQueue q = new DistQueue(O.V());
        DistQueue x = new DistQueue(d.V());

        //set externals prev to -1
        for (int i = 0; i < prev.length; i++) {
            prev[i] = -1;
        }

        //add to queue
        for (int i = 0; i < O.V(); i++) {
            if (!internal[i]) {
                if (s.index(O.getCode(i)) == -1) {
                    dist[i] = Integer.MAX_VALUE;
                }
                q.insert(i, dist[i]);
            }
        }

        // main loop
        while (!q.isEmpty()) {
            int u = q.delMin();
            /*System.out.println("O vertices: " + O.V());
            System.out.println("curr vertex: "+ u+ O.getCode(u));
            System.out.println("dest graph:\n"+ d.toString());
            System.out.println("dest index: "+ d.index(O.getCode(u)));*/
            if (d.index(O.getCode(u)) != -1) {
                //System.out.println("I' here");
                //System.out.println("dist:" + dist[u]);
                x.insert(d.index(O.getCode(u)), dist[u]);
            }
            else {
                for (int i = 0; i < O.adj(u).size(); i++) {
                    int v = O.adj(u).get(i);
                    if (q.inQueue(v)) {
                        int alt = dist[u] + O.getEdgeWeight(u, v);
                        if (alt < dist[v]) {
                            dist[v] = alt;
                            prev[v] = u;
                            q.set(v, alt);
                        }
                    }
                }
            }
        }
        int tail = O.index(d.getCode(x.delMin()));
        int head = prev[tail];
        while (head != -1) {
            G.addEdge(O.getEdge(head, tail));
            tail = head;
            head = prev[head];
        }

        return G;
    }

    public static boolean[] isInternal(Graph O, Graph s, Graph d) {
        boolean[] x = new boolean[O.V()];
        for (int i = 0; i < s.V(); i++) {
            boolean out = false;
            for (int j = 0; j < O.adj(s.getCode(i)).size(); j++) {
                if (s.index(O.getCode(O.adj(s.getCode(i)).get(j))) == -1) {
                    out = true;
                    break;
                }
            }
            if (!out) {
                x[O.index(s.getCode(i))] = true;
            }
        }
        for (int i = 0; i < d.V(); i++) {
            boolean out = false;
            for (int j = 0; j < O.adj(d.getCode(i)).size(); j++) {
                if (d.index(O.getCode(O.adj(d.getCode(i)).get(j))) == -1) {
                    out = true;
                    break;
                }
            }
            if (!out) {
                x[O.index(d.getCode(i))] = true;
            }
        }
        return x;
    }

    public static void main(String[] args) {
        boolean[] x = new boolean[3];
        System.out.println(x[0]);
    }
}

    
    
    