# Pygram
Bot Telegram using kotlin language

## Tech Stack

| Dependency             | Version |
| -----------------------|---------|
| 'Kotin'.               | '1.5.0' |
| 'Spring Boot'          | '2.4.5' |
| 'Junit'                | '5.6.0' |
| 'Telegram Bot'         | '5.2.0' |
| 'WebFlux'              | '2.4.5' |
| 'SpringData Cassandra' | '2.4.5' |

## Run Locally Configuration

- Clone the repository

```bash
 HTTPS https://github.com/Puglielli/Pygram.git
 
 SSH git@github.com:Puglielli/Pygram.git
```

## Docker Configuration

- Install Docker
- Download [Docker](https://docs.docker.com/get-docker/).

- Install and configure the *Cassandra* database
  - Pull from *Cassandra* repository
    - `docker pull datastax/dse-server:5.1.18`

  - To create the *Cassandra* container, run the command:
    - `docker run -e DS_LICENSE=accept --memory 4g --name cassandra -p 9042:9042 -d datastax/dse-server:5.1.18`

  - Para fazer a copia do arquivo *cassandra.yaml* para dentro do container, execute o comando:
    - `docker cp <FILE_CASSANDRA> cassandra:/opt/dse/resources/cassandra/conf/`
    
    Note: Replace the ***<FILE_CASSANDRA>*** with the *cassandra.yaml* file directory, which is located in the project repository             
    
    - `_**/src/main/resources/cassandra.yaml_`

  - Perform stop and start of container *Cassandra*
    - `docker stop cassandra`
    - `docker start cassandra`

  - To configure and create the tables, run the command:
    - `docker exec -it cassandra bash`

  - To log into *Cassandra*, run the command:**
    - `cqlsh -u cassandra -p cassandra`

  - Create User
    - `CREATE ROLE root with SUPERUSER = true AND LOGIN = true and PASSWORD = 'root';`

  - Criar o Keyspace
    - `CREATE KEYSPACE dbo WITH REPLICATION = {'class': 'SimpleStrategy','replication_factor' : 1};`
    - `USE dbo;`

  - **Criar as tabelas**
    ```bash
      CREATE TABLE chat (
          id UUID PRIMARY KEY,
          type VARCHAR
      );
    ```
    ```bash
      CREATE TABLE contact (
          id UUID PRIMARY KEY,
          phoneNumber VARCHAR,
          first_name VARCHAR,
          last_name VARCHAR,
          vcard VARCHAR
      );
    ```
    ```bash
      CREATE TABLE response_chat (
          id UUID PRIMARY KEY,
          text VARCHAR,
          contact timestamp,
          date timestamp,
          user VARCHAR,
          chat VARCHAR,
      );
    ```
    ```bash
      CREATE TABLE user_chat (
          id UUID PRIMARY KEY,
          isBot INT VARCHAR,
          first_name VARCHAR,
          last_name VARCHAR,
          history VARCHAR,
      );
    ```

- import the project into IntelliJ IDEA
- Run the `PygramApplication` class.


## Information Telegram Bot

- Link [PYgram](https://t.me/PYGRAMPUGLIELLIBOT).
