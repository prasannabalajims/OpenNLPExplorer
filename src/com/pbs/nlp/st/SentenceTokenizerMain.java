package com.pbs.nlp.st;

public class SentenceTokenizerMain {

	public static void main(String[] args) {
		String modelFile = "C:\\Users\\psadasivam\\Documents\\GIT_HUB_WORKSPACE\\JAVA\\OpenNLPExplorer\\bin\\models\\en-token.bin";
		String data = "This is my first NLP Program, well to be specific Sentence Tokenizer";

		SentenceTokenizerService tokenizerUtil = new SentenceTokenizerServiceImpl(modelFile);
		String[] tokens = tokenizerUtil.tokenizeUsingLearnableTokenizer(data);

		for (String token : tokens)
			System.out.println(token);
	}
}
