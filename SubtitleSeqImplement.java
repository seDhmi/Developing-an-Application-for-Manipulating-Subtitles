
public class SubtitleSeqImplement implements SubtitleSeq {

	private SortedBST<TimeInterval,Subtitle> bst = new SortedBST<TimeInterval,Subtitle>();
	
	private int transferToMS(Time time) { // this method transfer the time to millisecond						
		return (time.getHH() * 3600000) + (time.getMM() * 60000) + (time.getSS() * 1000) + (time.getMS());
	}
	private Time transferFromMS(int milliseconed){
		Time time=new CTime();
		time.setHH(milliseconed/3600000);
		milliseconed %=3600000;
		time.setMM(milliseconed/60000);
		milliseconed %=60000;
		time.setSS(milliseconed/1000);
		milliseconed %=1000;
		time.setMS(milliseconed);
		return time;
	}
	@Override
	public void addSubtitle(Subtitle st) {
		// TODO Auto-generated method stub
		
		TimeInterval ti = new TimeInterval(st.getStartTime(),st.getEndTime());
		
		int stStart=transferToMS(st.getStartTime());
		int stEnd=transferToMS(st.getEndTime());
		int size =0;
		if(bst.empty()){
			bst.insert(ti,st);
		}else bst.findFirst(); 
		 
		if(stEnd<transferToMS(bst.retrieve().second.getStartTime())){
			
			Subtitle tmp=bst.retrieve().second;
			bst.insert(ti,st);
			bst.findFirst();
			bst.update(st);
			bst.findNext();
			bst.update(tmp);
		}else{
			bst.findFirst();
			
			while (!bst.last()){
				bst.findNext();
                size++;
			}
			 size++;
			 
			int subEnd=transferToMS(bst.retrieve().second.getEndTime());
			if(stStart>subEnd){
				bst.insert(ti,st);
			}else{
				bst.findFirst();
				
			for(int i=0;i<size;i++){
				int subEnd1=transferToMS(bst.retrieve().second.getEndTime());//there is a problem
				if(stStart>subEnd1){
					bst.findNext();
				int subStart1=transferToMS(bst.retrieve().second.getStartTime());
				if(stEnd<subStart1){
					bst.findFirst();
					for(int j=0;j<i;j++){
						bst.findNext();
					}
					bst.insert(ti,st);
					return;
				}	
				}

			}
			}
		}
		
		
	}

	@Override
	public List<Subtitle> getSubtitles() {
		// TODO Auto-generated method stub
		
		LinkedList<Subtitle> subList = new LinkedList<Subtitle>();
		
		bst.findFirst();
		
		while(!bst.last()){
			subList.insert(bst.retrieve().second);
			bst.findNext();
		}
		subList.insert(bst.retrieve().second);
		return subList;
	}

	@Override
	public Subtitle getSubtitle(Time time) {
		// TODO Auto-generated method stub
		
		int timeMS = transferToMS(time); 
		bst.findFirst();
		
		while(!bst.last()){
			int StimeMS=transferToMS(bst.retrieve().second.getStartTime());
			int EtimeMS=transferToMS(bst.retrieve().second.getEndTime());
			if(StimeMS<=timeMS&&EtimeMS>=timeMS)
				return bst.retrieve().second;
			else {
				bst.findNext();  
			}
		}
		
		int StimeMS=transferToMS(bst.retrieve().second.getStartTime());
		int EtimeMS=transferToMS(bst.retrieve().second.getEndTime());
		if(StimeMS<=timeMS&&EtimeMS>=timeMS)
			return bst.retrieve().second;
		
		return null;
	}

