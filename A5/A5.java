import java.util.*;
import java.io.*;


class A5{
  static Scanner console = new Scanner(System.in);
  public static int count = 0;
  public static void main(String[] args){
    try{
      String key = args[0]; //get word to search for
      try{
       String specifier1=args[1];  //specifier found
       try{
         String specifier2=args[2]; //check for second specifier
         if(!(specifier2.equals("-i") || specifier2.equals("-w"))){
         System.out.println("Invalid Input");
         return;
         }
         System.out.println(" Text Search: '" + key + "'\n Ignore Case \n Word Only\n");
         search('b', key);
         System.out.println("\n" + count + " matches found");
         return;
       }catch(ArrayIndexOutOfBoundsException ii){}
       if(!(specifier1.equals("-i") || specifier1.equals("-w"))){
         System.out.println("Invalid Input");
         return;
       }
       if(specifier1.equals("-i")){
        System.out.println(" Text Search: '" + key + "'\n Ignore Case\n");
        search('i', key);
        System.out.println("\n" + count + " matches found");
        return;
       }
       if(specifier1.equals("-w")){
        System.out.println(" Text Search: '" + key + "'\n Word Only\n");
        search('w', key);
        System.out.println("\n" + count + " matches found");
        return;
       }
      }catch(ArrayIndexOutOfBoundsException ar){}
      System.out.println(" Text Search: '" + key + "'\n");
      search('n', key);
      System.out.println("\n" + count + " matches found");
      return;
    }catch(ArrayIndexOutOfBoundsException ari){
     System.out.println("No pattern Specified");
     return;
    }
   
  }//end main method
  
  public static String readLine(){
    return console.nextLine();
  }
  
  public static boolean textSearch(String line,String key){
   char [] line_array = line.toCharArray();
   char [] key_array = key.toCharArray();
   for(int i = 0; i<(line.length()-key.length());i++){
    int j = 0;
    while(j < key.length() && key_array[j]==line_array[i+j]){
     j++; 
    }//end while
    
    if(j==key.length()){
      count++;
      for(int k = 0;k<i;k++){
       System.out.print(" "); //print apporpriate number of spaces on next line
      }//end for
      System.out.print("^");  //print the ^ to point to the start of the match
      line = line.substring(i+1);
      if(line.length()<key.length()){
       return true; 
      }
      textSearch(line, key);
      return true;
    }//end if
   }//end for
   return false;
  }//end textSearch()
  
  public static boolean textSearchIgnoreCase(String line, String key){
   char [] line_array = line.toLowerCase().toCharArray();
   char [] key_array = key.toLowerCase().toCharArray();
   for(int i = 0; i<(line.length()-key.length()); i++){
    int j= 0;
    while(j<key.length() && key_array[j] == line_array[i+j]){
     j++; 
    }//end while
    if(j==key.length()){
     count++;
     for(int k = 0; k<i; k++){
       System.out.print(" "); //print apporpriate number of spaces on next line
     }//end for
     System.out.print("^");  //print the carrot pointing to the start of the match
     line = line.substring(i+1);
     if(line.length()< key.length()){
      return true; 
     }
     textSearchIgnoreCase(line, key);
     return true;
    }//end if
   }//end for
   return false;
  }//end textSerachIgnoreCase();
  
  public static boolean textSearchWord(String line, String key){
    StringTokenizer st = new StringTokenizer(line, " ");
    String current;
    boolean flag = false;
    if(hasMatch(line,key)){
    while(st.hasMoreTokens()){
      current = st.nextToken();
      int length = current.length();
      if(current.endsWith(",")||current.endsWith(".")||current.endsWith("?")||current.endsWith(";")||current.endsWith(":")){
       current = current.substring(0,current.length()-1) ;
      }
      if(current.equals(key)){
        System.out.print("^");
        count++;
        flag = true;
        for(int i = 0; i< length; i++){
          System.out.print(" ");
         }
      }else{
        for(int i = 0; i< length+1; i++){
          System.out.print(" ");
        }
     
      }
    }
    }
    
   
    return flag;
  }
  
  public static boolean textSearchWordIgnoreCase(String line, String key){
   StringTokenizer st = new StringTokenizer(line, " ");
    String current;
    boolean flag = false;
    if(hasMatchIgnoreCase(line , key)){
    while(st.hasMoreTokens()){
      current = st.nextToken();
      int length = current.length();
      if(current.endsWith(".")||current.endsWith(",")||current.endsWith("?")||current.endsWith(";")||current.endsWith(":")){
       current = current.substring(0,current.length()-1) ;
      }
      if(current.equalsIgnoreCase(key)){
        System.out.print("^");
        count++;
        flag = true;
        for(int i = 0; i< length; i++){
          System.out.print(" ");
         }
      }else{
        for(int i = 0; i< length+1; i++){
          System.out.print(" ");
        }
     
      }
    }
    }
    return flag;
  }
  
  public static void search(char t, String key){
     while(console.hasNext()){
      String line = readLine();
      System.out.println(line);
      switch(t)
      {
        case 'n':
          if(textSearch(line,key)==true){
          System.out.println();
        }
          break;
        case 'i':
          if(textSearchIgnoreCase(line,key)==true){
          System.out.println();
        }
          break;
        case 'w':
          if(textSearchWord(line,key)==true){
          System.out.println();
        }
          break;
        
        case 'b':
          if(textSearchWordIgnoreCase(line, key)==true){
          System.out.println();
         
        }
         break;
        
      }//end switch
     
    }//end while
    
  }//end search()
  
  public static boolean hasMatch(String line, String key){
   StringTokenizer st = new StringTokenizer(line);
   boolean flag = false;
   String current;
   while(st.hasMoreTokens()){
     current = st.nextToken();
     if(current.endsWith(",")||current.endsWith(".")||current.endsWith("?")||current.endsWith(";")||current.endsWith(":")){
       current = current.substring(0,current.length()-1) ;
      }
     if(current.equals(key)){
      flag = true; 
     }
   }
   return flag;
  }
  
  public static boolean hasMatchIgnoreCase(String line, String key){
   StringTokenizer st = new StringTokenizer(line);
   boolean flag = false;
   String current;
   while(st.hasMoreTokens()){
     current = st.nextToken();
     if(current.endsWith(",")||current.endsWith(".")||current.endsWith("?")||current.endsWith(";")||current.endsWith(":")){
       current = current.substring(0,current.length()-1) ;
      }
     if(current.equalsIgnoreCase(key)){
      flag = true; 
     }
   }
   return flag;
  }

  
  
  
}