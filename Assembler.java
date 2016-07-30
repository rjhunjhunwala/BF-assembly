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
		out += "#";
		out += "[<]#";

		for (int i = 0; i < 51; i++) {
			out += ">>>>>>>>>->-";
		}
		for (int i = 0; i < 510; i++) {
			out += "<";
		}
		out += "#";
		int x;
		for (String line : prog) {
			String[] tokens = line.split(" ");
			switch (tokens[0]) {
				case "print":
					for (char c : line.substring(5).toCharArray()) {
						for (int i = 0; i < c; i++) {
							out += "+";
						}
						out += ".[-]";
					}

					break;
				case "printSpace":
					x = ' ';
					for (int i = 0; i < x; i++) {
						out += "+";
					}
					out += ".[-]";
					break;
				case "printLine":
					x = '\n';
					for (int i = 0; i < x; i++) {
						out += "+";
					}
					out += ".[-]\n";
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
					out += "#>";
					for (int i = 0; i < spot; i++) {
						for (int j = 0; j < 10; j++) {
							out += "<";
						}
					}
					out+="#";
					break;
				case "for":
					int index = Integer.parseInt(tokens[1]);
					String output = (tokens[2]);
					for (int i = 0; i < index; i++) {
						for (int j = 0; j < 10; j++) {
							out += ">";
						}
					}
					out += "<#";
					out += "[<+<+>>-]";
					out += "<<";
					out += "[>>>>[-]";
					for (char c : output.toCharArray()) {
						for (int i = 0; i < c; i++) {
							out += "+";
						}
						out += ".[-]<<<<";
					}
					out += "-]>[>+<-]";
					break;
				case "rawBF":
					out += tokens[1];
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
