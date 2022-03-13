# SpringHttpOnlyCookie
Spring Http Only Cookie Test

## Start MySQL Container and Load Data
1. Start MySQL Container
  ```shell
  docker run --name mysql-container -p 33061:3306  -e MYSQL_ROOT_PASSWORD=test12345 -e MYSQL_DATABASE=demo_schema  mysql
  ```
2. Navigate to [src/main/resources](src/main/resources) directory and load Data
  ```shell
  docker exec -i mysql-container mysql -uroot -ptest12345 demo_schema < db.sql
  ```
