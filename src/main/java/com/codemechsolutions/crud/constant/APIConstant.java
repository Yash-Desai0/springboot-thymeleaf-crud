package com.codemechsolutions.crud.constant;

public interface APIConstant {
    String BASE_URL = "/api/v1";
    String ACTOR_URL = BASE_URL+"/actors";
    String ACTOR_ID = "/{actorId}";

    String MOVIE_URL = BASE_URL+"/movies";

    String MOVIE_ID = "/{movieId}";

    String CLS_MET_ERROR = "class = {} method={} errorMessage={}";

}
