package com.springinaction.knights;

public class BraveKnight implements Knight{
	
	private Quest quest;
	
	private Minstrel minstrel;
	
	//进行git测试 
	public BraveKnight(Quest quest ,Minstrel minstrel) {
		this.quest = quest;
		this.minstrel = minstrel;
	}

	public void embarkOnQuest() {
		minstrel.singBeforeQuest();
		quest.embark();
		minstrel.singAfterQuest();
		
	}

}
