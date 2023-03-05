# InventorySystem
## A Java game that uses the terminal where the player tries to go for as much value or strength as possible

## How does anyone play the game?

Anyone should be aiming to see the game like this:

![image](https://user-images.githubusercontent.com/22280271/222943976-e03c20fa-80cf-4289-8204-cdb8e14b169f.png)

First, all 9 files that end with .txt or .java must be downloaded all in the same location (like a folder). To do that, it is suggested to download the ZIP file from [Releases](https://github.com/bluelightspirit/InventorySystem/releases/) or copy the repo from a console like Git. It is also suggested to double check if all 3 files are in the same folder. Then, all 3 files should be compiled. After that, [Main.java](https://github.com/bluelightspirit/InventorySystem/blob/main/Main.java)'s main method should be ran using any IDE that supports Java such as [IntelliJ](https://www.jetbrains.com/idea/download/) or [BlueJ](https://www.bluej.org/).

Then, to play the game, the user should input options between 1-6 for the action they want to do.

Note: When adding random items (entering 2), there is a weight limit of 25,000 pounds by default. Currently, the only way to change this is to go to Main.java & edit the maxWeight number within the created Inventory stuff object.

If the user has never played any sort of game where you equip weapons or armor, they can reference [More examples on how the game works (without actually playing)](https://github.com/bluelightspirit/InventorySystem#more-examples-on-how-the-game-works-without-actually-playing)

## What's the goal of the game?

Currently, the goal is to go for as much value or strength as possible. It is up to the player which one they would rather go for the most.

## What does (J/ms in SkyWars) after Strength mean?

In Minecraft Java Edition for PC, there is a server called Hypixel that has a game called SkyWars. In that game, strength matters a lot so you can have the advantage over other players as it's a competitive game where people fight to the death to win, like survival to fittest. J/ms is joules per milliseconds, which is power in Physics.

## More examples on how the game works (without actually playing)

Adding a random item should look like this:

![image](https://user-images.githubusercontent.com/22280271/222944137-59213f42-fa58-448a-a248-7ccd1a76bebe.png)

Showing inventory should look like this:
![image](https://user-images.githubusercontent.com/22280271/222944147-77c1bc2b-9206-4029-969e-fc61cd539d34.png)

Since the randomized item in the example is a miscellaneous item, it cannot be equipped.

Dropping an item should look like this:
![image](https://user-images.githubusercontent.com/22280271/222944203-e25dbd73-f839-4065-b0a4-7ae9cf1d316f.png)

Which then should show an empty inventory:
![image](https://user-images.githubusercontent.com/22280271/222944216-76aeee14-26fb-4eba-953b-99f28c4a85e8.png)

Equipping a weapon or armor should look like this:
![image](https://user-images.githubusercontent.com/22280271/222944283-d625b3fb-f4a0-4e78-8e21-c4a9efd7c1ee.png)

An equipped weapon or armor should have () after the item details when showing inventory, like this:
![image](https://user-images.githubusercontent.com/22280271/222944311-a9c609de-f80b-4905-af8c-e34dde12a6e1.png)

Equipping a different weapon or a different armor already equipped should override the previous weapon equipped.

Unequipping or dropping an equipped weapon or equipped armor should make the () disappear after the item details when showing inventory.

## What did I learn?

1) How to create an object that references another object from another class, regardless of how many object parameters may be needed.
2) That adding "static" to all of the Item class' and Inventory class' methods means that only one Inventory object can be created, meaning the game would have no potential to have multiple players.
3) How to assign values to every object in the enum created.
4) How .ordinal() & .values() works in enums (to traverse through the enum's constants).
5) That enum's allow methods & variables to be created similar to a class.

## What goal(s) did I accomplish?

I created an inventory system where the player can aim to go for as much value ($) or strength (J/ms in SkyWars) as much as possible by adding random items to their inventory while trying not to exceed a maximum weight!

## Compiling

This program uses solely Java to compile.
