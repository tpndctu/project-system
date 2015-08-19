package spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

/*
 * script run app with Spark :
 * 		spark-submit --class "spark.SimpleApp" target\spark-0.0.1-SNAPSHOT.jar
 */
public class SimpleApp {
	private static JavaSparkContext context;

	public static void main(String[] args) {
		System.out.println("Hello");
		String logFile = "E:\\spark\\README.md";
		SparkConf conf = new SparkConf().setAppName("Simple Application");
		context = new JavaSparkContext(conf);
		JavaRDD<String> logData = context.textFile(logFile).cache();
		long numAs = logData.filter(new Function<String, Boolean>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Boolean call(String s) throws Exception {
				// TODO Auto-generated method stub
				return s.contains("a");
			}
		}).count();

		long numBs = logData.filter(new Function<String, Boolean>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Boolean call(String s) throws Exception {
				// TODO Auto-generated method stub
				return s.contains("b");
			}
		}).count();

		System.out.println("A: " + numAs);
		System.out.println("B: " + numBs);
	}
}
