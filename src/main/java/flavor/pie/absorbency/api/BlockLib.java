package flavor.pie.absorbency.api;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;
import org.spongepowered.api.block.BlockSnapshot;

import flavor.pie.absorbency.util.MetatableBindings;

public class BlockLib extends TwoArgFunction {
    public static LuaTable instance;
    public static LuaTable mt;

    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaTable tbl = tableOf();
        instance = tbl;
        mt = MetatableBindings.of(tbl);
        env.set("block", tbl);
        return tbl;
    }

    public static LuaTable fromSnapshot(BlockSnapshot snap) {
        //TODO
        return null;
    }

    public static BlockSnapshot toSnapshot(LuaValue value) {
        //TODO
        return null;
    }
}