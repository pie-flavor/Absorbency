package flavor.pie.absorbency.util;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ThreeArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.VarArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class FunctionHelper {
    public static ZeroArgFunction func0V(Runnable runnable) {
        return new ZeroArgFunction() {
            @Override
            public LuaValue call() {
                runnable.run();
                return NIL;
            }
        };
    }
    public static ZeroArgFunction func0(Supplier<LuaValue> supplier) {
        return new ZeroArgFunction() {
            @Override
            public LuaValue call() {
                return supplier.get();
            }
        };
    }
    public static OneArgFunction func1(UnaryOperator<LuaValue> operator) {
        return new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue arg) {
                return operator.apply(arg);
            }
        };
    }
    public static OneArgFunction func1V(Consumer<LuaValue> consumer) {
        return new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue arg) {
                consumer.accept(arg);
                return NIL;
            }
        };
    }
    public static TwoArgFunction func2(BinaryOperator<LuaValue> operator) {
        return new TwoArgFunction() {
            @Override
            public LuaValue call(LuaValue arg1, LuaValue arg2) {
                return operator.apply(arg1, arg2);
            }
        };
    }
    public static TwoArgFunction func2V(BiConsumer<LuaValue, LuaValue> consumer) {
        return new TwoArgFunction() {
            @Override
            public LuaValue call(LuaValue arg1, LuaValue arg2) {
                consumer.accept(arg1, arg2);
                return NIL;
            }
        };
    }
    public static ThreeArgFunction func3(LuaFunction3 function) {
        return new ThreeArgFunction() {
            @Override
            public LuaValue call(LuaValue arg1, LuaValue arg2, LuaValue arg3) {
                return function.apply(arg1, arg2, arg3);
            }
        };
    }
    public static ThreeArgFunction func3V(LuaVoidFunction3 function) {
        return new ThreeArgFunction() {
            @Override
            public LuaValue call(LuaValue arg1, LuaValue arg2, LuaValue arg3) {
                function.apply(arg1, arg2, arg3);
                return NIL;
            }
        };
    }
    public static VarArgFunction funcv(LuaVarargFunction function) {
        return new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                return function.apply(args);
            }
        };
    }
    public static VarArgFunction funcvV(LuaVoidVarargFunction function) {
        return new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                function.apply(args);
                return NIL;
            }
        };
    }

    @FunctionalInterface
    public interface LuaFunction3 {
        LuaValue apply(LuaValue arg1, LuaValue arg2, LuaValue arg3);
    }
    @FunctionalInterface
    public interface LuaVoidFunction3 {
        void apply(LuaValue arg1, LuaValue arg2, LuaValue arg3);
    }
    @FunctionalInterface
    public interface LuaVarargFunction {
        Varargs apply(Varargs args);
    }
    @FunctionalInterface
    public interface LuaVoidVarargFunction {
        void apply(Varargs args);
    }
}
