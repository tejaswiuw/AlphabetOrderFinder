# AlphabetOrderFinder

Java program to find the order of the alphabets based on a list of sorted words provided as input.


## Problem Statement:

You are given a list of words sorted in alphabetical order. The only issue is that this alphabet isn’t English. Can you determine the ordering of the alphabet?

Note that you can assume this list of words has enough information to derive the complete order of the alphabet.

Sample input: ["bca", "aaa", "acb"]

Sample output: ['b', 'a', 'c']  

## Assumptions

Following assumptions were made while writing the program - 

- All the alphabet characters belong to the actual english alphabet set - 'a' to 'z' (irrespective of the order)
- All the alphabet characters are in lowercase
- If the input order is invalid, program returns an empty list of characters

## Test Cases

Following are some of the testcases that validates the correctness of the program - 

| Test Case      | Input (String Array)           | Output (List of Characters)  |
| :------------- |:-------------:| :-----:|
| Null value as input     | null | [] |
| Empty array of words    | {} | [] |
| Array of empty strings    | {"",""} | [] |
| One of the input words is null | {"abc", null} | [] |
| Array of words with a word followed by its prefix (invalid input)| {"foo","fo"}     |   [] |
| Incomplete array of words with one word (invalid input)| {"git"}     |   (any order of characters) |
| Incomplete array of words with length more than 1 (invalid input)| {"itg", "tig"}     |   (any order of characters) |
| Array of words with complete information (valid input) | {"bca", "aaa", "acb"} |    ['b', 'a', 'c'] |
