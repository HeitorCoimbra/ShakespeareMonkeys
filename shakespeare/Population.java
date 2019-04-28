package evo.shakespeare;

import java.util.*;

public class Population {
	private Prompt[] pop;
	private double mutationRate;
	// The topFraction is the denominator of the fraction that should represent the
	// selected survivors of each generations.
	// eg. 2 for 50%,4 for 25%, 5 for 20%, 10 for 10% etc
	private int topFraction = 10;

	private Random r = new Random();

	// Constructs a population by filling the array of Prompts with one random
	// Prompt(text and fitness) at each position
	public Population(String target, double mutationRate, int populationSize) {
		this.pop = new Prompt[populationSize];
		for (int i = 0; i < populationSize; i++) {
			pop[i] = new Prompt(target);
		}
		this.sortPop();
		this.mutationRate = mutationRate;
	}

	// Constructs the next generation of Prompts by scrapping the Prompts with the
	// worst fitness scores of the population.
	// The top fraction that survives acts as "parents" of the next generation
	// providing.
	// their own texts as their "genome".
	// After all that, this function sorts once again the generation.
	public void nextGen() {
		for (int i = this.pop.length / topFraction; i < this.pop.length; i++) {
			this.pop[i] = nextChild();
		}
		this.sortPop();
	}

	// Generates a new Prompt from two Parent Prompts and a mutationRate.
	public Prompt nextChild() {
		return new Prompt(getParent(), getParent(), mutationRate);

	}

	// Selects a Parent from the top pool of Prompts.
	// Each prompts fitness factors in as the probability of a prompt to be chosen
	// as Parent.
	public Prompt getParent() {
		int fitnessSum = 0;
		Prompt[] sortitionArr;
		for (int i = 0; i < pop.length / topFraction; i++) {
			fitnessSum += this.getPrompt(i).getFitness();
		}

		sortitionArr = new Prompt[fitnessSum];
		for (int i = 0, j = 0; i < this.pop.length / topFraction; i++) {
			for (int k = 0; k < this.getPrompt(i).getFitness(); k++) {
				sortitionArr[j] = this.getPrompt(i);
				j++;
			}
		}
		return sortitionArr[r.nextInt(fitnessSum)];
	}

	// Checks if the wanted result has been achieved and, if so, shuts the program.
	public void evaluate() {
		if (this.getPrompt(0).getFitness() == this.getPrompt(0).getText().length()) {
			System.exit(0);
		}
	}

	// Returns the prompt in the i position in the population.
	public Prompt getPrompt(int i) {
		return this.pop[i];
	}

	// Quicksort comparing each Prompt's fitness score and rearranging the
	// population in a decreasing order.
	public void sortPop() {
		quickSort(this.pop, 0, this.pop.length - 1);
	}

	public void quickSort(Prompt oarr[], int begin, int end) {
		if (begin < end) {
			int partitionIndex = partition(oarr, begin, end);

			quickSort(oarr, begin, partitionIndex - 1);
			quickSort(oarr, partitionIndex + 1, end);
		}
	}

	private int partition(Prompt[] oarr, int begin, int end) {
		int pivot = oarr[end].getFitness();
		int i = (begin - 1);
		for (int j = begin; j < end; j++) {
			if (oarr[j].getFitness() >= pivot) {
				i++;

				Prompt swapTemp = oarr[i];
				oarr[i] = oarr[j];
				oarr[j] = swapTemp;
			}
		}
		Prompt swapTemp = oarr[i + 1];
		oarr[i + 1] = oarr[end];
		oarr[end] = swapTemp;

		return i + 1;
	}
}
