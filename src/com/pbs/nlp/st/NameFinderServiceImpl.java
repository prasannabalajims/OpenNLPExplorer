package com.pbs.nlp.st;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.pbs.nlp.common.util.FileReader;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

public class NameFinderServiceImpl implements NameFinderService {

	public String[] getNames(String tokenizerModel, String nameModel, String inputFile) throws FileNotFoundException {

		InputStream inputModel = new FileInputStream(nameModel);
		TokenNameFinderModel nameFinderModel = null;

		try {
			nameFinderModel = new TokenNameFinderModel(inputModel);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (nameFinderModel != null) {
				try {
					inputModel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		NameFinderME nameFinder = new NameFinderME(nameFinderModel);
		SentenceTokenizerService sentenceTokenizerService = new SentenceTokenizerServiceImpl(tokenizerModel);
		String[] tokens = sentenceTokenizerService
				.tokenizeUsingLearnableTokenizer(FileReader.getFileContentAsString(inputFile));

		Span nameSpans[] = nameFinder.find(tokens);
		List<String> nameList = new ArrayList<String>();

		for (Span span : nameSpans) {
			String name = "";
			for (int counter = span.getStart(); counter < span.getEnd(); counter++) {
				name += tokens[counter];
			}
			nameList.add(name);
		}
		String[] nameArray = new String[nameList.size()];
		return nameList.toArray(nameArray);

	}
}
