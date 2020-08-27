import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;


public class RelNgram {

public static void main(String[] args)
{
     File folder = new File("E:\\RELs\\");
File[] listOfFiles = folder.listFiles();
	//String filePath1 = "F:\\G-DRelWords.txt";
    BufferedReader br,br1;
    String line = "";
	
	

    try {
	for (File file : listOfFiles) 
			{
         br = new BufferedReader(new FileReader(file));
		 String srn=file.getName();
				srn = srn.replaceFirst("[.][^.]+$", "");
				srn=srn+"RELNgram";
				FileOutputStream f = new FileOutputStream("E://"+srn+".txt");
		PrintStream ps = new PrintStream(f);
		
	        try {
            while((line = br.readLine()) != null)
             {
			 ps.print(line+" ");	
			String [] gene =line.split("<GENE>");
			String [] generev =line.split("</GENE>");
			String [] dis =line.split("<DISEASE>");
			String [] disrev =line.split("</DISEASE>");
			
			if(gene[0]!=null)
			{
			 String [] res=gene[0].split(" ");
			 int count=0;
			
			for(int i=res.length-1;i>=0;i--)
			{
			count++;
			if(count<5)
			{
				String rul="<GW-"+count+">"+res[i]+"</GW-"+count+">";
				ps.print(rul);	
				}
			   
			}
			}
			if(generev[1]!=null)
			{
				String [] res1=generev[1].split(" ");
			int count=0;
			for(int i=0;i<res1.length;i++)
			{
			count++;
			if(count<5)
			{
				String rul="<GWR-"+count+">"+res1[i]+"</GWR-"+count+">";
				ps.print(rul);
				}
			   
			}
			}
			
			if(dis[0]!=null)
			{
				String [] res2=dis[0].split(" ");
			int count=0;
			for(int i=res2.length-1;i>=0;i--)
			{
			count++;
			if(count<5)
			{
				String rul="<DW-"+count+">"+res2[i]+"</DW-"+count+">";
				ps.print(rul);
				}
			   
			}
			}
			if(disrev[1]!=null)
			{
				String [] res3=disrev[1].split(" ");
			int count=0;
			for(int i=0;i<res3.length;i++)
			{
			count++;
			if(count<5)
			{
				String rul="<DWR-"+count+">"+res3[i]+"</DWR-"+count+">";
				ps.print(rul);	
			}
			   
			}
			}
						
			
			ps.println();	
				//String [] result1 =result[2].split("\t",2);
													
											
				
				}									
            
			 br.close();
			ps.close();
			
		
        } catch (IOException e) {
            
            e.printStackTrace();
								}
		
   }
    
    } catch (FileNotFoundException e) {
       
        e.printStackTrace();
									  }

  }
}