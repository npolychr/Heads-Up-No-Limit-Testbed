Heads Up No Limit Bot Simulation Testbed
----------------------------------------


This is a fork from https://github.com/corintio/opentestbed


/* Corintio's Notes

I've ported it to a maven project, so it should be easier to setup the development environment (specially in an IDE other than Eclipse) and make it run. For more information on how to run it, see the [Wiki](https://github.com/corintio/opentestbed/wiki/How-To-Simulate-Cash-Games)


Dependencies
------------

This project requires the Meerkat API 2.5 available at http://www.poker-academy.com/community.php
The .jar is located in the /lib folder. Documentation can be found at the URL above.

To install the meerkat api into your maven repository:
````mvn install:install-file -Dfile=lib/meerkat-api.jar -DgroupId=com.biotools.meerkat -DartifactId=meerkat-api -Dversion=2.5 -Dpackaging=jar````

*/

Heads Up is a game played a little different from the regular 3+ player games, because the SB and BTN seats are merged into one. The open meerkat testbed 
perceived the SB, BB and BTN seats as three different entities. To make it possible for the SB and BTN seats to merge, I had to disable the built in testers that made 
sure everything was ok. For this reason I cannot guarantee that it will come without problems, however I have tested it and it seems fine. 
If you encounter any problem, please contact me.


Using the testbed
-----------------

The main file is ..Heads-Up-No-Limit-Testbed\src\main\java\CashGameConsoleStarter.java

Here you can modify the game characteristics including:

1) Setting the number of games, the amount of small and big blind and the amount of the starting stack (bankroll).

2) Setting the game to Limit or No Limit.

3) Setting the number of bots.

4) Using a serialized or a random deck.

1) To set the number of games you have to modify line 31.
To set the amount of small and big blind you have to modify lines 56 and 59.
To set the amount of the starting stack you have to modify line 62. Currently it is at 100bb (100x0.02).

2) The game currently is set at No Limit Hold'em. You can choose to set the game to Limit Texas Hold'em. To do this, you have to set line 53 to "false".

3) To set the number of bots and to choose them you have to modify line 40. In default the bot "SimpleBot" plays with itself. The string "DemoBots\SimpleBot"
points at the location of the bot which is in the folder DemoBots inside the project.

4) It is best to use a random deck. However if you want to use a serialized deck for various reasons, you can do so by 
uncommenting the import at line 7, uncommenting line 74 and commenting line 77.



Visualizing the hand history
----------------------------

The testbed writes a history file for you to review the hands. The hand history and the bankroll chart are saved into ..Heads-Up-No-Limit-Testbed\data .
You can visualize the hand history with a hand reviewer like Universal Replayer. You can find it at http://www.universal-replayer.net/

To use it, open the hand history txt, copy everything, open Universal Replayer, hit File -> Convert History, select Text and paste the hand history. 
Set Poker Room to FullTilt Poker, hit Convert and then Launch.


Encountering and solving problems
---------------------------------

1)Problem Encountered: The POM for nz.ac.waikato.cms.weka:weka-dev:jar:3.6.0 is missing, no dependency information available.

Solution:  

-Copy meerkat-api, weka-3.6.0 into \Java\apache-maven-3.3.3\bin

-Open cmd.exe

-cd \Java\apache-maven-3.3.3\bin

-Run: mvn install:install-file -Dfile=meerkat-api.jar -DgroupId=com.biotools.meerkat -DartifactId=meerkat-api -Dversion=1.0 -Dpackaging=jar

Same with weka-3.6.0
