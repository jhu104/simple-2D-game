<h1>Simple 2D game</h1>
This game contains many different type of terrains:
grass,river,desert,mountain, and forest

the cost of each for player to move are as follow, respectively:
1,2,5,10, and 3

the players can either move up, down, left right or attack

the object hiearchy
Character
|
|HealthCharacter
||Player
||Monster
|
|Healer
|Animal

At this point, the main class creates World and then adds the neccessary Characters into the game<br>
then the game is started using the startGame method within World. The Game will continue until either <br>
100 moves have been made by each players or the players all died.
