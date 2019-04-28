### shakespeare-monkeys
#Genetic Algorithm applied to the Shakespeare Monkey theorem.
##Introduction
There's an old theorem called the Shakespeare Monkey Theorem and it suggests that, with an infinite amount of time, a monkey randomly typing at a typewriter could actually recreate the famous writers's works.

This project has the goal of simulating a population of randomly generated Prompts and, by using a Genetic Algorithm and artificial decentralized selection and reproduction system, evolve the population to a point in which some of the monkeys have written exactly the inputted Prompt.

The program is all written in java. It works with both low or upper case alphabet letters and blankspaces(" ").
##How it works

The way this genetic algorithm made work is by creating a pool of 1000 randomly generated Prompts(of the size of the target word) containing only lowcase alphabet letters or spaces(" "). Each Prompt object has an a "DNA" that corresponds to the string it holds and a fitness scores that corresponds to how many letters in the right position it matched to the target. The population is then sorted.

###The generation 
After sorting the first gen a while loop is initiated that does the following: The generation number is printed along with the top 5 prompts with the highest fitness scores of the gen from the 5# to the 1#. The next gen is then formed by replacing the worst 90% of the population by new Prompts that are made by a proccess of reproduction between two of the 90th percentile Prompts. These two prompts are chosen at random, but the highest the fitness score of a Prompt the more likely it is to be a parent. This while loops until a match-Prompt is generated, making the loop stop and printing the message: 
>"In generation %d the evolutionary line succeeded in producing the following Prompt: %s"

###The reproduction mechanism
The reproduction mechanism has two steps. First it generates a new Prompt using half of each parents DNA(this is not the most optimal generation mechanism and I intend to work on it in the future to implement a better method) and then it mutates the DNA of the child by a mutationRate(0.01). The mutationRate is the same throughout the population and it determines the probability of each character of the child to be replaced by a random character. This step is neccessary to promote diversity in the pool so it doesn't get stuck if it happens that all Prompts are missing the same match-char, for example.

###The caseChange system
To make the program work with both upper and lower cases I could have added 26 more chars to the selection pool, but that would make it run much slower and would not be the smartest the choice. The system I chose to use does not take part in the Artificial Evolution procces and it works by storing the input prompt's capitalized letters in a boolean array, makes changes the input prompt to all-lowercase and works with it this way. Then, when at least one generation has produced a perfect Prompt, a for loop corrects the cases of the appropriate letters back to the way they were in the originial input.


