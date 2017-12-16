public class TimeInterval implements Comparable<TimeInterval> {
	private Time startTime;
	private Time endTime;
	
	public TimeInterval (Time start,Time end){
		this.startTime=start;
		this.endTime=end;
	}
	@Override
	public int compareTo(TimeInterval that) {
		if ( ((TimeInterval) startTime).compareTo((TimeInterval) that.endTime) > 0) {
			return 1;
		}
		if (((TimeInterval) endTime).compareTo((TimeInterval) that.startTime) < 0) {
			return -1;
		}
		return 0;
	}
}
