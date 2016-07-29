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
		for (String line : prog) {
			String[] tokens = line.split(" ");
			switch (tokens[0]) {
				case "print":
					char[] stuff = tokens.length==1?" ".toCharArray():line.substring(6).toCharArray();
					for (char c : stuff) {
						for (int i = 0; i < c; i++) {
							out += "+";
						}
						out += ".[-]";
					}
					break;
				case "printLine":
					for (int i = 0; i < '\n'; i++) {
						out += "+";
					}
					out += ".[-]";
					break;
				case "for":
					int pointer = (int) tokens[1].charAt(0);
					for (int i = 0; i < pointer; i++) {
						out += ">";
					}
					out += "[<+<+>>-]";
					out += "<[<<";
					for (int i = 0; i < (tokens.length == 2 ? ' ' : tokens[2].charAt(0)); i++) {
						out += "+";
					}
					out += ".[-]";
					out += ">>-]";
					out += "<[>>+<<-]>>";
					for (int i = 0; i < pointer; i++) {
						out += "<";
					}
					break;
				case "in":
					pointer = (int) tokens[1].charAt(0);
					for (int i = 0; i < pointer; i++) {
						out += ">";
					}
					out += ",";
					for (int i = 0; i < 48; i++) {
						out += "-";
					}
					for (int i = 0; i < pointer; i++) {
						out += "<";
					}
					break;
				case "ld":
					for (int i = 0; i < (int) 'z' + 2; i++) {
						out += ">";
					}
					out += "+[><<+>>,]";
					for (int i = 0; i < (int) 'z' + 6; i++) {
						out += "<";
					}
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
