# Maze Maker
Maze Maker is a game made using LibGDX and Java that allows users to create levels and play through other people's levels. Each level has a start and end point, and the player must solve their way through each level to complete the level.
## Table of Contents
## How to Play
A player will start on a start space, and their goal is to interact with an end space to complete the level, having to traverse through various puzzles created by other players.
### Making Levels
Each level must have exactly 1 start space and at least 1 end space for a player to interact with. Currently levels do not have to be validated, but that will be implemented in the future.

Components can be dragged from the top bar onto the grid by simply clicking on the component, then clicking where on the grid you want to place it.

The grid can also be resized to whatever dimensions fit your need by entering your new width and height, then pressing `resize`.

To save a grid, click the `save` button, then select the path you want to save your file to. It will be saved as a `.mzmk` file, which is a required extension for loading levels to play.

### Playing Levels
To load a level, press the `select` button, select the file to upload, then click the `load` button.

After loading a level, the player will spawn on the start space and can move around using the arrow keys. To interact with an object, press space. The object you interact with will be on the grid space above, below, left, or right of the player, depending which direction they are facing.

Beating a level will take you to a win screen, where you can go back to either play another level or make one yourself.

## Components
### Start Space
![Start Space](/core/assets/start.png)
- Space player is spawned at
### End Space
![End Space](/core/assets/end.png)
- Interacting with this space completes the level
### Floors
![Floor](/core/assets/floor.png)
- Default space that can always be walked through
### Walls
![Wall](/core/assets/wall.png)
- Normal Space that can always be walked through
- Can never be walked through
### Power Switch
![Power Switch](/core/assets/power-off.png) ![Power Switch](/core/assets/power-on.png)
- Switches the power off (left image) or on (right image) depending on if the power was previously on or off
- Cannot be walked through
### Keys
![Key](/core/assets/key.png)
- Can be picked up by interacting with a square with a key
- Square becomes regular floor once key is picked up
### Doors
![Door](/core/assets/closed-door.png) ![Door](/core/assets/open-door.png)
- Door starts as closed (left image) and cannot be walked through
- Door can be unlocked (right image) if the power is on and the player interacting with the door has a key, which allows it to be walked through
### Electric Fences
![Electric Fence](/core/assets/electric-fence-disabled.png) ![Electric Fence](/core/assets/electric-fence-enabled.png)
- Electric fences are disabled (left image) or enabled (right image) if the power is off or on
- An electric fence can only be walked through if it's disabled
### Floor Switches
![Floor Switches](/core/assets/switch-off.png) ![Floor Switches](/core/assets/switch-orange.png) ![Floor Switches](/core/assets/switch-blue.png)
- Cannot be interacted with is power is off (left image)
- Switches the temporary floor state from orange (middle image) or blue (right image) depending if the previous floor state was blue or orange
- Cannot be walked through
### Temporary Floors
![Temporary Floor](/core/assets/invisible-floor.png) ![Temporary Floor](/core/assets/orange-floor.png) ![Temporary Floor](/core/assets/blue-floor.png)
- Can only be walked through if the floor state is the same as the temporary floor color
- Orange (middle image) can only be walked through if the floor state is orange, same for blue with a blue floor state (right image)
- If a floor cannot be walked through currently, it will appear as invisible (left image)
## Current Bugs / Future Improvements
- UI overhaul
- Add level completion validation
- Add more components
- Create a proper player sprite
- Make Server for people to upload levels to
- Please leave other suggestions for improvements!
