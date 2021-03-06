/* John Lomenick
 * Assignment 1
 * CSCI 433
 * 16 February 2010
 * 
 */


import java.util.*;

/*==========================
 * NODE CLASS FOR EACH TEAM
 * ========================
 */
class TeamNode{
  String team;
  public int wins;
  public int losses;
  public int ties;
  public TeamNode nextTeam;
  public GameNode firstGame;
  
  //Empty Node constructor
  public TeamNode(){
    nextTeam=null;

  }
  //Node Intializer
  public TeamNode(String team_name){
    team=team_name;
    wins=0;
    losses=0;
    ties=0;
    firstGame=null;
  }
  
  public void addWin(){
   wins+=1; 
  }
  public void addLoss(){
   losses+=1; 
  }
  
  public void addTie(){
   ties+=1; 
  }
  
  //Print team and its games
  public void printTeam(){
   GameNode currentG=firstGame;
   System.out.println(team + ": " + wins + "-" + losses + "-" + ties);
   while(currentG.nextGame!=null){
    currentG.printGame();
    currentG=currentG.nextGame;
  }
   currentG.printGame();
 }
}


/*==========================
 * NODE CLASS FOR EACH GAME
 * =========================
 */
class GameNode{
  char result;
  String opponent;
  int score1;
  int score2;
  GameNode nextGame;
  
  //game node constructor
  public GameNode(char results, String opponents, int score1s, int score2s){
   result=results;
   opponent=opponents;
   result = results;
   score1=score1s;
   score2=score2s;
   nextGame=null;
  }
  //print contents of current game
   public void printGame(){
    System.out.println("" + result + " " + score1 + "-" + score2 + " " + opponent + ""); 
   }
   
}


/*=========================
 * CLASS FOR LIST OF TEAMS
 * =======================
 */
class TeamList{
 private TeamNode first;
 private GameNode firstG;
 private GameNode lastG;

 

 //checks to see if the game list of current team is empty 
 public boolean isEmptyGame(TeamNode team){
   return team.firstGame==null; 
  }
  
 //inserts game at the end of the game list of current team
  public void insertGame(TeamNode currentTeam, char result, String opponent, int score1, int score2){
    GameNode new_game = new GameNode(result,opponent, score1, score2);
    GameNode currentG;
    if(isEmptyGame(currentTeam)){
      currentTeam.firstGame=new_game;
      lastG=new_game;
    }else{
      currentG=currentTeam.firstGame;
      while(currentG.nextGame!=null){
        currentG=currentG.nextGame;
      }
      currentG.nextGame=new_game;
    }
  }
 

 public TeamList(){
  first=null;
 }
 
 //checks to see if list is empty
 public boolean isEmpty(){
  return first==null;
 }
 
 //checks to see if team exists in teamlist
 public boolean teamExists(String team_name){
  TeamNode current = new TeamNode();
  boolean flag=false;
  current=first;
  while(current!=null){
    if(team_name.equals(current.team)){
      flag = true;
    }
    current=current.nextTeam;
 }
  return flag;
}
 
 //Inserts team into list alphabetically
 public TeamNode insertTeam(String team_name){
   TeamNode team_node= new TeamNode(team_name);
   TeamNode current = new TeamNode();

  //empty list case
   if(isEmpty()){
     first=team_node;
     return first;
   }else if(!teamExists(team_name)){
   //case where team needs to be inserted first
     
     if(first.team.compareToIgnoreCase(team_name)>0){
       team_node.nextTeam=first;
       first=team_node;
       return first;
       //team does not exists and it does not belong first
     }else{
       current=first;
       while((current.nextTeam!=null) && (current.nextTeam.team.compareTo(team_node.team) < 0)) {
         current=current.nextTeam;
       }
       
       team_node.nextTeam=current.nextTeam;
       current.nextTeam=team_node;
       return team_node;
     }
     //team exists so it just returns the node of that team
   }else{
     current=first;
     while(current.team.compareTo(team_name)!=0){
       current=current.nextTeam;
     }
     return current;
     
   }
}
 
// prints every team of list
 public void printList(){
  TeamNode currentTeam=first;
  while(currentTeam.nextTeam!= null){
   currentTeam.printTeam();
   System.out.println();
   currentTeam=currentTeam.nextTeam;
  }
  currentTeam.printTeam();
 }
}


//main method
class A1revised{
  static Scanner console = new Scanner(System.in);
  public static void main(String[] args){
    String team1;
    String team2;
    int score1;
    int score2;
    char result1=' ';
    char result2=' ';
    TeamNode currentTeamNode;
    TeamList team_list = new TeamList(); 
    
    //loop to get each game and insert teams and games into list
    while(console.hasNext()){            
     team1=console.next();  
     score1=console.nextInt();     
     team2=console.next();
     score2=console.nextInt();
     if(score1>score2){
      result1='W';
      result2='L';
     }else if(score1<score2){
       result1='L';
       result2='W';
     }else if(score1==score2){
       result1='T';
       result2='T';
     }
     
     
     currentTeamNode=team_list.insertTeam(team1);
    
     if(result1=='W'){
      currentTeamNode.addWin(); 
      team_list.insertGame(currentTeamNode, result1, team2, score1, score2);
     }else if(result1=='L'){
      currentTeamNode.addLoss();
      team_list.insertGame(currentTeamNode, result1, team2, score1, score2);
     }else if(result1=='T'){
      currentTeamNode.addTie();
      team_list.insertGame(currentTeamNode, result1, team2, score1, score2);
     }
   
     currentTeamNode=team_list.insertTeam(team2);
     
     if(result2=='W'){
      currentTeamNode.addWin(); 
      team_list.insertGame(currentTeamNode, result2, team1, score2, score1);
     }else if(result2=='L'){
      currentTeamNode.addLoss();
      team_list.insertGame(currentTeamNode, result2, team1, score2, score1);
     }else if(result2=='T'){
      currentTeamNode.addTie();
      team_list.insertGame(currentTeamNode, result2, team1, score1, score2);
     }
    }//end loop
    
    
    team_list.printList();
  }
  
}



