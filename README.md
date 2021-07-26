# Maze Maker
Maze Maker is a game made using LibGDX and Java that allows users to create levels and play through other people's levels. Each level has a start and end point, and the player must solve their way through each level to complete the level.
## Table of Contents
## How to Play
### Making Levels
### Playing Levels
## Components
### Start Space
![Start Space](/core/assets/start.png)
- Space player is spawned at
### End Space
![End Space](/core/assets/end.png)
- Interacting with this space completes the level
### Floors
![Floor](/core/assets/floor.png)
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
- Square can be walked through after key is picked up
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
- Make Server for people to upload levels to
