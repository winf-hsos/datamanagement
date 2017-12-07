// Databricks notebook source
// MAGIC %md
// MAGIC ## Read data from a text file into a DataFrame

// COMMAND ----------

/* The sqlContext allows access to DataFrames, and we can create DataFrames from any source with a schema */
val dfLines = sqlContext.read.option("header","true").csv("/FileStore/tables/script_lines_export.csv")
dfLines.count()

// COMMAND ----------

// MAGIC %md
// MAGIC ## Apply some basic filters with the DataFrame API

// COMMAND ----------

val what = dfLines.where("normalized_text LIKE 'what %'")
val whatHomer = what.where("normalized_text LIKE '%homer%'")
whatHomer.count()

// COMMAND ----------

// MAGIC %md
// MAGIC ## Create a table from a DataFrame

// COMMAND ----------

/* This creates a temporary table in our spark cluster memory */
dfLines.createOrReplaceTempView("script_lines");

// COMMAND ----------

// MAGIC %md
// MAGIC ## Use plain SQL to query that table
// MAGIC Adding the keyword `%sql` tells Databricks that the following code should be interpreted as plain SQL code.

// COMMAND ----------

// MAGIC %sql
// MAGIC select count(1) from script_lines
// MAGIC where normalized_text LIKE '%homer%'
// MAGIC and normalized_text LIKE 'what %'

// COMMAND ----------

// MAGIC %sql
// MAGIC /* Show the longest lines first */
// MAGIC select * from script_lines
// MAGIC order by length(normalized_text) desc
