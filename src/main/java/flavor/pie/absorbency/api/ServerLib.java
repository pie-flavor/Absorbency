package flavor.pie.absorbency.api;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;
import org.spongepowered.api.Sponge;

import flavor.pie.absorbency.util.FunctionHelper;

public class ServerLib extends TwoArgFunction {
    public static LuaTable instance;
    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaTable tbl = tableOf();
        tbl.set("console", FunctionHelper.func0(ServerLib::console));
        env.set("server", tbl);
        if (instance == null) {
            instance = tbl;
        }
        return tbl;
    }

    public static LuaValue console() {
        return userdataOf(Sponge.getServer().getConsole(), ChatLib.mt);
    }

}