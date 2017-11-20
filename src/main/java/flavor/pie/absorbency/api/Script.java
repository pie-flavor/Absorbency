package flavor.pie.absorbency.api;

import org.luaj.vm2.LuaValue;

public class Script {
    public static LuaValue bindings(Script s) {
        return LuaValue.tableOf(new LuaValue[]{
                LuaValue.valueOf("id"), LuaValue.valueOf(s.id),
                LuaValue.valueOf("script"), s.script
        });
    }

    public final String id;
    public final LuaValue script;
    public Script(String id, LuaValue script) {
        this.id = id;
        this.script = script;
    }

}
