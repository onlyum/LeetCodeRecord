cmake_minimum_required(VERSION 3.16)
project(LeetCodeCPP)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Auto-discover all .cpp files under LeetCodeRecord/cpp
file(GLOB_RECURSE CPP_SOURCES "${CMAKE_CURRENT_SOURCE_DIR}/LeetCodeRecord/cpp/*.cpp")

# Create an executable target for each .cpp file
# Target names are sanitized: Q-number prefix is kept, Chinese chars are stripped
foreach(CPP_FILE ${CPP_SOURCES})
    get_filename_component(FILENAME_WE ${CPP_FILE} NAME_WE)
    string(REGEX MATCH "^Q[0-9]+" QNUMBER ${FILENAME_WE})
    if(QNUMBER)
        set(TARGET_NAME ${QNUMBER})
    else()
        string(REGEX REPLACE "[^A-Za-z0-9_]" "_" TARGET_NAME ${FILENAME_WE})
    endif()
    add_executable(${TARGET_NAME} ${CPP_FILE}
            LeetCodeRecord/cpp/滑动窗口/Q0003.cpp
            LeetCodeRecord/cpp/滑动窗口/Q0438.cpp)
endforeach()