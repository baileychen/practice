For example, if you have:
entrances = [0, 1]
exits = [4, 5]
path = [
[0, 0, 4, 6, 0, 0], # Room 0: Bunnies
[0, 0, 5, 2, 0, 0], # Room 1: Bunnies
[0, 0, 0, 0, 4, 4], # Room 2: Intermediate room
[0, 0, 0, 0, 6, 6], # Room 3: Intermediate room
[0, 0, 0, 0, 0, 0], # Room 4: Escape pods
[0, 0, 0, 0, 0, 0], # Room 5: Escape pods
]

Then in each time step, the following might happen:
0 sends 4/4 bunnies to 2 and 6/6 bunnies to 3
1 sends 4/5 bunnies to 2 and 2/2 bunnies to 3
2 sends 4/4 bunnies to 4 and 4/4 bunnies to 5
3 sends 4/6 bunnies to 4 and 4/6 bunnies to 5

So, in total, 16 bunnies could make it to the escape pods at 4 and 5 at each time step. (Note that in this example, room 3 could have sent any variation of 8 bunnies to 4 and 5, such as 2/6 and 6/6, but the final solution remains the same.)
