package flavor.pie.absorbency.util;

import flavor.pie.absorbency.api.EventLibs;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.achievement.GrantAchievementEvent;
import org.spongepowered.api.event.action.FishingEvent;
import org.spongepowered.api.event.action.LightningEvent;
import org.spongepowered.api.event.action.SleepingEvent;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.block.CollideBlockEvent;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.block.NotifyNeighborBlockEvent;
import org.spongepowered.api.event.block.tileentity.BrewingEvent;
import org.spongepowered.api.event.block.tileentity.ChangeSignEvent;
import org.spongepowered.api.event.block.tileentity.SmeltEvent;
import org.spongepowered.api.event.command.SendCommandEvent;
import org.spongepowered.api.event.command.TabCompleteEvent;
import org.spongepowered.api.event.economy.EconomyTransactionEvent;
import org.spongepowered.api.event.entity.AttackEntityEvent;
import org.spongepowered.api.event.entity.BreedEntityEvent;
import org.spongepowered.api.event.entity.ChangeEntityEquipmentEvent;
import org.spongepowered.api.event.entity.ChangeEntityExperienceEvent;
import org.spongepowered.api.event.entity.ChangeEntityPotionEffectEvent;
import org.spongepowered.api.event.entity.CollideEntityEvent;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.event.entity.ExpireEntityEvent;
import org.spongepowered.api.event.entity.HealEntityEvent;
import org.spongepowered.api.event.entity.IgniteEntityEvent;
import org.spongepowered.api.event.entity.InteractEntityEvent;
import org.spongepowered.api.event.entity.LeashEntityEvent;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.entity.RideEntityEvent;
import org.spongepowered.api.event.entity.SpawnEntityEvent;
import org.spongepowered.api.event.entity.TameEntityEvent;
import org.spongepowered.api.event.entity.UnleashEntityEvent;
import org.spongepowered.api.event.entity.explosive.DetonateExplosiveEvent;
import org.spongepowered.api.event.entity.explosive.PrimeExplosiveEvent;
import org.spongepowered.api.event.entity.living.humanoid.ChangeGameModeEvent;
import org.spongepowered.api.event.entity.living.humanoid.ChangeLevelEvent;
import org.spongepowered.api.event.entity.living.humanoid.player.KickPlayerEvent;
import org.spongepowered.api.event.entity.living.humanoid.player.ResourcePackStatusEvent;
import org.spongepowered.api.event.entity.living.humanoid.player.RespawnPlayerEvent;
import org.spongepowered.api.event.entity.projectile.LaunchProjectileEvent;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.item.inventory.DropItemEvent;
import org.spongepowered.api.event.item.inventory.InteractInventoryEvent;
import org.spongepowered.api.event.item.inventory.InteractItemEvent;
import org.spongepowered.api.event.item.inventory.UseItemStackEvent;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.event.network.BanIpEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.event.network.PardonIpEvent;
import org.spongepowered.api.event.network.rcon.RconConnectionEvent;
import org.spongepowered.api.event.server.ClientPingServerEvent;
import org.spongepowered.api.event.server.query.QueryServerEvent;
import org.spongepowered.api.event.statistic.ChangeStatisticEvent;
import org.spongepowered.api.event.user.BanUserEvent;
import org.spongepowered.api.event.user.PardonUserEvent;
import org.spongepowered.api.event.world.ChangeWorldGameRuleEvent;
import org.spongepowered.api.event.world.ChangeWorldWeatherEvent;
import org.spongepowered.api.event.world.ConstructPortalEvent;
import org.spongepowered.api.event.world.ExplosionEvent;
import org.spongepowered.api.event.world.GenerateChunkEvent;
import org.spongepowered.api.event.world.LoadWorldEvent;
import org.spongepowered.api.event.world.SaveWorldEvent;
import org.spongepowered.api.event.world.UnloadWorldEvent;
import org.spongepowered.api.event.world.chunk.LoadChunkEvent;
import org.spongepowered.api.event.world.chunk.UnloadChunkEvent;

