package flavor.pie.absorbency.api;

import java.util.function.Function;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaUserdata;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.Transaction;
import flavor.pie.absorbency.util.FunctionHelper;
import flavor.pie.absorbency.util.MetatableBindings;

public class UtilLib extends TwoArgFunction {
    public static LuaTable instance;
    public static LuaTable transactionMt;

    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaTable tbl = tableOf();
        LuaTable transaction = tableOf();
        transaction.set("get_original", FunctionHelper.func1(f -> ((LuaTransaction<?>) f.checkuserdata(LuaTransaction.class)).getLuaOriginal()));
        transaction.set("get_final", FunctionHelper.func1(f -> ((LuaTransaction<?>) f.checkuserdata(LuaTransaction.class)).getLuaFinal()));
        transaction.set("get_default", FunctionHelper.func1(f -> ((LuaTransaction<?>) f.checkuserdata(LuaTransaction.class)).getLuaDefault()));
        transaction.set("set_custom", FunctionHelper.func2V((f, custom) -> ((LuaTransaction<?>) f.checkuserdata(LuaTransaction.class)).setLuaCustom(custom)));
        transaction.set("get_custom", FunctionHelper.func1(f -> ((LuaTransaction<?>) f.checkuserdata(LuaTransaction.class)).getLuaCustom()));
        transaction.set("is_valid", FunctionHelper.func1(f -> ((LuaTransaction<?>) f.checkuserdata(LuaTransaction.class)).isLuaValid()));
        transaction.set("set_valid", FunctionHelper.func2V((f, valid) -> ((LuaTransaction<?>) f.checkuserdata(LuaTransaction.class)).setLuaValid(valid)));
        tbl.set("transaction", transaction);
        transactionMt = MetatableBindings.of(transaction);
        instance = tbl;
        env.set("util", tbl);
        return tbl;
    }

    public static class LuaTransaction<T extends DataSerializable> {

        private Transaction<T> transaction;
        private Function<LuaValue, T> fromLuaFunction;
        private Function<T, LuaValue> toLuaFunction;
    
        @SuppressWarnings("unchecked")
        public LuaTransaction(Transaction<T> transaction, Class<T> clazz, LuaTable mt) {
            this(transaction, v -> (T) v.checkuserdata(clazz), v -> new LuaUserdata(v, mt));
        }

        public LuaTransaction(Transaction<T> transaction, Function<LuaValue, T> fromLuaFunction, Function<T, LuaValue> toLuaFunction) {
            this.transaction = transaction;
            this.fromLuaFunction  = fromLuaFunction;
            this.toLuaFunction = toLuaFunction;
        }

        public Transaction<T> getTransaction() {
            return transaction;
        }
        
        public LuaValue getLuaOriginal() {
            return toLuaFunction.apply(transaction.getOriginal());
        }

        public void setLuaCustom(LuaValue value) {
            transaction.setCustom(fromLuaFunction.apply(value));
        }

        public LuaValue getLuaFinal() {
            return toLuaFunction.apply(transaction.getFinal());
        }

        public LuaValue getLuaDefault() {
            return toLuaFunction.apply(transaction.getDefault());
        }

        public LuaValue getLuaCustom() {
            return transaction.getCustom().map(toLuaFunction).orElse(LuaValue.NIL);
        }

        public LuaValue isLuaValid() {
            return valueOf(transaction.isValid());
        }

        public void setLuaValid(LuaValue valid) {
            transaction.setValid(valid.checkboolean());
        }
    }
}