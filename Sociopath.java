import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Sociopath {
    public static void main(String[] args) {
        
        WeightedGraph<Integer, Integer> graph1 = new WeightedGraph<>();
        Random random = new Random();

        //add vertex for all 10 students
        for (int i = 1; i < 11; i++){
            graph1.addVertex(i);   
        }
        
        System.out.println("The number of students: " + graph1.getSize());
        
        //add the representation points to the students
        boolean temp = graph1.addEdge(1, 2, 5);
        temp = graph1.addEdge(1, 7, 4);
        temp = graph1.addEdge(2, 1, 8);
        temp = graph1.addEdge(2, 3, 5);
        temp = graph1.addEdge(2, 5, 6);
        temp = graph1.addEdge(2, 6, 9);
        temp = graph1.addEdge(3, 2, 4);
        temp = graph1.addEdge(4, 8, 7);
        temp = graph1.addEdge(4, 10, 7);
        temp = graph1.addEdge(5, 2, 2);
        temp = graph1.addEdge(6, 2, 7);
        temp = graph1.addEdge(7, 1, 3);
        temp = graph1.addEdge(8, 4, 10);
        temp = graph1.addEdge(9, 10, 5);
        temp = graph1.addEdge(10, 4, 7);
        temp = graph1.addEdge(10, 9, 6);

        System.out.println("Print Edges: ");
        graph1.printEdges();
        
        Event1and2(graph1);
        Event6();
    }
    
    public void Event1(){
        //code for event 1
    }
    
    //Event 2 -----------------------------------------------------------------------------------------------
    public static void Event1and2(WeightedGraph<Integer, Integer> graph1){
        //code for event 2
        System.out.println("This is Event 2: \n");
        Random random = new Random();
        int a = random.nextInt(11); //decide you (a)
        while(a==0){
            a = random.nextInt(11);
        }
        System.out.println("You is Person "+a+".\n");
        
        ArrayList<Integer> b2 = graph1.getNeighbours(a);
        b2.add(a);
        ArrayList<Integer> b1 = new ArrayList<>();// list all edge except a
       
        for(int i=1; i<=10; i++){
            b1.add(i);
            for(int x=0; x<b2.size(); x++){
                if (i == b2.get(x)) {
                    b1.remove(b1.size()-1);
                    break;
                }
            }
        }
        System.out.println("Let's choose who you will teach: "+b1.toString()+"\n");
        
        int b = b1.get(random.nextInt(b1.size())); // choose strangers (b)
        
        System.out.println("You will teach Person "+b+".\n");
        
        ArrayList<Integer> friendB = graph1.getNeighbours(b); //choose who chit chat with b (c)
        int cSize = friendB.size();
        int c = random.nextInt(cSize);
        c = friendB.get(c);
        //System.out.println("Person "+b+" chit-chat with Person "+c+" about you.\n");
        
        boolean temp = graph1.addEdge(b, a, 10); //event1 =you good at programming
        //Event 1
        int learn = random.nextInt(2);

      
            if (learn == 0){ //good at programming
            
                System.out.println("Person "+b+" having fun learning with you.");
                System.out.println("Person "+b+" become friend with you.");
                temp = graph1.addEdge(b, a, 10); 

                int getrep1 = 0;
                boolean check = graph1.hasEdge(c, a);
                if (check == true){
                    getrep1 = (int) (graph1.getEdgeWeight(c, a));   
                }
                int getrep = (int) (graph1.getEdgeWeight(b, a)*0.5);
                getrep = getrep1+getrep;
                System.out.println("Person "+b+" chit-chat with Person "+c+" good things about you. ("+ graph1.addEdge(c, a, getrep)+")\n");
            }

            else if (learn == 1) { //bad at programming

                System.out.println("You guys have an unpleasant teaching-learning session.");
                System.out.println("However, you guys still friends");
                temp = graph1.addEdge(b, a, 10); 

                int getrep1 = 0;
                boolean check = graph1.hasEdge(c, a);
                if (check == true){
                    getrep1 = (int) (graph1.getEdgeWeight(c, a));   
                }
                int getrep = (int) (graph1.getEdgeWeight(b, a));
                getrep = getrep1-getrep;
                System.out.println("Person "+b+" chit-chat with Person "+c+" bad things about you. ("+ graph1.addEdge(c, a, getrep)+")\n");
            }
        

        System.out.println("Person "+b+" chit-chat with Person "+c+" about you.\n");
        System.out.println("From Person " +c+ " point of view, Person "+a+" having "+graph1.getEdgeWeight(c, a)+" rep points.\n ");

    }
    
    public void Event3(){
        //code for event 3

    }
    
    public void Event4(){
        //code for event 4

    }
    
    public void Event5(){
        //code for event 5

    }
    
    
    //Event 6 -----------------------------------------------------------------------------------------------
    public static ArrayList<Integer>[] adjacencyList;
    public static ArrayList<Integer[]> sequence = new ArrayList<>(); //to hold all the possible paths

    // A recursive function to find
    // all paths from 'u' to 'd'.
    // isVisited[] keeps track of
    // vertices in current path.
    // localPathList<> stores actual
    // vertices in the current path
    public static void findAllPathsUtil(Integer u, Integer d,
                                         boolean[] isVisited,
                                         ArrayList<Integer> localPathList) {

        if (u.equals(d)) {
            // if match found then no need to traverse more till depth
            //add the path to the sequence list
            Integer[] tempArr = new Integer[localPathList.size()];
            for (int n = 0; n < localPathList.size(); n++) {
                tempArr = localPathList.toArray(new Integer[n]);
            }
            sequence.add(tempArr);
            return;
        }

        // Mark the current node
        isVisited[u] = true;

        // Recur for all the vertices
        // adjacent to current vertex
        for (Integer i : adjacencyList[u]) {
            if (!isVisited[i]) {
                // store current node
                // in path[]
                localPathList.add(i);
                findAllPathsUtil(i, d, isVisited, localPathList);

                // remove current node
                // in path[]
                localPathList.remove(i);
            }
        }
        // Mark the current node
        isVisited[u] = false;
    }

    private static void findAllPaths(int s, int d, int v) {
        boolean[] isVisited = new boolean[v];   //v = number of vertex
        ArrayList<Integer> pathList = new ArrayList<>();

        // add source to path[]
        pathList.add(s);

        // Call recursive utility
        findAllPathsUtil(s, d, isVisited, pathList);
    }

    //reverse a sequence to find redundancy(non-unique)
    public static Integer[] reverse(Integer[] intArr) {
        Integer[] temp = new Integer[intArr.length];
        int index = 0;
        for (int i = intArr.length - 1; i >= 0; i--) {
            temp[index] = intArr[i];
            index++;
        }
        return temp;
    }

    public static void Event6() {
        UnweightedGraph<Integer, Integer> graph6 = new UnweightedGraph<>();
        graph6.addVertex(0);
        Scanner scan = new Scanner(System.in);

        //initialize the graph
        System.out.println("Enter the number of friendships: ");
        int numOfFriendship = scan.nextInt();

        for (int i = 1; i <= numOfFriendship; i++) {
            System.out.println("Friendship " + i + " (Enter 2 individuals): ");
            int friend1 = scan.nextInt();
            int friend2 = scan.nextInt();
            graph6.addVertex(friend1);
            graph6.addVertex(friend2);
            graph6.addUndirectedEdge(friend1, friend2);
        }
        //graph6.printEdges();
        int v = graph6.getSize();

        //create the adjacency list
        adjacencyList = new ArrayList[graph6.getSize()];
        for (int i = 0; i < graph6.getSize(); i++) {
            adjacencyList[i] = graph6.getNeighbours(graph6.getVertex(i));
        }

        //find all the possible paths
        for (int i = 1; i < v; i++) {
            for (int j = 1; j < v; j++) {
                if (i != j) {
                    findAllPaths(i, j, v);
                }
            }
        }
        //remove any non-unique sequence
        for (int i = sequence.size() - 1; i >= 0; i--) {
            for (int j = 0; j < sequence.size(); j++) {
                if (Arrays.equals(sequence.get(j), reverse(sequence.get(i)))) {
                    sequence.remove(i);
                    break;
                }
            }
        }
        //sort the sequence according to length to look nicer
        ArrayList<Integer[]> sortList = new ArrayList<>();
        for (int i = 2; i < v; i++) {
            for (int j = 0; j < sequence.size(); j++) {
                if (sequence.get(j).length == i) {
                    sortList.add(sequence.get(j));
                }
            }
        }
        //display the final list and output
        System.out.println("Number of possible friendship you can form: " + sortList.size());
        int index = 1;
        for (Integer[] x : sortList) {
            System.out.println(index +". "+Arrays.toString(x));
            index++;
        }

    }
}
