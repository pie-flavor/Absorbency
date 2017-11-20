package flavor.pie.absorbency.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaUserdata;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.TwoArgFunction;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.ChatTypeMessageReceiver;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.channel.MessageReceiver;
import org.spongepowered.api.text.chat.ChatType;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.storage.WorldProperties;
import flavor.pie.absorbency.util.FunctionHelper;
import flavor.pie.absorbency.util.LuaUtils;
import flavor.pie.absorbency.util.MetatableBindings;

public class ChatLib extends TwoArgFunction {
    public static LuaTable instance;
    public static LuaTable mt;

    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaTable tbl = tableOf();
        instance = tbl;
        mt = MetatableBindings.of(tbl);
        tbl.set("broadcast", FunctionHelper.funcvV(ChatLib::broadcast));
        tbl.set("all", userdataOf(MessageChannel.TO_ALL, mt));
        tbl.set("all_players", userdataOf(MessageChannel.TO_PLAYERS, mt));
        tbl.set("console", userdataOf(MessageChannel.TO_CONSOLE, mt));
        tbl.set("none", userdataOf(MessageChannel.TO_NONE, mt));
        tbl.set("send", FunctionHelper.funcvV(ChatLib::send));
        tbl.set("send_from", FunctionHelper.funcvV(ChatLib::sendFrom));
        tbl.set("fixed", FunctionHelper.funcv(ChatLib::fixed));
        tbl.set("combined", FunctionHelper.funcv(ChatLib::combined));
        tbl.set("permission", FunctionHelper.func1(ChatLib::permission));
        tbl.set("world", FunctionHelper.func1(ChatLib::world));
        env.set("chat", tbl);
        return tbl;
    }

    public static void broadcast(Varargs varargs) {
        send(varargsOf(new LuaUserdata(Sponge.getServer().getBroadcastChannel()), varargs.subargs(2)));
    }

    public static void send(Varargs varargs) {
        Text text = TextLib.toText(varargs.arg(2));
        LuaValue type = varargs.arg(3);
        if (varargs.arg1().isuserdata(MessageChannel.class)) {
            MessageChannel chan = (MessageChannel) varargs.arg1().checkuserdata(MessageChannel.class);
            if (type.isnil()) {
                chan.send(text);
            } else {
                ChatType ctype = LuaUtils.toCatalog(type, ChatType.class);
                chan.send(text, ctype);
            }
        } else {
            if (type.isnil()) {
                MessageReceiver receiver = (MessageReceiver) varargs.arg1().checkuserdata(MessageReceiver.class);
                receiver.sendMessage(text);
            } else {
                ChatTypeMessageReceiver receiver = (ChatTypeMessageReceiver) varargs.arg1().checkuserdata(ChatTypeMessageReceiver.class);
                ChatType ctype = LuaUtils.toCatalog(type, ChatType.class);
                receiver.sendMessage(ctype, text);
            }
        }
    }

    public static void sendFrom(Varargs varargs) {
        MessageChannel chan;
        if (varargs.arg1().isuserdata(MessageChannel.class)) {
            chan = (MessageChannel) varargs.arg1().checkuserdata(MessageChannel.class);
        } else {
            MessageReceiver receiver = (MessageReceiver) varargs.arg1().checkuserdata(MessageReceiver.class);
            chan = MessageChannel.fixed(receiver);
        }
        LuaValue senderValue = varargs.arg(2);
        CommandSource sender;
        if (senderValue.isstring()) {
            String value = senderValue.checkjstring();
            Optional<Player> player = Sponge.getServer().getPlayer(value);
            if (!player.isPresent()) {
                throw new LuaError("Unknown source " + value);
            } else {
                sender = player.get();
            }
        } else {
            sender = (CommandSource) senderValue.checkuserdata(CommandSource.class);
        }
        Text text = TextLib.toText(varargs.arg(3));
        LuaValue type = varargs.arg(4);
        if (type.isnil()) {
            chan.send(sender, text);
        } else {
            ChatType ctype = LuaUtils.toCatalog(type, ChatType.class);
            chan.send(text, ctype);
        }
    }

    public static LuaValue fixed(Varargs varargs) {
        List<MessageReceiver> receivers = new ArrayList<>();
        for (LuaValue value : LuaUtils.iterate(varargs)) {
            if (value.istable()) {
                for (LuaValue receiver : LuaUtils.iterate(value.checktable())) {
                    receivers.add((MessageReceiver) receiver.checkuserdata(MessageReceiver.class));
                }
            } else {
                receivers.add((MessageReceiver) value.checkuserdata(MessageReceiver.class));
            }
        }
        MessageChannel chan = MessageChannel.fixed(receivers);
        return userdataOf(chan, mt);
    }

    public static LuaValue combined(Varargs varargs) {
        List<MessageChannel> chans = new ArrayList<>();
        for (LuaValue value : LuaUtils.iterate(varargs)) {
            if (value.istable()) {
                for (LuaValue chan : LuaUtils.iterate(value.checktable())) {
                    chans.add((MessageChannel) chan.checkuserdata(MessageChannel.class));
                }
            } else {
                chans.add((MessageChannel) value.checkuserdata(MessageChannel.class));
            }
        }
        MessageChannel chan = MessageChannel.combined(chans);
        return userdataOf(chan, mt);
    }

    public static LuaValue permission(LuaValue perm) {
        MessageChannel chan = MessageChannel.permission(perm.checkjstring());
        return userdataOf(chan, mt);
    }

    public static LuaValue world(LuaValue world) {
        MessageChannel chan;
        if (world.isstring()) {
            try {
                chan = MessageChannel.world(Sponge.getServer().getWorld(UUID.fromString(world.checkjstring())).orElseThrow(() -> new LuaError("no such world")));
            } catch (IllegalArgumentException ex) {
                chan = MessageChannel.world(Sponge.getServer().getWorld(world.checkjstring()).orElseThrow(() -> new LuaError("no such world")));
            }
        } else if (world.isuserdata(World.class)) {
            chan = MessageChannel.world((World) world.checkuserdata(World.class));
        } else {
            chan = MessageChannel.world(Sponge.getServer().getWorld(((WorldProperties) world.checkuserdata(WorldProperties.class)).getUniqueId()).orElseThrow(() -> new LuaError("world not loaded")));
        }
        return userdataOf(chan, mt);
    }
}