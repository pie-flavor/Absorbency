package flavor.pie.absorbency.util;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class LuaJson {
    public static String toJson(LuaTable tbl) {
        StringWriter writer = new StringWriter();
        try (JsonWriter json = new JsonWriter(writer)) {
            writeTable(json, tbl);
        } catch (IOException ex) {
            Throw.t(ex);
        }
        return writer.toString();
    }

    private static void writeTable(JsonWriter writer, LuaTable tbl) {
        Throw.unchecked(() -> {
            if (LuaUtils.isArray(tbl)) {
                writer.beginArray();
                LuaValue key = LuaValue.NIL;
                while (true) {
                    Varargs value = tbl.next(key);
                    if ((key = value.arg1()).isnil()) {
                        break;
                    }
                    LuaValue val = value.arg(2);
                    writeValue(writer, val);
                }
                writer.endArray();
            } else {
                writer.beginObject();
                LuaValue key = LuaValue.NIL;
                while (true) {
                    Varargs value = tbl.next(key);
                    if ((key = value.arg1()).isnil()) {
                        break;
                    }
                    LuaValue val = value.arg(2);
                    writer.name(key.checkjstring());
                    writeValue(writer, val);
                }
                writer.endObject();
            }
        });
    }

    private static void writeValue(JsonWriter writer, LuaValue val) {
        while (val.type() == LuaValue.TFUNCTION) {
            val = val.invoke().arg1();
        }
        try {
            switch (val.type()) {
                case LuaValue.TBOOLEAN:
                    writer.value(val.checkboolean());
                    break;
                case LuaValue.TINT:
                    writer.value(val.checkint());
                    break;
                case LuaValue.TSTRING:
                    writer.value(val.checkjstring());
                    break;
                case LuaValue.TNUMBER:
                    writer.value(val.checknumber().tofloat());
                    break;
                case LuaValue.TTABLE:
                    writeTable(writer, val.checktable());
                    break;
            }
        } catch (Throwable t) {
            Throw.t(t);
        }
    }

    public static LuaTable fromJson(String json) {
        try (JsonReader reader = new JsonReader(new StringReader(json))) {
            return Throw.uncheckedV(() -> {
                if (reader.peek() == JsonToken.BEGIN_ARRAY) {
                    return readArray(reader);
                } else {
                    return readTable(reader);
                }
            });
        } catch (IOException ex) {
            return Throw.t(ex);
        }
    }

    private static LuaValue readValue(JsonReader reader) {
        return Throw.uncheckedV(() -> {
            switch (reader.peek()) {
                case NUMBER:
                    return LuaValue.valueOf(reader.nextDouble());
                case STRING:
                    return LuaValue.valueOf(reader.nextString());
                case BOOLEAN:
                    return LuaValue.valueOf(reader.nextBoolean());
                case BEGIN_ARRAY:
                    return readArray(reader);
                case BEGIN_OBJECT:
                    return readTable(reader);
                default: return null;
            }
        });
    }

    private static LuaTable readTable(JsonReader reader) {
        return Throw.uncheckedV(() -> {
            LuaTable tbl = LuaValue.tableOf();
            reader.beginObject();
            while (reader.hasNext()) {
                String key = reader.nextName();
                LuaValue val = readValue(reader);
                tbl.set(key, val);
            }
            reader.endObject();
            return tbl;
        });
    }

    private static LuaTable readArray(JsonReader reader) {
        return Throw.uncheckedV(() -> {
            int i = 1;
            LuaTable tbl = LuaValue.tableOf();
            reader.beginArray();
            while (reader.hasNext()) {
                LuaValue val = readValue(reader);
                tbl.set(i, val);
                i++;
            }
            reader.endArray();
            return tbl;
        });
    }
}
