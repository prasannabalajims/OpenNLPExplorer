package com.pbs.nlp.st;

import java.io.FileNotFoundException;

public interface NameFinderService {

	public String[] getNames(String tokenizerModel, String nameModel, String inputFile) throws FileNotFoundException;
}
