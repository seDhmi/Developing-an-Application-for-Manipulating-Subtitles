// This interface represents a subtitle sequence.
public interface SubtitleSeq {

	// Add a subtitle.
	void addSubtitle(Subtitle st);

	// Return all subtitles in their chronological order.
	List<Subtitle> getSubtitles();

	// Return the subtitle displayed at the specified time, null if no
	// subtitle is displayed.
	Subtitle getSubtitle(Time time);

	// Return the number of nodes in the search path for finding the subtitle.
	int nbNodesInSearchPath(Time time);

	// Return, in chronological order, all subtitles displayed between the
	// specified start and end times. The first element of this list is the
	// subtitle of which the display interval contains or otherwise comes
	// Immediately after startTime. The last element of this list is the
	// subtitle of which the display interval contains or otherwise comes
	// immediately before endTime.
	List<Subtitle> getSubtitles(Time startTime, Time endTime);

	// Shift the subtitles by offseting their start/end times with the specified
	// offset (in milliseconds). The value offset can be positive or negative.
	// Negative time is not allowed and must be replaced with 0. If the end time
	// becomes 0, the subtitle must be removed.
	void shift(int offset);
}
