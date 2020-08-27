import java.io.*;
import java.text.*;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import opennlp.tools.sentdetect.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import opennlp.tools.cmdline.BasicCmdLineTool;
import opennlp.tools.cmdline.CLI;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import edu.ucdenver.ccp.nlp.biolemmatizer.BioLemmatizer;
import opennlp.tools.cmdline.BasicCmdLineTool;
import opennlp.tools.cmdline.CLI;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.postag.POSModel;
import opennlp.tools.chunker.*;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
public class DnerTag  {

public static void main(String[] args) throws IOException
{
		InputStream is = new FileInputStream("en-sent.bin");
		InputStream modelIn = new FileInputStream("en-pos-maxent.bin");
		InputStream modelchunk = new FileInputStream("en-chunker.bin");
		BioLemmatizer bioLemmatizer = new BioLemmatizer();
		SentenceModel smodel = new SentenceModel(is);
		POSModel model= new POSModel(modelIn);
		ChunkerModel cmodel = new ChunkerModel(modelchunk);
		POSTaggerME tagger = new POSTaggerME(model);
		ChunkerME chunker = new ChunkerME(cmodel);
         ObjectStream<String> lineStream = null;
			PrintStream printer = null;
			String lines = null;
			String line = null;
			String sentence = null;
		SentenceDetectorME sdetector = new SentenceDetectorME(smodel);
		String fnname="F:\\TaggerCode\\COMAG.txt";
		BufferedReader reader = new BufferedReader(new FileReader(fnname));
		
		
   
		 while((line = reader.readLine()) != null)
            {
		
			String sentences[]=sdetector.sentDetect(line);
			for(int j=0;j<sentences.length;j++)
			{
			sentence=sentences[j];
			
			 StringTokenizer st = new StringTokenizer(sentence," \t\n\r\f`~!@#$%^&*()-–=_+[]\\{}|;':\",./<>?",true);
     
 
		//System.out.println("---- Split by space ------");
		while (st.hasMoreTokens()) {
		String token = st.nextToken();
		if(token.equals(" ")) continue;
			//System.out.println(token);
			lines=token;
		
      
        if (lines.isEmpty()) {
          System.out.println();
        } else if (lines.startsWith("//")) {
          System.out.println(line);
        } else {
          		  String[] sent={lines};
				  String[] tags = tagger.tag(sent);
		 
		  for (int i=0;i<sent.length;i++){
		  //System.out.println(sent[i]+"\t"+tags[i]);
		  //System.out.println(tags[i]);
		  
		  String Lemmin=sent[i]+"\t"+tags[i];
         String lemma;
		 
  //      perfMon.incrementCounter();
      if(Lemmin.isEmpty()){
			System.out.println();
			}
			else{
			String lsentences[]=Lemmin.split("\t");
			lemma = bioLemmatizer.lemmatizeByLexiconAndRules(lsentences[0], lsentences[1]).lemmasToString();
			//System.out.println(Lemmin+"\t"+lemma);
			String Chunkin=Lemmin+"\t"+lemma;
			if (Chunkin.isEmpty()) {
          printer.println();
        } else if (Chunkin.startsWith("//")) {
          printer.println(Chunkin);
        } else {
          //String[] sent = WhitespaceTokenizer.INSTANCE.tokenize(line);
		  String s[]=Chunkin.split("\t");
		  String[] tok={s[0]};
		  String[] pos={s[1]};
			String ctags[] = chunker.chunk(tok, pos);
		
		  for (int k=0;k<tags.length;k++){
		  System.out.println(s[0]+"\t"+s[1]+"\t"+s[2]+"\t"+ctags[k]);
		  //printer.println();
		  }
          //printer.println(sample.toString());
          //printer.println(sample.toString());
        }
			}
			}
			}
		
		
		}
		is.close();
		System.out.println();
		}
}
		}


}