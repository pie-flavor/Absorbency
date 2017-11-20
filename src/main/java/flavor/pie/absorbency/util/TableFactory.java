package flavor.pie.absorbency.util;

import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.spongepowered.api.CatalogType;
import org.spongepowered.api.Sponge;

public class TableFactory {
    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> LuaTable ofEnum(Class<T> clazz) {
        return Throw.uncheckedV(() -> {
            LuaTable tbl = LuaValue.tableOf();
            T[] ts = (T[]) clazz.getMethod("values", (Class<?>[]) null).invoke(null, (Object[]) null);
            for (T t : ts) {
                tbl.set(t.name().toLowerCase(), LuaValue.userdataOf(t));
            }
            return tbl;
        });
    }

    public static <T extends CatalogType> LuaTable ofCatalog(Class<T> clazz) {
        LuaTable tbl = LuaValue.tableOf();
        LuaTable mt = LuaValue.tableOf();
        mt.set("__index", FunctionHelper.func2((table, value) -> {
            String str = value.checkjstring();
            T type = Sponge.getRegistry().getType(clazz, str).orElseThrow(() -> new LuaError("Invalid " + clazz.getSimpleName()));
            return LuaValue.userdataOf(type);
        }));
        tbl.setmetatable(mt);
        return tbl;
    }
}