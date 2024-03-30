package com.codemechSolutions.crud.constant;

public interface APIConstant {

    /*Api base urls*/
    String BASE_URL = "/api/v1";


    /*Actor base urls*/
    String ACTOR_URL = BASE_URL+"/actors";
    String ACTOR_ID = "/{id}";

    /*Movie base urls*/
    String MOVIE_URL = BASE_URL+"/movies";
    String MOVIE_ID = "/{id}";

    /*  LOGGER   */
    String CLS_MET_ERROR = "class = {} method={} errorMessage={}";

    /*  LOGGER METHOD FOR ACTOR */
    String MET_GET_ACTOR_BY_ID = "getActorById";
    String MET_UPDATE_ACTOR_BY_ID = "updateActor";
    String MET_DELETE_ACTOR_BY_ID = "deleteActorById";
    String MET_GET_ALL_ACTORS = "getAllActors";
    String MET_SAVE_ACTOR = "saveActor";

    /*  LOGGER METHOD FOR MOVIE */
    String MET_GET_MOVIE_BY_ID = "getMovieById";
    String MET_UPDATE_MOVIE_BY_ID = "updateMovie";
    String MET_DELETE_MOVIE_BY_ID = "deleteMovieById";
    String MET_GET_ALL_MOVIES = "getAllMovies";
    String MET_SAVE_MOVIE = "saveMovie";
    
    
}
