#!/bin/bash

CHECK_LANGUAGE=()

# Check for C language
if [[ -f Makefile ]]; then
    CHECK_LANGUAGE+=("c")
fi

# Check for Java language
if [[ -f app/pom.xml ]]; then
    CHECK_LANGUAGE+=("java")
fi

# Check for JavaScript language
if [[ -f package.json ]]; then
    CHECK_LANGUAGE+=("javascript")
fi

# Check for Python language
if [[ -f requirements.txt ]]; then
    CHECK_LANGUAGE+=("python")
fi

# Check for Befunge language
if [[ -f app/main.bf ]]; then
    CHECK_LANGUAGE+=("befunge")
fi

# Check if no or multiple languages detected
if [[ ${#CHECK_LANGUAGE[@]} == 0 || ${#CHECK_LANGUAGE[@]} != 1 ]]; then
    echo 'Error: Unsupported or multiple languages detected.'
    exit 84
fi

# Docker build based on Dockerfile existence
if [[ -f Dockerfile ]]; then
    docker build . -t "whanos-${CHECK_LANGUAGE[0]}"
else
    docker build . -f "../../images/whanos-${CHECK_LANGUAGE[0]}/Dockerfile.standalone" -t "whanos-${CHECK_LANGUAGE[0]}"
fi
