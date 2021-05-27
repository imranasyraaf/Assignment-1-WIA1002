import java.util.ArrayList;

public class UnweightedGraph<T extends Comparable<T>, N extends Comparable <N>> {

    Vertex<T,N> head;
    int size;

    public UnweightedGraph() {
        head = null;
        size = 0;
    }

    //return the size of the graph (amount of vertices)
    public int getSize() {
        return this.size;
    }

    //get the in degree of a vertex
    public int getIndeg(T v) {
        //check if the vertex exist
        if (hasVertex(v)) {
            Vertex<T,N> temp = head;
            while (temp != null) {
                //traverse each vertex from head and compare its info(name) to the input vertex
                if (temp.vertexInfo.compareTo(v) == 0) {
                    //if found equal, return the respective in degree
                    return temp.indeg;
                }
                temp = temp.nextVertex; //go to check the next vertex
            }
        }
        return -1; //if the vertex is not in the graph
    }

    //get the out degree of a vertex
    public int getOutdeg(T v) {
        //check if the vertex exist
        if (hasVertex(v)) {
            Vertex<T,N> temp = head;
            while (temp != null) {
                //traverse each vertex from head and compare its info(name) to the input vertex
                if (temp.vertexInfo.compareTo(v) == 0) {
                    //if found equal, return the respective out degree (vertex found)
                    return temp.outdeg;
                }
                temp = temp.nextVertex; //go to check the next vertex
            }
        }
        return -1; //if the vertex is not in the graph
    }

    //to check whether a vertex is in the graph
    public boolean hasVertex(T v) {
        //check if the graph is empty
        if (head == null) {
            return false;
        }
        //if graph is not empty
        Vertex<T,N> temp = head;
        while (temp != null) {
            //traverse each vertex from head and compare its info(name) to the input vertex
            if (temp.vertexInfo.compareTo(v) == 0) {
                return true; //vertex found
            }
            temp = temp.nextVertex; // go to check next vertex
        }
        return false; //vertex not found
    }

    public boolean addVertex(T v) {
        //check whether the vertex is already exist or not (add vertex if not exist)
        if(!hasVertex(v)) {
            Vertex<T,N> temp = head;
            Vertex<T,N> newVertex = new Vertex<>(v, null); //create new vertex
            if (head == null) {
                head = newVertex;   //if graph is empty, point head to the new vertex
            }else{
                Vertex<T,N> previous = head;
                //traverse to the last vertex in the graph
                while (temp != null) {
                    previous = temp;
                    temp = temp.nextVertex;
                }
                previous.nextVertex = newVertex; //point the next vertex of the last vertex to the new vertex
            }
            size++; //increase the size of the graph
            return true; //successful adding vertex

        }else{
            return false; //cannot add vertex, vertex already exist
        }
    }

    //return the index of certain vertex
    public int getIndex(T v) {
        Vertex<T,N> temp = head;
        int index = 0;
        while (temp != null) {
            //traverse until finding the vertex
            if (temp.vertexInfo.compareTo(v) == 0) {
                return index;
            }
            temp = temp.nextVertex;
            index += 1;
        }
        return -1; //vertex not found
    }

    //return all vertices info(name) into an arraylist
    public ArrayList<T> getAllVertexObjects(){
        ArrayList<T> list = new ArrayList<>();  //the type of arraylist is T
        Vertex<T,N> temp = head;
        while (temp != null) {
            list.add(temp.vertexInfo);
            temp = temp.nextVertex;
        }
        return list;
    }

    //return all vertices into an arraylist
    public ArrayList<Vertex<T,N>> getAllVertices(){
        ArrayList<Vertex<T,N>> list = new ArrayList<>();    //the type of arraylist is Vertex<T,N>
        Vertex<T,N> temp = head;
        while (temp != null) {
            list.add(temp);
            temp = temp.nextVertex;
        }
        return list;
    }

