## 1. Which Phase I classes/interfaces were reused unchanged?

- **GameInterface.java** (this remains the console interface fo users to interact whether they want to play a new game, and can provide their input. Has win/loss behvaious, and lets users play a new game, so it doesn't really need to know if the current game is Mastermind or Wordle).

- **GameConfiguration.java** [renamed to MastermindConfiguration.java] (as mentioned in AI.pdf, I was debating to also convert this to a parent + child architecture, but realized the configuration of each new game is pretty unique, so I wanted to just keep the configuration separate per game).

## 2. Which Phase I classes/interfaces were modified? For each, why was the change necessary?

- **Driver.java** (the only thing that changed is that given a new input of 'Wordle,' I created a new game object of runtime type of Wordle. Apart from this, the file was the same.)

- **GameTemplate.java** (this class became abstract, and I thought this was important as all the 'word-guessing' games that we would create would have a secret_word, would have a win status, and would have a startNewGame() method. Most of these methods would be in all games, so I decided to make a GameTemplate.java abstract class, and then have a WordleGame.java and MastermindGame.java extend this class, and add their own specific implementation of 'randomCode()' for example, as both generate code in different ways [but still generate one regardless]).

- **MastermindGame.java** [Renamed from Mastermind.java] (This is now a child class of GameTemplate.java, and this class now defines game specific implementations of methods like randomCode(), getInstructions(), validateGuess() and scoreGuess(). As mentioned, the GameTemplate.java class has all the abstract methods, and this child class just has to implement the game specifics.) 

- **FeedbackTemplate.java** (I initially had a Feedback.java class, and I dediced that the feedback for Mastermind involves the same checking for exact match, and correct but in wrong place checking. So I decided to have a parent abstract class with the logic for determining perfect and partial match, and then let children call them as needed, and implement their own toString() method, as the feedback is customized per game.)

- **MastermindFeedback.java** (This used to be Feedback.java, and I had all my feedback logic there. But as mentioned for the FeedbackTemplate.java class, most of the feedback logic for word-guessing games were the same, and the only difference was how the result was returned. So I had a abstract parent class with all the implementation, and this child class is only 17 lines with a toString() on formatting the result.)

## 3. Which new types are Wordle-specific?

- **Dictionary.txt** (This is an external data file, not necessarily a class, and contains all the words used in this game. Source: http://www.gwicks.net/dictionaries.htm)

- **DictionaryHelper.java** (this file is for loading the external word list, and then selecting all the words that are of the length of the user configuration, and then writes specific contain() and generateRandomWord() methods to fit the 'Dictionary.txt')

- **WordleGame.java** (This is now a child class of GameTemplate.java, and this class now defines Wordle specific implementations of methods like randomCode(), getInstructions(), validateGuess() and scoreGuess(). As mentioned, the GameTemplate.java class has all the abstract methods, and this child class just has to implement the Wordle game specifics.)

- **WordleConfiguration.java** (I decided that each game's configuration was very specific, and that most of the work was just being done in the constructor based on the input dictionary file, and the user's configurations. Although I maybe could have spent more time to having a parent abstract class, I decided that having a standalone class for the Wordle game was the best choice!)

- **WordleFeedback.java** (Initially, I had all my feedback logic in standalone classes per game. But as mentioned for the FeedbackTemplate.java class, most of the feedback logic for word-guessing games were the same, and the only difference was how the result was returned. So I had a abstract parent class with all the implementation, and this child class is only 26 lines with a toString() on formatting the result.)

## 4. Which Phase I abstractions turned out to be too Mastermind-specific?

This is an ironic answer, as I initially coded the GameConfiguration.java class first for Mastermind, thinking that this would work as an abstraction for all game types. However, most of the members of the class were too Mastermind-specific such as NUMBER_PEGS, COLORS_IN_GAME, isLegalColor(), getColors(), and having an array of max colors. When trying to create an abstract class for GameConfiguration, such that each child class could override the methods, I found it very difficult given how I designed Mastermind's Game Configuration, and therefore was a limitation of my initial design, and caused me to also create a standard WordleConfiguration.java file.

## 5. Describe at least one refactoring that improved reuse or separation of concerns.

I believe that my best example of refactoring was changing Feedback.java to FeedbackTemplate.java, and then having a MastermindFeedback.java and WordleFeedback.java files. I was particularly impressed as I was able to just copy my exact match and partial match logic from Mastermind directly into the parent abstract class, and only make the toString() method abstract, as that was specific to how each game returned feedback. This improves reuse and makes it that all new games just need to override the toString() method as needed, and can keep their feedback files super short.

## 6. Did any abstraction become more complicated solely to support both games? Was that complexity worthwhile?

Honestly, I don't believe any abstraction that I initially planned had become more complicated to support both games. I believe my Phase I code was designed really well, as I was just able to convert the initial files I had designed into a abstract parent class + 2 child classes for each games. In that sense, yes, instead of having 1 files per logic, now I had a parent class + child class per logic, but I don't believe this made it more complicated, and in fact rewriting the code made it more clear for me. Adding this extra layer was definitely very worthwhile, as after I converted the Feedback.java to a FeedbackTemplate.java, and coded a MastermindFeedback.java, I realized how the child class literally needed to only override 1 method, and everything else was the same. Because of this, I was able to get a WordleFeedback.java file ready in less that 5 minutes!

## 7. If you had known about Wordle before Phase I, what would you have designed differently?

If I knew about Wordle in specific before my submission of Phase I, I honestly would not have changed much, as we were tasked to design our initial submission with knowing that we would have to have the abstraction extend this code to other word-guessing games. But there are some specific that I would change, such as naming the method generateSecret() instead of randomCode(), and also making the GameConfiguration class more abstract. I had a lot of information specific to Mastermind such as the array of colors, and a datastructure with accepted colors. It would be better if I initially had a ConfigurationTemplate.java, and then made a MastermindConfiguration.java, so that I could add a WordleConfiguration.java later easier. But due to how complex I made my GameConfiguration.java for Mastermind, I was too opposed to abstracting the structure to a parent+child class separation.

## 8. Include before-and-after class/interface diagrams

> **NOTE:** Available in the ARCHITECTURE_CHANGE.pdf file after the last question, as I cannot add drawings to a '.md' file.