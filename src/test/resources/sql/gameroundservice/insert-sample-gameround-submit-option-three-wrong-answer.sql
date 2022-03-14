insert into GAME(id_game, player,total_round, total_point, dat_start,dat_end) values (5,'test',4,3,'2022-03-13 10:00:00',null);

INSERT INTO GAME_ROUND(id_round,id_game,player,round_number,
                       id_movie_1,id_movie_2,answer,point)
values (23,5,'test',1,5,6,1,0);

INSERT INTO GAME_ROUND(id_round,id_game,player,round_number,
                       id_movie_1,id_movie_2,answer,point)
values (33,5,'test',2,15,26,1,0);

INSERT INTO GAME_ROUND(id_round,id_game,player,round_number,
                       id_movie_1,id_movie_2,answer,point)
values (32,5,'test',3,15,26,1,3);

INSERT INTO GAME_ROUND(id_round,id_game,player,round_number,
                       id_movie_1,id_movie_2)
values (43,5,'test',4,10,20);

INSERT INTO MOVIE(id_movie,point,title,imdb_rating,imdb_votes) values (10,25,'Movie 1',4.3,2162);
INSERT INTO MOVIE(id_movie,point,title,imdb_rating,imdb_votes) values (20,20,'Movie 2',6.2,2033);