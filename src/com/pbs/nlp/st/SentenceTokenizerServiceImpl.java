package com.pbs.nlp.st;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import com.pbs.nlp.common.util.FileReader;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;

public class SentenceTokenizerServiceImpl implements SentenceTokenizerService {

	private TokenizerModel model = null;
	private Tokenizer learnableTokenizer = null;

	/**
	 * Parameterized constructor with model file path as parameter
	 * 
	 * @param modelFile
	 */
	public SentenceTokenizerServiceImpl(String modelFile) {
		initializerTokenizerModel(modelFile);
		learnableTokenizer = new TokenizerME(model);
	}

	/**
	 * Method to return instance of Tokenizer - Learnable
	 * 
	 * @return
	 */
	public Tokenizer getLearnableTokenizer() {
		return learnableTokenizer;
	}

	/**
	 * Method to return instance of Whitespace Tokenizer
	 * 
	 * @return
	 */
	public Tokenizer getWhiteSpaceTokenizer() {
		return WhitespaceTokenizer.INSTANCE;
	}

	/**
	 * Initialize tokenizer with the model file of NLP
	 * 
	 * @param modelFile
	 */
	private void initializerTokenizerModel(String modelFile) {
		Objects.nonNull(modelFile);
		InputStream inputStream = null;

		try {
			inputStream = new FileInputStream(modelFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}

		try {
			model = new TokenizerModel(inputStream);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Closing InputStream when the model file is loaded
			if (model != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					System.out.println("IOException when closing InputStream for Model file");
				}
			}
		}
	}

	/**
	 * Tokenize file content using Learnable Tokenizer
	 */
	public String[] tokenizeFileUsingLearnableTokenizer(String file) {
		return learnableTokenizer.tokenize(FileReader.getFileContentAsString(file));
	}

	/**
	 * Tokenize data using Learnable Tokenizer
	 */
	public String[] tokenizeUsingLearnableTokenizer(String data) {
		return learnableTokenizer.tokenize(data);
	}

	/**
	 * Tokenize file content using Whitespace Tokenizer
	 */
	public String[] tokenizeFileUsingWhiteSpaceTokenizer(String file) {
		return getWhiteSpaceTokenizer().tokenize(FileReader.getFileContentAsString(file));
	}

	/**
	 * Tokenize data using Whitespace Tokenizer
	 */
	public String[] tokenizeUsingWhiteSpaceTokenizer(String data) {
		return getWhiteSpaceTokenizer().tokenize(data);
	}
}
