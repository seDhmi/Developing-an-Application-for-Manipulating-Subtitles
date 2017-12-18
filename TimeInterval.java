public class TimeInterval implements Comparable<TimeInterval> {
	
	
	private CTime startTime;
	private CTime endTime;
	
	public TimeInterval (Time time,Time time2){
		startTime= new CTime(time.getHH(),time.getMM(),time.getSS(),time.getMS());
		endTime= new CTime(time2.getHH(),time2.getMM(),time2.getSS(),time2.getMS());
	}
	

	@Override
	public int compareTo(TimeInterval that) {
		if (((startTime).compareTo(that.endTime)) > 0) {
			return 1;
		}
		if (((endTime).compareTo(that.startTime)) < 0) {
			return -1;
		}
		return 0;
	}

	 
}
