import java.io.*;
import java.util.*;

/*
 * This project deals with building an inverted index for a given collection of 
 * 2,916 emails. Each email was modeled as a “Bag of Words”: a set of words present
 * in the email along with its frequency of occurrence. Some neutral words such as ‘the’, ‘a’, 
 * ‘an’ have been removed. The compressed archive consists of 2,916 files. Each of these files 
 * consists of a list of words (one per line) along with their frequency count.
 *
 * Builds a dictionary using all documents.  The terms will be ordered alphabetically and stored
 * in a text file.
 *
 * Constructs postings for each term in the dictionary
 * Each posting includes the term frequency along with each DocID
 *
 * includes method getpostings(), which returns postings for a given input term.
 */
 
/*
 * ==========================================
 * Class for each term of linked list
 * ==========================================
 * 
 */
class DictionaryTerm{
 public String term;
 public int numDocs;
 public int frequency;
 public DictionaryTerm nextTerm;
 public DocNode firstDoc;

 
 public DictionaryTerm(){
  nextTerm=null; 
 }
 public DictionaryTerm(String new_term, int freq){
  term=new_term;
  frequency = freq;
  numDocs=1;
  firstDoc=null;
 }
 public String printTerm(){
  return term; 
 }
 public int printNumDocs(){
  return numDocs; 
 }
 
 public void addDoc(){
  numDocs+=1; 
 }
 public void addFreq(int freq){
  frequency+=freq; 
 }
 
}
/*
 * 
 * ==========================================
 * CLASS TO CREATE AND ADD TERMS TO DICTONARY
 * ==========================================
 */

class DictionaryList{
 private DictionaryTerm firstTerm;
 private DocNode lastDoc;

 
 public DictionaryList(){
  firstTerm=null; 
 }
 
 public boolean isEmptyDictionary(){
  return firstTerm==null; 
 }
 
 public boolean isEmptyDocList(DictionaryTerm currentTerm){
  return currentTerm.firstDoc==null; 
 }
 
 public DictionaryTerm termExists(String term){
  DictionaryTerm current = new DictionaryTerm();
  current=firstTerm;
  while(current!=null){
    if(term.equals(current.term)){
      return current;
    }
    current=current.nextTerm;
  }
  return null;
 }
 
 public DictionaryTerm insertTerm(String new_term, int freq){
   DictionaryTerm term= new DictionaryTerm(new_term, freq);
   DictionaryTerm current= new DictionaryTerm();
   DictionaryTerm existingTerm= new DictionaryTerm();
   if(isEmptyDictionary()){
     firstTerm=term;
     return term;
   }else if((existingTerm=termExists(new_term))==null){
     
     if(firstTerm.term.compareTo(new_term)>0){
      term.nextTerm=firstTerm;
      firstTerm=term;
      return term;
     }else{
      current = firstTerm;
      while((current.nextTerm!=null) && (current.nextTerm.term.compareTo(new_term) < 0)) {
         current=current.nextTerm;
       }
      term.nextTerm=current.nextTerm;
      current.nextTerm=term;
      return term;
     }
   }else{
    existingTerm.addDoc(); 
    existingTerm.addFreq(freq);
    return existingTerm;
   }
 }
 
 public void insertDoc(DictionaryTerm currentTerm, int doc_number){
   DocNode new_doc = new DocNode(doc_number);
   DocNode current_doc;
   if(isEmptyDocList(currentTerm)){
     System.out.println("empty docs");
     currentTerm.firstDoc=new_doc;
     lastDoc=new_doc;
     System.out.println("DocID inserted:  " + doc_number);
   }
   else{
     System.out.println("doc list exists, adding to end");
     current_doc=currentTerm.firstDoc;
     System.out.println("first docID: " + current_doc.docID);
     while(current_doc.nextDoc!=null){
       System.out.println("This ID exists: " + current_doc.docID);
       current_doc=current_doc.nextDoc;
     }
     System.out.println("Inserting after this: " + current_doc.docID);
     current_doc.nextDoc=new_doc;
     //lastDoc.nextDoc=new_doc;
     //lastDoc=new_doc;
     System.out.println("Another DocID inserrted: " + doc_number);
   }
 }
 
 public void printDictionary(){
  DictionaryTerm current=firstTerm;
  int i =0;
  try{
    PrintWriter dictionary = new PrintWriter("dictionary.txt");
    while(current.nextTerm!=null){
      dictionary.println(current.printTerm() + " " + current.printNumDocs());
      current=current.nextTerm;
      i++;
    }
    dictionary.println(current.printTerm() + " " + current.printNumDocs());
    dictionary.close();
  }catch(FileNotFoundException FNFE){
   System.out.println("Output File not found"); 
    
  }
 }
 
 public void printPostings(){
  DictionaryTerm current=firstTerm;
  while(current!=null){
    try{
     String currentTerm= current.term;
     String filename="/Users/jtlomeni/Desktop/School Stuff/Information Retrieval/P1/output/";
     filename=filename.concat(currentTerm);
     filename=filename.concat(".txt");
     DocNode currentDocID=current.firstDoc;
     
     PrintWriter term = new PrintWriter(filename);
     term.println("Term     : " + current.term);
     term.println("Frequency: " + current.frequency);
     //System.out.println("Term     : " + current.term);
     //System.out.println("Frequency: " + current.frequency);
     while(currentDocID!=null){
       term.println(currentDocID.docID);
       currentDocID=currentDocID.nextDoc;

     }
    // term.println(currentDocID.docID);
    // System.out.println(currentDocID.docID);
     current=current.nextTerm;
     term.close();
    }
    catch(FileNotFoundException FNFE){
     System.out.println("Posting file not found"); 
    }
  }
 }
 
 public void getPostings(String term){
  DictionaryTerm current = firstTerm;
  while(current!=null){
    if (current.term.equals(term)){
     DocNode currentDocID= current.firstDoc;
     while(currentDocID!=null){
       System.out.println(currentDocID.docID);
       currentDocID=currentDocID.nextDoc;
     }
    }
    current=current.nextTerm;
 }
}
}

class DocNode{
  public int docID;
  public DocNode nextDoc;
  
  public DocNode(int id_number){
   docID= id_number; 
   nextDoc=null;
  }
  
  
}

class P1{

 public static void main(String[] args){
   int i;
   int j=0;
   String term_to_find;
   Scanner scan = new Scanner(System.in);
   
   int lastFile=2916;
   DictionaryList list = new DictionaryList();
   DictionaryTerm currentTerm;
   
   
  for(i=1;i<=lastFile;i++){
     
     int freq;
     String term;
     String filename;
     j=0;
     
     filename=Integer.toString(i);
     filename=filename.concat(".res");
     
     try{
       Scanner inFile = new Scanner(new FileReader(filename));
       while(inFile.hasNext()){
        term=inFile.next();
        freq=inFile.nextInt();
        currentTerm= list.insertTerm(term, freq);
        System.out.println("Inserting Doc #: " + i + " into " + currentTerm.term); 
        list.insertDoc(currentTerm, i);
        j++;
        
       }
     }
     catch(FileNotFoundException FNFE){
      System.out.println("File Not found"); 
     }
     
   }
   
   
  while(true){
   System.out.println("Enter the term for the postings you would like to see:");
   term_to_find=scan.next();
  list.getPostings(term_to_find);
  }
 }
}



