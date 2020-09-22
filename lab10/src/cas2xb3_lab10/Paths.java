package cas2xb3_lab10;

public class Paths {
	
	Graph G = new Graph(new In(args[0]));
	int s = Integer.parseInt(args[1]);
	Paths search = new Paths(G, s);
	for (int v = 0; v < G.V(); v++){
		System.out.print(s + " to " + v + ": ");
		if (search.hasPathTo(v))
			for (int x : search.pathTo(v))
				if (x == s) System.out.print(x);
				else System.out.print("-" + x);
		System.out.println();
	 } 

}
