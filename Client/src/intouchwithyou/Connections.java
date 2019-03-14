/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intouchwithyou;

/**
 *
 * @author Florin
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import intouchwithyou.server.Streams;

public class Connections {
	private Streams selectedStreams;
        public String streamResponse = "" ;
       
        String getResponse(){
            return this.streamResponse;
        }
        void setResponse(String streamResponse){
            this.streamResponse = streamResponse;
        }
	public Connections(Streams selectedStreams) {
		if (selectedStreams == null)
			throw new IllegalArgumentException();
		this.selectedStreams = selectedStreams;
             
	}
    public void name () {
        selectedStreams.sendMsg("PCNAME");
        //System.out.println("Device's name is: " + selectedStreams.readMsg());
        this.streamResponse = "Device's name is: " + selectedStreams.readMsg();
       
    }
    public void ip (){
        selectedStreams.sendMsg("IP");
	System.out.println("Device's IP is: " + selectedStreams.readMsg());
        
    }
    public void os() {
        selectedStreams.sendMsg("OS");
	System.out.println("Device's OS is: " + selectedStreams.readMsg());
    }
	public void execute() {
		while(true) {
		System.out.println("Select an operation to execute:");
		System.out.println("a - Show device's name");
		System.out.println("b - Show device's remote IP");
		System.out.println("c - Show device's operating system");
		System.out.println("e - Back to menu");
		try {
			String selected = new BufferedReader(new InputStreamReader(System.in)).readLine();
			switch(selected) {
				case "a":	selectedStreams.sendMsg("PCNAME");
							System.out.println("Device's name is: " + selectedStreams.readMsg());
							break;
				case "b":	selectedStreams.sendMsg("IP");
							System.out.println("Device's IP is: " + selectedStreams.readMsg());
							break;
				case "c":	selectedStreams.sendMsg("OS");
							System.out.println("Device's OS is: " + selectedStreams.readMsg());
							break;
				case "e":	return;
				default: 	break;
			}
		} catch (IOException e) {
			System.err.println("Exception!");
			e.printStackTrace();
			return;
		}
		}
	}
}

