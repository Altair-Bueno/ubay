# Ubay

A web auction platform using Jakarta EE servlets

## Running Ubay

You can run the deployment server using Docker compose.

```shell
git clone https://github.com/Altair-Bueno/ubay.git
cd ubay
docker compose up -d
# To stop the server
docker compose down
```

Ubay will be available on `0.0.0.0:8080/ubay-1.0-SNAPSHOT/`

---

## Building the Docker image

To build the Docker image, you first need to install
[Maven](https://maven.apache.org) and a [Java 11 JDK](https://adoptopenjdk.net/)

```bash
mvn clean compile package
docker build .
```

## Development

The following software is required:

- GlassFish server 6 or later
- Minio object storage
- Java JDK 11 or later
- Maven
- Docker

### Start the Postgres server

#### Using Docker

You can use the following Docker compose file to create a development database
```yml
version: '3.1'
services:
  postgres:
    image: postgres
    ports:
      - '5432:5432'
    environment:
      POSTGRES_PASSWORD: mysecretpassword
      POSTGRES_DB: UBAY
    volumes:
      - ./sql/scheme.sql:/docker-entrypoint-initdb.d/init.sql
      - postgres-data-dev:/var/lib/postgresql/data
volumes:
  postgres-data-dev:
```

#### Manual

Spin up a new Postgres instance with the following configuration:

```yml
port: 5432
address: localhost
username: postgres
password: mysecretpassword
database: UBAY
  scheme: sql/scheme.sql
```

### Start the minio server bucket

#### Using Docker

1. Create a new Minio server by running the following command:

```bash
 docker run \
  -p 9000:9000 \
  -p 9001:9001 \
  minio/minio server /data --console-address ":9001"
```

2. Open <http://127.0.0.1:9001> on your browser
3. Login using the default username and password (minioadmin/minioadmin)
4. Create a new storage bucket called "ubay"

### Set up the GlassFish server

You can set up GlassFish using the [web client](http://localhost:4848/) or the
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

> Note: You can test the connection by pinging the database. You may need to
> restart GlassFish

#### Create a JDBC resource

```yaml
JNDI name: jdbc/ubay
pool name: postgresql
```

#### Create the JNDI custom resources

```yaml
jndi:
  custom resources:
    - jndi name: minio/bucket
      resource type: java.lang.String
      factory class: org.glassfish.resources.custom.factory.PrimitivesAndStringFactory
      properties:
        value: ubay
    - jndi name: minio/username
      resource type: java.lang.String
      factory class: org.glassfish.resources.custom.factory.PrimitivesAndStringFactory
      properties:
        value: minioadmin
    - jndi name: minio/password
      resource type: java.lang.String
      factory class: org.glassfish.resources.custom.factory.PrimitivesAndStringFactory
      properties:
        value: minioadmin
    - jndi name: minio/url
      resource type: java.lang.String
      factory class: org.glassfish.resources.custom.factory.PrimitivesAndStringFactory
      properties:
        value: http://localhost:9000
```

### Deploying the application to GlassFish

1. Compile the application to a `.war` package: `mvn compile package`
2. Locate the `.war` file under the `target` directory
3. Deploy the application to GlassFish using `asadmin` or the web client

> Note: Some IDEs, like IntelliJ IDEA can automate these steps by adding a **run
> configuration**. For more information visit
> <https://www.jetbrains.com/help/idea/creating-and-running-your-first-restful-web-service.html#run_config>
