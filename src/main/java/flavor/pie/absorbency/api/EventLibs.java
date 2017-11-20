package flavor.pie.absorbency.api;

import flavor.pie.absorbency.util.FunctionHelper;
import flavor.pie.absorbency.util.LuaUtils;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaUserdata;
import org.luaj.vm2.LuaValue;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.weather.Lightning;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.achievement.GrantAchievementEvent;
import org.spongepowered.api.event.action.FishingEvent;
import org.spongepowered.api.event.action.LightningEvent;
import org.spongepowered.api.event.action.SleepingEvent;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.cause.NamedCause;
import org.spongepowered.api.event.entity.ChangeEntityExperienceEvent;
import org.spongepowered.api.event.entity.TargetEntityEvent;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.event.message.MessageEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.text.channel.MessageChannel;

import java.util.List;
import java.util.stream.Stream;

import static org.luaj.vm2.LuaValue.tableOf;
import static org.luaj.vm2.LuaValue.valueOf;

public class EventLibs {
    public static final LuaTable
            achievementGet = tableOf(),
            fishHook = tableOf(),
            fishStart = tableOf(),
            fishStop = tableOf(),
            lightning = tableOf(),
            sleepStart = tableOf(),
            sleepEnd = tableOf(),
            blockBreak = tableOf(),
            blockDecay = tableOf(),
            blockGrow = tableOf(),
            blockModify = tableOf(),
            blockChange = tableOf(),
            blockPlace = tableOf(),
            blockCollide = tableOf(),
            blockRightclickMainhand = tableOf(),
            blockLeftclick = tableOf(),
            blockRightlickOffhand = tableOf(),
            blockRightclick = tableOf(),
            blockUpdate = tableOf(),
            brewFinish = tableOf(),
            brewStart = tableOf(),
            brewStop = tableOf(),
            signChange = tableOf(),
            smeltStart = tableOf(),
            smeltStop = tableOf(),
            smeltConsume = tableOf(),
            smeltFinish = tableOf(),
            command = tableOf(),
            tabCommand = tableOf(),
            tabChat = tableOf(),
            tab = tableOf(),
            economy = tableOf(),
            attackEntity = tableOf(),
            breed = tableOf(),
            equip = tableOf(),
            equipEntity = tableOf(),
            equipPlayer = tableOf(),
            changeXp = tableOf(),
            potionGet = tableOf(),
            potionRemove = tableOf(),
            potionStop = tableOf(),
            entityCollide = tableOf(),
            damageEntity = tableOf(),
            entityDeath = tableOf(),
            entityRemove = tableOf(),
            itemExpire = tableOf(),
            entityHeal = tableOf(),
            entityIgnite = tableOf(),
            entityRightclickMainhand = tableOf(),
            entityRightclickOffhand = tableOf(),
            entityRightclick = tableOf(),
            entityLeftclick = tableOf(),
            entityLeash = tableOf(),
            entityUnleash = tableOf(),
            entityMove = tableOf(),
            entityTeleport = tableOf(),
            entityPortal = tableOf(),
            entityRide = tableOf(),
            entityDismount = tableOf(),
            entitySpawn = tableOf(),
            entityChunkload = tableOf(),
            spawnerSpawn = tableOf(),
            entityTame = tableOf(),
            explosivePrime = tableOf(),
            explosiveExplode = tableOf(),
            gamemodeChange = tableOf(),
            xpLevelChange = tableOf(),
            playerKick = tableOf(),
            resourcepackStatus = tableOf(),
            playerRespawn = tableOf(),
            projectileLaunch = tableOf(),
            reload = tableOf(),
            invDragLeft = tableOf(),
            invDragRight = tableOf(),
            invPlaceLeft = tableOf(),
            invPlaceRight = tableOf(),
            invSwap = tableOf(),
            invShiftClick = tableOf(),
            invClick = tableOf(),
            changeHeldItem = tableOf(),
            itemPickup = tableOf(),
            itemTransfer = tableOf(),
            itemDrop = tableOf(),
            itemDropDestruct = tableOf(),
            itemDispense = tableOf(),
            invOpen = tableOf(),
            invClose = tableOf(),
            itemLeftclick = tableOf(),
            itemRightclick = tableOf(),
            itemClick = tableOf(),
            itemRightclickMainhand = tableOf(),
            itemRightclickOffhand = tableOf(),
            itemStartUse = tableOf(),
            itemStopUse = tableOf(),
            itemFinishUse = tableOf(),
            itemReplaceUse = tableOf(),
            chat = tableOf(),
            messageChannel = tableOf(),
            playerJoin = tableOf(),
            playerQuit = tableOf(),
            rconConnect = tableOf(),
            rconDisconnect = tableOf(),
            serverPing = tableOf(),
            serverQuery = tableOf(),
            serverQueryBasic = tableOf(),
            statChange = tableOf(),
            ban = tableOf(),
            ipBan = tableOf(),
            unban = tableOf(),
            ipUnban = tableOf(),
            gamerule = tableOf(),
            weather = tableOf(),
            makePortal = tableOf(),
            explosion = tableOf(),
            chunkGen = tableOf(),
            worldLoad = tableOf(),
            worldSave = tableOf(),
            worldUnload = tableOf(),
            chunkLoad = tableOf(),
            chunkUnload = tableOf();

