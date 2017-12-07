# Uploading data to Databricks

Databricks Community Edition allows you to store file in the Databricks File System (DBFS). Using the web interface, you can upload files from your local computer to the DBFS. Make sure the file are not too big, as they must go over the internet and large files might take a while.

## Step 1: Sign into Databricks

If you don't have an account yet, sign up for [Databricks Community Edition](https://databricks.com/try-databricks) (it's free). If you have an account, [log in](https://community.cloud.databricks.com/login.html) to access your dashboard.

## Step 2: Upload your file via the data menu

Click on the data menu entry on the left. You should see the default database in your database list. Click on that and then click the plus symbol to add a new table. This takes you the a dialog like this:

![Databricks Create Table Dialog](/media/databricks-create-table-dialog.png)

Select "Upload File" and then click the grey area (or drag & drop a file from your file browser to that area) to upload a file. When the upload finishes, you'll see the path to that file in the DBFS. Copy that path to your clipboard.

## Step 3: Use the file path to create an RDD or DataFrame

Now that you have the file in DBFS, you can access the file from any notebook.

### Create an RDD from a text file in DBFS

```scala
val lines = sc.textFile("/FileStore/tables/script_lines_export.csv");
```

### Create a DataFrame from a text file in DBFS

```scala
val lines = sqlContext.read.csv("/FileStore/tables/script_lines_export.csv");
```
