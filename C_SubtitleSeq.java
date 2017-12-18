
public class C_SubtitleSeq implements SubtitleSeq {

	// private TimeInterval key;
	private SortedBST<TimeInterval, Subtitle> bst = new SortedBST<TimeInterval, Subtitle>();

	private int transferToMS(Time time) { // this method transfer the time to millisecond
		return (time.getHH() * 3600000) + (time.getMM() * 60000) + (time.getSS() * 1000) + (time.getMS());
	}

	private Time transferFromMS(int milliseconed) {
		Time time = new CTime();
		time.setHH(milliseconed / 3600000);
		milliseconed %= 3600000;
		time.setMM(milliseconed / 60000);
		milliseconed %= 60000;
		time.setSS(milliseconed / 1000);
		milliseconed %= 1000;
		time.setMS(milliseconed);
		return time;
	}

	@Override
	public void addSubtitle(Subtitle st) {
		// TODO Auto-generated method stubS
		TimeInterval key = new TimeInterval(st.getStartTime(), st.getEndTime());
		bst.insert(key, st);
	}

	@Override
	public List<Subtitle> getSubtitles() {
		// TODO Auto-generated method stub
		if (bst.empty())
			return null;
		List<Subtitle> list = new LinkedList<Subtitle>();
		bst.findFirst();
		while (!bst.last()) {
			list.insert(bst.retrieve().second);
			bst.findNext();
		}
		list.insert(bst.retrieve().second);
		return list;
	}

	@Override
	public Subtitle getSubtitle(Time time) {
		// TODO Auto-generated method stub
		TimeInterval key = new TimeInterval(time, time);
		bst.findFirst();
		while (!bst.last()) {
			if (bst.retrieve().first.compareTo(key) == 0)
				return bst.retrieve().second;
			bst.findNext();
		}
		if (bst.retrieve().first.compareTo(key) == 0)
			return bst.retrieve().second;
		return null;
	}

	@Override
	public int nbNodesInSearchPath(Time time) {
		// TODO Auto-generated method stub
		int nb = 0;
		TimeInterval key = new TimeInterval(time, time);
		return bst.nbNodesInSearchPath(key);

	}

	@Override
	public List<Subtitle> getSubtitles(Time startTime, Time endTime) {
		// TODO Auto-generated method stub
		List<Subtitle> spSub = new LinkedList<Subtitle>();
		bst.findFirst();
		int startTimeMS = transferToMS(startTime);// transfer the time of
													// startTime to millisecond
		int endTimeMS = transferToMS(endTime);

		while (!bst.last()) {
			int st = transferToMS(bst.retrieve().second.getStartTime());// transfer start time in the list to millisecond
			int et = transferToMS(bst.retrieve().second.getEndTime());// transfer end time in the list to millisecond
			if (st <= startTimeMS && et >= startTimeMS) {
				spSub.insert(bst.retrieve().second);
			} else if (st <= endTimeMS && et >= endTimeMS) {
				spSub.insert(bst.retrieve().second);
			} else if (st >= startTimeMS && et <= endTimeMS) {
				spSub.insert(bst.retrieve().second);
			}
			bst.findNext();
		}
		if (transferToMS(bst.retrieve().second.getStartTime()) <= startTimeMS
				&& transferToMS(bst.retrieve().second.getEndTime()) >= startTimeMS) {
			spSub.insert(bst.retrieve().second);
		} else if (transferToMS(bst.retrieve().second.getStartTime()) <= endTimeMS
				&& transferToMS(bst.retrieve().second.getEndTime()) >= endTimeMS) {
			spSub.insert(bst.retrieve().second);
		} else if (transferToMS(bst.retrieve().second.getStartTime()) >= startTimeMS
				&& transferToMS(bst.retrieve().second.getEndTime()) <= endTimeMS) {
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
			if (transferToMS(bst.retrieve().second.getStartTime()) < 0) {
				bst.retrieve().second.setStartTime(transferFromMS(0));
			}
			if (transferToMS(bst.retrieve().second.getEndTime()) < 0) {
				bst.retrieve().second.setEndTime(transferFromMS(0));
			}
			if (transferToMS(bst.retrieve().second.getEndTime()) == 0) {
				bst.remove();
			} else {
				bst.findNext();
			}
		}
		int StartTime = transferToMS(bst.retrieve().second.getStartTime());
		int EndTime = transferToMS(bst.retrieve().second.getEndTime());
		StartTime += offset;
		EndTime += offset;
		bst.retrieve().second.setStartTime(transferFromMS(StartTime));
		bst.retrieve().second.setEndTime(transferFromMS(EndTime));
		if (transferToMS(bst.retrieve().second.getStartTime()) < 0) {
			bst.retrieve().second.setStartTime(transferFromMS(0));
		}
		if (transferToMS(bst.retrieve().second.getEndTime()) < 0) {
			bst.retrieve().second.setEndTime(transferFromMS(0));
		}
		if (transferToMS(bst.retrieve().second.getEndTime()) == 0) {
			bst.remove();
		}

	}

}
