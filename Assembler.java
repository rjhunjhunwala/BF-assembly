/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brains;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author rohan
 */
public class Assembler {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.print("FileName:");
		String fileName = new java.util.Scanner(System.in).nextLine();
		File file = new File(fileName);
		ArrayList<String> prog = new ArrayList<>();
		java.util.Scanner s = new java.util.Scanner(file);
		while (s.hasNextLine()) {
			prog.add(s.nextLine());
		}
		//, = in . = out +/- inc dec <> move pointer [] while ptr
		System.out.println("Source Code ===============================");
		for (String s1 : prog) {
			System.out.println(s1);
		}
		System.out.println("===========================================");
		String out = "";
		System.out.println("Generated Code ============================");
		for (int i = 0; i < 510; i++) {
			out += ">+";
		}
		out += "";
		out += "[<]";
out+=">+++++++++<";
		for (int i = 0; i < 51; i++) {
			out += ">>>>>>>>>->-";
		}
		for (int i = 0; i < 510; i++) {
			out += "<";
		}
		int lastPrinted = 0;
		int x;
		for (String line : prog) {
			String[] tokens = line.split(" ");
			switch (tokens[0]) {
				case "print":
					for (char c : line.substring(6).replaceAll("NEWLINE","\n").replaceAll("SPACE"," ").toCharArray()) {
int dif = c - lastPrinted;
	for(int i = 0;i<Math.abs(dif);i++){
		out+=(dif>0?"+":"-");
	}

						out += ".";
						lastPrinted = c;
					}

					break;
				case "printSpace":
					out+="[-]";
					x = ' ';
					for (int i = 0; i < x; i++) {
						out += "+";
					}
					out += ".[-]";
					lastPrinted = 0;
					break;
				case "printLine":
					out+=">.<";
					break;
				case "set":
					int spot = Integer.parseInt(tokens[1]);
					int value = Integer.parseInt(tokens[2]);
					for (int i = 0; i < spot; i++) {
						for (int j = 0; j < 10; j++) {
							out += ">";
						}
					}
					out += "<[-]";
					for (int i = 0; i < value; i++) {
						out += "+";
					}
					out += ">";
					for (int i = 0; i < spot; i++) {
						for (int j = 0; j < 10; j++) {
							out += "<";
						}
					}
			
					break;
				case "for":
					int index = Integer.parseInt(tokens[1]);
					String output = (tokens.length==2?" ":tokens[2].replaceAll("NEWLINE","\n").replaceAll("SPACE"," "));
				System.out.print(output);
					for (int i = 0; i < index; i++) {
						for (int j = 0; j < 10; j++) {
							out += ">";
						}
					}
					out += "<<-<->>";
					out += "[<+<+>>-]";
					out += "<<";
					out += "[<<<<";
					for (char c : output.toCharArray()) {
						for (int i = 0; i < c - 1; i++) {
							out += "+";
						}
						out += ".[-]+";
					}
	
					out += ">>>>-]>[>+<-]+<+>>>";
														for (int i = 0; i < index; i++) {
						for (int j = 0; j < 10; j++) {
							out += "<";
						}
					}
					break;
				case "rawBF":
					out += tokens[1];
break;
				case "ld":
					for(int i = 0;i<512;i++){
						out+=">";
					}
					//Haley's Comment
					out+="#,#[>#<[<]<<#+#>>>[>]#,#]<[<]";
					//They dont call it Brainf*** for nothing ^ that reads stdin and loads the string as
					//ascii code points starting at 512
					//it also automagically loads the length of the string into heap spot 51
					//Tape assuming input of ascii byte values 66-68
					//0 1 -   508    509 510 511 512  513  514   515 516  517 518  .  .
					//0 1111111100...  4   0   0  66   67   68    69  0    0   0   .  .

					out+="[<]";
									for(int i = 0;i<511;i++){
						out+="<";
					}
				out+="#";//whew we're back at  index 0 on the tape!
			break;
			case "cat"://cats out input only after its been loaded
			for(int i = 0; i<512;i++){
				out+=">";
			}
			out+="[.>]";//cat out the string
				out+="<[<]";//"slide" down to index 511
				for(int i = 0;i<511;i++){
					out+="<";//get back to the start
				}
			out+="#";//safe at index 0!
				break;
			}
		}
		System.out.println(out);
		System.out.println("=========================================");
		writeToFile("out.txt", out);
		System.out.println("The generated program was saved to out.txt");
	}

	public static void writeToFile(String fileName, String program) {

		BufferedWriter output = null;
		try {
			File aFile = new File(fileName);
			FileWriter myWriter = new FileWriter(aFile);
			output = new BufferedWriter(myWriter);

			output.write(program);

			output.close();//file must be closed when you are done
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
}
