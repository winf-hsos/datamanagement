// Databricks notebook source
// MAGIC %md
// MAGIC ##  Load script lines from text file

// COMMAND ----------

/* Load the previously uploaded file into the variable lines. The result is an RDD. */
val lines = sc.textFile("/FileStore/tables/script_lines_export.csv");

// COMMAND ----------

// MAGIC %md
// MAGIC ##  Apply some filters

// COMMAND ----------

/* Filter the RDD to contain only lines that start with a what followed by a space */
val startsWithWhat = lines.filter(_.startsWith("what "));

/* Show the remaining rows after the filter has been applied. This is an action. */
startsWithWhat.count()


// COMMAND ----------

/* Filter the RDD from the first filter step to get only lines with 'homer' in them */
val containsHomer = startsWithWhat.filter(_.contains("homer"));

/* How many rows are now left? */
containsHomer.count()

// COMMAND ----------

/* Get the result from the last RDD and apply println for each row */
containsHomer.collect().foreach(println)

// COMMAND ----------

// MAGIC %md
// MAGIC ##  Map the result to represent the line lengths

// COMMAND ----------

/* Convert (or map) each line to a represent only its length */
var lineLengths = containsHomer.map(line => line.length)
lineLengths.collect()

// COMMAND ----------

// MAGIC %md
// MAGIC ##  Word count with RDDs

// COMMAND ----------

/* Perform a word count on the result from the last RDD 
 * Step 1: Split words by space */
val splitWords = containsHomer.flatMap(_.split(" "))

/* Show the first ten results */
splitWords.take(10)

// COMMAND ----------

/* Step 2: Map each word into a key value pair of (word, 1) */
val keyValuePairs = splitWords.map((word => (word, 1)))

/* Show the first ten results */
keyValuePairs.take(10)

// COMMAND ----------

/* Step 3: Reduce the key value pairs and sum up the value for each key */
val reducedPairs = keyValuePairs.reduceByKey(_ + _)

/* Output the word count result */
reducedPairs.collect().foreach(println)

// COMMAND ----------

/* Sort by value (number of words) */
val sortedReducedPairs = reducedPairs.sortBy(pair => pair._2, false)

/* Output the sorted word count result */
sortedReducedPairs.collect().foreach(println)


// COMMAND ----------

// MAGIC %md
// MAGIC ##  Word count in one step

// COMMAND ----------

/* Whe can chain the steps together since each transformation
 * returns another RDD, on which can execute the next step */
val counts = containsHomer.flatMap(_.split(" "))
      .map((word => (word, 1)))
      .reduceByKey(_ + _)
      .sortBy(pair => pair._2, false)

counts.collect().foreach(println)