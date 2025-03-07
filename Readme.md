# Compile Main.java
javac -cp ../htmlparser1_6_20060610/htmlparser1_6/lib/htmlparser.jar Crawler.java Main.java && java -cp .:../htmlparser1_6_20060610/htmlparser1_6/lib/htmlparser.jar Main

# Compile Crawler.java
javac -cp ../htmlparser1_6_20060610/htmlparser1_6/lib/htmlparser.jar Crawler.java

# Compile InvertedIndex.java
javac -cp ../jdbm-1.0/lib/jdbm-1.0.jar InvertedIndex.java && java -cp ../jdbm-1.0/lib/jdbm-1.0.jar InvertedIndex.java

javac -cp .:../htmlparser1_6_20060610/htmlparser1_6/lib/htmlparser.jar:../jdbm-1.0/lib/jdbm-1.0.jar Crawler.java Main.java && java -cp .:../htmlparser1_6_20060610/htmlparser1_6/lib/htmlparser.jar:../jdbm-1.0/lib/jdbm-1.0.jar Main