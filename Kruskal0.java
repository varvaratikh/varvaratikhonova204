package AISD.Semestrovka;

import java.util.*;
import java.io.*;

public class Kruskal0 {
    public static void main(String[] args) {
        ArrayList<Edge> graphEdges = new ArrayList<Edge>();
        graphEdges.add(new Edge(3, 5, 2));
        graphEdges.add(new Edge(6, 7, 5));
        graphEdges.add(new Edge(3, 4, 6));
        graphEdges.add(new Edge(4, 8, 7));
        graphEdges.add(new Edge(1, 2, 9));
        graphEdges.add(new Edge(4, 5, 11));
        graphEdges.add(new Edge(1, 6, 14));
        graphEdges.add(new Edge(1, 7, 15));
        graphEdges.add(new Edge(5, 8, 16));
        graphEdges.add(new Edge(3, 6, 18));
        graphEdges.add(new Edge(3, 8, 19));
        graphEdges.add(new Edge(7, 5, 20));
        graphEdges.add(new Edge(2, 3, 24));
        graphEdges.add(new Edge(7, 8, 44));
        graphEdges.add(new Edge(6, 5, 30));

        int nodeCount = 8;

        Kruskal0 graph = new Kruskal0();
        long start = System.nanoTime();
        graph.kruskalMST(graphEdges, nodeCount);
        long finish = System.nanoTime();
        long resulTime = finish-start;
        System.out.println("Algorithm running time:"+resulTime/1000);

        //Вывод:
        //Total weight of all edges in MST = 61
        //Algorithm running time: 219182
        //Number of iterations in the algorithmiter: 60
    }

    int iter = 0;
    public void kruskalMST(ArrayList<Edge> graphEdges, int nodeCount){
        String outputMessage="";

        Collections.sort(graphEdges);
        iter++;
        ArrayList<Edge> mstEdges = new ArrayList<Edge>();
        DisjointSet nodeSet = new DisjointSet(nodeCount+1);
        for(int i=0; i<graphEdges.size() && mstEdges.size()<(nodeCount-1); i++){
            iter++;
            Edge currentEdge = graphEdges.get(i);
            int root1 = nodeSet.find(currentEdge.getVertex1());
            int root2 = nodeSet.find(currentEdge.getVertex2());
            outputMessage+="find("+currentEdge.getVertex1()+") returns "+root1+", find("+currentEdge.getVertex2()+") returns "+root2;		//just print, keep on same line for union message
            iter++;
            String unionMessage=",\tNo union performed\n";
            if(root1 != root2){
                mstEdges.add(currentEdge);
                iter++;
                nodeSet.union(root1, root2);
                iter++;
                unionMessage=",\tUnion("+root1+", "+root2+") done\n";
                iter++;
            }
            outputMessage+=unionMessage;
            iter++;
        }

        outputMessage+="\nFinal Minimum Spanning Tree ("+mstEdges.size()+" edges)\n";
        int mstTotalEdgeWeight=0;
        for(Edge edge: mstEdges){
            outputMessage+=edge +"\n";
            mstTotalEdgeWeight += edge.getWeight();
            iter++;
        }
        outputMessage+="\nTotal weight of all edges in MST = "+mstTotalEdgeWeight;

        iter++;
        System.out.println(outputMessage);

        try (PrintWriter outputFile = new PrintWriter( new File("06outputMST.txt") ); ){
            outputFile.println(outputMessage);
            System.out.println("\nOpen \"06outputMST.txt\" for backup copy of answers");
        } catch (FileNotFoundException e) {
            System.out.println("Error! Couldn't create file");
        }
        System.out.println("Number of iterations in the algorithmiter: " + iter);
    }
}


class Edge implements Comparable<Edge>{
    private int vertex1;
    private int vertex2;
    private int weight;

    public Edge(int vertex1, int vertex2, int weight){
        this.vertex1=vertex1;
        this.vertex2=vertex2;
        this.weight=weight;
    }

    public int getVertex1(){
        return vertex1;
    }

    public int getVertex2(){
        return vertex2;
    }

    public int getWeight(){
        return weight;
    }

    @Override
    public int compareTo(Edge otherEdge) {
        return this.getWeight() - otherEdge.getWeight();
    }

    @Override
    public String toString() {
        return "Edge ("+getVertex1()+", "+getVertex2()+") weight="+getWeight();
    }
}


class DisjointSet{
    private int[] set;

    public int[] getSet(){
        return set;
    }


    public DisjointSet(int numElements) {
        set = new int [numElements];
        for(int i = 0; i < set.length; i++){
            set[i] = -1;
        }
    }


    public void union(int root1, int root2) {
        if(set[root2] < set[root1]){
            set[root1] = root2;
        }
        else {
            if(set[root1] == set[root2]){
                set[root1]--;
            }
            set[root2] = root1;
        }
    }

    public int find(int x) {
        if(set[x] < 0){
            return x;
        }
        int next = x;
        while(set[next] > 0){
            next=set[next];
        }
        return next;
    }

}