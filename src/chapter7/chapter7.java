package chapter7;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class chapter7 {
	public static void main(String[] args) {
		String SENTENCE = "javaagent:C:  Program Files  JetBrains   IntelliJ IDEA Community Edition 2023.1.1  lib  idea_rt.jar";
		Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
		Stream<Character> stream = StreamSupport.stream(spliterator, true);
		System.out.println("Found " + countWords(stream.parallel()) + " words");
	}
	private static int countWords(Stream<Character> stream) {
		WordCounter wordCounter = stream.reduce(new WordCounter(0,true),
													WordCounter::accumulate,
													WordCounter::combine);
		return wordCounter.getCounter();
	}
}
