package flavor.pie.absorbency.api;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

import flavor.pie.absorbency.util.MetatableBindings;

public class ItemLib extends TwoArgFunction {
    public static LuaTable instance;
    public static LuaTable mt;

    //TODO
    public static LuaTable fromStack(ItemStackSnapshot stack) {
        return null;
    }

    //TODO
    public static ItemStackSnapshot toStack(LuaValue value) {
        return null;
    }

    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaTable tbl = tableOf();
        instance = tbl;
        mt = MetatableBindings.of(tbl);
        env.set("item", tbl);
        return tbl;
    }
}