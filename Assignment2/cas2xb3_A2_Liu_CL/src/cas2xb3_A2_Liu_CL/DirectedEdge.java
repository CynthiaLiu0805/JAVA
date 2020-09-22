package cas2xb3_A2_Liu_CL;

import java.util.ArrayList;

public class DirectedEdge {

    String v;
    String w;
    double weight;

    public DirectedEdge(String v, String w, double weight) {
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    
    public String from() {
        return v;
    }

    public String to() {
        return w;
    }

    public double weight() {
        return weight;
    }

    
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }
    
   
    
    
}