    //return the vertex at given position/index
    public T getVertex(int pos){
        //check if the given position is out of bounds
        if (pos > size - 1 || pos < 0) {
            return null;
        }
        Vertex<T,N> temp = head;
        //traverse up until the given position
        for (int i = 0; i < pos; i++) {
            temp = temp.nextVertex;
        }
        return temp.vertexInfo;
    }

    //add a directed edge (from source(vertex info) to destination(vertex info))
    public boolean addEdge(T source, T destination){
        //if the graph is empty (cannot add edge)
        if (head == null) {
            return false;
        }
        //if source vertex and destination vertex do not exist
        if (!hasVertex(source) || !hasVertex(destination)) {
            return false;
        }
        Vertex<T,N> sourceVertex = head;
        while (sourceVertex != null) {
            //finding the source vertex in the graph
            if (sourceVertex.vertexInfo.compareTo(source) == 0) {
                // Reached source vertex, look for destination now
                Vertex<T,N> destinationVertex = head;
                while (destinationVertex != null) {
                    //finding destination vertex in the graph
                    if (destinationVertex.vertexInfo.compareTo(destination) == 0) {
                        // Reached destination vertex, add edge here
                        Edge<T,N> currentEdge = sourceVertex.firstEdge; //temporary edge for the next edge reference
                        Edge<T,N> newEdge = new Edge<>(destinationVertex,null, currentEdge);
                        sourceVertex.firstEdge = newEdge; //update a new edge at first edge
                        sourceVertex.outdeg++;  //increase the out degree of the source vertex
                        destinationVertex.indeg++;  //increase the in degree of the destination vertex
                        return true;
                    }
                    destinationVertex = destinationVertex.nextVertex;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

    //add undirected edge
    public boolean addUndirectedEdge(T vertex1, T vertex2) {
        //if the graph is empty (cannot add edge)
        if (head == null) {
            return false;
        }
        //if source vertex and destination vertex do not exist
        //Can be omitted
        if (!hasVertex(vertex1) || !hasVertex(vertex2)) {
            return false;
        }
        Vertex<T,N> tempVertex1 = head;
        while (tempVertex1 != null) {
            //finding the vertex 1 in the graph
            if (tempVertex1.vertexInfo.compareTo(vertex1) == 0) {
                Vertex<T,N> tempVertex2 = head;
                while (tempVertex2 != null) {
                    //finding the source vertex 2 in the graph
                    if (tempVertex2.vertexInfo.compareTo(vertex2) == 0){
                        //just like directed edge but do it for both ways

                        //edge for vertex 1 to vertex 2
                        Edge<T,N> currentEdge1 = tempVertex1.firstEdge;
                        Edge<T,N> newEdge1 = new Edge<>(tempVertex2,null, currentEdge1);
                        tempVertex1.firstEdge = newEdge1;
                        tempVertex1.outdeg++;
                        tempVertex2.indeg++;

                        //edge for vertex 2 to vertex 1
                        Edge<T,N> currentEdge2 = tempVertex2.firstEdge;
                        Edge<T,N> newEdge2 = new Edge<>(tempVertex1,null, currentEdge2);
                        tempVertex2.firstEdge = newEdge2;
                        tempVertex1.indeg++;
                        tempVertex2.outdeg++;

                        return true;
                    }
                    tempVertex2 = tempVertex2.nextVertex;
                }
            }
            tempVertex1 = tempVertex1.nextVertex;
        }
        return false;
    }

    //check if there is an edge between 2 vertices
    public boolean hasEdge(T source, T destination){
        //if graph is empty (no edge exist)
        if (head == null) {
            return false;
        }
        //if the vertices do not exist (no edge exist)
        if (!hasVertex(source) || !hasVertex(destination)) {
            return false;
        }

        Vertex<T,N> sourceVertex = head;
        while (sourceVertex != null) {
            //finding the source vertex
            if (sourceVertex.vertexInfo.compareTo(source) == 0) {
                // Reached source vertex, look for destination now
                Edge<T,N> currentEdge = sourceVertex.firstEdge;
                while (currentEdge != null) {
                    //finding edge
                    if (currentEdge.toVertex.vertexInfo.compareTo(destination) == 0){
                        // destination vertex found
                        return true;
                    }
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

    public boolean removeEdge(T source, T destination){
        //if graph is empty (cannot remove edge)
        if (head == null) {
            return false;
        }
        //if vertices do not exist (cannot remove edge that does not exist)
        if (!hasEdge(source, destination)) {
            return false;
        }
        Vertex<T,N> sourceVertex = head;
        while (sourceVertex != null) {
            //finding the source vertex
            if (sourceVertex.vertexInfo.compareTo(source) == 0) {
                // Reached source vertex, look for destination now
                Edge<T,N> currentEdge = sourceVertex.firstEdge;
                /*
                Edge<T,N> prevEdge = currentEdge;
                while (currentEdge != null) {
                    //finding edge
                    if (currentEdge.toVertex.vertexInfo.compareTo(destination) == 0){
                        // destination vertex found
                        break;
                    }
                    prevEdge = currentEdge;
                    currentEdge = currentEdge.nextEdge;
                }
                Edge<T,N> tempEdge = currentEdge.nextEdge;
                currentEdge.toVertex.indeg--;
                sourceVertex.outdeg--;
                currentEdge.clear();
                prevEdge.nextEdge = tempEdge;
                return true;
                */
                //If destination is inside first edge
                if (currentEdge != null) {
                    if (sourceVertex.firstEdge.toVertex.vertexInfo.compareTo(destination) == 0) {
                        currentEdge.toVertex.indeg--;
                        sourceVertex.outdeg--;
                        currentEdge = sourceVertex.firstEdge.nextEdge;
                        sourceVertex.firstEdge.clear();
                        sourceVertex.firstEdge = currentEdge;
                        return true;
                    }
                }
                //If destination is inside other edges rather than first edge
                while (currentEdge != null) {
                    if (currentEdge.nextEdge.toVertex.vertexInfo.compareTo(destination) == 0) // destination vertex found
                    {
                        Edge<T,N> tempEdge = currentEdge.nextEdge;
                        tempEdge.toVertex.indeg--;
                        sourceVertex.outdeg--;
                        currentEdge.nextEdge = tempEdge.nextEdge;
                        tempEdge.clear();
                        return true;
                    }
                    currentEdge = currentEdge.nextEdge;
                }


            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

    //return all neighbours of the vertex into an arraylist
    public ArrayList<T> getNeighbours(T v){
        //if vertex does not exist
        if (!hasVertex(v)) {
            return null;
        }

        ArrayList<T> list = new ArrayList<T>();
        Vertex<T,N> temp = head;
        while (temp != null) {
            //finding the vertex
            if (temp.vertexInfo.compareTo(v) == 0) {
                // Reached vertex, look for destination(neighbours) now
                Edge<T,N> currentEdge = temp.firstEdge;
                while (currentEdge != null) {
                    list.add(currentEdge.toVertex.vertexInfo);  //add the neighbour to the list
                    currentEdge = currentEdge.nextEdge;         //finding the next neighbour
                }
            }
            temp = temp.nextVertex;
        }
        return list;
    }

    //print the graph
    public void printEdges(){
        Vertex<T,N> temp = head;
        while (temp != null) {
            //print the vertex and its edges
            System.out.print("# " + temp.vertexInfo + " : ");
            Edge<T,N> currentEdge = temp.firstEdge;
            while (currentEdge != null) {
                System.out.print("[" + temp.vertexInfo + "," + currentEdge.toVertex.vertexInfo + "] ");
                currentEdge = currentEdge.nextEdge;
            }
            System.out.println();
            temp = temp.nextVertex; //go to the next vertex
        }
    }

}
