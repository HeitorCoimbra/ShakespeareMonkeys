package evo.shakespeare;

import java.util.*;;

public class Generator {
	// inputWord is the Prompt exactly as it has been written in the console
	public static String inputWord = getInput();
	// targetWord is the Prompt word but every uppercase letter turned lowcase so
	// the genetic algorithm can work on it more efficiently.
	public static String targetWord = getTargetWord(inputWord);
	// uppercaseIndex is an array that stores which letters in targetWord should be
	// transformed back to uppercase to match the inputWord.
	public static boolean[] uppercaseIndex = getUppercaseIndex(inputWord);

	public static void main(String[] args) {
		Random r = new Random();
		int populationSize = 1000;
		double mutationRate = 0.08;
		int genCounter = 1;
		boolean sucesso = false;
		// Time that takes for each generation to be printed in the console.
		// A time of 500-1000ms is suggested in order to have a more visually pleasing
		// experience.
		int timeStep = 100;
		Population Populacao = new Population(targetWord, mutationRate, populationSize);

		// Prints each generation's top 5 Prompts according to Fitness score.
		// In here is the wait function that actually produces the timeStep effect.
		// At the end of the while loop it proceeds to the next generation and adds to
		// the genCounter.
		while (!sucesso) {
			System.out.println("Generation " + genCounter + ":");
			for (int i = 4; i >= 0; i--) {
				System.out.println(Populacao.getPrompt(i).getText() + " ");
			}
			System.out.println();
			wait(timeStep);
			if (Populacao.getPrompt(0).getText().equals(targetWord)) {
				sucesso = true;
			}
			if (!sucesso) {
				Populacao.nextGen();
				genCounter++;
			}
		}
		System.out.printf("In generation %d the evolutionary line succeeded in producing the following Prompt:\n%s",
				genCounter, changeCases(Populacao.getPrompt(0).getText()));
	}

	public static String getInput() {
		Scanner reader = new Scanner(System.in);
		return reader.nextLine();
	}

	// Turns every uppercase letter from input to lowcase.
	public static String getTargetWord(String input) {
		String output = "";
		for (int i = 0; i < input.length(); i++) {
			int decimalOfi = (int) input.charAt(i);
			if (decimalOfi > 64 && decimalOfi < 91) {
				output += (char) (decimalOfi + 32);
			} else {
				output += (char) decimalOfi;
			}
		}
		return output;
	}

	// Fills the uppercaseIndex bool[].
	public static boolean[] getUppercaseIndex(String input) {
		boolean[] output = new boolean[input.length()];
		for (int i = 0; i < input.length(); i++) {
			int decimalOfi = (int) input.charAt(i);
			if (decimalOfi > 64 && decimalOfi < 91) {
				output[i] = true;
			}
		}
		return output;
	}

	// Changes the cases of the Prompt back to match the inputWord.
	public static String changeCases(String s) {
		int len = s.length();
		for (int i = 0; i < len; i++) {
			if (uppercaseIndex[i]) {
				s = s.substring(0, i) + (char) ((int) (s.charAt(i)) - 32) + s.substring(i + 1);
			}
		}
		return s;
	}

	// wait is the function that manages the time between each generation being
	// generated and printed.
	public static void wait(int t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}
