/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rohan
 */
public class Brains {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		System.out.println("Enter a valid BF program");
		String bfProg = (new java.util.Scanner(System.in)).nextLine();
	char[] mem = new char[6400000];
		int dataPTR = 50;
		int pgrPTR = 0;
		int[] jumpTable = new int[bfProg.length()];
		Stack<Integer> stack = new Stack<>();
		for(int i = 0;i<bfProg.length();i++){
			if(bfProg.charAt(i)=='['){
				stack.push(i);
			}else if(bfProg.charAt(i)==']'){
                int tmp = stack.pop();
                jumpTable[tmp] = i;
				jumpTable[i]=tmp;
			}
		}
System.out.println(Arrays.toString(jumpTable));
		while (pgrPTR < bfProg.length()) {
			switch (bfProg.charAt(pgrPTR)) {
				case '>':
					dataPTR++;
					break;
				case '<':
					dataPTR--;
					break;
				case '+':
					mem[dataPTR]++;
					break;
				case '-':
					mem[dataPTR]--;
					break;
				case '.':
					//System.out.print((char) mem[dataPTR] > 20 || mem[dataPTR] == 10 ? (char) mem[dataPTR] : "");
					System.out.print(mem[dataPTR]);
					break;
				case ',':
			{
				try {
					//	System.out.println("Waiting for input!");

					mem[dataPTR] = (char) System.in.read();
				} catch (IOException ex) {
					Logger.getLogger(Brains.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

					break;
				case ']':
pgrPTR = jumpTable[pgrPTR]-1;
					break;
				case '[':
if(mem[dataPTR]==0)pgrPTR = jumpTable[pgrPTR];
					break;
			}

//	System.out.println(bfProg.charAt(pgrPTR));
			pgrPTR++;
		}
	}

}