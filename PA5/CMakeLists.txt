cmake_minimum_required(VERSION 3.6)   # CMake version check
project(PA5)               # Create project "simple_example"
set(CMAKE_CXX_STANDARD 11)            # Enable c++11 standard

set(SOURCE_FILES

        listFuncs.cpp
        listFuncs.h
        table.cpp
        table.h
        #concord.cpp
        grades.cpp
        #pa5list.cpp
        )

add_executable(PA5 ${SOURCE_FILES})