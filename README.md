# JSONParserStackOverflowExample
Demonstrates JSONParser StackOverflowError in case of long arrays
There are two tests TestJSONParserSpec, one demonstrating that JSONParser#parseJSON works
fine with short length arrays, and the other one demonstrates that it throws StackOverflowError in
case of long arrays.
