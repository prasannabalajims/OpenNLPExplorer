package com.pbs.nlp.st;

public interface SentenceTokenizerService {
	public String[] tokenizeFileUsingLearnableTokenizer(String fileName);

	public String[] tokenizeUsingLearnableTokenizer(String content);

	public String[] tokenizeFileUsingWhiteSpaceTokenizer(String fileName);

	public String[] tokenizeUsingWhiteSpaceTokenizer(String data);
}
