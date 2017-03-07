#!/bin/bash
sqlplus -s system/manager@//localhost:1521/XE << EOF
whenever sqlerror exit sql.sqlcode;
set heading off

@create_user.sql
@drop_tables.sql
@create_tables.sql
@insert_product_category.sql
@insert_product.sql
exit;
EOF
