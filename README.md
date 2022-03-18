# Ubay

A web auction platform developed using Jakarta EE servlets

## Build and deploy the application

The application requires the following software installed on your machine

- Glassfish server version 6+
- Java JDK 11
- Maven
- Docker

### Start the Postgres server

We provided a `docker-compose.yml` file to start a Postgres database using
Docker that automatically loads the required scheme

```shell
# Start the container
docker-compose up -d
```

### Set up Glassfish server

You can set up Glassfish using the [web client](http://localhost:4848/) or the
[administration console](https://docs.oracle.com/cd/E19798-01/821-1751/giobi/index.html)

#### Add the Postgres JDBC driver

1. Download the [driver](https://jdbc.postgresql.org/download.html)
2. Move the `jar` file to `$GLASSFISH_HOME/glassfish/lib`

#### Create a JDBC connection pool

```yaml
Resource name: postgresql
Resource type: javax.sql.DataSource
Database driver vendor: PostgreSQL
Datasource classname: org.postgresql.jdbc3.Jdbc3PoolingDataSource
Properties:
  password: mysecretpassword
  databaseName: UBAY
  serverName: localhost
  roleName: postgresql
  user: postgres
```

#### Create a JDBC resource

```yaml
JNDI name: jdbc/ubay
pool name: postgresql
```

#### Test the connection

You can test the connection by pinging the database on the database. You may
need to restart Glassfish

### Deploying the application to Glassfish

1. Compile the application to a `.war` package: `mvn compile package`
2. Locate the `.war` file under the `target` directory
3. Deploy the application to glassfish using asadmin or the web client

> Note: Some IDEs, like IntelliJ IDEA can automate these steps by adding a **run configuration**. For more information visit <https://www.jetbrains.com/help/idea/creating-and-running-your-first-restful-web-service.html#run_config>
