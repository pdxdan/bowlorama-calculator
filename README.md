# bowlorama-calculator

Part of the bowlorama demo application. This piece provides a functional programming approach to the classic 
bowling game score calculator. The functions here have now side affects and attempt to encapsulate the voodoo
of calculating bowling scores. 
 
Two functions are exposed from this library to Lambda:
- bowlorama-sore, which calculates a score from a series of rolls. 
- bowlorama-to-frames, which calculates the score for each frame from a series of rolls.

The other pieces of Bowlorama are in the following repos:
https://github.com/pdxdan/bowlorama-calculator
https://github.com/pdxdan/bowlorama-history-tracker
https://github.com/pdxdan/bowlorama-jsclient

## Usage

Simply call the endpoint, passing it a series of rolls as in the following example:

{
    "rolls": "10,1,2,3,4"
}
which should return a score of 23


## Testing

The unit tests here are all self contained and should all pass when running "lein test"
The tests are intented to be further documentation of how to use these functions. 