#!/bin/sh 

program="java Main.java" 
input="input.txt" 
actual="actual.txt" 
output="output.txt" 

$program < "$input" > "$actual" 

if diff -q --strip-trailing-cr "$actual" "$output" > /dev/null; then 
	echo "done" 
	exit 0 
else 
	echo "something wrong below" 
	diff --color=always --strip-trailing-cr -U 0 "$actual" "$output" 
	exit 1	
fi 
