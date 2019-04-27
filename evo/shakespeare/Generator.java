package evo.shakespeare;

import java.util.*;;

public class Generator {
	public static String targetWord = getInput();

	public static void main(String[] args) {
		Random r = new Random();
		int populationSize = 1000;
		double mutationRate = 0.1;
		int genCounter = 1;
		boolean sucesso = false;
		// Time that takes for each generation to be printed in the console.
		// A time of 500-1000ms is suggested in order to have a more visually pleasing
		// experience
		int timeStep = 500;
		Population Populacao = new Population(targetWord, mutationRate, populationSize);

		// Prints each generation's top 5 Prompts according to Fitness score.
		// In here is the wait function that actually produces the timeStep effect.
		// At the end of the while loop it proceeds to the next generation and adds to
		// the genCounter
		while (!sucesso) {
			System.out.println("Geraçao " + genCounter + ":");
			for (int i = 4; i >= 0; i--) {
				System.out.println(Populacao.getPrompt(i).getText() + " ");
			}
			System.out.println();
			wait(timeStep);
			if (Populacao.getPrompt(0).getText().equals(targetWord)) {
				sucesso = true;
			}
			Populacao.nextGen();
			genCounter++;
		}
	}

	public static String getInput() {
		Scanner reader = new Scanner(System.in);
		return reader.nextLine();
	}

	public static void wait(int t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}
