cmake_minimum_required(VERSION 3.16)
project(LeetCodeCPP)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# 自动扫描 LeetCodeRecord/cpp 下的所有 .cpp 文件
file(GLOB_RECURSE CPP_SOURCES "${CMAKE_CURRENT_SOURCE_DIR}/LeetCodeRecord/cpp/*.cpp")

# 为每一个 .cpp 文件创建一个独立的 executable
foreach(CPP_FILE ${CPP_SOURCES})
    get_filename_component(FILENAME_WE ${CPP_FILE} NAME_WE)

    # 提取 Q+数字 作为 Target 名称（如 Q0438）
    string(REGEX MATCH "^Q[0-9]+" QNUMBER ${FILENAME_WE})
    if(QNUMBER)
        set(TARGET_NAME ${QNUMBER})
    else()
        string(REGEX REPLACE "[^A-Za-z0-9_]" "_" TARGET_NAME ${FILENAME_WE})
    endif()

    # 重点修正：每个 Target 只包含它自身的 CPP_FILE，不要多加其他 cpp！
    add_executable(${TARGET_NAME} ${CPP_FILE})
endforeach()