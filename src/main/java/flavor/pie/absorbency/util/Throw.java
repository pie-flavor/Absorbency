package flavor.pie.absorbency.util;

import java.util.concurrent.Callable;

public class Throw {
    @SuppressWarnings("unchecked")
    public static <T extends Throwable, V> V t(Throwable t) throws T {
        throw (T) t;
    }

    public static <T> T uncheckedV(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Throwable t) {
            return t(t);
        }
    }

    @FunctionalInterface
    public interface NoThrow {
        void execute() throws Throwable;
    }

    public static void unchecked(NoThrow noThrow) {
        try {
            noThrow.execute();
        } catch (Throwable t) {
            t(t);
        }
    }
}
