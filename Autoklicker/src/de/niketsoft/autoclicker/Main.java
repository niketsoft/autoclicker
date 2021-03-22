package de.niketsoft.autoclicker;

import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.InputEvent;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JFrame;

public class Main {
	
	private static boolean clickToggle = false;
	private static Robot bot = null;
	
	public static void main(String[] args) throws AWTException, InterruptedException {
		bot = new Robot();

		Thread thread = new Thread(){
		    public void run(){
		    	while(true) {
		    		if(clickToggle) {
		    			//Click
//		  		      System.out.println("mouse clicked");
		    		try {
						click();
//						makeSound();
						Thread.sleep(3);
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		}else {
		    		try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		}
		    	}

		    }
		  };

		  thread.start();
		
		
		
		Toolkit.getDefaultToolkit().addAWTEventListener(new Listener(),
				AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_WHEEL_EVENT_MASK);
		JFrame frame = new JFrame();
		frame.setAlwaysOnTop( true );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void click() throws AWTException {
		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	public static void click(int x, int y) throws AWTException {
		bot.mouseMove(x, y);
		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	public static void makeSound() throws LineUnavailableException {
		 java.awt.Toolkit.getDefaultToolkit().beep();
		
//		AudioFormat af = new AudioFormat((float) 44100, 16, 1, true, false);
//	    SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
//	    sdl.open();
//	    sdl.start();
//	    
//	    
//	    
//	    
//	    sdl.drain();
//	    sdl.stop();
	}

	private static class Listener implements AWTEventListener {
		public void eventDispatched(AWTEvent event) {
//			System.out.print(MouseInfo.getPointerInfo().getLocation() + " | ");
//			System.out.println(event);
			if(event instanceof  java.awt.event.MouseEvent) {
				java.awt.event.MouseEvent me = (java.awt.event.MouseEvent)event;
				if(me.isControlDown() && me.getID() == java.awt.event.MouseEvent.MOUSE_CLICKED) {
					clickToggle = true;
				}
				if(me.isAltDown() && me.getID() == java.awt.event.MouseEvent.MOUSE_CLICKED) {
					clickToggle = false;
				}
			}
		}
	}
}