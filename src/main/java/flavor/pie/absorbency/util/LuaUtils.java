package flavor.pie.absorbency.util;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.tuple.Pair;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaString;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;
import org.spongepowered.api.CatalogType;
import org.spongepowered.api.Sponge;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collector;

public class LuaUtils {

    @SuppressWarnings("unchecked")
    public static <T extends CatalogType> T toCatalog(LuaValue id, Class<T> clazz) {
        if (id.isuserdata()) {
            return (T) id.checkuserdata(clazz);
        } else {
            return Sponge.getRegistry().getType(clazz, id.checkjstring()).orElseThrow(() -> new LuaError("Invalid " + clazz.getSimpleName()));
        }
    }

    public static LuaString fromCatalog(CatalogType t) {
        return LuaValue.valueOf(t.getId());
    }

    public static boolean isArray(LuaTable table) {
        int i = 0;
        LuaValue val = LuaValue.NIL;
        while (true) {
            if ((val = table.next(val).arg1()).isnil()) {
                return true;
            }
            if (table.get(++i).isnil()) {
                return false;
            }
        }
    }

    public static LuaTable fromMap(Map<String, ?> map) {
        LuaTable tbl = LuaValue.tableOf();
        map.forEach((str, obj) -> {
            if (obj instanceof LuaValue) {
                tbl.set(str, (LuaValue) obj);
            } else {
                tbl.set(str, CoerceJavaToLua.coerce(obj));
            }
        });
        return tbl;
    }

    public static Map<String, ?> fromTable(LuaTable table) {
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        for (Pair<LuaValue, LuaValue> val : pairs(table)) {
            String key = val.getLeft().checkjstring();
            LuaValue lvalue = val.getRight();
            Object value;
            switch (lvalue.type()) {
                case LuaValue.TBOOLEAN:
                    value = lvalue.checkboolean();
                    break;
                case LuaValue.TNUMBER:
                    value = lvalue.isint() ? lvalue.checkint() : lvalue.checkdouble();
                    break;
                case LuaValue.TSTRING:
                    value = lvalue.checkjstring();
                    break;
                case LuaValue.TTABLE:
                    value = fromTable(lvalue.checktable());
                    break;
                case LuaValue.TUSERDATA:
                case LuaValue.TLIGHTUSERDATA:
                    value = lvalue.checkuserdata();
                    break;
                default:
                    value = null;
                    break;
            }
            builder.put(key, value);
        }
        return builder.build();
    }

    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> fromTable(LuaTable table, Class<T> clazz) {
        ImmutableMap.Builder<String, T> builder = ImmutableMap.builder();
        for (Pair<LuaValue, LuaValue> val : pairs(table)) {
            String key = val.getLeft().tojstring();
            T value = (T) CoerceLuaToJava.coerce(val.getRight(), clazz);
            builder.put(key, value);
        }
        return builder.build();
    }

    public static Iterable<Pair<LuaValue, LuaValue>> pairs(LuaTable table) {
        return () -> new Iterator<Pair<LuaValue, LuaValue>>() {
            LuaValue index = LuaValue.NIL;
            @Override
            public boolean hasNext() {
                return !table.next(index).isnil(1);
            }

            @Override
            public Pair<LuaValue, LuaValue> next() {
                Varargs v = table.next(index);
                LuaValue newIndex = v.arg1();
                if (newIndex.isnil()) {
                    throw new NoSuchElementException("next");
                }
                index = newIndex;
                return Pair.of(v.arg(1), v.arg(2));
            }
        };
    }

    public static Iterable<LuaValue> iterate(LuaTable table) {
        return () -> new Iterator<LuaValue>() {
            LuaValue index = LuaValue.NIL;

            @Override
            public boolean hasNext() {
                return !table.next(index).isnil(1);
            }

            @Override
            public LuaValue next() {
                Varargs v = table.next(index);
                LuaValue newIndex = v.arg1();
                if (newIndex.isnil()) {
                    throw new NoSuchElementException("next");
                }
                index = newIndex;
                return v.arg(2);
            }
        };
    }

    public static Iterable<LuaValue> iterate(Varargs varargs) {
        return () -> new Iterator<LuaValue>() {
            int index = 1;

            @Override
            public boolean hasNext() {
                return varargs.isvalue(index);
            }

            @Override
            public LuaValue next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("next");
                }
                LuaValue ret = varargs.arg(index);
                index++;
                return ret;
            }
        };
    }
    
    public static LuaTable makeTable(List<? extends LuaValue> list) {
        LuaTable tbl = LuaValue.tableOf(list.size(), 0);
        int index = 1;
        for (LuaValue val : list) {
            tbl.set(index, val);
            index++;
        }
        return tbl;
    }

    public static <T> LuaTable makeTable(List<T> list, Function<T, ? extends LuaValue> mapperFunction) {
        LuaTable tbl = LuaValue.tableOf(list.size(), 0);
        int index = 1;
        for (T t : list) {
            tbl.set(index, mapperFunction.apply(t));
            index++;
        }
        return tbl;
    }

    public static void insertAtEnd(LuaTable table, LuaValue value) {
        table.set(table.length() + 1, value);
    }

    public static LuaTable concatTables(LuaTable table, LuaTable table2) {
        LuaTable res = LuaValue.tableOf();
        for (Pair<LuaValue, LuaValue> pair : pairs(table)) {
            res.set(pair.getKey(), pair.getValue());
        }
        for (Pair<LuaValue, LuaValue> pair : pairs(table2)) {
            res.set(pair.getKey(), pair.getValue());
        }
        return res;
    }

    public static Collector<LuaValue, LuaTable, LuaTable> tableCollector() {
        return Collector.of(LuaValue::tableOf, LuaUtils::insertAtEnd, LuaUtils::concatTables);
    }

    public static LuaTable makeTable(Collection<LuaValue> collection) {
        LuaTable table = LuaValue.tableOf(collection.size(), 0);
        int index = 0;
        for (LuaValue value : collection) {
            table.set(++index, value);
        }
        return table;
    }
}
