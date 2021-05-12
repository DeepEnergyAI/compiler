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
FLAGS = -g

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
