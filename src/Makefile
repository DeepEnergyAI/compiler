####
# This Makefile can be used to make a scanner for the Simple language
# (Yylex.class) and to make a program that tests the scanner (P2.class).
#
# make clean removes all generated files.
#
###

###
# define the Java compiler and flags
###
JC = javac
FLAGS = -g -cp .:java-cup-11b-runtime.jar
RFLAGS = -cp .:java-cup-11b-runtime.jar

###
# Here are the rules.
###

P2.class: P2.java Yylex.class sym.class
	$(JC) $(FLAGS) P2.java

Yylex.class: simple.jlex.java Errors.class sym.class
	$(JC) $(FLAGS) simple.jlex.java

simple.jlex.java: simple.jlex
	jlex simple.jlex

sym.class: sym.java
	$(JC) $(FLAGS) sym.java

Errors.class: Errors.java
	$(JC) $(FLAGS) Errors.java

build_P2: P2.class Yylex.class simple.jlex.java sym.class Errors.class

P2: $(test_file) build_P2 
	java $(RFLAGS) P2 $(test_file)
###
# handout
###
handout:
	zip handout.zip Errors.java Makefile simple.jlex P2.java test.sim test2.sim sym.java eof.sim
###
# submit
###

submit:
	zip submit.zip *.java test.sim Makefile simple.jlex test.sim test2.sim Errors.java 
