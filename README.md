# movies-battle

### Pendente
- [ ] Organizar/incrementar documentação do swagger
- [ ] Ajustar Rank para atualizar pontuação se baseando na quantidade de acerto. Formula = (Qtd_de_acertos/n_partidas) * 100
- [ ] Criar testes de intergação para as APIs que estão sem
- [ ] Atingir 80% de coverage
- [ ] Tratativa de exceptions não esperadas nos retornos da API

### Build

`./gradlew build`

### Endereço para swagger-ui : documentação OpenAPI 3.0

http://localhost:8080/swagger-ui.html

### Massa de dados
massa de dados foi gerada a partir da API https://www.omdbapi.com/

[Utilizada classe a OmdbApiClientUtil para criar inserts a parir da API](https://github.com/arthurrio/movies-battle/blob/main/src/main/java/com/letscode/moviesbattle/util/OmdbApiClientUtil.java) 

Foi utilizado 2 Chamadas, a primeira para coletar uma lista de filmes, e a massa foi gerada até a página
30, acho que é suficiente para um teste, lembrando que quanto maior a massa, mais tempo leva para o sistema iniciar.

- https://www.omdbapi.com/?apikey=[apiToken]&s=spider&type=movie&page=1
- http://www.omdbapi.com/?i=tt0164334&plot=full

### Tecnologias utilizadas
- Java 11
- Spring Data
- Spring Boot
- Spring Security
- Spring MVC
- Spring Doc
- Lombok
- Spring Mock Mvc
