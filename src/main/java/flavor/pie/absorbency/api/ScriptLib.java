package flavor.pie.absorbency.api;

import flavor.pie.absorbency.Absorbency;
import flavor.pie.absorbency.util.FunctionHelper;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaUserdata;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

public class ScriptLib extends TwoArgFunction {

    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaTable tbl = tableOf();
        tbl.set("get_id", FunctionHelper.func1(ScriptLib::getId));
        tbl.set("get_by_id", FunctionHelper.func1(ScriptLib::getScript));
        env.set("script", tbl);
        return tbl;
    }

    public static LuaValue getId(LuaValue script) {
        return valueOf(((Script) script.checkuserdata(Script.class)).id);
    }

    public static LuaValue getScript(LuaValue id) {
        String s = id.checkjstring();
        if (!Absorbency.instance.scripts.containsKey(s)) {
            error("No script by that name found");
        }
        return new LuaUserdata(Absorbency.instance.scripts.get(s));
    }

}
