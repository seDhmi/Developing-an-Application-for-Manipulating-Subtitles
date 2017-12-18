
public class C_SubtitleSeq implements SubtitleSeq {

//	private TimeInterval key;
	private SortedBST<TimeInterval,Subtitle> bst=new SortedBST<TimeInterval,Subtitle>();
	
	@Override
	public void addSubtitle(Subtitle st) {
		// TODO Auto-generated method stub
		TimeInterval key= new TimeInterval(st.getStartTime(),st.getEndTime());
		bst.insert(key, st);
	}

	@Override
	public List<Subtitle> getSubtitles() {
		// TODO Auto-generated method stub
		if(bst.empty())
			return null;
		List<Subtitle> list = new LinkedList<Subtitle>();
		bst.findFirst();
		while(!bst.last()){
			list.insert(bst.retrieve().second);
			bst.findNext();
		}
		list.insert(bst.retrieve().second);
		return list;
	}

	@Override
	public Subtitle getSubtitle(Time time) {
		// TODO Auto-generated method stub
		TimeInterval key= new TimeInterval(time,time);
		bst.findFirst();
		while(!bst.last()){
			if(bst.retrieve().first.compareTo(key)==0)
			return bst.retrieve().second;
			bst.findNext();
		}
		if(bst.retrieve().first.compareTo(key)==0)
			return bst.retrieve().second;
		return null;
	}

	@Override
	public int nbNodesInSearchPath(Time time) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Subtitle> getSubtitles(Time startTime, Time endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shift(int offset) {
		// TODO Auto-generated method stub
		
	}

}
