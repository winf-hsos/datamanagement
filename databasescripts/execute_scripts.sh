#!/bin/bash
sqlplus -s system/manager@//localhost:1521/XE << EOF
whenever sqlerror exit sql.sqlcode;
set heading off

@/home/vagrant/vagrant-ubuntu-oracle-xe/database/create_user.sql
@/home/vagrant/vagrant-ubuntu-oracle-xe/database/drop_tables.sql
@/home/vagrant/vagrant-ubuntu-oracle-xe/database/create_tables.sql
@/home/vagrant/vagrant-ubuntu-oracle-xe/database/insert_product.sql
@/home/vagrant/vagrant-ubuntu-oracle-xe/database/insert_product_category.sql
exit;
EOF
