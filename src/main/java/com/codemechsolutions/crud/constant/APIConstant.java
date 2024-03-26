package com.codemechsolutions.crud.constant;

public interface APIConstant {
    String BASE_URL = "/api/v1";
    String ACTOR_URL = BASE_URL+"/actors";
    String ACTOR_ID = "/{id}";

    String MOVIE_URL = BASE_URL+"/movies";

    String MOVIE_ID = "/{id}";

    String CLS_MET_ERROR = "class = {} method={} errorMessage={}";

}
