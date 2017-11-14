package adapters;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import app.models.Statistic;

public class StatisticAdapter extends TypeAdapter<Statistic>{

    @Override
    public void write(JsonWriter writer, Statistic value) throws IOException {
        writer.beginObject();

        writer.name("id").value(value.getId());
        writer.name("date").value(value.getData_date().toString());  
        writer.name("area").value(value.getArea().getName());
        writer.endObject();
    }

    @Override
    public Statistic read(JsonReader in) throws IOException {
        // do something you need
        return null;
    }
}
