# stat-z-test
Runs a statistical Z-test to test the mean of a provided pattern/sample with normal distribution. More info: [Z-test](https://en.wikipedia.org/wiki/Z-test)
## Example
```
statZtest.java 500 8 95 ..\resources\pattern.txt
```
Supplied CLI in order: *μ0, variance, significance level, path to a text file containing the sample*

Numbers in the file can be delimited by commas, semicolons, or spaces; and additional grouped into any amount of lines as newlines are omitted.
## Built with
[**Maven**](https://maven.apache.org/) - Dependency Management
