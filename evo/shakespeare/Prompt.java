package evo.shakespeare;

import java.util.*;

public class Prompt {
	private String text = "";
	private int fitness = 0;
	private Random r = new Random();
	private String targetWord = Generator.targetWord;

	// constructor of the generation 1 Prompts
	// each Prompt is assigned a String called text with the same size as the target
	// text and random chars in each position
	public Prompt(String meta) {
		this.fitness = 0;
		int len = meta.length();
		for (int i = 0; i < len; i++) {
			text += getRandomChar();
		}
		for (int i = 0; i < len; i++) {
			if (text.charAt(i) == meta.charAt(i)) {
				this.fitness++;
			}
		}
	}

	// constructor of child Prompts(Prompts that take their "genome" from two other
	// parent Prompts)
	public Prompt(Prompt a, Prompt b, double mutationRate) {
		this.fitness = 0;
		int len = targetWord.length();
		// this creates the text based on its parents' contents, taking half of each
		// text
		this.text = a.getText(0, (len / 2)) + b.getText((len / 2), len);
		// the mutationRate describes the probability of each char to be replaced by a
		// random alphabet letter or a ' ' in the next loop
		for (int i = 0; i < len; i++) {
			if (r.nextInt(100) + 1 < (int) (mutationRate * 100)) {
				this.text = this.text.substring(0, i) + getRandomChar() + this.text.substring(i + 1);
			}
		}
		// evaluation of fitness
		for (int i = 0; i < len; i++) {
			if (text.charAt(i) == Generator.targetWord.charAt(i)) {
				this.fitness++;
			}
		}

	}

	public String getText() {
		return this.text;
	}

	public String getText(int x, int y) {
		return this.text.substring(x, y);
	}

	public int getFitness() {
		return this.fitness;
	}

	public char getRandomChar() {
		int x = (r.nextInt(26) + 65);
		if (x == 90) {
			return ' ';
		} else {
			return ((char) (x));
		}
	}

}
