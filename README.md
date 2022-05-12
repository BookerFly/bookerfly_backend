# BookerFly

### Database
**Running Postgres.**
```shell
docker run --name bookerfly-postgres -p 5432:5432 -e POSTGRES_PASSWORD=root -d postgres
```
Note: add a `test` database for test

**Running Postgres client: pgadmin4.**
```shell
docker run --name bookerfly-pgadmin -p 5050:80 -e PGADMIN_DEFAULT_EMAIL=bookerfly@gmail.com -e PGADMIN_DEFAULT_PASSWORD=bookerfly -d dpage/pgadmin4
```
Note: use `host.docker.internal` as host name to connect the postgres server running in a docker container