	@Override
	public int nbNodesInSearchPath(Time time) {
		// TODO Auto-generated method stub
		int nb=0;
		
		int timeMS = transferToMS(time); 
		bst.findFirst();
		
		while(!bst.last()){
			int StimeMS=transferToMS(bst.retrieve().second.getStartTime());
			int EtimeMS=transferToMS(bst.retrieve().second.getEndTime());
			if(StimeMS<=timeMS&&EtimeMS>=timeMS)
				nb++;
			else {
				bst.findNext();  
			}
		}
		
		int StimeMS=transferToMS(bst.retrieve().second.getStartTime());
		int EtimeMS=transferToMS(bst.retrieve().second.getEndTime());
		if(StimeMS<=timeMS&&EtimeMS>=timeMS)
			nb++;
		
		
		
		return nb;
	}

	@Override
	public List<Subtitle> getSubtitles(Time startTime, Time endTime) {
		// TODO Auto-generated method stub
		List<Subtitle> spSub = new LinkedList<Subtitle>();
		bst.findFirst();
		int startTimeMS=transferToMS(startTime);//transfer the time of startTime to millisecond
		int endTimeMS=transferToMS(endTime);
		
		while(!bst.last()){
			int st=transferToMS(bst.retrieve().second.getStartTime());//transfer start time in the list to millisecond
			int et=transferToMS(bst.retrieve().second.getEndTime());//transfer end time in the list to millisecond
			if(st<=startTimeMS&&et>=startTimeMS){
				spSub.insert(bst.retrieve().second);
			}else if(st<=endTimeMS&&et>=endTimeMS){
				spSub.insert(bst.retrieve().second);
			}else if(st>=startTimeMS&&et<=endTimeMS){
				spSub.insert(bst.retrieve().second);
			}
			bst.findNext();
		}
		if(transferToMS(bst.retrieve().second.getStartTime())<=startTimeMS&&transferToMS(bst.retrieve().second.getEndTime())>=startTimeMS){
			spSub.insert(bst.retrieve().second);
			}else if(transferToMS(bst.retrieve().second.getStartTime())<=endTimeMS&&transferToMS(bst.retrieve().second.getEndTime())>=endTimeMS){
				spSub.insert(bst.retrieve().second);
			}else if(transferToMS(bst.retrieve().second.getStartTime())>=startTimeMS&&transferToMS(bst.retrieve().second.getEndTime())<=endTimeMS){
				spSub.insert(bst.retrieve().second);
			}
		return spSub;
		
	}

	@Override
	public void shift(int offset) {
		// TODO Auto-generated method stub
		
		bst.findFirst();

		while (!bst.last()) {
			int StartTime = transferToMS(bst.retrieve().second.getStartTime());
			int EndTime = transferToMS(bst.retrieve().second.getEndTime());
			StartTime += offset;
			EndTime += offset;
			bst.retrieve().second.setStartTime(transferFromMS(StartTime));
			bst.retrieve().second.setEndTime(transferFromMS(EndTime));
			if (transferToMS(bst.retrieve().second.getStartTime())< 0) {
				bst.retrieve().second.setStartTime(transferFromMS(0));
			}if (transferToMS(bst.retrieve().second.getEndTime()) < 0) {
				bst.retrieve().second.setEndTime(transferFromMS(0));
			}
			if (transferToMS(bst.retrieve().second.getEndTime()) == 0){
				bst.remove();
			}else{
			bst.findNext();
		}
		}
		int StartTime = transferToMS(bst.retrieve().second.getStartTime());
		int EndTime = transferToMS(bst.retrieve().second.getEndTime());
		StartTime += offset;
		EndTime += offset;
		bst.retrieve().second.setStartTime(transferFromMS(StartTime));
		bst.retrieve().second.setEndTime(transferFromMS(EndTime));
		if (transferToMS(bst.retrieve().second.getStartTime())< 0) {
			bst.retrieve().second.setStartTime(transferFromMS(0));
		}if (transferToMS(bst.retrieve().second.getEndTime()) < 0) {
			bst.retrieve().second.setEndTime(transferFromMS(0));
		}
		if (transferToMS(bst.retrieve().second.getEndTime()) == 0){
			bst.remove();
		}
		
	}

}
