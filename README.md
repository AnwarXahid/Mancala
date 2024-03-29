# Mancala

We are going to implement a game player which uses adversarial search algorithms to play a two player game.
The basic implementation includes minimax algorithm with alpha-beta pruning. Also, you can experiment with 
different search strategies. You can experiment with iterativedeepening search with varying depth-limits and
move-ordering. You should also implement various heuristics and find out which one is better by running experiments.
For example, you can determine the win-loss ratio by running 100 games with computervs-computer autoplay.
The task is to implement a game player for mancala. 
( http://play-mancala.com/ provides an online implementation. )
A heuristic functions estimates how good a particular state is for a player. A number of factors determine whether 
a given state of the game is good for a player.  

# Mancala-heuristics
In Mancala, the board consists of six (6) bins on each side, and a home position (called storage) on the right of the bins. 
The board is laid out typically starting with four stones in each bin and both storage bins empty.
For Mancala, the following strategic factors  can be used to design a good heuristic to determine how favorable a
particular position is for a player:

(1)  First valid move [furthest valid bin ( a bin on my side which is not empty) from my storage ]

(2) How far ahead of my 
opponent I am now [the difference in stone count between my storage and opponent's storage]

(3)	How close I am to winning (If my storage is already close to containing half of total number of stones)

(4)	How close opponent is to winning 

(5)	the total number of stones residing in the six bins of my side

(6)	the total number of stones residing in the six bins of opponent's side

(7)	Number of stones close to my storage ( a stone, although residing in a bin on my side, is not close to my storage,
if it is going to be overflowed to my opponent's side )

(8)	Number of stones close to  my opponent's storage

(9)	Have I earned an extra move

(10)	Have I captured any stone
You can experiment with the following four heuristics (and some of your own heuristics): 

# heuristic-1: The evaluation function is
( stones_in_my_storage – stones_in_opponents_storage )

# heuristic-2: The evaluation function is
W1 * (stones_in_my_storage – stones_in_opponents_storage) + W2 * (stones_on_my_side – stones_on_opponents_side)

# heuristic-3: The evaluation function is
W1 * (stones_in_my_storage – stones_in_opponents_storage) + W2 * (stones_on_my_side – stones_on_opponents_side) + W3 * (additional_move_earned)

# heuristic-4: The evaluation function is
W1 * (stones_in_my_storage – stones_in_opponents_storage) + W2 * (stones_on_my_side – stones_on_opponents_side)  + W3 * (additional_move_earned) + W4 * (stones_captured)
