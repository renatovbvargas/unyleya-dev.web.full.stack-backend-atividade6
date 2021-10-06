# unyleya-dev.web.full.stack-backend-atividade6

## Iniciar

### Com Docker
    startMySQL.bat
    start.bat

### Sem o Docker 
* Instalar MySQL
* Iniciar o serviço do MySQL
* Criar o banco de dados cidadao no MySQL *(mysql/init.sql tem o comando)*.
    
    java -jar cidadao-0.0.1-SNAPSHOT.jar

## Endpoints
* GET http://localhost:8080/api/v1/cidadaos
* GET http://localhost:8080/api/v1/cidadaos/:id
* POST http://localhost:8080/api/v1/cidadaos

    {
        "nome":"Fulano",
        "cpf":"222.222.222-66",
        "sexo": "masculino",
        "endereco": "Rua A...."
    }
* PUT http://localhost:8080/api/v1/cidadaos/:id
* DELETE http://localhost:8080/api/v1/cidadaos/:id