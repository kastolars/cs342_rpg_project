Author: Karol Stolarski
NetID: kstola2
CS342 Semester Project Deliverable 2

Version 4.0:



Version 3.0:

The player may now discover and pick up items in the environment.
Some of these items are keys, which have the ability to be used
on door locks, provided that the key pattern matches the lock pattern.
There are also master-keys that are supported; these will open
any door. They player also has a health bar and an carrying
capacity limit. If the player has less than 100 health, they
can drink potions to replenish their health bars. There is also a
limit on carrying capacity, so the player must carefully choose
which items are worth picking up. If the player picks up a leather
bag, this will support more items to be carried.

SETUP: Running the program with a filename (WITH NO SPACES)
as a system argument should parse the file and set up the game
for the player. Otherwise, a filename will need to be provided
by the player via standard input.


Version 1.0:

This program runs from the GameTester class. It has a hardcoded set of places
that make up the game world. For now, a player is able to move
from place to place and explore the game world. Upon start of
the game, the player will see a name and description of the
current place they are in. They are able to execute the following commands:
Look (returns a name and description of the current room),
Quit/Exit (These will terminate the game), or they can provide a direction
(full word or just the first character) to go in, optionally preceded by
the word "go". The directions supported currently are the four main
cardinal directions, as well as up and down, as we were told that is
all we need for the current deliverable. If a player chooses to
move in a direction, it must satisfy two criteria: (1) that it is
a direction that has a way out of the current place you're in,
(2) if the direction exists and is valid, that it is also unlocked.
Locked directions are inaccessible. All commands the player
inputs are case insensitive so the player so so long as the player
adheres to the input criteria, case shouldn't matter. Exceptions are
thrown when attempting to follow a locked direction.