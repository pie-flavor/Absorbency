package flavor.pie.absorbency.api;

import com.flowpowered.math.vector.Vector3d;
import flavor.pie.absorbency.util.LuaUtils;
import flavor.pie.absorbency.util.MetatableBindings;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.TwoArgFunction;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class WorldLib extends TwoArgFunction {

    public static LuaTable instance;
    public static LuaTable mt;

    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaTable tbl = tableOf();
        instance = tbl;
        mt = MetatableBindings.of(instance);

        env.set("world", tbl);
        return tbl;
    }

    public static Location<World> toLocation(Varargs varargs) {
        if (varargs.arg1().isuserdata(Location.class)) {
            return (Location<World>) varargs.arg1().checkuserdata(Location.class);
        } else {
            World world;
            if (varargs.arg1().isstring()) {
                String w = varargs.arg1().checkjstring();
                world = Sponge.getServer().getWorld(w).orElseThrow(() -> new LuaError("world not loaded"));
            } else {
                world = (World) varargs.arg1().checkuserdata(World.class);
            }
            Vector3d pos = LuaUtils.toVector3d(varargs.subargs(2));
            return world.getLocation(pos);
        }
    }

    public static Varargs fromLocation(Location<World> location) {
        LuaValue world = userdataOf(location.getExtent(), WorldLib.mt);
        Varargs vector = LuaUtils.toVarargs(location.getPosition());
        return LuaUtils.concatVarargs(world, vector);
    }
}
