### shakespeare-monkeys
# Genetic Algorithm applied to the Shakespeare Monkey theorem
## Introduction
There's an old theorem called the Shakespeare Monkey Theorem and it suggests that, with an infinite amount of time, a monkey randomly typing in a typewriter could actually recreate the famous writer's works. 

Sadly, I don't have an infinite amount of time nor do I have a monkey, so the point of this project is to use principles of evolution and genetic algorithm to generate, from a randomly generated "population" of sentences, a prompt identical to an inputed sentence through the simulation of natural selection and hereditary reproduction of prompts.

The program is all written in Java. It works with both low or upper case alphabet letters and blankspaces(" ").
## How it works

The way this genetic algorithm works is by creating a pool of 1000 randomly generated Prompts(of the size of the target word) containing only lowcase alphabet letters or spaces(" "). Each Prompt object has it's own DNA that corresponds to the string it holds, and a fitness score that corresponds to how many letters in the right position it matched to the target word. The population is then sorted and new generations are created from the first until the desired result is achieved.

### The generation 
After sorting the first gen a while loop is initiated that does the following: The generation number is printed along with the top 5 prompts with the highest fitness scores of the gen from the 5# to the 1#. The next gen is then formed by replacing the worst 90% of the population by new Prompts that are the offspring of two of the 90th percentile Prompts. These two prompts are chosen at random, but the highest the fitness score of a Prompt the more likely it is to be a parent. This loop ends when a match-Prompt is generated, printing the message: 
>"In generation %d the evolutionary line succeeded in producing the following Prompt: %s"

### The reproduction mechanism
The reproduction mechanism has two steps. First it generates a new Prompt using half of each parents DNA* and then it mutates the DNA of the child by a mutationRate**. The mutationRate is the same throughout the population and it determines the probability of each character of the child to be replaced by a random character. This step is neccessary to promote diversity in the pool so it doesn't get staggered if it happens that all Prompts are missing the same match-char, for example.

### The caseChange system
To make the program work with both upper and lower cases I could have added 26 more chars to the selection pool, but that would make it run much slower and would not be the smartest choice. The system I chose to use does not take part in the Artificial Evolution process and it works by storing the input prompt's capitalized letters in a boolean array, makes changes the input prompt to all-lowercase and works with it this way. Then, when at least one generation has produced a perfect Prompt, a for loop corrects the cases of the appropriate letters back to the way they were in the originial input.

## Demonstration 
![](https://i.imgur.com/LEfgWga.gif)

## References
### Footnotes
* *_This is not the most optimal generation mechanism and I intend to work on it in the future to implement a better method. Some simulation of the biological phenomenon of chromosome crossing over is what comes to mind now._

* **_In the current version of this project, the mutationRate is fixed to 0.1, but this rate is not optimal to every population size nor to every target word length. For the project's future I intend to create a function that determinates the optimal mutationRate based on the population size and target word lenth._

### Bibliography

- Shiffman, Daniel. _"The Nature of Code: Simulating Natural Systems with Processing"_. 2012.
