package cas2xb3_A2_Liu_CL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DepthFirstSearch
{
//   private boolean[] marked;
   HashMap <String, Boolean> marked;
   HashMap <String, String> edgeTo;

   private String s; 
   int count=0;
   
   
   public DepthFirstSearch(Graph G, String s)
   {
	  edgeTo = new HashMap<String, String>();
      this.s = s;
      marked = new HashMap<String, Boolean>();
      for (String w : G.adj.keySet()) {
    	  marked.put(w, false);
    	  edgeTo.put(w, null);
      }
//      System.out.println(marked);
      dfs(G, s); 
      }
   
	public void dfs(Graph G, String city) {
		count++;
		marked.replace(city, true); // mark that already go to this city
//		System.out.println(G.adj.get(city));
		if (G.adj.get(city) == null) {
			System.out.println("not connected, pass");
		}
		else {
			for (String w : G.adj.get(city)) { // for the cities that is connected to this city
				if (marked.get(w) == false) { // if havent been to the connected city
					edgeTo.put(w,city);
					dfs(G, w); // dfs on that connected city
				}
			}
		}
//		System.out.println("marked"+marked);
	}

   public boolean hasPathTo(String v)
   {  return marked.get(v);  }
   
   
   public Iterable<String> pathTo(String v) {
       if (!hasPathTo(v)) {
    	   

    	   return null;
       }
       List<String> path = new ArrayList<>();
       int counter=0;
       while (counter<edgeTo.size()) {
    	   if (edgeTo.get(v)==null) break;
    	   path.add(v);
    	   v=edgeTo.get(v);
    	   counter++;
       }     
       
       path.add(s); 
       Collections.reverse(path);
       return path;
   }
   
}