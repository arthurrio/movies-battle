insert into GAME(id_game, player,total_round, total_point, dat_start,dat_end) values (5,'test',5,9,'2022-03-13 10:00:00',null);

INSERT INTO GAME_ROUND(id_round,id_game,player,round_number,
                       id_movie_1,id_movie_2,answer,point)
                       values (22,5,'test',1,5,6,1,3);

INSERT INTO GAME_ROUND(id_round,id_game,player,round_number,
                       id_movie_1,id_movie_2,answer,point)
values (23,5,'test',2,7,6,1,3);

INSERT INTO GAME_ROUND(id_round,id_game,player,round_number,
                       id_movie_1,id_movie_2,answer,point)
values (24,5,'test',3,7,5,1,3);

INSERT INTO GAME_ROUND(id_round,id_game,player,round_number,
                       id_movie_1,id_movie_2,answer,point)
values (25,5,'test',4,8,5,1,3);

INSERT INTO GAME_ROUND(id_round,id_game,player,round_number,
                       id_movie_1,id_movie_2,answer,point)
values (26,5,'test',5,8,6,1,3);

