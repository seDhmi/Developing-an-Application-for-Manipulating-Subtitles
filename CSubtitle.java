
public class CSubtitle implements Subtitle {
private Time startTime;
private Time endTime;
private String text;
	@Override
	public Time getStartTime() {
		// TODO Auto-generated method stub
		return startTime;
	}

	@Override
	public Time getEndTime() {
		// TODO Auto-generated method stub
		return endTime;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

	@Override
	public void setStartTime(Time startTime) {
		// TODO Auto-generated method stub
		this.startTime=startTime;
	}

	@Override
	public void setEndTime(Time endTime) {
		// TODO Auto-generated method stub
		this.endTime=endTime;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		this.text=text;
	}
	
}
