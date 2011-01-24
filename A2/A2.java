/*John Lomenick
 * 3 March 2010
 * CSCI 433
 * Assignment 2: Depth-First and Breadth-First Search
 * */


import java.io.*;
import java.util.*;




/*===========================================
 * CLASS FOR NODES IN THE LINKED LIST
 * ===========================================*/

class adjNode{
 public int vertex;
 public adjNode nextVertex;
 
 public adjNode(int j){
  vertex=j;
  nextVertex=null;
 }
}//end class adjNode



/*========================================
 * CLASS FOR LINKED LISTS WITHIN THE ARRAY
 * =======================================*/

class adjList{
 public adjNode firstNode;
 public adjNode lastNode;
 
 //constructor
 public adjList(){
  firstNode=null;
  lastNode=null;
 }
 
 /*
  * checks to see if current adjList is empty
  * */
 public boolean isEmptyAdjList(){
  return firstNode==null; 
 }
 
 //add vertex to end of the current vertex's adjList
 public void addVertex(adjNode new_node){
   if(isEmptyAdjList()){
    firstNode= new_node;
    lastNode= new_node;
   }else{
    lastNode.nextVertex=new_node;
    lastNode=new_node;
   }
 }//end addVertex
 
 //prints each adjList
 public void printList(){
  adjNode current = firstNode;
  if(isEmptyAdjList()){
  }
  else{
    while(current!=lastNode){
      System.out.print(current.vertex);
      current=current.nextVertex;
  }
    System.out.print(current.vertex);
 }
}//end printList
}//end class adjList

/*=========================================
 * CLASS QUEUE
 * ========================================
 * */

class Queue{
 int first;
 int last;
 int data[];
 int size;
 
 //constructor
 public Queue(int n){
   first = -1;
   last =  -1;
   size = n-1;
   data = new int[n];
 }
 
 public boolean queueIsFull(){
   if ((first == last+1)){
     return true;
   }else if(((first == 0) && (last == size))){
     return true;
   }else{
     return false;
   }
 }//end queueISFull

 public boolean queueIsEmpty(){
   return first == -1;
 }//end queueIsEmpty
 
 public void enqueue(int value){
   if( queueIsFull()){
    System.out.print("ERROR: queue overflow"); 
   }else if(queueIsEmpty()){
     first = 0;
     last = 0;
   }else{
      last = last +1;
      if(last == size){
       last = 0; 
      }
     }
   data[last]=value;
 }//end enqueue
 
 public int dequeue(){
   int value = -1;
   if(queueIsEmpty()){
     System.out.print("ERROR: queue underflow error ");
   }else{
     value = data[first];
     if(first ==last){
      first = -1;
      last = -1;
     }else if(first == size ){
       first = 0;
     }else{
      first = first +1; 
     }
   }
   return value;
 }//end dequeue
}//end queue class


/*========================================
 * MAIN METHOD
 * ===================================*/
class A2{
  static Scanner console = new Scanner(System.in);
  public static void main(String [] args){
    while(console.hasNext()){
      int n = console.nextInt();
      readMatrix(n);
   }
  }//end main method

  //Method for reading in values of matrix and placing them in adjacency list 
  //CALLS DFS and BFS
  public static void readMatrix(int n){
   
    adjList[] arrayOfLists = new adjList[n];
    for(int c=0 ; c<n ;c++){
     arrayOfLists[c]= new adjList(); 
    }
      for(int i = 0 ; i < n  ; i++){
        String s = console.next();
        for(int j = 0; j < n   ; j++){
          if(s.charAt(j)=='1'){
            arrayOfLists[i].addVertex(new adjNode(j));
          }
      }
    }
      System.out.println("Depth-First Search:");
      boolean[] visit = new boolean[n];
      for(int k = 0 ; k< n ; k++){
        setFalse(visit);
        dfs(arrayOfLists, k, n , visit);
        System.out.println();
      }
      System.out.println("Breadth-First Search:");
      for(int z = 0 ; z < n ; z ++){
        Queue queue = new Queue(n);
        setFalse(visit);
        bfs(arrayOfLists, queue, z , visit);
        System.out.println();
      }
 }//end method readMatrix()
 
  //Depth first search
  //********************
  public static void dfs(adjList[] arrayList, int start, int length , boolean[] visit){
    System.out.printf("%2s " , start+1);
    adjNode current =  arrayList[start].firstNode;
    visit[start]=true;
    
    while(current != null){
      if(visit[current.vertex] == false ){
        dfs(arrayList, current.vertex, length, visit);
      }
      current= current.nextVertex;
    }
 }//end method dfs
  
  //Breadth first search
  //***********************
  public static void bfs(adjList [] arrayList, Queue queue, int start, boolean[] visit){
    adjNode current;
    int dq;
    System.out.printf("%2s ", start + 1 );
    visit[start]=true;
    queue.enqueue(start);
    
    while(!queue.queueIsEmpty()){
      dq = queue.dequeue();
      
      current= arrayList[dq].firstNode;
      while(current != null){
      if(visit[current.vertex]==false){
        System.out.printf("%2s " , current.vertex + 1 );
        visit[current.vertex] = true;
        queue.enqueue(current.vertex);
       }
      current= current.nextVertex;
    }
  }
 }
  //method to make the array false
  public static boolean[] setFalse(boolean array[]){
    for(int i=0; i < array.length; i ++){
      array[i]=false;
    }
    return array;
 }//end method setfalse
  
}//end class p2
  