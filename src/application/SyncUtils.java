package application;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class SyncUtils {
	public static final Semaphore SPHR = new Semaphore(1, false);
//	public static final CountDownLatch CDL = new CountDownLatch(Level.getNumElements());
//	public static final CountDownLatch GATE = new CountDownLatch(1);
}
