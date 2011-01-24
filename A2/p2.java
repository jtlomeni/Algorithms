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

/*========================================
 * MAIN METHOD
 * ===================================*/
class p2{
  static Scanner console = new Scanner(System.in);
  public static void main(String [] args){
    int vertexFlag;
    while(console.hasNext()){
    int n = console.nextInt();
    System.out.println(n);
    readMatrix(n);
   }
  }

  
  public static void readMatrix(int n){
   
    adjList[] arrayOfLists = new adjList[n];
    for(int c=0 ; c<n ;c++){
     arrayOfLists[c]= new adjList(); 
    }
      for(int i = 0 ; i < n  ; i++){
        String s = console.next();
        System.out.println(s);
        for(int j = 0; j < n   ; j++){
          if(s.charAt(j)=='1'){
            System.out.println(s.charAt(j));
            arrayOfLists[i].addVertex(new adjNode(j));
          }
      }
    }
      
  }//end method readMatrix()
}//end main method
  