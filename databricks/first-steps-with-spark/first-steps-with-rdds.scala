// Databricks notebook source
val lines = sc.textFile("/FileStore/tables/script_lines_export.csv");

// COMMAND ----------

var lineLengths = lines.map(line => line.length)
lineLengths.take(10)

// COMMAND ----------

val containsHomer = lines.filter(_.contains("homer"));
val startsWithWhat = containsHomer.filter(_.startsWith("what "));
startsWithWhat.count()
//startsWithWhat.takeSample(false, 10).foreach(println);

// COMMAND ----------

val dfLines = sqlContext.read.option("header","true").csv("/FileStore/tables/script_lines_export.csv")
val what = dfLines.where("normalized_text LIKE 'what %'")
val whatHomer = what.where("normalized_text LIKE '%homer%'")
whatHomer.count()


// COMMAND ----------

dfLines.createOrReplaceTempView("lines");

// COMMAND ----------

// MAGIC %sql
// MAGIC select count(1) from lines
// MAGIC where normalized_text LIKE '%homer%'
// MAGIC and normalized_text LIKE 'what %'

// COMMAND ----------

val sensorsDf = sqlContext.read.option("header", "true").option("inferSchema", "true").csv("/FileStore/tables/sensor_values_export.csv")
sensorsDf.createOrReplaceTempView("sensorValues")
sensorsDf.printSchema()

// COMMAND ----------

// MAGIC %sql
// MAGIC select date_format(time, 'YYYY-MM-dd hh:mm') as minute,
// MAGIC from sensorValues

// COMMAND ----------

import org.apache.spark.sql.functions._
val sensorDfWithMinute = sensorsDf.withColumn("minute", date_format($"time", "YYYY-MM-dd hh:mm"));
sensorDfWithMinute.createOrReplaceTempView("sensorValues")

// COMMAND ----------

// MAGIC %sql
// MAGIC select * from (
// MAGIC SELECT id, uid, time, minute, LAG(minute) OVER ( PARTITION BY uid ORDER BY time ) as prevMinute
// MAGIC FROM sensorValues
// MAGIC   ) 
// MAGIC   where minute <> prevMinute