import static org.luaj.vm2.LuaValue.tableOf;

public class MetatableBindings {
    public static LuaTable readOnly(LuaTable in) {
        LuaValue val = in.getmetatable();
        if (val == null) {
            val = tableOf();
        }
        val.set("__newindex", FunctionHelper.func3V((t, k, v) -> {
            throw new LuaError("Table is readonly");
        }));
        in.setmetatable(val);
        return in;
    }

    public static LuaTable of(LuaTable lib) {
        LuaTable mt = tableOf();
        mt.set("__index", lib);
        mt.set("__newindex", FunctionHelper.func3V((t, k, v) -> {
            throw new LuaError("Table is readonly");
        }));
        return mt;
    }

    public static LuaTable ofMutable(LuaTable lib) {
        LuaTable mt = tableOf();
        mt.set("__index", lib);
        return mt;
    }

    public static LuaTable apply(LuaTable value, LuaTable lib) {
        value.setmetatable(of(lib));
        return value;
    }

    public static LuaTable applyMutable(LuaTable value, LuaTable lib) {
        value.setmetatable(ofMutable(lib));
        return value;
    }

    public static LuaTable forEvent(Class<? extends Event> clazz) {
        if (clazz.equals(GrantAchievementEvent.TargetPlayer.class)) {
            return of(EventLibs.achievementGet);
        } else if (clazz.equals(FishingEvent.HookEntity.class)) {
            return of(EventLibs.fishHook);
        } else if (clazz.equals(FishingEvent.Start.class)) {
            return of(EventLibs.fishStart);
        } else if (clazz.equals(FishingEvent.Stop.class)) {
            return of(EventLibs.fishStop);
        } else if (clazz.equals(LightningEvent.Strike.class)) {
            return of(EventLibs.lightning);
        } else if (clazz.equals(SleepingEvent.Pre.class)) {
            return of(EventLibs.sleepStart);
        } else if (clazz.equals(SleepingEvent.Finish.class)) {
            return of(EventLibs.sleepEnd);
        } else if (clazz.equals(ChangeBlockEvent.Break.class)) {
            return of(EventLibs.blockBreak);
        } else if (clazz.equals(ChangeBlockEvent.Decay.class)) {
            return of(EventLibs.blockDecay);
        } else if (clazz.equals(ChangeBlockEvent.Grow.class)) {
            return of(EventLibs.blockGrow);
        } else if (clazz.equals(ChangeBlockEvent.Modify.class)) {
            return of(EventLibs.blockModify);
        } else if (clazz.equals(ChangeBlockEvent.class)) {
            return of(EventLibs.blockChange);
        } else if (clazz.equals(ChangeBlockEvent.Place.class)) {
            return of(EventLibs.blockPlace);
        } else if (clazz.equals(CollideBlockEvent.Impact.class)) {
            return of(EventLibs.blockCollide);
        } else if (clazz.equals(InteractBlockEvent.Secondary.MainHand.class)) {
            return of(EventLibs.blockRightclickMainhand);
        } else if (clazz.equals(InteractBlockEvent.Secondary.OffHand.class)) {
            return of(EventLibs.blockLeftclick);
        } else if (clazz.equals(InteractBlockEvent.Secondary.OffHand.class)) {
            return of(EventLibs.blockRightlickOffhand);
        } else if (clazz.equals(InteractBlockEvent.Secondary.class)) {
            return of(EventLibs.blockRightclick);
        } else if (clazz.equals(NotifyNeighborBlockEvent.class)) {
            return of(EventLibs.blockUpdate);
        } else if (clazz.equals(BrewingEvent.Finish.class)) {
            return of(EventLibs.brewFinish);
        } else if (clazz.equals(BrewingEvent.Start.class)) {
            return of(EventLibs.brewStart);
        } else if (clazz.equals(BrewingEvent.Interrupt.class)) {
            return of(EventLibs.brewStop);
        } else if (clazz.equals(ChangeSignEvent.class)) {
            return of(EventLibs.signChange);
        } else if (clazz.equals(SmeltEvent.Start.class)) {
            return of(EventLibs.smeltStart);
        } else if (clazz.equals(SmeltEvent.ConsumeFuel.class)) {
            return of(EventLibs.smeltConsume);
        } else if (clazz.equals(SmeltEvent.Interrupt.class)) {
            return of(EventLibs.smeltStop);
        } else if (clazz.equals(SmeltEvent.Finish.class)) {
            return of(EventLibs.smeltFinish);
        } else if (clazz.equals(SendCommandEvent.class)) {
            return of(EventLibs.command);
        } else if (clazz.equals(TabCompleteEvent.Command.class)) {
            return of(EventLibs.tabCommand);
        } else if (clazz.equals(TabCompleteEvent.Chat.class)) {
            return of(EventLibs.tabChat);
        } else if (clazz.equals(TabCompleteEvent.class)) {
            return of(EventLibs.tab);
        } else if (clazz.equals(EconomyTransactionEvent.class)) {
            return of(EventLibs.economy);
        } else if (clazz.equals(AttackEntityEvent.class)) {
            return of(EventLibs.attackEntity);
        } else if (clazz.equals(BreedEntityEvent.Breed.class)) {
            return of(EventLibs.breed);
        } else if (clazz.equals(ChangeEntityEquipmentEvent.class)) {
            return of(EventLibs.equip);
        } else if (clazz.equals(ChangeEntityEquipmentEvent.TargetLiving.class)) {
            return of(EventLibs.equipEntity);
        } else if (clazz.equals(ChangeEntityEquipmentEvent.TargetPlayer.class)) {
            return of(EventLibs.equipPlayer);
        } else if (clazz.equals(ChangeEntityExperienceEvent.class)) {
            return of(EventLibs.changeXp);
        } else if (clazz.equals(ChangeEntityPotionEffectEvent.Gain.class)) {
            return of(EventLibs.potionGet);
        } else if (clazz.equals(ChangeEntityPotionEffectEvent.Remove.class)) {
            return of(EventLibs.potionRemove);
        } else if (clazz.equals(ChangeEntityPotionEffectEvent.Expire.class)) {
            return of(EventLibs.potionStop);
        } else if (clazz.equals(CollideEntityEvent.Impact.class)) {
            return of(EventLibs.entityCollide);
        } else if (clazz.equals(DamageEntityEvent.class)) {
            return of(EventLibs.damageEntity);
        } else if (clazz.equals(DestructEntityEvent.Death.class)) {
            return of(EventLibs.entityDeath);
        } else if (clazz.equals(DestructEntityEvent.class)) {
            return of(EventLibs.entityRemove);
        } else if (clazz.equals(ExpireEntityEvent.TargetItem.class)) {
            return of(EventLibs.itemExpire);
        } else if (clazz.equals(HealEntityEvent.class)) {
            return of(EventLibs.entityHeal);
        } else if (clazz.equals(IgniteEntityEvent.class)) {
            return of(EventLibs.entityIgnite);
        } else if (clazz.equals(InteractEntityEvent.Secondary.MainHand.class)) {
            return of(EventLibs.entityRightclickMainhand);
        } else if (clazz.equals(InteractEntityEvent.Secondary.OffHand.class)) {
            return of(EventLibs.entityRightclickOffhand);
        } else if (clazz.equals(InteractEntityEvent.Secondary.class)) {
            return of(EventLibs.entityRightclick);
        } else if (clazz.equals(InteractEntityEvent.Primary.class)) {
            return of(EventLibs.entityLeftclick);
        } else if (clazz.equals(LeashEntityEvent.class)) {
            return of(EventLibs.entityLeash);
        } else if (clazz.equals(UnleashEntityEvent.class)) {
            return of(EventLibs.entityUnleash);
        } else if (clazz.equals(MoveEntityEvent.class)) {
            return of(EventLibs.entityMove);
        } else if (clazz.equals(MoveEntityEvent.Teleport.class)) {
            return of(EventLibs.entityTeleport);
        } else if (clazz.equals(MoveEntityEvent.Teleport.Portal.class)) {
            return of(EventLibs.entityPortal);
        } else if (clazz.equals(RideEntityEvent.Mount.class)) {
            return of(EventLibs.entityRide);
        } else if (clazz.equals(RideEntityEvent.Dismount.class)) {
            return of(EventLibs.entityDismount);
        } else if (clazz.equals(SpawnEntityEvent.class)) {
            return of(EventLibs.entitySpawn);
        } else if (clazz.equals(SpawnEntityEvent.ChunkLoad.class)) {
            return of(EventLibs.entityChunkload);
        } else if (clazz.equals(SpawnEntityEvent.Spawner.class)) {
            return of(EventLibs.spawnerSpawn);
        } else if (clazz.equals(TameEntityEvent.class)) {
            return of(EventLibs.entityTame);
        } else if (clazz.equals(PrimeExplosiveEvent.Pre.class)) {
            return of(EventLibs.explosivePrime);
        } else if (clazz.equals(DetonateExplosiveEvent.class)) {
            return of(EventLibs.explosiveExplode);
        } else if (clazz.equals(ChangeGameModeEvent.TargetPlayer.class)) {
            return of(EventLibs.gamemodeChange);
        } else if (clazz.equals(ChangeLevelEvent.TargetPlayer.class)) {
            return of(EventLibs.xpLevelChange);
        } else if (clazz.equals(KickPlayerEvent.class)) {
            return of(EventLibs.playerKick);
        } else if (clazz.equals(ResourcePackStatusEvent.class)) {
            return of(EventLibs.resourcepackStatus);
        } else if (clazz.equals(RespawnPlayerEvent.class)) {
            return of(EventLibs.playerRespawn);
        } else if (clazz.equals(LaunchProjectileEvent.class)) {
            return of(EventLibs.projectileLaunch);
        } else if (clazz.equals(GameReloadEvent.class)) {
            return of(EventLibs.reload);
        } else if (clazz.equals(ClickInventoryEvent.Drag.Primary.class)) {
            return of(EventLibs.invDragLeft);
        } else if (clazz.equals(ClickInventoryEvent.Drag.Secondary.class)) {
            return of(EventLibs.invDragRight);
        } else if (clazz.equals(ClickInventoryEvent.Drop.Full.class)) {
            return of(EventLibs.invPlaceLeft);
        } else if (clazz.equals(ClickInventoryEvent.Drop.Single.class)) {
            return of(EventLibs.invPlaceRight);
        } else if (clazz.equals(ClickInventoryEvent.NumberPress.class)) {
            return of(EventLibs.invSwap);
        } else if (clazz.equals(ClickInventoryEvent.Shift.class)) {
            return of(EventLibs.invShiftClick);
        } else if (clazz.equals(ClickInventoryEvent.class)) {
            return of(EventLibs.invClick);
        } else if (clazz.equals(ChangeInventoryEvent.Held.class)) {
            return of(EventLibs.changeHeldItem);
        } else if (clazz.equals(ChangeInventoryEvent.Pickup.class)) {
            return of(EventLibs.itemPickup);
        } else if (clazz.equals(ChangeInventoryEvent.Transfer.class)) {
            return of(EventLibs.itemTransfer);
        } else if (clazz.equals(DropItemEvent.class)) {
            return of(EventLibs.itemDrop);
        } else if (clazz.equals(DropItemEvent.Destruct.class)) {
            return of(EventLibs.itemDropDestruct);
        } else if (clazz.equals(DropItemEvent.Dispense.class)) {
            return of(EventLibs.itemDispense);
        } else if (clazz.equals(InteractInventoryEvent.Open.class)) {
            return of(EventLibs.invOpen);
        } else if (clazz.equals(InteractInventoryEvent.Close.class)) {
            return of(EventLibs.invClose);
        } else if (clazz.equals(InteractItemEvent.Primary.class)) {
            return of(EventLibs.itemLeftclick);
        } else if (clazz.equals(InteractItemEvent.Secondary.class)) {
            return of(EventLibs.itemRightclick);
        } else if (clazz.equals(InteractItemEvent.class)) {
            return of(EventLibs.itemClick);
        } else if (clazz.equals(InteractItemEvent.Secondary.MainHand.class)) {
            return of(EventLibs.itemRightclickMainhand);
        } else if (clazz.equals(InteractItemEvent.Secondary.OffHand.class)) {
            return of(EventLibs.itemRightclickOffhand);
        } else if (clazz.equals(UseItemStackEvent.Start.class)) {
            return of(EventLibs.itemStartUse);
        } else if (clazz.equals(UseItemStackEvent.Stop.class)) {
            return of(EventLibs.itemStopUse);
        } else if (clazz.equals(UseItemStackEvent.Finish.class)) {
            return of(EventLibs.itemFinishUse);
        } else if (clazz.equals(UseItemStackEvent.Replace.class)) {
            return of(EventLibs.itemReplaceUse);
        } else if (clazz.equals(MessageChannelEvent.Chat.class)) {
            return of(EventLibs.chat);
        } else if (clazz.equals(MessageChannelEvent.class)) {
            return of(EventLibs.messageChannel);
        } else if (clazz.equals(ClientConnectionEvent.Join.class)) {
            return of(EventLibs.playerJoin);
        } else if (clazz.equals(ClientConnectionEvent.Disconnect.class)) {
            return of(EventLibs.playerQuit);
        } else if (clazz.equals(RconConnectionEvent.Connect.class)) {
            return of(EventLibs.rconConnect);
        } else if (clazz.equals(RconConnectionEvent.Disconnect.class)) {
            return of(EventLibs.rconDisconnect);
        } else if (clazz.equals(ClientPingServerEvent.class)) {
            return of(EventLibs.serverPing);
        } else if (clazz.equals(QueryServerEvent.Full.class)) {
            return of(EventLibs.serverQuery);
        } else if (clazz.equals(QueryServerEvent.Basic.class)) {
            return of(EventLibs.serverQueryBasic);
        } else if (clazz.equals(ChangeStatisticEvent.TargetPlayer.class)) {
            return of(EventLibs.statChange);
        } else if (clazz.equals(BanUserEvent.class)) {
            return of(EventLibs.ban);
        } else if (clazz.equals(BanIpEvent.class)) {
            return of(EventLibs.ipBan);
        } else if (clazz.equals(PardonUserEvent.class)) {
            return of(EventLibs.unban);
        } else if (clazz.equals(PardonIpEvent.class)) {
            return of(EventLibs.ipUnban);
        } else if (clazz.equals(ChangeWorldGameRuleEvent.class)) {
            return of(EventLibs.gamerule);
        } else if (clazz.equals(ChangeWorldWeatherEvent.class)) {
            return of(EventLibs.weather);
        } else if (clazz.equals(ConstructPortalEvent.class)) {
            return of(EventLibs.makePortal);
        } else if (clazz.equals(ExplosionEvent.Detonate.class)) {
            return of(EventLibs.explosion);
        } else if (clazz.equals(GenerateChunkEvent.class)) {
            return of(EventLibs.chunkGen);
        } else if (clazz.equals(LoadWorldEvent.class)) {
            return of(EventLibs.worldLoad);
        } else if (clazz.equals(SaveWorldEvent.class)) {
            return of(EventLibs.worldSave);
        } else if (clazz.equals(UnloadWorldEvent.class)) {
            return of(EventLibs.worldUnload);
        } else if (clazz.equals(LoadChunkEvent.class)) {
            return of(EventLibs.chunkLoad);
        } else if (clazz.equals(UnloadChunkEvent.class)) {
            return of(EventLibs.chunkUnload);
        } else return tableOf();
    }
}