    static {
        // Cancellable (isCancelled/setCancelled)
        Stream.of(
                attackEntity, ipBan, breed, brewStart, brewStop, brewFinish, blockChange, blockBreak, blockModify,
                blockDecay, blockGrow, blockPlace, equip, equipEntity, equipPlayer, changeXp, potionGet, potionRemove,
                potionStop, gamemodeChange, itemPickup, changeHeldItem, itemTransfer, xpLevelChange, signChange,
                statChange, gamerule, weather, invClick, invDragLeft, invDragRight, invPlaceLeft, invPlaceRight,
                invShiftClick, invSwap, playerJoin, serverPing, blockCollide, entityCollide, makePortal, damageEntity,
                explosiveExplode, itemDrop, itemDropDestruct, itemDispense, explosion, fishHook, fishStart, fishStop,
                achievementGet, entityHeal, entityIgnite, blockLeftclick, blockRightclick, blockRightclickMainhand,
                blockRightlickOffhand, entityLeftclick, entityRightclick, entityRightclickMainhand,
                entityRightclickOffhand, invClose, invOpen, itemLeftclick, itemRightclick, itemRightclickMainhand,
                itemRightclickOffhand, projectileLaunch, entityLeash, lightning, worldLoad, chat, entityMove,
                entityTeleport, entityPortal, blockUpdate, ipUnban, explosivePrime, rconConnect, entityRide,
                entityDismount, worldSave, sleepStart, command, smeltConsume, smeltStart, entitySpawn, entityChunkload,
                spawnerSpawn, tab, tabChat, tabCommand, entityTame, entityUnleash, worldUnload, itemFinishUse,
                itemReplaceUse, itemStartUse, itemStopUse
        ).forEach(tbl -> {
            tbl.set("is_cancelled", FunctionHelper.func1(e ->
                    valueOf(((Cancellable) e.checkuserdata(Cancellable.class)).isCancelled())));
            tbl.set("set_cancelled", FunctionHelper.func2V((e, v) ->
                    ((Cancellable) e.checkuserdata(Cancellable.class)).setCancelled(v.checkboolean())));
        });
        //MessageEvent (getMessage/setMessage)
        Stream.of(playerJoin, playerQuit, entityRemove, entityDeath, achievementGet, playerKick, chat,
                messageChannel).forEach(tbl -> {
            tbl.set("get_message", FunctionHelper.func1(e ->
                    TextLib.fromText(((MessageEvent) e.checkuserdata(MessageEvent.class)).getMessage())));
            tbl.set("set_message", FunctionHelper.func2V((e, m) ->
                    ((MessageEvent) e.checkuserdata(MessageEvent.class)).setMessage(TextLib.toText(m))));
        });
        //TargetEntityEvent (getEntity)
        Stream.of(
                attackEntity, breed, equip, equipEntity, potionGet, potionRemove, potionStop, damageEntity,
                entityRemove, entityDeath, explosiveExplode, entityHeal, entityIgnite,
                entityLeftclick, entityRightclick, entityRightclickMainhand, entityRightclickOffhand, projectileLaunch,
                entityLeash, entityMove, entityTeleport, entityPortal, explosivePrime,
                entityTame, entityUnleash
        ).forEach(tbl -> tbl.set("get_entity", FunctionHelper.func1(e ->
                new LuaUserdata(((TargetEntityEvent) e.checkuserdata(TargetEntityEvent.class)).getTargetEntity(),
                        EntityLib.mt))));
        //Player oriented TargetEntityEvent (getEntity)
        Stream.of(
                ban, equipPlayer, changeXp, gamemodeChange, xpLevelChange, statChange, playerJoin, playerQuit, achievementGet,
                playerKick, playerRespawn, sleepStart, sleepEnd, blockChange, blockBreak, blockPlace, blockDecay, blockGrow,
                blockModify
        ).forEach(tbl -> tbl.set("get_player", FunctionHelper.func1(e ->
                new LuaUserdata(((TargetEntityEvent) e.checkuserdata(TargetEntityEvent.class)).getTargetEntity(),
                        EntityLib.mt))));
        //Item oriented TargetEntityEvent (getEntity)
        Stream.of(itemPickup, itemExpire).forEach(tbl -> tbl.set("get_item_entity", FunctionHelper.func1(e ->
                new LuaUserdata(((TargetEntityEvent) e.checkuserdata(TargetEntityEvent.class)).getTargetEntity(),
                        EntityLib.mt))));
        //MessageChannelEvent (getChannel/setChannel)
        Stream.of(
                playerQuit, playerJoin, entityRemove, entityDeath, achievementGet, playerKick, chat, messageChannel
        ).forEach(tbl -> {
                tbl.set("get_channel", FunctionHelper.func1(e ->
                        new LuaUserdata(((MessageChannelEvent) e.checkuserdata(MessageChannelEvent.class)).getChannel(), ChatLib.mt)));
                tbl.set("set_channel", FunctionHelper.func2V((e, chan) ->
                        ((MessageChannelEvent) e).setChannel((MessageChannel) e.checkuserdata(MessageChannel.class))));
        });
        //ChangeEntityExperienceEvent (getExperience/setExperience)
        Stream.of(changeXp, fishStop).forEach(tbl -> {
                tbl.set("get_xp", FunctionHelper.func1(e -> valueOf(((ChangeEntityExperienceEvent) e.checkuserdata(ChangeEntityExperienceEvent.class)).getExperience())));
                tbl.set("set_xp", FunctionHelper.func2V((e, i) -> ((ChangeEntityExperienceEvent) e.checkuserdata(ChangeEntityExperienceEvent.class)).setExperience(i.checkint())));
        });
        //Player-oriented Cause source
        Stream.of(fishStart, fishStop, fishHook).forEach(tbl ->
                tbl.set("get_player", FunctionHelper.func1(e -> ((Event) e.checkuserdata(Event.class)).getCause().get(NamedCause.SOURCE, Player.class).<LuaValue>map(p -> new LuaUserdata(p, EntityLib.mt)).orElse(LuaValue.NIL))));

        achievementGet.set("get_achievement", FunctionHelper.func1(e -> valueOf(((GrantAchievementEvent) e.checkuserdata(GrantAchievementEvent.class)).getAchievement().getId())));

        fishStop.set("get_item", FunctionHelper.func1(e -> {
                List<Transaction<ItemStackSnapshot>> transactions = ((FishingEvent.Stop) e.checkuserdata(FishingEvent.Stop.class)).getItemStackTransaction();
                if (transactions.size() == 0) return LuaValue.NIL;
                ItemStackSnapshot stack = transactions.get(0).getOriginal();
                if (stack.getType().equals(ItemTypes.NONE)) return LuaValue.NIL;
                return ItemLib.fromStack(stack);
                
        }));
        fishStop.set("set_item", FunctionHelper.func2V((e, item) -> {
                ItemStackSnapshot stack = ItemLib.toStack(item);
                List<Transaction<ItemStackSnapshot>> transactions = ((FishingEvent.Stop) e.checkuserdata(FishingEvent.Stop.class)).getItemStackTransaction();
                if (transactions.size() == 0) return;
                transactions.get(0).setCustom(stack);
        }));
        Stream.of(fishStart, fishStop, fishHook).forEach(tbl -> tbl.set("get_hook", FunctionHelper.func1(e -> new LuaUserdata(((FishingEvent) e.checkuserdata(FishingEvent.class)).getFishHook(), EntityLib.mt))));
        Stream.of(fishStop, fishHook).forEach(tbl -> tbl.set("get_hooked_entity", FunctionHelper.func1(e -> new LuaUserdata(((TargetEntityEvent) e.checkuserdata(TargetEntityEvent.class)).getTargetEntity(), EntityLib.mt))));

        lightning.set("get_lightning", FunctionHelper.func1(e -> ((LightningEvent.Strike) e.checkuserdata(LightningEvent.Strike.class)).getCause().get(NamedCause.SOURCE, Lightning.class).<LuaValue>map(l -> new LuaUserdata(l, EntityLib.mt)).orElse(LuaValue.NIL)));
        lightning.set("get_hit_entities", FunctionHelper.func1(e -> LuaUtils.makeTable(((LightningEvent.Strike) e.checkuserdata(LightningEvent.Strike.class)).getEntities(), ent -> new LuaUserdata(ent, EntityLib.mt))));
        lightning.set("get_hit_blocks", FunctionHelper.func1(e -> LuaUtils.makeTable(((LightningEvent.Strike) e.checkuserdata(LightningEvent.Strike.class)).getTransactions(), t -> BlockLib.fromSnapshot(t.getFinal()))));

        Stream.of(sleepStart, sleepEnd).forEach(tbl -> tbl.set("get_bed", FunctionHelper.func1(e -> BlockLib.fromSnapshot(((SleepingEvent) e.checkuserdata(SleepingEvent.class)).getBed()))));
        
        Stream.of(
                blockChange, blockBreak, blockDecay, blockGrow, blockModify, blockPlace
        ).forEach(tbl -> tbl.set("get_blocks", FunctionHelper.func1(e -> LuaUtils.makeTable(((ChangeBlockEvent) e.checkuserdata(ChangeBlockEvent.class)).getTransactions(), t -> new LuaUserdata(new UtilLib.LuaTransaction<>(t, BlockLib::toSnapshot, BlockLib::fromSnapshot))))));
        //todo some common cause options
    }
}
