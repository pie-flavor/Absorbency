package flavor.pie.absorbency.api;

import flavor.pie.absorbency.Absorbency;
import flavor.pie.absorbency.util.FunctionHelper;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.TwoArgFunction;
import org.spongepowered.api.scheduler.Task;

import java.util.function.BiConsumer;

public class GlobalFunctions extends TwoArgFunction {
    public static LuaValue copy(LuaValue value, boolean safe) {
        LuaValue mt = value.getmetatable();
        if (mt == null || mt.get("__cancopy").optfunction(FunctionHelper.func1(v -> valueOf(true))).call(value).checkboolean()) {
            if (mt != null && mt.get("__copy").isfunction()) {
                return mt.get("__copy").call(value);
            }
            LuaValue copy;
            if (value.type() == TTABLE) {
                copy = tableOf();
                foreach(value.checktable(), (k, v) -> copy.rawset(k, copy(v, safe)));
            } else {
                copy = value;
            }
            return copy;
        } else {
            if (safe) {
                return value;
            } else {
                return error("Not copyable");
            }
        }
    }

    public static LuaValue shallowCopy(LuaValue value, boolean safe) {
        LuaValue mt = value.getmetatable();
        if (mt == null || mt.get("__cancopy").optfunction(FunctionHelper.func1(v -> valueOf(true))).call(value).checkboolean()) {
            if (mt != null && mt.get("__pcopy").isfunction()) {
                return mt.get("__pcopy").call(value);
            }
            LuaValue copy;
            if (value.type() == TTABLE) {
                copy = tableOf();
                foreach(value.checktable(), copy::rawset);
            } else {
                copy = value;
            }
            return copy;
        } else {
            if (safe) {
                return value;
            } else {
                return error("Not copyable");
            }
        }
    }

    public static void foreach(LuaTable tbl, BiConsumer<LuaValue, LuaValue> consumer) {
        LuaValue k = NIL;
        while (true) {
            Varargs n = tbl.next(k);
            if ((k = n.arg1()).isnil()) {
                break;
            }
            LuaValue v = n.arg(2);
            consumer.accept(k, v);
        }
    }

    public static void sync(LuaValue fn) {
        fn.checkfunction();
        Task.builder().execute((Runnable) fn::call).submit(Absorbency.instance);
    }

    public static void async(LuaValue fn) {
        if (!fn.isfunction() && !fn.isclosure()) {
            fn.checkfunction();
        }
        Task.builder().async().execute((Runnable) fn::call).submit(Absorbency.instance);
    }

    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        env.set("copy", FunctionHelper.func1(val -> copy(val, false)));
        env.set("pcopy", FunctionHelper.func1(val -> copy(val, true)));
        env.set("scopy", FunctionHelper.func1(val -> shallowCopy(val, false)));
        env.set("spcopy", FunctionHelper.func1(val -> shallowCopy(val, true)));
        env.set("sync", FunctionHelper.func1V(GlobalFunctions::sync));
        env.set("async", FunctionHelper.func1V(GlobalFunctions::async));
        return env;
    }
}
