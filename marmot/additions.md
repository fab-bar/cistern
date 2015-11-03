This version of MarMoT contains the following additions and changes by Fabian Barteld:

- a command _Annotator_ for Lemming to run a pipeline model independently of
  MarMoT  
  To lemmatize a text using a Lemming model lemming.srl run:
  <pre>
    java -Xmx5g -cp marmot.jar:trove.jar lemming.lemma.cmd.Annotator \
                lemming.srl \
                form-index=0,tag-index=1,morph-index=2,input.tsv \
                output.tsv
  </pre>
  input.tsv contains lines with the token (first column) with pos (second
  column) and morphology tags (third column).  
  The results are written to output.tsv using the
  [CoNLL09](http://ufal.mff.cuni.cz/conll2009-st/task-description.html) format
  (only the first 8 columns), the predicted lemmas are in the 4th column).
