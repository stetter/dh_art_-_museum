package com.stetter.dhartmuseum.data.database;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stetter.dhartmuseum.model.Exhibition;
import com.stetter.dhartmuseum.model.Venue;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class Converters {
    @TypeConverter
    public Date toDate (Long timestamp){
        if(timestamp == null){
            return null;
        }else{
            return new Date( timestamp );
        }
    }
    @TypeConverter
    public Long toTimestamp (Date date){
        return date.getTime();
    }

    public List<String> fromString(String value){
        Type listType = new TypeToken<List<String>>(){

        }.getType();
        return new Gson().fromJson( value,listType );
    }
    @TypeConverter
    public String fromList (List<String> list){
        Gson gson = new Gson();
        return gson.toJson( list );
    }
    @TypeConverter
    public Object fromString2(String value){
        Type listType = new TypeToken<List<Exhibition>>(){

        }.getType();
        return new Gson().fromJson( value,listType );
    }
    @TypeConverter
    public String froObject(Object object){
        Gson gson = new Gson();
        return gson.toJson( object );
    }
    @TypeConverter
    public List<Venue> fromVenue(String value){
        Type listType = new TypeToken<List<Exhibition>>(){

        }.getType();
        return new Gson().fromJson( value,listType );
    }
    @TypeConverter
    public  String fromVenue2(List<Venue> list){
        Gson gson = new Gson();
        return gson.toJson( list );
    }

}
