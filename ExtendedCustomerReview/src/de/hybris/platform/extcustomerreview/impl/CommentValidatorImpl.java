package de.hybris.platform.extcustomerreview.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hybris.platform.extcustomerreview.CommentValidator;

/**
 * 
 * @author Parasu17
 *
 */
public class CommentValidatorImpl implements CommentValidator {

	private static final List<String> curseWords;
	
	static {
		BufferedReader br = null;
		List<String> words = new ArrayList<String>();
		try {
			br = new BufferedReader(new InputStreamReader(CommentValidatorImpl.class.getResourceAsStream("CurseWords.txt")));
			String word = null;
			while( (word = br.readLine()) != null ) {
				word = word.trim();
				if(! (word.isEmpty() || word.startsWith("#")) ) {
					words.add(word);
				}
			}
			words = Collections.unmodifiableList(words);
		} catch(Exception exp) {
			words = Collections.unmodifiableList(new ArrayList<String>());
		} finally {
			try {
				if(br != null) {
					br.close();
				}
			} catch(Exception exp) {
				
			}
		}
		
		curseWords = words;
	}
	
	public void validateComment(String comment) {
		if(comment == null || comment.trim().isEmpty()) {
			throw new IllegalArgumentException("The review comment should not be null or empty");
		}
		
		String[] words = comment.split(" ");

		for(String word : words) {
			if(curseWords.contains(word)) {
				throw new IllegalArgumentException("The review comment should not contain any curse words: " + word);
			}
		}

	}

}
