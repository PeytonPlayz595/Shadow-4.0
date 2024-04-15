# Shadow-4.0


# Compiling desktop runtime
1. download the zip of this repo or make a github codespace (extract the zip to a folder if you downloaded a zip)
2. rename build.gradle to build-teavm.gradle or anything else
3. rename build-desktopruntime.gradle to build.gradle
4. open a terminal or cmd in the folder with the repo and type `./gradlew jar`

# Running desktop runtime without compiling to jar
note: this dosnt work on github codespaces
1. follow step 1 through 3 of "Compiling desktop runtime"
2. open a terminal or cmd in the folder with the repo and type `./gradlew runclient`
3. wait for it to compile the classes
