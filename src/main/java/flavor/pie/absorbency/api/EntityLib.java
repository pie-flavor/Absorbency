package flavor.pie.absorbency.api;

import com.flowpowered.math.vector.Vector3d;
import flavor.pie.absorbency.util.FunctionHelper;
import flavor.pie.absorbency.util.LuaUtils;
import flavor.pie.absorbency.util.MetatableBindings;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.TwoArgFunction;
import org.spongepowered.api.CatalogTypes;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Collection;
import java.util.UUID;

public class EntityLib extends TwoArgFunction {
    public static LuaTable instance;
    public static LuaTable mt;


    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaTable tbl = tableOf();
        instance = tbl;
        mt = MetatableBindings.of(tbl);
        tbl.setmetatable(PlayerLib.mt);
        tbl.set("add_passenger", FunctionHelper.func2(EntityLib::addPassenger));
        tbl.set("can_see", FunctionHelper.func2(EntityLib::canSee));
        tbl.set("clear_passengers", FunctionHelper.func1V(EntityLib::clearPassengers));
        tbl.set("damage", FunctionHelper.funcv(EntityLib::damage));
        tbl.set("get_base_vehicle", FunctionHelper.func1(EntityLib::getBaseVehicle));
        tbl.set("get_creator", FunctionHelper.func1(EntityLib::getCreator));
        tbl.set("get_nearby_entities", FunctionHelper.func2(EntityLib::getNearbyEntities));
        tbl.set("get_passengers", FunctionHelper.func1(EntityLib::getPassengers));
        tbl.set("get_rotation", FunctionHelper.funcv(EntityLib::getRotation));
        tbl.set("get_type", FunctionHelper.func1(EntityLib::getType));
        tbl.set("get_vehicle", FunctionHelper.func1(EntityLib::getVehicle));
        tbl.set("get_velocity", FunctionHelper.funcv(EntityLib::getVelocity));
        tbl.set("has_gravity", FunctionHelper.func1(EntityLib::hasGravity));
        tbl.set("has_passenger", FunctionHelper.func2(EntityLib::hasPassenger));
        tbl.set("is_loaded", FunctionHelper.func1(EntityLib::isLoaded));
        tbl.set("is_on_ground", FunctionHelper.func1(EntityLib::isOnGround));
        tbl.set("is_removed", FunctionHelper.func1(EntityLib::isRemoved));
        tbl.set("remove", FunctionHelper.func1V(EntityLib::remove));
        tbl.set("remove_passenger", FunctionHelper.func2V(EntityLib::removePassenger));
        tbl.set("set_creator", FunctionHelper.func2V(EntityLib::setCreator));
        tbl.set("set_location", FunctionHelper.funcv(EntityLib::setLocation));
        tbl.set("set_safe_location", FunctionHelper.funcv(EntityLib::setSafeLocation));
        tbl.set("set_vehicle", FunctionHelper.func2(EntityLib::setVehicle));
        tbl.set("transfer_to_world", FunctionHelper.funcv(EntityLib::transferToWorld));
        tbl.set("get_uuid", FunctionHelper.func1(EntityLib::getUUID));
        tbl.set("get_location", FunctionHelper.funcv(EntityLib::getLocation));
        tbl.set("get_world", FunctionHelper.func1(EntityLib::getWorld));
        tbl.set("translate", FunctionHelper.func1(EntityLib::translate));
        tbl.set("get_head_rotation", FunctionHelper.funcv(EntityLib::getHeadRotation));
        tbl.set("set_head_rotation", FunctionHelper.funcvV(EntityLib::setHeadRotation));
        env.set("entity", tbl);
        return tbl;
    }

    public static LuaValue addPassenger(LuaValue self, LuaValue value) {
        Entity entity = (Entity) value.checkuserdata(Entity.class);
        Entity selfEntity = (Entity) self.checkuserdata(Entity.class);
        return valueOf(selfEntity.addPassenger(entity));
    }

    public static LuaValue canSee(LuaValue self, LuaValue value) {
        Entity entity = (Entity) value.checkuserdata(Entity.class);
        Entity selfEntity = (Entity) self.checkuserdata(Entity.class);
        return valueOf(selfEntity.canSee(entity));
    }

    public static void clearPassengers(LuaValue self) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        entity.clearPassengers();
    }

    public static LuaValue damage(Varargs varargs) {
        Entity entity = (Entity) varargs.arg1().checkuserdata(Entity.class);
        double damage = varargs.arg(2).checkdouble();
        DamageSource.Builder builder = DamageSource.builder();
        if (varargs.isnoneornil(3)) {
            LuaValue tbl = varargs.checktable(3);
            LuaValue type = tbl.get("type");
            if (!type.isnil()) {
                builder.type(Sponge.getRegistry().getType(CatalogTypes.DAMAGE_TYPE, type.checkjstring())
                        .orElseThrow(() -> new LuaError("No such damage type")));
            }
            if (tbl.get("affect_creative").optboolean(false)) {
                builder.creative();
            }
            if (tbl.get("absolute").optboolean(false)) {
                builder.absolute();
            }
            if (tbl.get("bypass_armor").optboolean(false)) {
                builder.bypassesArmor();
            }
            if (tbl.get("explosion").optboolean(false)) {
                builder.explosion();
            }
            if (tbl.get("magical").optboolean(false)) {
                builder.magical();
            }
            if (tbl.get("difficulty_scale").optboolean(false)) {
                builder.scalesWithDifficulty();
            }
        }
        return valueOf(entity.damage(damage, builder.build()));
    }

    public static LuaValue getBaseVehicle(LuaValue self) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        return userdataOf(entity.getBaseVehicle(), mt);
    }

    public static LuaValue getCreator(LuaValue self) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        return valueOf(entity.getCreator().toString());
    }

    public static LuaValue getNearbyEntities(LuaValue self, LuaValue distance) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        Collection<Entity> nearby;
        if (distance.isnumber()) {
            nearby = entity.getNearbyEntities(distance.checkdouble());
        } else {
            nearby = entity.getNearbyEntities(LuaUtils.toPredicate(distance.checkclosure(), e -> userdataOf(e, mt)));
        }
        return LuaUtils.makeUserdataTable(nearby, mt);
    }

    public static LuaValue getPassengers(LuaValue self) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        return LuaUtils.makeUserdataTable(entity.getPassengers(), mt);
    }

    public static Varargs getRotation(Varargs varargs) {
        Entity entity = (Entity) varargs.arg1().checkuserdata(Entity.class);
        return LuaUtils.toVarargs(entity.getRotation());
    }

    public static LuaValue getType(LuaValue self) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        return valueOf(entity.getType().getId());
    }

    public static LuaValue getVehicle(LuaValue self) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        return entity.getVehicle().<LuaValue>map(e -> userdataOf(e, mt)).orElse(NIL);
    }

    public static Varargs getVelocity(Varargs varargs) {
        Entity entity = (Entity) varargs.arg1().checkuserdata(Entity.class);
        return LuaUtils.toVarargs(entity.getVelocity());
    }

    public static LuaValue hasGravity(LuaValue self) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        return valueOf(entity.gravity().get());
    }

    public static LuaValue hasPassenger(LuaValue self, LuaValue passenger) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        Entity passengerEntity = (Entity) passenger.checkuserdata(Entity.class);
        return valueOf(entity.hasPassenger(passengerEntity));
    }

    public static LuaValue isLoaded(LuaValue self) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        return valueOf(entity.isLoaded());
    }

    public static LuaValue isOnGround(LuaValue self) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        return valueOf(entity.isOnGround());
    }

    public static LuaValue isRemoved(LuaValue self) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        return valueOf(entity.isRemoved());
    }

    public static void remove(LuaValue self) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        entity.remove();
    }

    public static void removePassenger(LuaValue self, LuaValue passenger) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        Entity passengerEntity = (Entity) passenger.checkuserdata(Entity.class);
        entity.removePassenger(passengerEntity);
    }

    public static void setCreator(LuaValue self, LuaValue uuid) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        entity.setCreator(UUID.fromString(uuid.checkjstring()));
    }

    public static LuaValue setLocation(Varargs varargs) {
        Entity entity = (Entity) varargs.arg1().checkuserdata(Entity.class);
        Location<World> loc;
        if (varargs.narg() == 4) {
            loc = entity.getWorld().getLocation(LuaUtils.toVector3d(varargs.subargs(2)));
        } else {
            loc = ((World) varargs.arg(2).checkuserdata(World.class))
                    .getLocation(LuaUtils.toVector3d(varargs.subargs(3)));
        }
        return valueOf(entity.setLocation(loc));
    }

    public static LuaValue setSafeLocation(Varargs varargs) {
        Entity entity = (Entity) varargs.arg1().checkuserdata(Entity.class);
        Location<World> loc;
        if (varargs.narg() == 4) {
            loc = entity.getWorld().getLocation(LuaUtils.toVector3d(varargs.subargs(2)));
        } else {
            loc = ((World) varargs.arg(2).checkuserdata(World.class))
                    .getLocation(LuaUtils.toVector3d(varargs.subargs(3)));
        }
        return valueOf(entity.setLocationSafely(loc));
    }

    public static LuaValue setVehicle(LuaValue self, LuaValue value) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        Entity target = (Entity) value.checkuserdata(Entity.class);
        return valueOf(entity.setVehicle(target));
    }

    public static LuaValue transferToWorld(Varargs varargs) {
        Entity entity = (Entity) varargs.arg1().checkuserdata(Entity.class);
        if (varargs.arg(2).isstring()) {
            String world = varargs.arg(2).checkjstring();
            Vector3d pos = LuaUtils.toVector3d(varargs.subargs(3));
            return valueOf(entity.transferToWorld(world, pos));
        } else {
            World world = (World) varargs.arg(2).checkuserdata(World.class);
            if (varargs.narg() > 2) {
                Vector3d pos = LuaUtils.toVector3d(varargs.subargs(3));
                return valueOf(entity.transferToWorld(world, pos));
            } else {
                return valueOf(entity.transferToWorld(world));
            }
        }
    }

    public static LuaValue getUUID(LuaValue self) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        return valueOf(entity.getUniqueId().toString());
    }

    public static Varargs getLocation(Varargs varargs) {
        Entity entity = (Entity) varargs.arg1().checkuserdata(Entity.class);
        return WorldLib.fromLocation(entity.getLocation());
    }

    public static LuaValue getWorld(LuaValue self) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        return userdataOf(entity.getWorld(), WorldLib.mt);
    }

    public static LuaValue translate(LuaValue self) {
        Entity entity = (Entity) self.checkuserdata(Entity.class);
        return TextLib.fromText(Text.of(entity.getTranslation()));
    }

    public static Varargs getHeadRotation(Varargs varargs) {
        Living entity = (Living) varargs.arg1().checkuserdata(Living.class);
        return LuaUtils.toVarargs(entity.getHeadRotation());
    }

    public static void setHeadRotation(Varargs varargs) {
        Living entity = (Living) varargs.arg1().checkuserdata(Living.class);
        entity.setHeadRotation(LuaUtils.toVector3d(varargs.subargs(2)));
    }

}
