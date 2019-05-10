[![CircleCI](https://img.shields.io/circleci/project/github/MihaiOnSoftware/mars-rover-scala.svg?style=flat-square)](https://circleci.com/gh/MihaiOnSoftware/mars-rover-scala)
[![codecov](https://img.shields.io/codecov/c/github/MihaiOnSoftware/mars-rover-scala.svg?style=flat-square)](https://codecov.io/gh/MihaiOnSoftware/mars-rover-scala)

# Purpose
Solution to the Mars Rover problem as part of an interview process. Written in Scala.

# Description of the problem

A squad of robotic rovers are to be landed by NASA on a plateau on Mars.

This plateau, which is curiously rectangular, must be navigated by the rovers so that their on board cameras can get a complete view of the surrounding terrain to send back to Earth.

A rover's position is represented by a combination of an x and y co-ordinates and a letter representing one of the four cardinal compass points. The plateau is divided up into a grid to simplify navigation. An example position might be 0, 0, N, which means the rover is in the bottom left corner and facing North.

In order to control a rover, NASA sends a simple string of letters. The possible letters are 'L', 'R' and 'M'. 'L' and 'R' makes the rover spin 90 degrees left or right respectively, without moving from its current spot.

'M' means move forward one grid point, and maintain the same heading.

Assume that the square directly North from (x, y) is (x, y+1).

## Input:

The first line of input is the upper-right coordinates of the plateau, the lower-left coordinates are assumed to be 0,0.

The rest of the input is information pertaining to the rovers that have been deployed. Each rover has two lines of input. The first line gives the rover's position, and the second line is a series of instructions telling the rover how to explore the plateau.

The position is made up of two integers and a letter separated by spaces, corresponding to the x and y co-ordinates and the rover's orientation.

Each rover will be finished sequentially, which means that the second rover won't start to move until the first one has finished moving.

## Output:

The output for each rover should be its final co-ordinates and heading.

Test Input:

    5 5
    1 2 N
    LMLMLMLMM
    3 3 E
    MMRMMRMRRM

Expected Output:

    1 3 N
    5 1 E

# How to run

Install [SBT](http://www.scala-sbt.org/)

in the command line run "sbt compile" in the project's root directory where the build.sbt file is(for example /home/mpopescu/workspaces/other/rover)

to run tests run "sbt test" in the project's root directory

to run the app run "sbt run" in the project's root directory, note that the app waits for user input to end, on linux/mac that's CTRL + D, it can also be CTRL + C on windows machines (if for some reason this doesn't work, just use the hard coded values in ProcessTest)

## Assumptions Made

Rovers cannot pass through other Rovers

Rovers cannot escape the plateau

Invalid input will return a short message why it's invalid and the program will terminate

## Thought Process

As part of the interview process I had to outline my thought process and approach to this problem:

I tried to approach this in a functional way while also staying true to the domain driven design, because of this I started with the domain first and later worked my way up to the interface.

The original idea was to have a plateau, rovers, and instructions for those rovers. That mostly stayed through to the end as can be seen in the three packages movement, rover, and world.

I tried to stick to feature and domain based packaging rather than splitting by layer, however I ended up pulling the interface stuff into it's own package because it just didn't belong anywhere else.

Originally the instructions were a simple function that was applied on a rover and changed it's state, as I developed further it grew to include plateau and other rovers, especially for the move function.

I wanted to keep the Readers very generic and provide a way to "read" in different domain concepts from any source, I've only written the string readers however I played around with the idea of Json readers using the play json library but eventually discarded this as unnecessary for the given requirements. I wanted the Readers to live by their domain concepts and not be tied to any one interface implementation since code that changes together should live together.

I've made use of Type Classing in the Process class to provide compile time injection of different potential readers and writers allowing this code to remain generic and pushing the knowledge of what types we're dealing with to the top most layer.

The Rove class is essentially an interactor, it understands domain concepts that have been read in by the readers and knows what to do with them to effectively run the domain. I tried to prettify the interface for it as much as possible, thinking of it as a sort of internal API. This is essentially where the domain ends and the outward facing world begins.

I tried to stay away from utils and anything that wasn't either domain code or user interface code.

Some things that could still be improved are:
 1. Refactoring the tests, there's a lot of repeated code in them
 2. Refactoring the exceptions and pulling them all into one place
 3. Refactoring some of the error handling, some of the code is repeated and not that pretty.

Despite that I think the code is in good enough shape to ship out to you. Hope you enjoy it, I certainly had fun making it.

# Things I Would Change
I would rather have taken a more TDD approach then I did, as it is the design is a bit fragile when it comes to the `Instructions` class.
