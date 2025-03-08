# Compile Main.java
javac -cp .:../htmlparser1_6_20060610/htmlparser1_6/lib/htmlparser.jar:../jdbm-1.0/lib/jdbm-1.0.jar Crawler.java Main.java && java -cp .:../htmlparser1_6_20060610/htmlparser1_6/lib/htmlparser.jar:../jdbm-1.0/lib/jdbm-1.0.jar Main

# Compile Crawler.java
javac -cp ../htmlparser1_6_20060610/htmlparser1_6/lib/htmlparser.jar Crawler.java

# Compile InvertedIndex.java
javac -cp ../jdbm-1.0/lib/jdbm-1.0.jar InvertedIndex.java

# Compile StopStem.java
javac StopStem.java


# Compile amd run
javac -cp .:../htmlparser1_6_20060610/htmlparser1_6/lib/htmlparser.jar:../jdbm-1.0/lib/jdbm-1.0.jar *.java && java -cp .:../htmlparser1_6_20060610/htmlparser1_6/lib/htmlparser.jar:../jdbm-1.0/lib/jdbm-1.0.jar Main
