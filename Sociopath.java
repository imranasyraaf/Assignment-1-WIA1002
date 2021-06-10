import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Sociopath {
    public static Random random = new Random();
    public static void main(String[] args) {
        
        WeightedGraph<Integer, Integer> graph1 = new WeightedGraph<>();
       
       

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
        int[]variableABC= new int[3];
        int [] vertex = {1,2,3,4,5,6,7,8,9,10}; //for check rep and check hate
        WeightedGraph<Integer, Integer> graphEnemy = initEnemyGraph();  //initialize enemy graph

        Scanner scan = new Scanner(System.in);
        try{
            System.out.println("\nList of events:");
            System.out.println("Event 1 = '1'\nEvent 2 = '2'\nEvent 3 = '3'\n" +
                    "Event 4 = '4'\nEvent 5 = '5'\nEvent 6 = '6'\nFrenemies = '7'\n" +
                    "Check Reputation = '9'\nCheck Hate ='0'\n");
            System.out.print("Enter the event number(or type 'quit' to exit): ");
            String event = scan.nextLine();
            while(!event.equals("quit")){
                if(event.equals("1") || event.equals("2")){
                    Event1and2(graph1,variableABC);
                }else if(event.equals("3")){
                    Event3(graph1);
                }else if(event.equals("4")){
                    Event4();
                }else if(event.equals("5")){
                    Event5(graph1, variableABC);;
                }else if(event.equals("6")){
                    Event6();
                }else if(event.equals("7")){
                    frenemies(graph1, graphEnemy);
                }else if(event.equals("9")){
                    CheckReputation(vertex,graph1);
                }else if(event.equals("0")){
                    checkHate(vertex,graphEnemy);
                }
                else{
                    System.out.println("Invalid event number.");
                }
                System.out.println("\nList of events:");
                System.out.println("Event 1 = '1'\nEvent 2 = '2'\nEvent 3 = '3'\n" +
                        "Event 4 = '4'\nEvent 5 = '5'\nEvent 6 = '6'\nFrenemies = '7'\n" +
                        "Check Reputation = '9'\nCheck Hate ='0'\n");
                System.out.print("Enter the event number(or type 'quit' to exit): ");
                event = scan.nextLine();
            }

        }catch(InputMismatchException e){
            System.out.println("Invalid input.");
        }
    }
    

    
    //Event 2 -----------------------------------------------------------------------------------------------
    public static void Event1and2(WeightedGraph<Integer, Integer> graph1,int[] variableABC){
        //code for event 2
        System.out.println("This is Event 1 and 2: \n");
        // Random random = new Random();
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
        
       // boolean temp = graph1.addEdge(b, a, 10); //event1 =you good at programming
        //Event 1
        int learn = random.nextInt(2);

      
            if (learn == 0){ //good at programming
            
                System.out.println("Person "+b+" having fun learning with you.");
                System.out.println("Person "+b+" become friend with you.");
                graph1.addEdge(b, a, 10); 

                int getrep1 = 0;
                boolean check = graph1.hasEdge(c, a);
                if (check == true){
                    getrep1 = (int) (graph1.getEdgeWeight(c, a));   
                }
                int getrep = (int) (graph1.getEdgeWeight(b, a)*0.5);
                getrep = getrep1+getrep;
                if(check){//to prevent redundant node
                    graph1.removeEdge(c, a);
                  
                System.out.println("Person "+b+" chit-chat with Person "+c+
                    " good things about you. ("+ graph1.addEdge(c, a, getrep)+")\n");
                }
                else{
                     System.out.println("Person "+b+" chit-chat with Person "+c+
                    " good things about you. ("+ graph1.addEdge(c, a, getrep)+")\n");
                    
                }
            }

            else if (learn == 1) { //bad at programming

                System.out.println("You guys have an unpleasant teaching-learning session.");
                System.out.println("However, you guys still friends");
                graph1.addEdge(b, a, 10); 

                int getrep1 = 0;
                boolean check = graph1.hasEdge(c, a);
                if (check == true){
                    getrep1 = (int) (graph1.getEdgeWeight(c, a));   
                }
                int getrep = (int) (graph1.getEdgeWeight(b, a));
                getrep = getrep1-getrep;
                if(check){//to ptevent redundant node
                    graph1.removeEdge(c, a);
                System.out.println("Person "+b+" chit-chat with Person "+c+
                    " bad things about you. ("+ graph1.addEdge(c, a, getrep)+")\n");
                }
                else{
                     System.out.println("Person "+b+" chit-chat with Person "+c+
                    " bad things about you. ("+ graph1.addEdge(c, a, getrep)+")\n");
                    
                }
            }
        

        System.out.println("Person "+b+" chit-chat with Person "+c+" about you.\n");
        System.out.println("From Person " +c+ " point of view, Person "+a+" having "+graph1.getEdgeWeight(c, a)+" rep points.\n ");
        graph1.printEdges();
            variableABC[0]=a;
            variableABC[1]=b;
            variableABC[2]=c;
    }
    //end of event 2 --------------------------------------------------------------------------------------------
    
    public static void Event3(WeightedGraph<Integer, Integer> graph1){
        System.out.println("Event 3----------------------------------------");
        Scanner scan = new Scanner(System.in);
        String [] day={"Monday","Tuesday","Wednesday","Thursday","Friday"};
      
      int [] vertex = {1,2,3,4,5,6,7,8,9,10};
      int [] dive = new int[10];
      int[] Period = new int[10];     
      int[]Start = new int[10];

      for (int i = 0; i < dive.length; i++) {//initialize dive rate
        dive[i]=random.nextInt(101);
    }

        System.out.println("---------------");

        System.out.println("Start time and time taken for lunch for every student:");
        for (int i = 0; i < Period.length; i++) {
            System.out.println("Student:"+(i+1));
            for (int j = 0; j < day.length; j++) {
            int temp2 =  random.nextInt(1355-1100)+1100;
            int temp = random.nextInt(60-5)+5;
              while((temp2%100)>=60){//start random
                temp2=random.nextInt(1355-1100)+1100;
            
        }
            while(changetime(temp2,temp)>1400){
                 temp = random.nextInt(60-5)+5;
            }
            Start[i]+=temp2;
            Period[i]+=temp;
                System.out.print(day[j]+":["+"Start:"+temp2+" "+"Minutes:"+temp+"]"+" ");
           
            }
            System.out.println("");
        }
        
        //average time taken for lunch
        for (int i = 0; i < Period.length; i++) {
            Period[i]=Period[i]/5;
        }
        
         //average start time for lunch
        for (int i = 0; i < Start.length; i++) {
            Start[i]=Start[i]/5;
           Start[i]= changetime(Start[i],0);
        }

         //dive rate each student
         System.out.println("-----------------------");
         for (int i = 0; i < dive.length; i++) {
               System.out.println("Student "+(i+1)+":"+dive[i]+"%");
         }
        
        System.out.println("-----------------------");
        System.out.println("Average start time for each student:");//For initialize lunch period
        for (int i = 0; i < Start.length; i++) {
            System.out.println("Student "+(i+1)+":"+Start[i]);
        }
        
       
      
  
        
           System.out.println("-----------------------");
        System.out.println("Average Lunch period(minute):");//For initialize lunch period
        for (int i = 0; i < Period.length; i++) {
            System.out.println("Student "+(i+1)+":"+Period[i]);
        }

        for (int i = 0; i < Start.length; i++) {//initialize start time and lunch period
            graph1.AddStart(vertex[i], Start[i]);
            graph1.AddLunch(vertex[i], Period[i]);
            graph1.AddDive(vertex[i], dive[i]);
        }

        System.out.println("------------");
        graph1.printEdges();
           System.out.println("------------");
        CheckReputation(vertex,graph1);
           System.out.println("------------");

           int a = 1;//initailiazie main character

        //    int[]checkrelia =relia(vertex,graph1); //Find reliability of each student by finding its average rep among his friend
        //    System.out.println("----------------");
        //    System.out.println("List of reliability:");
        //  for (int i = 0; i < 10; i++) {//Get reliability of each student by getting their average rep among their friends
     
        //     System.out.println("Reliability student "+vertex[i]+" is "+checkrelia[i]);
        //  }//sort the array astu

             System.out.println("-----------------");
             int[] prio =priorityReliability(dive,vertex);//Arrange student with highest reliability at top

             System.out.println("Priority list according to reliability");
         	for (int i = 0; i <prio.length ; i++) {//sort the student according to reliability ascending
                    System.out.println("Student: "+prio[i]);
        }
            

           //to find the lunch time of every student
        int[] endtime = new int[10];//hold end time
         int[] starttime = new int[10];//hold start time
         FindLunchTime(vertex,starttime,endtime,graph1);//Find lunch time of every student

         System.out.println("----------------------------------------");
        System.out.println("List of the time taken for a student lunch:");
          for (int i = 0; i < 10; i++) {//list of all students lunch period
             System.out.println("lunch period for student "+vertex[i]+": "+starttime[i]+"-"+endtime[i]);
        }
          
          //to find number of student that can have lunch together at one time
          ArrayList<Integer> maxrep = new ArrayList<>();
         //use student 1 as the main character
         System.out.println("--------------------------");
         int num =a;
          System.out.println("The student that will find people to have lunch with is student:"+a);
          System.out.println("His lunch time is at "+starttime[a-1]+"-"+endtime[a-1]);
         // int num = scan.nextInt();
          System.out.println("--------------------------------------");
          FindMaxReputation(a,prio,starttime,endtime,maxrep);//Find max reputation per day for a student

          System.out.println("List of people that student "+num+" can have lunch with:");
          for (int i = 0; i <maxrep.size(); i++) {//from this data we can add 1 to each rep and add as new friends if they are no friends yet relative to the main character
              System.out.println("The student can have lunch with is "+maxrep.get(i)+" which is at "+starttime[maxrep.get(i)-1]+
                                    "-"+endtime[maxrep.get(i)-1]);
        }

        AddMaxRepPerDay(maxrep,a,graph1);//update all reputation a student can get with each people he have lunch with
        System.out.println("------------");
      graph1.printEdges();
         System.out.println("------------");
       CheckReputation(vertex,graph1);
        System.out.println("-------------");

   
        }
        

    //event 4 -------------------------------------------------------------------------------
    public static void Event4(){
        System.out.println("Event 4----------------------------------------");
        Scanner a = new Scanner(System.in);
        System.out.print("Enter number of book: ");
        int numbook = a.nextInt();
        a.nextLine();
        String[] Book =new String[numbook];
        System.out.println("Enter the books separated by spaces: ");
        String buku = a.nextLine();
        Book = buku.split(" ");
    
       Stack<String> BookShelf = new Stack<>();
        for (int i = Book.length-1; i >= 0; i--) {
            BookShelf.push(Book[i]);
        }
        Stack<String> leftSide = new Stack<>();
        Stack<String> Table = new Stack<>();
        Stack<String> copybook = new Stack<>();
        int round=0;
        
        while(true){
            int length = BookShelf.size();
            
          while(!BookShelf.isEmpty()){
             if(leftSide.isEmpty()){//if left side of books shelf is empty
                 leftSide.push(BookShelf.pop());
             }
             else if(Integer.parseInt(leftSide.peek())>Integer.parseInt(BookShelf.peek())){//if book on left side of bookshelf is higher than book on the right side 
                 leftSide.push(BookShelf.pop());
             }
             else{
                 Table.push(BookShelf.pop());//if book on left side has lower height than book on right in bookshelf, put the book on the right side of the bookshelf on the table
                 if(!BookShelf.isEmpty()){
                     while(Integer.parseInt(Table.peek())<Integer.parseInt(BookShelf.peek())){//if book on table is lower height than book on right in bookshelf, keep putting the book from bookshelf on the table
                         
                         Table.push(BookShelf.pop());
                         if(BookShelf.isEmpty())//if book on right side is empty
                             break;
                        
                     }
                    
                     if(!BookShelf.isEmpty())
                     leftSide.push(BookShelf.pop());
                 }
             }
              
              
              
              
          }
          copybook=(Stack)leftSide.clone();
          int temp = leftSide.size();
           while(!leftSide.isEmpty()){
           
               BookShelf.push(leftSide.pop());//push all the book to the right side
           }
          if(temp==length)//check if number of book in bookshelf is still the same
              break;
            System.out.println(copybook.toString());
          round++;
              
        }
        System.out.println("Round needed is "+round);
        
        
    }
    //end of event 5 --------------------------------------------------------------------------
    
    public static void Event5(WeightedGraph<Integer, Integer> graph1,int[]variableABC){
        //code for event 5
        int a = variableABC[0];
        int b = variableABC[1];
        int c = variableABC[2];
        System.out.println();
        System.out.println("You have join one volunteering program");
        System.out.println("When you doing your work, you bump into someone\n");
        
        ArrayList<Integer> d2 = graph1.getNeighbours(a); //list all you friends
        d2.add(a); // add you in the list
        d2.add(b); // add strangers in the list
        ArrayList<Integer> d1 = new ArrayList<>();// list all edge except you and strangers
       
        for(int i=1; i<=10; i++){
            d1.add(i);
            for(int x=0; x<d2.size(); x++){ //check if d1 has in d2
                if (i == d2.get(x)) { 
                    d1.remove(d1.size()-1); //remove d1 since it has in d2 
                    break; // continue to add next edge
                }
            }
        }
        
        int d = d1.get(random.nextInt(d1.size())); // choose the crush
        System.out.println("You bump to "+d+".\n");
        
        ArrayList<Integer> a5 = graph1.getNeighbours(a);
        System.out.print(a+ " : ");
        System.out.println(a5); //print all you friends
        ArrayList<Integer> b5 = graph1.getNeighbours(b);
        System.out.print(b+ " : ");
        System.out.println(b5); // print all strangers friends
        ArrayList<Integer> c5 = graph1.getNeighbours(c);
        System.out.print(c+ " : ");
        System.out.println(c5); // print all chitchat friends
        ArrayList<Integer> d5 = graph1.getNeighbours(d);
        System.out.print(d+ " : ");
        System.out.println(d5);// print all crush friends
                
     
        boolean spread = graph1.hasEdge(d, b); // check if crush friend with strangers
        boolean spread1 = false;
        
        if (d != c)//check id d and c is same person or not
            spread1 = graph1.hasEdge(d, c);// check if crush friend with chitchat
        
        if (spread == true && spread1 == true){//crush friends with b and c
            System.out.println("There is someone who knows you that are friends"
                    + " with your crush: Person "+b+ " and " +c);
            System.out.println("You guys meet up");
            System.out.println("Person "+b+ " and " +c+ " promise not to tell "
                    +d+ " that you have crush on him/her"); 
        }
        else if (spread == true){//crush friend with b only
            System.out.println("There is someone who knows you that are friends"
                    + " with your crush: Person "+b);
            System.out.println("You guys meet up");
            System.out.println("Person "+b+ " promise not to tell "
                    +d+ " that you have crush on him/her"); 
        }
        else if (spread1 == true){//crush friend with c only
            System.out.println("There is someone who knows you that are friends"
                    + " with your crush: Person "+c);
            System.out.println("You guys meet up");
            System.out.println("Person " +c+ " promise not to tell "
                    +d+ " that you have crush on him/her"); 
        }
        else // crush not friend with anybody
            System.out.println("Your crush will not know you like her/his");
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
        System.out.println("Event 6----------------------------------------");
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
        
        sequence.clear();   //clear back the sequence arraylist for next input
    }
    //end of event 6 -------------------------------------------------------------------


    public static int changetime(int start,int min){
            
           
        int hold = start%100;
        hold+=min;//start minute + min lunch
        int end = start+min;
        if(hold>=60){
             int hold2 = start;
            if(hold2%100!=0){
                hold2=hold2-(hold2%100);
            }
            int hold3 = (hold-60)+100;
            hold2+=hold3;
            return hold2;
            
            
        }else 
        return end;
            
       }

       public static void CheckReputation(int[]vertex,WeightedGraph<Integer,Integer> graph){
        ArrayList<Integer> temp = new ArrayList<>();
        System.out.println("List of reputation");
        for (int i = 0; i < vertex.length; i++) {
            temp = graph.getNeighbours(vertex[i]);
            if(temp.size()!=0){
            System.out.println("Student "+vertex[i]);
            for (int j = 0; j < temp.size(); j++) {
                System.out.print("[Student "+temp.get(j)+", Reputation:"+graph.getEdgeWeight(vertex[i], temp.get(j))+"]");
            }
            System.out.println("");
            
        }
        }
    }

    // public static int[] relia (int[] vertex,WeightedGraph<Integer,Integer> graph){//method find reliability
          
         
    //     int[] listofreli = new int[10];
    //     int[] numberofedges = new int[10];
        
       
    //       for (int i = 0; i < 10; i++) {
    //           ArrayList<Integer> temp = graph.getNeighbours(vertex[i]);
    //         for (int j = 0; j < temp.size(); j++) {
             
    //               listofreli[i]+=graph.getEdgeWeight(vertex[i], temp.get(j));
    //                 numberofedges[i]+=1;
                
                    
                    
    //         }
            
    //     }
    //       int[] reliability = new int[10];
    //       for (int i = 0; i < 10; i++) {
    //           reliability[i] = listofreli[i]/numberofedges[i];
             
    //     }




    //     return reliability; 
    //     }   
        
        public static int[] priorityReliability (int[] checkrelia,int[]vertex){//method find reliability
          
         
            int[] prio = vertex.clone();
           for ( int pass = 1; pass < prio.length; pass++ ){
                  
            for ( int i = 0; i < prio.length - 1; i++ ) {
                if ( checkrelia[ i ] > checkrelia[ i + 1 ] )  {
                                    int hold = checkrelia[i];        
                    checkrelia[i] = checkrelia[i+1];  
                    checkrelia[i+1] = hold;
                                    int pegang =prio[i];
                                    prio[i]=prio[i+1];
                                    prio[i+1]=pegang;
                }
                        
                    }
              } 
            
           
    
            return prio;
            
            }  

            public static void FindLunchTime(int[] vertex,int[]starttime,int[]endtime,WeightedGraph<Integer,Integer>graph1){
                for (int i = 0; i < starttime.length; i++) {//Get each student lunch period by adding starttime and lunch period
              
                starttime[i] = graph1.getStart(vertex[i]);
                int end = graph1.getStart(vertex[i])+graph1.getLunch(vertex[i]);
                int hold = end%100;
                if(hold>=60){
                    int hold2 = graph1.getStart(vertex[i]);
                    if(hold2%100!=0){
                        hold2=hold2-(hold2%100);
                    }
                    int hold3 = (hold-60)+100;
                    hold2+=hold3;
                    endtime[i]=hold2;
                    continue;
                }
                endtime[i]=end;
            
            }
          }

          public static void FindMaxReputation(int a,int[]prio,int[]starttime,int[]endtime,ArrayList<Integer>maxrep){
          
            for (int i = 0; i <prio.length ; i++) {
                if(maxrep.isEmpty()&&(starttime[a-1]>=starttime[prio[i]-1]&&endtime[a-1]<=endtime[prio[i]-1])&&prio[i]!=a){
                    System.out.println("yahoo");
                     maxrep.add(prio[i]);//the main character lunch time is within other student lunch time 
                     break;
                }
                else if(maxrep.isEmpty()&&
              ((starttime[prio[i]-1]>=starttime[a-1]&&starttime[prio[i]-1]<=endtime[a-1])//the start time is withinn main character lunch time
                     ||
              (endtime[prio[i]-1]>=starttime[a-1]&&endtime[prio[i]-1]<=endtime[a-1])//the end time is within main character lunch time
                     ||
             ((starttime[prio[i]-1]>=starttime[a-1]&&starttime[prio[i]-1]<=endtime[a-1])
                     &&                    //both start time and end time is within the main character lunch time
              (endtime[prio[i]-1]>=starttime[a-1]&&endtime[prio[i]-1]<=endtime[a-1])))
               &&prio[i]!=a){
               
               maxrep.add(prio[i]);
               
               
           }
               
               
               
           else if(prio[i]!=a&&
             ((starttime[prio[i]-1]>=starttime[a-1]&&starttime[prio[i]-1]<=endtime[a-1])//the start time is withinn main character lunch time
                     ||
              (endtime[prio[i]-1]>=starttime[a-1]&&endtime[prio[i]-1]<=endtime[a-1])//the end time is within main character lunch time
                     ||
             ((starttime[prio[i]-1]>=starttime[a-1]&&starttime[prio[i]-1]<=endtime[a-1])
                     &&                    //both start time and end time is within the main character lunch time
              (endtime[prio[i]-1]>=starttime[a-1]&&endtime[prio[i]-1]<=endtime[a-1])))
                   &&!maxrep.isEmpty()
                   ){
                 System.out.println("The student who pass is:"+prio[i]);
               int index = 0;
               for (int j = 0; j < maxrep.size(); j++) {
                   if((starttime[prio[i]-1]>=endtime[maxrep.get(j)-1])||
                        (endtime[prio[i]-1]<=starttime[maxrep.get(j)-1])   
                          )
                   {

                       index++;
                   }
                   else{
                       
                      continue;
                       
                   }
                   
               }
               if(index==maxrep.size())
                   maxrep.add(prio[i]);
               else 
                   continue;
           }
           else continue;
       }
    }

    public static void AddMaxRepPerDay(ArrayList<Integer>maxrep,int num,WeightedGraph<Integer,Integer>graph1){
            
        for (int i = 0; i < maxrep.size(); i++) {
        if(graph1.hasEdge(maxrep.get(i), num)){
          int hold = graph1.getEdgeWeight(maxrep.get(i), num);
            System.out.println("huhuh");
            graph1.removeEdge(maxrep.get(i), num);
            graph1.addEdge(maxrep.get(i), num, hold+1);
        }
       else
             graph1.addEdge(maxrep.get(i), num, 1);
          
      }
      
    }
    public static void initializeStartandPeriod(int[]Start,int[]Period,String[]day){
        System.out.println("Start time and time taken for lunch for every student:");
     for (int i = 0; i < Period.length; i++) {
         System.out.println("Student:"+(i+1));
         for (int j = 0; j < day.length; j++) {
         int temp2 =  random.nextInt(1355-1100)+1100;
         int temp = random.nextInt(60-5)+5;
           while((temp2%100)>=60){//start random
             temp2=random.nextInt(1355-1100)+1100;
         
     }
         while(changetime(temp2,temp)>1400){
              temp = random.nextInt(60-5)+5;
         }
         Start[i]+=temp2;
         Period[i]+=temp;
             System.out.print(day[j]+":["+"Start:"+temp2+" "+"Minutes:"+temp+"]"+" ");
        
         }
         System.out.println("");
     }
   }
    
    //Extra feature 1 : social dynamics - Frenemies----------------------------------------------------------
    public static WeightedGraph<Integer, Integer> initEnemyGraph(){
        WeightedGraph<Integer, Integer> graphEnemy = new WeightedGraph<>();
        //add vertex for all 10 students
        for (int i = 1; i < 11; i++){
            graphEnemy.addVertex(i);
        }

        return graphEnemy;
    }
    public static void frenemies(WeightedGraph<Integer, Integer> graph1, WeightedGraph<Integer, Integer> graphEnemy){
        Scanner scan = new Scanner(System.in);
        System.out.println("Frenemies---------------------------------");
        System.out.println("Do you want to:");
        System.out.println("Establish an enemy edge between 2 students (1)\n" +
                "Move on from enemy relationship between 2 students (2)\n" +
                "Check Frenemies Statuses (3)\n" );
        System.out.print("Your option (1/2/3): ");
        String options = scan.nextLine();
        if(options.equals("1")){
            System.out.print("Enter the student number: ");
            int source = scan.nextInt();
            System.out.print("Enter his/her enemy: ");
            int dest = scan.nextInt();
            System.out.print("Enter the weight of hate of the student: ");
            int weight = scan.nextInt();
            boolean temp = graphEnemy.addEdge(source, dest, weight);
            System.out.println("Student " + source +" is now having enemy relationship with Student " + dest);

            System.out.println("[Enemy Edges]");
            graphEnemy.printEdges();

        }else if(options.equals("2")){
            System.out.print("Enter the student number: ");
            int source = scan.nextInt();
            System.out.print("Enter his/her enemy: ");
            int dest = scan.nextInt();
            graphEnemy.removeEdge(source,dest);
            System.out.println("Student " + source +" has move on from enemy relationship with Student " + dest);
            graphEnemy.printEdges();

        }else if(options.equals("3")){
            System.out.println("[Friends Edge]");
            graph1.printEdges();
            System.out.println("\n[Enemies Edge]");
            graphEnemy.printEdges();
            ArrayList<Integer> enemy = new ArrayList<>();
            ArrayList<Integer> friend = new ArrayList<>();

            boolean hasFrenemy = false;

            for(int i = 1; i <=10 ;i++){
                enemy = graphEnemy.getNeighbours(i);
                friend = graph1.getNeighbours(i);
                for(int j = 0; j < enemy.size(); j++){
                    for(int k = 0; k < friend.size(); k++){
                        if(enemy.get(j).equals(friend.get(k))){
                            System.out.println("Student " + i + " is frenemies with Student " + enemy.get(j));
                            hasFrenemy = true;
                        }
                    }

                }
            }
            if(!hasFrenemy){
                System.out.println("No frenemies relationship founded yet.");
            }
        }else{
            System.out.println("Invalid options.");
        }
    }
    public static void checkHate(int[]vertex,WeightedGraph<Integer,Integer> graph){
        ArrayList<Integer> temp = new ArrayList<>();
        System.out.println("List of Hate Rate:");
        for (int i = 0; i < vertex.length; i++) {
            temp = graph.getNeighbours(vertex[i]);
            if(temp.size()!=0){
                System.out.println("Student "+vertex[i]);
                for (int j = 0; j < temp.size(); j++) {
                    System.out.print("[Student "+temp.get(j)+", Hate:"+graph.getEdgeWeight(vertex[i], temp.get(j))+"]");
                }
                System.out.println("");

            }
        }
    }
    // end of extra features 1 ------------------------------------------------------------------

}
