# Makefile for CS 455 extra credit assgt
#
#
#     make ectest
#        Makes ectest executable
#


#CXX = g++

CXXFLAGS = -ggdb -Wall
OBJS = ecListFuncs.o ectest.o


ectest: $(OBJS)
	$(CXX) $(CXXFLAGS) $(OBJS) -o ectest

$(OBJS): ecListFuncs.h
