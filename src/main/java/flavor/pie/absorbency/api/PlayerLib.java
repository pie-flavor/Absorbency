package flavor.pie.absorbency.api;

import flavor.pie.absorbency.util.FunctionHelper;
import flavor.pie.absorbency.util.MetatableBindings;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;
import org.spongepowered.api.entity.living.player.Player;

public class PlayerLib extends TwoArgFunction {
    public static LuaTable instance;
    public static LuaTable mt;
    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaTable tbl = tableOf();
        tbl.set("get_name", FunctionHelper.func1(PlayerLib::getName));
        tbl.setmetatable(ChatLib.mt);
        env.set("player", tbl);
        instance = tbl;
        mt = MetatableBindings.of(tbl);
        return tbl;
    }

    public static LuaValue getName(LuaValue player) {
        return valueOf(((Player) player.checkuserdata(Player.class)).getName());
    }
}
