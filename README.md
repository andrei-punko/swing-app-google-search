# Test task: Swing desktop app to make Google search

[![Maven CI](https://github.com/andrei-punko/swing-app-google-search/actions/workflows/maven.yml/badge.svg)](https://github.com/andrei-punko/swing-app-google-search/actions/workflows/maven.yml)
[![Coverage](.github/badges/jacoco.svg)](https://github.com/andrei-punko/swing-app-google-search/actions/workflows/maven.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## Prerequisites:

- Maven 3 (embedded)
- JDK 21

## Task definition

1. Try to develop desktop application which consists of form with:
    - Input field (JTextField)
    - `Search` button (JButton)
    - Table (JTable)

2. After press on `Search` button we need to get result page from Google by entered into input field phrase
   (for example https://www.google.com/search?q=open+source for phrase `open source`)

3. Get 10 result urls from this page and put them into table

*) If you want to avoid UI hanging - make data extraction in separate thread, not in Swing thread

**) If you want, make results in table for different phrases visually different  
(results gotten by same phrase will be overwritten)

Hint: to get page from Google you could use
[this](https://stackoverflow.com/questions/3727662/how-can-you-search-google-programmatically-java-api)
code from StackOverflow
