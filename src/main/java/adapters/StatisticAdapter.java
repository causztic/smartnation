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

        if (value.getDataDate() != null)
        	writer.name("date").value(value.getDataDate().toString());
        writer.name("count").value(value.getCount());
        writer.endObject();
    }

    @Override
    public Statistic read(JsonReader in) throws IOException {
        return null;
    }
}
