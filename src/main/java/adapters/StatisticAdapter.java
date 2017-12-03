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

        // writer.name("id").value(value.getId());
        if (value.getData_date() != null)
        	writer.name("date").value(value.getData_date().toString());
        writer.name("count").value(value.getCount());
        writer.endObject();
    }

    @Override
    public Statistic read(JsonReader in) throws IOException {
        return null;
    }
}
