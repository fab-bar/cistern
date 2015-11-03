// Copyright 2015 Thomas Müller
// This file is part of MarMoT, which is licensed under GPLv3.

package lemming.lemma.cmd;

import lemming.lemma.Lemmatizer;
import marmot.util.FileUtils;

public class Annotator {

	public static void main(String[] args) {		
		String model_file = args[0];
		String input_file = args[1];
		String pred_file = args[2];
		Lemmatizer lemmatizer = FileUtils.loadFromFile(model_file);
		Trainer.annotate(lemmatizer, input_file, pred_file);	
	}

}
