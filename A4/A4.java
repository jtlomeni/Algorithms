/*
 * John Lomenick
 * CSCI 433
 * Dr. Rice
 * 12 April 2010
 * */

import java.util.*;
import java.io.*;

/*********************************
 * Class for node for each state
 * ******************************/
 
class stateNode{
 public int city_count;
 public String state;
 public stateNode nextState;
 
 public stateNode(String st){
  city_count = 1;
  state = st;
  nextState = null;
 }
 
 public void addCity(){
  city_count++; 
 }
 
 public void printState(){
  System.out.print( city_count + " cities in "); 
 }
 
}//end class stateNode

/*****************************************
 * Class for Linked Lists for Collissions
 * **************************************/
class stateList{
  public stateNode firstNode;
  public stateNode lastNode;
  
  public stateList(){
   firstNode = null;
   lastNode = null;
  }
  
  public boolean isEmptyStateList(){
   return firstNode==null; 
  }
  
  public boolean stateExists(String st){
   stateNode current = firstNode;
   
   while(current!=null){
     if(st.equals(current.state)){
      return true;
     }
     current = current.nextState;
   }
   return false;
  }//end stateExists
  
  public void insertState(stateNode new_state){
    if(isEmptyStateList()){
     firstNode=new_state;
     lastNode=new_state;
    }else if(stateExists(new_state.state)){
      stateNode current = firstNode;
      while(current!=null){
        if(current.state.equals(new_state.state)){
         current.addCity(); 
        }
        current= current.nextState;
      }
    }
    else{
      new_state.nextState= firstNode.nextState;
      firstNode.nextState = new_state;
    }
  }//end insertState
  
  // prints every team of list
 public void printList(){
  stateNode current=firstNode;
  while(current != null){
   current.printState();
   System.out.println();
   current=current.nextState;
  }

 }
 
 //prints called state in list
 public void printThisState( String st){
  stateNode current = firstNode;
  while(current!=null){
    if(current.state.equals(st)){
     current.printState();
     return;
    }
    current = current.nextState;
    if(current==null){                           //special case where state doesn't exist
     System.out.print("0 cities in "); 
    }
  }
 }
  
}//end class stateList

/***************************
  * Main Class
  * **********************/
class A4{
  static final int PRIME = 211;
  public static void main(String [] args){
   
    //fill table with lists
    stateList[] hashTable = new stateList[PRIME];
    for(int i = 0; i<PRIME; i++){
     hashTable[i]= new stateList(); 
    }
    
    try{
      Scanner city_in = new Scanner(new FileReader("A4_Cities.txt"));
      
    
      while(city_in.hasNext()){
        String line = city_in.nextLine();
        String city =  line.substring( 0, 13);
        city=city.trim();
        String states = line.substring(15);
        for(int i = 0; i< states.length(); i+=3){
          String st_abbr = states.substring(i, i+2);
          hashTable[hash(st_abbr)].insertState(new stateNode(st_abbr));
            
        }
   
      }
    }catch(FileNotFoundException FNF){
     System.out.println("File not found"); 
    }
    
    
    //print in alphabetical order
    try{
      Scanner state_in= new Scanner(new FileReader("A4_States.txt"));
      
      while(state_in.hasNext()){
       String state_short = state_in.next();
       String state_full =state_in.nextLine();
       hashTable[hash(state_short)].printThisState(state_short);
       System.out.println(state_full);
      }
      
    }catch(FileNotFoundException FNF){
      System.out.println("File not found");
    }
    
    
      
  }//end main
  
  /*
   * HASH FUNCTION
   * */
  public static int hash(String st){
   int sum = (int)st.charAt(0) + (int)st.charAt(1);
   return sum % PRIME;
  }

}