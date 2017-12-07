// Databricks notebook source
// MAGIC %md
// MAGIC ## Read the sensor values from the CSV file
// MAGIC The option `inferSchema` makes Spark guess the column type. For this file, it guesses correct!
// MAGIC 
// MAGIC `printSchema()` output the inferred schema:
// MAGIC 
// MAGIC ```
// MAGIC root
// MAGIC  |-- id: integer (nullable = true)
// MAGIC  |-- uid: string (nullable = true)
// MAGIC  |-- time: timestamp (nullable = true)
// MAGIC  |-- value: integer (nullable = true)
// MAGIC ```

// COMMAND ----------

/* Read the sensor data from DBFS into a DataFrame */
val sensorsDf = sqlContext.read.option("header", "true").option("inferSchema", "true").csv("/FileStore/tables/sensor_values_export.csv")
sensorsDf.printSchema()
sensorsDf.createOrReplaceTempView("sensorValues")


// COMMAND ----------

// MAGIC %md 
// MAGIC ## Calculating minute and hour averages with `window()`
// MAGIC 
// MAGIC It's that simple!

// COMMAND ----------

// MAGIC %sql
// MAGIC select uid, window(time, "1 minute").start as minute, avg(value) 
// MAGIC from sensorValues
// MAGIC where uid = 'CVW'
// MAGIC and time between '2017-11-28' and '2017-11-29'
// MAGIC group by uid, window(time, '1 minute')
// MAGIC order by minute

// COMMAND ----------

// MAGIC %sql
// MAGIC select uid, window(time, "1 hour").start as hour, avg(value) 
// MAGIC from sensorValues
// MAGIC where uid = 'CVW'
// MAGIC group by uid, window(time, '1 hour')
// MAGIC order by hour
