package flavor.pie.absorbency.api;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

import flavor.pie.absorbency.util.MetatableBindings;

public class EntityLib extends TwoArgFunction{
    public static LuaTable instance;
    public static LuaTable mt;
    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaTable tbl = tableOf();
        instance = tbl;
        mt = MetatableBindings.of(tbl);
        tbl.setmetatable(PlayerLib.mt);
        env.set("entity", tbl);
        return tbl;
    }
}
