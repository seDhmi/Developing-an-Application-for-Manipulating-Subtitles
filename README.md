# Developing an Application for Manipulating Subtitles

# 1. Introduction:
Subtitles are captions displayed at the bottom of a cinema or television screen that translate or transcribe the dialogue or events taking place in the video. Subtitles can be hard-coded into the video stream or come as a separate file. The goal of this project is to develop an application for manipulating subtitle files.

# 2 Data format:

There are many file formats for representing subtitles, but arguably the simplest of these formats is the SubRip format, also known as SRT format because of its file extension. An SRT file is a text file consisting of a sequence of subtitles, each having the following structure:
- A sequential number which starts at 1.
- The start and end times when the subtitle should appear on screen. The time is specified in the format: HH:MM:SS,ms (the last field is an integer value in milliseconds). The display intervals of subtitles must not overlap.
- The text of the subtitle. Line breaks are allowed.
- An empty line indicating the start of a new subtitle (the last subtitle is not followed by a an empty line).

# 3 Requirements:
- required to implement the All classes and interfaces, as A linked list and Binary Tree.
