# Challenge 3 - Big Data and Text Analytics

For challenge 3, please collect your answers as scala Spark commands in a databricks notebook. Export the notebook (HTML or link), and paste the notebook in your teams's channel.

## 3.1 - Create a dataframe from the product metadata! How many records does the file contain?

Download the file with the metadata information [here (52 MB)](https://s3.amazonaws.com/nicolas.meseth/amazon_reviews/meta_Grocery_and_Gourmet_Food.json.gz). The format is a zipped `.gz`-file containing a JSON file. You don't have to unpack the file. Simply upload the file as is to databricks. Spark will detect that the file is zipped and automatically unzip on reading it.

> **HINT 1**: You can use the following line to read a JSON file in databricks as a Data Frame:

```scala
val df = spark.read.json("/FileStore/tables/<your-file-name>.gz")
```

> **HINT 2**: You can speed-up queries by caching a Data Frame on the cluster. Make sure you only cache frequently used DFs, as your cluster memory is limited:

```scala
df.cache()
```

## 3.2 - Which brands have the most products in the product metadata?

> **HINT 1**: Remember, you can use plain SQL to answer these questions! Just register a temporary view and use the `%sql` keyword!

## 3.3 - Which products claim the sales ranks 1 through 5 in "Grocery & Gourmet Food"?

> **HINT 1**: Use `printSchema()` to see the data type of the field `salesRank`

> **HINT 2**: Research online how to access this datatype and get the right value according to the question. Share your findings with the rest of the group - the first group to publish a working solution receives a **1% bonus** for challenge 3!

## 3.4 - On average, which brands have the most expensive products?

> **HINT 1**: Remember, you can use plain SQL to answer these questions! Just register a temporary view and use the `%sql` keyword!

## 3.5 - Create a dataframe from the product reviews! How many records does the file contain?

Download the file with the reviews [here (220 MB)](https://s3.amazonaws.com/nicolas.meseth/amazon_reviews/meta_Grocery_and_Gourmet_Food.json.gz). The format is a zipped `.gz`-file containing a JSON file. You don't have to unpack the file. Simply upload the file as is to databricks. Spark will detect that the file is zipped and automatically unzip on reading it.

## 3.6 - Create a new dataframe containing all review texts for Nestle products! How many reviews do all Nestle products have? 

> **HINT 1**: Remember you can create a new dataframe using SQL. Simply build your SQL statement and wrap it inside `val newDataframe = sqlContext.sql("...")`.

> **HINT 2**: You can use all SQL commands in Spark SQL, including JOINS.

> **HINT 3**: Name the resulting Data Frame `nestleReviews`, cache it, and create a temporary view, as we will continue working with this DF in the next tasks.

## 3.7 - Transform the review texts into a new data frame containing a word per record

> **HINT 1**: `split()` is also available as an SQL function

> **HINT 2**: The result from a `split()` operation is an array. So instead of a review text per record, you now have an array per record. That's not what we want. Check out `explode()` and see if it can help you!

> **HINT 3**: Save the resulting data frame as a new data frame. We'll need it subsequently.

## 3.8 - Based on the result from 3.7, what are the top 100 most frequent words and how often do they occur?

> **HINT 1**: To limit the result to 100 rows, simply append `limit 100` to your SQL query!


## 3.9 - Looking at the results of 3.8, what problems do you see? What could you do to increase the quality of the result?

> **HINT 1**: There are at least 3 distinct problems to mention here. Come up with and describe solutions to solve these problems. You don't have to implement them.


## 3.10 - Filter common English stop words to increase the result!

Download [this file](https://s3.amazonaws.com/nicolas.meseth/amazon_reviews/words.zip) containing a `.csv` file with common English stop words.

> **HINT 1**: Load the file as a data frame and use SQL to filter words contained in this file from the result of task 3.8

> **HINT 2**: The file contains only lower case words. How do you handle this?