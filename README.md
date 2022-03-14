# movies-battle


### Build

`./gradlew build`

### Endereço para swagger-ui : documentação OpenAPI 3.0

http://localhost:8080/swagger-ui.html

### Massa de dados
massa de dados foi gerada a partir da API https://www.omdbapi.com/

Foi utilizado 2 Chamadas, a primeira para coletar uma lista de filmes, e a massa foi gerada até a página
30, acho que é suficiente para um teste, lembrando que quanto maior a massa, mais tempo leva para o sistema iniciar.

- https://www.omdbapi.com/?apikey=[apiToken]&s=spider&type=movie&page=1
- http://www.omdbapi.com/?i=tt0164334&plot=full

### Libs utilizadas
- Spring Data
- Spring Boot
- Spring Security
- Spring MVC
- Spring Doc
- Lombok
- Spring Mock Mvc