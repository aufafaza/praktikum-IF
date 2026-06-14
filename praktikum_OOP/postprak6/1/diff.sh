#!/bin/bash 

program="java Main.java" 
input_file="./contoh_input.txt" 
expected_output="./contoh_output.txt"
actual_output="./actual.txt" 

$program < "$input_file" > "$actual_output"

if diff -q --strip-trailing-cr "$actual_output" "$expected_output" > /dev/null; then 
	echo "correct" 
	rm "$actual_output"
	exit 0 
else 
	echo "something wrong below" 
	diff --color=always --strip-trailing-cr -U 0 "$actual_output" "$expected_output"
	exit 1 
fi 
	
