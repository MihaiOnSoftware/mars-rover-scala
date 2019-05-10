[![CircleCI](https://img.shields.io/circleci/project/github/MihaiOnSoftware/mars-rover-scala.svg?style=flat-square)](https://circleci.com/gh/MihaiOnSoftware/mars-rover-scala)
[![codecov](https://img.shields.io/codecov/c/github/MihaiOnSoftware/mars-rover-scala.svg?style=flat-square)](https://codecov.io/gh/MihaiOnSoftware/mars-rover-scala)
[![Scala Versions](https://img.shields.io/badge/scala-2.12.3-blue.svg?style=flat-square)](https://github.com/MihaiOnSoftware/mars-rover-scala/blob/master/build.sbt#L5)

# Purpose
Once upon a time, this was a solution to the Mars Rover problem as part of an interview process.

Now it's a test-bed for different architectures, libraries, etc.
As well as a place where I can practice adding new features to an existing system.

Maybe I'll eventually make this into a web based game too.

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
