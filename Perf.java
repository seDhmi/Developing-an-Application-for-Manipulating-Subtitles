class TimeSt implements Time {
	private int hh;
	private int mm;
	private int ss;
	private int ms;

	public TimeSt(int hh, int mm, int ss, int ms) {
		this.hh = hh;
		this.mm = mm;
		this.ss = ss;
		this.ms = ms;
	}

	public TimeSt(String hh, String mm, String ss, String ms) {
		this.hh = Integer.parseInt(hh);
		this.mm = Integer.parseInt(mm);
		this.ss = Integer.parseInt(ss);
		this.ms = Integer.parseInt(ms);
	}

	@Override
	public int getHH() {
		return hh;
	}

	@Override
	public int getMM() {
		return mm;
	}

	@Override
	public int getSS() {
		return ss;
	}

	@Override
	public int getMS() {
		return ms;
	}

	@Override
	public void setHH(int hh) {
		this.hh = hh;
	}

	@Override
	public void setMM(int mm) {
		this.mm = mm;
	}

	@Override
	public void setSS(int ss) {
		this.ss = ss;
	}

	@Override
	public void setMS(int ms) {
		this.ms = ms;
	}
}

class SubtitleSt implements Subtitle {
	private Time startTime;
	private Time endTime;
	private String text;

	public SubtitleSt(Time startTime, Time endTime, String text) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.text = text;
	}

	@Override
	public Time getStartTime() {
		return startTime;
	}

	@Override
	public Time getEndTime() {
		return endTime;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	@Override
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}
}

public class Perf {

	public static void main(String[] args) {
		int[] nbSubtitles = { 1 << 12, 1 << 13, 1 << 14, 1 << 15, 1 << 16 };
		int nbRuns = 100;
		int nbSearches = 1000;
		java.util.Random rnd = new java.util.Random(777);

		// A first run that should be ignored
		{
			int nbSts = 10;
			for (int k = 0; k < nbRuns; k++) {
				SubtitleSeq stSeq = SubtitleSeqFactory.getSubtitleSeq();
				// Fill the sequence randomly
				for (int s = 0; s < nbSts; s++) {
					int hh = rnd.nextInt();
					Time start = new TimeSt(hh, 0, 0, 0);
					Time end = new TimeSt(hh, 0, 1, 0);
					stSeq.addSubtitle(new SubtitleSt(start, end, ""));
				}
				// Run random searches
				long stTime = System.nanoTime();
				for (int q = 0; q < nbSearches; q++) {
					stSeq.getSubtitle(new TimeSt(rnd.nextInt(), 0, 0, 0));
				}
				long edTime = System.nanoTime();
			}
		}

		System.out.println("Size\tTime");
		for (int i = 0; i < 5; i++) {
			int nbSts = nbSubtitles[i];
			long time = 0;
			for (int k = 0; k < nbRuns; k++) {
				SubtitleSeq stSeq = SubtitleSeqFactory.getSubtitleSeq();
				// Fill the sequence randomly
				for (int s = 0; s < nbSts; s++) {
					int hh = rnd.nextInt();
					Time start = new TimeSt(hh, 0, 0, 0);
					Time end = new TimeSt(hh, 0, 1, 0);
					stSeq.addSubtitle(new SubtitleSt(start, end, ""));
				}
				// Run random searches
				long stTime = System.nanoTime();
				for (int q = 0; q < nbSearches; q++) {
					stSeq.getSubtitle(new TimeSt(rnd.nextInt(), 0, 0, 0));
				}
				long edTime = System.nanoTime();
				time += edTime - stTime;
			}
			System.out.println(nbSts + "\t" + time / (nbRuns * nbSearches));
		}

	}
}
