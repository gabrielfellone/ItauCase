# Twitter Case

### Configuração

**1º - Subir a Imagem Docker do MYSQL.**

`docker run -d -p 2019:3306 --name mysql-docker-container -e MYSQL_ROOT_PASSWORD= -e MYSQL_DATABASE=twittercase -e MYSQL_USER=root -e MYSQL_PASSWORD= mysql:latest`

**2º - Buildar o core twitter-case-core.**

`mvn clean install -DskipTests` (skipar testes devido estar apontado para o banco no container) ;

**3º - Subir container com a imagem do modulo twitter-case-core.**

`docker build -t twitter-case-core .`

`docker run -t --name twitter-case-core-container --link mysql-docker-container:mysql -p 9022:9022 twitter-case-core`
(http://localhost:9022/twitter-case/swagger-ui.html#)

**4º - Subir container com a imagem do modulo twitter-case-front.**

`docker build -t twitter-case-front-image .`

`docker run -t --name twitter-case-front-container --link twitter-case-core-container -p 4200:4200 twitter-case-front-image`
