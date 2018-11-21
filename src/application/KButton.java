package application;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import javafx.scene.control.Button;

public class KButton extends Button implements Runnable{
	Semaphore s;
	CountDownLatch cdl;
	CountDownLatch gate;
	private boolean clicked = false;
	volatile private static int innerIteration;
	private static int iteration;
	KButton(Semaphore s, CountDownLatch cdl, CountDownLatch gate){
		this.s = s;
		this.cdl = cdl;
		this.gate = gate;
		innerIteration = 0;
		Thread th = new Thread(this); 
		th.start();
		th.setPriority((int)(Math.random()*9+1));
	}
	public static void setIterations(int i) {
		iteration = i;
	}
	@Override
	public void run() {
		try {
			gate.await();
			cdl.countDown();
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			s.acquire();
			if (innerIteration < iteration) {
			innerIteration++;
			clicked = true;
			setStyle(Palette.getRandomBackgroundColor());
			Thread.sleep(1000);
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		setStyle(Palette.getWhiteBackground());	
		s.release();
		
	}
	public boolean getClicked() {
		return clicked;
	}
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
}
