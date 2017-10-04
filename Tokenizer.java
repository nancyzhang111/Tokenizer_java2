import java.util.ArrayList;
import java.lang.String;
import java.util.Scanner;
//import java.io.PrintStream;
//import java.io.IOException;
import java.io.*;

public class Tokenizer{
	private String path;
	//private String wholeFile;
	private ArrayList<String> lines = new ArrayList<String>();
	private ArrayList<String> words = new ArrayList<String>();
	private int count = 0;
	//private PrintStream out = new PrintStream(System.out,true,"UTF-8");

	public Tokenizer(String fileName){
		this.path = fileName;
	}

	public void readFile(){
		//System.out.print("Start reading");
		try{
			FileInputStream fileReader = new FileInputStream(path);
			//FileReader fr = new FileReader(path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileReader,"UTF-8");
			BufferedReader readIn = new BufferedReader(inputStreamReader);
			//System.out.print("File opens.\n");

			String line = null;
			line = readIn.readLine();
			while(line!=null){
				//StringBuffer whole = new StringBuffer();
				//whole.append(line);
				//whole.append("\n");
				this.lines.add(line);
				line = readIn.readLine();
			}

			//this.wholeFile = whole.toString;
			//System.out.print("read in the memory.\n"); 
		}
		catch(IOException e){
			System.err.print("A I/O error happens.\n");
		}
	}//read into memory

	public void splitWord(){
		for (int i = 0;i < this.lines.size();i ++) {
			String[] wordsInLine = this.lines.get(i).split("\\.|,|:|\\[|\\]|\\(|\\)|\\+|\\-|\"|!|\\?|\\%|;| ");
			
			for (int j = 0;j < wordsInLine.length;j ++) {
				
				if (!wordsInLine[j].equals("")) {
					char[] c1 = wordsInLine[j].toCharArray();
					if (c1[0] == '\'') {
						wordsInLine[j] = wordsInLine[j].substring(1);
					}
					//remove the ' at begining
					if (c1[c1.length-1] == '\''&& !wordsInLine[j].equals("")) {
						//System.out.printf("%s\n",wordsInLine[j]);
						wordsInLine[j] = wordsInLine[j].substring(0,wordsInLine[j].length()-1);
					}
					//remove the ' at the end
					if (!wordsInLine[j].equals("")) {
						this.words.add(wordsInLine[j].toLowerCase());
					}
				}
				
			}
		}
	}

	public String nextToken(){
		try{
			String code = new String(this.words.get(this.count).getBytes("UTF-8"),"UTF-8");
			count++;
			return code;
		}catch(Exception e){
			System.err.print("UTF-8?");
			count++;
			return null;
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		String name = input.next();
		Tokenizer test = new Tokenizer(name);
		test.readFile();
		test.splitWord();
		for (int i = 0;i < test.words.size();i ++) {
			System.out.printf("%s\n",test.nextToken());
		}
	}
}
