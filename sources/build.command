DIR="/Users/macuser/programs/Java/Functional\ \(bad\ paths\)/2-D\ Cellular\ Automata/" \
&& cd /Users/macuser/programs/Java/Functional\ \(bad\ paths\)/2-D\ Cellular\ Automata/sources \
&& javac -d /Users/macuser/programs/Java/Functional\ \(bad\ paths\)/2-D\ Cellular\ Automata/classes cellular/*.java \
&& cd ../classes \
&& jar cfm Cells.jar Control.mf cellular/main.class cellular/*.class resources text