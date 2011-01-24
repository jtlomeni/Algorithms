import java.util.*;
import java.io.*;

class A3{
 static Scanner console = new Scanner(System.in);
 public static int[] c;
 public static void main(String [] args){
  char algIn = args[0].charAt(0);
  int n = console.nextInt();
  c = new int[n];
  readArray(n, algIn);
 }//end main
 
 public static void readArray(int n, char c){
   float startTime;
   float endTime;
   int[] arrayIn= new int[n];
   int i = 0;
   while(console.hasNext()){
     arrayIn[i] = console.nextInt();
     i++;
   }//end while
   if(c == 'i'){
     System.out.println("Insertion Sort:");
     startTime = System.currentTimeMillis();
     insertionSort(arrayIn , n);
     endTime =System.currentTimeMillis();
   
     printArray(arrayIn, n);
   
   }else if(c == 'm'){
     System.out.println("Merge Sort:");
     mergeSort(arrayIn, 0, n-1);
     printArray(arrayIn, n);
   }else{
     System.out.println("Invalid Input"); 
   }//end for
 }//end readArray
 
 public static void insertionSort(int[] array, int n){
   int value = array[0];
   int j = 0;
   for(int i = 1; i < n; i++){
     value = array[i];
     j = i-1;
     while((j >= 0) && (value < array[j])){
      array[j+1] = array[j];
      j = j-1; 
     }//end while
     array[j+1] = value;
    }//end for
   
 }//end InsertionSort
 
 public static void mergeSort(int[] array, int i, int j){
   if (i>=j){
    return;
   }
  int m = (i+j)/2;
  mergeSort(array, i , m);
  mergeSort(array, m+1, j);
  merge(array , i , m , j);
 }
 public static void merge(int[] array, int i, int m, int j){
  int p = i;
  int q = m+1;
  int r = i;
  while((p<= m) && (q<=j)){
    if(array[p]<=array[q]){
      c[r++]=array[p++];
    }else{
      c[r++]= array[q++];
    }//end if
  }//end while
  
  while(p<=m){
   c[r++]=array[p++]; 
  }//end while
  
  while (q<=j){
   c[r++]= array[q++]; 
  }//end while
  
  for(int f = i; f<= j ; f++){
   array[f] = c[f]; 
  }//end for
 }//end merge
 
 public static void printArray(int [] array, int n){
   for(int k = 0; k< n; k++){
    System.out.println(array[k]); 
   }//end for
 }
 
}//end class A3


