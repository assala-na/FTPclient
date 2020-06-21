package Test2;


import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Ftp {

   private String user;
   private String psw ;
   private Socket socket = null, socketData = null;
   private boolean DEBUG = true;
   private String host;
   private int port;
   
   private BufferedWriter writer;
   private BufferedInputStream readerData;
   private BufferedInputStream reader;
   private String dataIP;
   private int dataPort;

   
   public Ftp(String ipAddress, int pPort, String pUser ,String pswd){
      user = pUser;
      port = pPort;
      host = ipAddress;
      psw = pswd ; 
   }
   public Ftp(String pUser, String pswd){
      this("127.0.0.1", 21, pUser , pswd);      
   }
   
   /**
    * Méthode de connexion au FTP
    * @throws IOException
    */
   public void connect() throws IOException{
      //Si la socket est déjà initialisée
      if(socket != null)
      {
    	  showerror("connection already established");
           throw new IOException("La connexion est déjà activée");
      }
      //On se connecte
      socket = new Socket(host, port);
      

      //On crée object reader et writer pour communiquer
      reader = new BufferedInputStream(socket.getInputStream());
      writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      
      String response = read()    ;

      if(!response.startsWith("220")){  
    	  //showerror(response);
         throw new IOException("Erreur de connexion au FTP : \n" + response);
      }
      send("USER " + user);
      response = read();
      if(!response.startsWith("331"))  
      {  
         throw new IOException("Erreur de connexion avec le compte utilisateur : \n" + response);
      }
      
      String passwd =psw;
      send("PASS " + passwd);
      response = read();
      if(!response.startsWith("230"))  
      { 
    	  
    	  throw new IOException("Erreur de connexion avec le compte utilisateur : \n" + response);
      
      }
      
      
      
      if(!response.startsWith("530")){  
      	  showmsg("se connect");
          
        }
    	if(!response.startsWith("332"))  
        {  
      	  showmsg("besoin d'un compte pour le login");
           
        }
      
      
      
      
      
      if(!response.startsWith("120"))  
      { 
    	  showmsg("Service prêt dans nnn minutes. : \n");
    	  
      }
      if(!response.startsWith("211"))  
      { 
    	  showmsg("Statut du système, ou réponse d’aide du système.. : \n");
    	 
      }
      if(!response.startsWith("230"))  
      { 
    	  showmsg("Authentification réussie. : \n");
      }
      showmsg("logged on");
   }
   
   public void quit(){
      try {
    	  
         send("QUIT");
      } catch (IOException e) {
         e.printStackTrace();
      }  finally{
         if(socket != null){
            try {
               socket.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
            finally{
               socket = null;
            }
         }
      }
   }
   
   /**
    * @throws IOException
    */
   void enterPassiveMode() throws IOException{

     send("PASV");
     String response = read();
     if(!response.startsWith("227"))  
     { 
   	  showmsg("mode passive. : \n");
     }
     
     
     if(DEBUG)
        affiche(response);
     String ip = null;
     int port = -1;
     
     
     int debut = response.indexOf('(');
     int fin = response.indexOf(')', debut + 1);
     if(debut > 0){
        String dataLink = response.substring(debut + 1, fin);
        StringTokenizer tokenizer = new StringTokenizer(dataLink, ",");
        try {
           
           ip = tokenizer.nextToken() + "." + tokenizer.nextToken() + "."
                   + tokenizer.nextToken() + "." + tokenizer.nextToken();
          
           port = Integer.parseInt(tokenizer.nextToken()) * 256
                + Integer.parseInt(tokenizer.nextToken());
           dataIP = ip;
           dataPort = port; 
          
        } catch (Exception e) {
        	
          throw new IOException("received bad data link information: " + response);
        }        
     }
   }
   
   /**
    * Méthode initialisant les flux de communications
    * @throws UnknownHostException
    * @throws IOException
    */
   private void createDataSocket() throws UnknownHostException, IOException{
      socketData = new Socket(dataIP, dataPort);
      readerData = new BufferedInputStream(socketData.getInputStream());
      new BufferedWriter(new OutputStreamWriter(socketData.getOutputStream()));
   }
   
   /**
    * Retourne l'endroit où nous nous trouvons dur le FTP
    * @return
    * @throws IOException
    */
   public String pwd() throws IOException{
      //On envoie la commande PWD 
      send("PWD");
      //On lit la réponse du serveur
      return read();
   }
   
   /**
    * Permet de changer de répertoire (Change Working Directory)
    * @return
    * @throws IOException
    */
   public String cwd(String dir) throws IOException{
      send("CWD " + dir);
      return read();
   }
   
   public String list() throws IOException{
      send("TYPE ASCII");      
      read();
      
      enterPassiveMode();
      createDataSocket();
      send("LIST");
      
      return readData();
   }
   public String modeASCII() throws IOException{
	      send("TYPE ASCII");      
	      return read();
   }
   
   public String upload(String file) throws IOException{
	      send("TYPE ASCII");      
	      read();
	      
	      enterPassiveMode();
	      createDataSocket();
	      send("STOR " + file);
	      
	     
	      
	      OutputStream o= socketData.getOutputStream();
		     RandomAccessFile ofile= new RandomAccessFile(file,"r");
		     FileInputStream filestream= new FileInputStream(ofile.getFD());
		     String response = "";
		      int stream, stream2, i=0;
		      byte[] b = new byte[4096];
		      while((stream=filestream.read(b))>0)
		      {
		      o.write(b, 0, stream);
		      i=i+stream;
		      }
		      
		       stream2 =reader.read(b);
		       response =new String (b, 0, stream2);
		       
		       String str=String.valueOf("the size of the uploaded file is : "+ i+" byte");
		       
		       if(DEBUG)
		       {	 
		    	   showmsg(response);
		    	   affiche(response);
		    	   affiche(str);
		    	   showmsg(str);
	   }
		       if(!response.startsWith("552"))  
			      { 
			    	  showerror(response);
			    	  throw new IOException("Requête arrêtée : Allocation mémoire insuffisante. : \n" + response);
			      }
		       if(!response.startsWith("250"))  
		       { 
		     	  showerror("Action sur le fichier exécutée avec succès. : \n");
		       }
		       
			      return response;  
	   }
	  
   
   public String download(String file) throws IOException{
	      send("TYPE ASCII");      
	      read();
	      
	      enterPassiveMode();
	      createDataSocket();
	      send("RETR " + file);
	      
	      int i=0;
		     InputStream in= socketData.getInputStream();
		     RandomAccessFile ofile= new RandomAccessFile(file,"rw");
		     FileOutputStream filestream= new FileOutputStream(ofile.getFD());
		     String response = "";
		      int stream, stream2;
		      byte[] b = new byte[4096];
		      while((stream=in.read(b))>0)
		      {
		      filestream.write(b, 0, stream);
		      i=i+stream;
		      }
		      System.out.print(i +"byte");
		       stream2 =reader.read(b);
		       response =new String (b, 0, stream2);
		       
		       
		       if(!response.startsWith("502"))  
			      { 
			    	  showerror(response);
			    	  throw new IOException("Action non effectuée. Nom de fichier non autorisé. : \n" + response);
			      }
		       
		       String str=String.valueOf("the size of the downloaded file is : "+ i+" octect");
		       if(DEBUG)
		       {	 
		    	   showmsg(response);
		    	   affiche(response);
		    	   affiche(str);
		    	   showmsg(str);
	 }
			      return response;  
	   }
   /**
    * 
    * Méthode permettant d'envoyer les commandes au FTP
    * @param command
    * @throws IOException
    */
   private void send(String command) throws IOException{
      command += "\r\n";
      if(DEBUG)
         affiche(command);
      writer.write(command);
      writer.flush();
   }
   
   /**
    * Méthode permettant de lire les réponses du FTP
    * @return
    * @throws IOException
    */
   private String read() throws IOException{      
	   String response = "";
	      int stream;
	      byte[] b = new byte[4096];
	      stream = reader.read(b);
	      response = new String(b, 0, stream);
	      

	      if(DEBUG)
	    	  affiche(response);
	      return response;  
   }
   
    
   public String deleteFile(String fileName)throws IOException
   {
       send("dele " + fileName);
       String response=read();
       if(!response.startsWith("553"))  
	      { 
	    	  showerror(response);
	    	  throw new IOException("Action non effectuée. Nom de fichier non autorisé. : \n" + response);
	      }
       return response;
   }
   
  
   
 
   
   /**
    * Méthode permettant de lire les réponses du FTP
    * @return
    * @throws IOException
    */
   private String readData() throws IOException{
      
      String response = "";
      byte[] b = new byte[1024];
      int stream;
      
      while((stream = readerData.read(b)) != -1){
         response += new String(b, 0, stream);
      }
      
      if(DEBUG)
    	  
    	  affiche(response);
      return response;
      
   }
   public void showerror(String response)
	{
	   
			JFrame frame = new JFrame("ERROR");
		    
		    // show a joptionpane dialog using showMessageDialog
		    JOptionPane.showMessageDialog(frame,
		        " ERROR :" + response + " ");
		   
		
	}
   public void showmsg(String response)
  	{
  	   
  			JFrame frame = new JFrame("FTP msg");
  		    
  		    // show a joptionpane dialog using showMessageDialog
  		    JOptionPane.showMessageDialog(frame,
  		        " " + response + " ");
  		   
  		
  	}
   public void debugMode(boolean active){
      DEBUG = active;
   }
   
   private void affiche(String str){
      System.out.println("  " + str);
   }
   
}


