/*
findGhostMarbleImage() that does the same as findSelectedMarbleImage

findPlayer() that returns the text at the top of screen

Skip turn if no moves are available so add isLegalMove() in GameBoard - that will check move() in GameBoard for all 4 marble locations

We don't want to allow jumps
______________________________

Need to update Gameboard request move class to manipulate hole object to setEmpty false, color, etc.

Implement ability to roll again if 6 || 1 rolled

Add Knockoff() in GameBoard class

Add GameBoard class duties to GameOver such as clearing the board, resetting the data to original values

_________________________________

Don't allow player to skip move by double tapping


Note: call from GameActivity to isLegalMove() to check all 4 if return the same value

Fix bugs along the way


Add checkisGameOver() to check gameOver in GameBoard class and redraw empty board

 */
