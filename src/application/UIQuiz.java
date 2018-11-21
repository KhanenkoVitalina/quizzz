package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UIQuiz {
	private Level mLevel;
	private Map<Integer,KButton> kbuttonList = new ConcurrentHashMap<>();
	private int posClicksCounter = 0;
	private int negClicksCounter = 0;
	private int totalCount = 0;
	CountDownLatch CDL = new CountDownLatch(mLevel.getNumElements());
	CountDownLatch GATE = new CountDownLatch(1);
	private int i;
public UIQuiz(Level mLevel) {
	this.mLevel = mLevel;
	initButtons(mLevel.getNumElements());
	addOnActionListener(kbuttonList);
}
public CountDownLatch getcdl() {
	return CDL;
}
public CountDownLatch getGate() {
	return GATE;
}
private void initButtons(int numButtons) {
	for( int i = 0; i < numButtons; i++) {
		kbuttonList.put(i, new KButton(SyncUtils.SPHR, CDL, GATE));
	}
}
public Map<Integer,KButton>  getButtonsList(){
	return kbuttonList;
}
public void addOnActionListener(Map<Integer,KButton> kb) {
	for (i = 0; i < kb.size();i++) {
		KButton k = kb.get(i);
		k.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				totalCount++;
			if(totalCount < mLevel.getPlayingElements() ){
				if (k.getClicked()) {
					rightClick(k);
				} else {
					falseClick(k);
				}
			} else if ((posClicksCounter == (mLevel.getPlayingElements()-1)) && k.getClicked()) {
				if (mLevel.getLevel() == mLevel.getLastLevel()) {
					MainQuiz.getInstance().getResults(mLevel);
				}
				MainQuiz.getInstance().nextLevel(mLevel.getLevel()+1);
			}  else {
				MainQuiz.getInstance().getResults(mLevel);
			}
			}
		});
	}
}
public void rightClick(KButton k) {
	k.setClicked(false);
	posClicksCounter++;
	k.setStyle("-fx-background-color: #009900;");
}
public void falseClick(KButton k) {
	k.setStyle("-fx-background-color: #ff0000;");
	negClicksCounter++;
}

}
