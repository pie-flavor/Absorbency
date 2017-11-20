package flavor.pie.absorbency.api;

import flavor.pie.absorbency.Absorbency;
import flavor.pie.absorbency.util.FunctionHelper;
import flavor.pie.absorbency.util.MetatableBindings;

import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaUserdata;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.EventListener;
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

import static flavor.pie.absorbency.api.EventLibs.*;

public class EventLib extends TwoArgFunction {

    public static LuaTable instance;

    @SuppressWarnings("unchecked")
    public static <T extends Event> LuaValue register(LuaValue event, LuaValue fn) {
        Class<T> clazz = (Class<T>) event.checkuserdata(Class.class);
        EventListener<T> listener = new LuaEventListener<>(fn.checkfunction(), clazz);
        Sponge.getEventManager().registerListener(Absorbency.instance, clazz, listener);
        return new LuaUserdata(listener, MetatableBindings.of(instance.get("listener").checktable()));
    }

    @SuppressWarnings("unchecked")
    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaTable tbl = tableOf();
        tbl.set("register", FunctionHelper.func2(EventLib::register));
        tbl.set("achievement_get", new LuaUserdata(GrantAchievementEvent.TargetPlayer.class, MetatableBindings.of(achievementGet)));
        tbl.set("fish_hook", new LuaUserdata(FishingEvent.HookEntity.class, MetatableBindings.of(fishHook)));
        tbl.set("fish_start", new LuaUserdata(FishingEvent.Start.class, MetatableBindings.of(fishStart)));
        tbl.set("fish_stop", new LuaUserdata(FishingEvent.Stop.class, MetatableBindings.of(fishStop)));
        tbl.set("lightning", new LuaUserdata(LightningEvent.Strike.class, MetatableBindings.of(lightning)));
        tbl.set("sleep_start", new LuaUserdata(SleepingEvent.Pre.class, MetatableBindings.of(sleepStart)));
        tbl.set("sleep_end", new LuaUserdata(SleepingEvent.Finish.class, MetatableBindings.of(sleepEnd)));
        tbl.set("block_break", new LuaUserdata(ChangeBlockEvent.Break.class, MetatableBindings.of(blockBreak)));
        tbl.set("block_decay", new LuaUserdata(ChangeBlockEvent.Decay.class, MetatableBindings.of(blockDecay)));
        tbl.set("block_grow", new LuaUserdata(ChangeBlockEvent.Grow.class, MetatableBindings.of(blockGrow)));
        tbl.set("block_modify", new LuaUserdata(ChangeBlockEvent.Modify.class, MetatableBindings.of(blockModify)));
        tbl.set("block_change", new LuaUserdata(ChangeBlockEvent.class, MetatableBindings.of(blockChange)));
        tbl.set("block_place", new LuaUserdata(ChangeBlockEvent.Place.class, MetatableBindings.of(blockPlace)));
        tbl.set("block_collide", new LuaUserdata(CollideBlockEvent.Impact.class, MetatableBindings.of(blockCollide)));
        tbl.set("block_rightclick_mainhand", new LuaUserdata(InteractBlockEvent.Secondary.MainHand.class, MetatableBindings.of(blockRightclickMainhand)));
        tbl.set("block_leftclick", new LuaUserdata(InteractBlockEvent.Secondary.OffHand.class, MetatableBindings.of(blockLeftclick)));
        tbl.set("block_rightclick_offhand", new LuaUserdata(InteractBlockEvent.Secondary.OffHand.class, MetatableBindings.of(blockRightlickOffhand)));
        tbl.set("block_rightclick", new LuaUserdata(InteractBlockEvent.Secondary.class, MetatableBindings.of(blockRightclick)));
        tbl.set("block_update", new LuaUserdata(NotifyNeighborBlockEvent.class, MetatableBindings.of(blockUpdate)));
        tbl.set("brew_finish", new LuaUserdata(BrewingEvent.Finish.class, MetatableBindings.of(brewFinish)));
        tbl.set("brew_start", new LuaUserdata(BrewingEvent.Start.class, MetatableBindings.of(brewStart)));
        tbl.set("brew_stop", new LuaUserdata(BrewingEvent.Interrupt.class, MetatableBindings.of(brewStop)));
        tbl.set("sign_change", new LuaUserdata(ChangeSignEvent.class, MetatableBindings.of(signChange)));
        tbl.set("smelt_start", new LuaUserdata(SmeltEvent.Start.class, MetatableBindings.of(smeltStart)));
        tbl.set("smelt_consume", new LuaUserdata(SmeltEvent.ConsumeFuel.class, MetatableBindings.of(smeltConsume)));
        tbl.set("smelt_stop", new LuaUserdata(SmeltEvent.Interrupt.class, MetatableBindings.of(smeltStop)));
        tbl.set("smelt_finish", new LuaUserdata(SmeltEvent.Finish.class, MetatableBindings.of(smeltFinish)));
        tbl.set("command", new LuaUserdata(SendCommandEvent.class, MetatableBindings.of(command)));
        tbl.set("tab_command", new LuaUserdata(TabCompleteEvent.Command.class, MetatableBindings.of(tabCommand)));
        tbl.set("tab_chat", new LuaUserdata(TabCompleteEvent.Chat.class, MetatableBindings.of(tabChat)));
        tbl.set("tab", new LuaUserdata(TabCompleteEvent.class, MetatableBindings.of(tab)));
        tbl.set("economy", new LuaUserdata(EconomyTransactionEvent.class, MetatableBindings.of(economy)));
        tbl.set("attack_entity", new LuaUserdata(AttackEntityEvent.class, MetatableBindings.of(attackEntity)));
        tbl.set("breed", new LuaUserdata(BreedEntityEvent.Breed.class, MetatableBindings.of(breed)));
        tbl.set("equip", new LuaUserdata(ChangeEntityEquipmentEvent.class, MetatableBindings.of(equip)));
        tbl.set("equip_entity", new LuaUserdata(ChangeEntityEquipmentEvent.TargetLiving.class, MetatableBindings.of(equipEntity)));
        tbl.set("equip_player", new LuaUserdata(ChangeEntityEquipmentEvent.TargetPlayer.class, MetatableBindings.of(equipPlayer)));
        tbl.set("change_xp", new LuaUserdata(ChangeEntityExperienceEvent.class, MetatableBindings.of(changeXp)));
        tbl.set("potion_get", new LuaUserdata(ChangeEntityPotionEffectEvent.Gain.class, MetatableBindings.of(potionGet)));
        tbl.set("potion_remove", new LuaUserdata(ChangeEntityPotionEffectEvent.Remove.class, MetatableBindings.of(potionRemove)));
        tbl.set("potion_stop", new LuaUserdata(ChangeEntityPotionEffectEvent.Expire.class, MetatableBindings.of(potionStop)));
        tbl.set("entity_collide", new LuaUserdata(CollideEntityEvent.Impact.class, MetatableBindings.of(entityCollide)));
        tbl.set("damage_entity", new LuaUserdata(DamageEntityEvent.class, MetatableBindings.of(damageEntity)));
        tbl.set("entity_death", new LuaUserdata(DestructEntityEvent.Death.class, MetatableBindings.of(entityDeath)));
        tbl.set("entity_remove", new LuaUserdata(DestructEntityEvent.class, MetatableBindings.of(entityRemove)));
        tbl.set("item_expire", new LuaUserdata(ExpireEntityEvent.TargetItem.class, MetatableBindings.of(itemExpire)));
        tbl.set("entity_heal", new LuaUserdata(HealEntityEvent.class, MetatableBindings.of(entityHeal)));
        tbl.set("entity_ignite", new LuaUserdata(IgniteEntityEvent.class, MetatableBindings.of(entityIgnite)));
        tbl.set("entity_rightclick_mainhand", new LuaUserdata(InteractEntityEvent.Secondary.MainHand.class, MetatableBindings.of(entityRightclickMainhand)));
        tbl.set("entity_rightclick_offhand", new LuaUserdata(InteractEntityEvent.Secondary.OffHand.class, MetatableBindings.of(entityRightclickOffhand)));
        tbl.set("entity_rightclick", new LuaUserdata(InteractEntityEvent.Secondary.class, MetatableBindings.of(entityRightclick)));
        tbl.set("entity_leftclick", new LuaUserdata(InteractEntityEvent.Primary.class, MetatableBindings.of(entityLeftclick)));
        tbl.set("entity_leash", new LuaUserdata(LeashEntityEvent.class, MetatableBindings.of(entityLeash)));
        tbl.set("entity_unleash", new LuaUserdata(UnleashEntityEvent.class, MetatableBindings.of(entityUnleash)));
        tbl.set("entity_move", new LuaUserdata(MoveEntityEvent.class, MetatableBindings.of(entityMove)));
        tbl.set("entity_teleport", new LuaUserdata(MoveEntityEvent.Teleport.class, MetatableBindings.of(entityTeleport)));
        tbl.set("entity_portal", new LuaUserdata(MoveEntityEvent.Teleport.Portal.class, MetatableBindings.of(entityPortal)));
        tbl.set("entity_ride", new LuaUserdata(RideEntityEvent.Mount.class, MetatableBindings.of(entityRide)));
        tbl.set("entity_dismount", new LuaUserdata(RideEntityEvent.Dismount.class, MetatableBindings.of(entityDismount)));
        tbl.set("entity_spawn", new LuaUserdata(SpawnEntityEvent.class, MetatableBindings.of(entitySpawn)));
        tbl.set("entity_chunkload", new LuaUserdata(SpawnEntityEvent.ChunkLoad.class, MetatableBindings.of(entityChunkload)));
        tbl.set("spawner_spawn", new LuaUserdata(SpawnEntityEvent.Spawner.class, MetatableBindings.of(spawnerSpawn)));
        tbl.set("entity_tame", new LuaUserdata(TameEntityEvent.class, MetatableBindings.of(entityTame)));
        tbl.set("explosive_prime", new LuaUserdata(PrimeExplosiveEvent.Pre.class, MetatableBindings.of(explosivePrime)));
        tbl.set("explosive_explode", new LuaUserdata(DetonateExplosiveEvent.class, MetatableBindings.of(explosiveExplode)));
        tbl.set("gamemode_change", new LuaUserdata(ChangeGameModeEvent.TargetPlayer.class, MetatableBindings.of(gamemodeChange)));
        tbl.set("xp_level_change", new LuaUserdata(ChangeLevelEvent.TargetPlayer.class, MetatableBindings.of(xpLevelChange)));
        tbl.set("player_kick", new LuaUserdata(KickPlayerEvent.class, MetatableBindings.of(playerKick)));
        tbl.set("resourcepack_status", new LuaUserdata(ResourcePackStatusEvent.class, MetatableBindings.of(resourcepackStatus)));
        tbl.set("player_respawn", new LuaUserdata(RespawnPlayerEvent.class, MetatableBindings.of(playerRespawn)));
        tbl.set("projectile_launch", new LuaUserdata(LaunchProjectileEvent.class, MetatableBindings.of(projectileLaunch)));
        tbl.set("reload", new LuaUserdata(GameReloadEvent.class, MetatableBindings.of(reload)));
        tbl.set("inv_drag_left", new LuaUserdata(ClickInventoryEvent.Drag.Primary.class, MetatableBindings.of(invDragLeft)));
        tbl.set("inv_drag_right", new LuaUserdata(ClickInventoryEvent.Drag.Secondary.class, MetatableBindings.of(invDragRight)));
        tbl.set("inv_place_left", new LuaUserdata(ClickInventoryEvent.Drop.Full.class, MetatableBindings.of(invPlaceLeft)));
        tbl.set("inv_place_right", new LuaUserdata(ClickInventoryEvent.Drop.Single.class, MetatableBindings.of(invPlaceRight)));
        tbl.set("inv_swap", new LuaUserdata(ClickInventoryEvent.NumberPress.class, MetatableBindings.of(invSwap)));
        tbl.set("inv_shift_click", new LuaUserdata(ClickInventoryEvent.Shift.class, MetatableBindings.of(invShiftClick)));
        tbl.set("inv_click", new LuaUserdata(ClickInventoryEvent.class, MetatableBindings.of(invClick)));
        tbl.set("change_held_item", new LuaUserdata(ChangeInventoryEvent.Held.class, MetatableBindings.of(changeHeldItem)));
        tbl.set("item_pickup", new LuaUserdata(ChangeInventoryEvent.Pickup.class, MetatableBindings.of(itemPickup)));
        tbl.set("item_transfer", new LuaUserdata(ChangeInventoryEvent.Transfer.class, MetatableBindings.of(itemTransfer)));
        tbl.set("item_drop", new LuaUserdata(DropItemEvent.class, MetatableBindings.of(itemDrop)));
        tbl.set("item_drop_destruct", new LuaUserdata(DropItemEvent.Destruct.class, MetatableBindings.of(itemDropDestruct)));
        tbl.set("item_dispense", new LuaUserdata(DropItemEvent.Dispense.class, MetatableBindings.of(itemDispense)));
        tbl.set("inv_open", new LuaUserdata(InteractInventoryEvent.Open.class, MetatableBindings.of(invOpen)));
        tbl.set("inv_close", new LuaUserdata(InteractInventoryEvent.Close.class, MetatableBindings.of(invClose)));
        tbl.set("item_leftclick", new LuaUserdata(InteractItemEvent.Primary.class, MetatableBindings.of(itemLeftclick)));
        tbl.set("item_rightclick", new LuaUserdata(InteractItemEvent.Secondary.class, MetatableBindings.of(itemRightclick)));
        tbl.set("item_click", new LuaUserdata(InteractItemEvent.class, MetatableBindings.of(itemClick)));
        tbl.set("item_rightclick_mainhand", new LuaUserdata(InteractItemEvent.Secondary.MainHand.class, MetatableBindings.of(itemRightclickMainhand)));
        tbl.set("item_rightclick_offhand", new LuaUserdata(InteractItemEvent.Secondary.OffHand.class, MetatableBindings.of(itemRightclickOffhand)));
        tbl.set("item_start_use", new LuaUserdata(UseItemStackEvent.Start.class, MetatableBindings.of(itemStartUse)));
        tbl.set("item_stop_use", new LuaUserdata(UseItemStackEvent.Stop.class, MetatableBindings.of(itemStopUse)));
        tbl.set("item_finish_use", new LuaUserdata(UseItemStackEvent.Finish.class, MetatableBindings.of(itemFinishUse)));
        tbl.set("item_replace_use", new LuaUserdata(UseItemStackEvent.Replace.class, MetatableBindings.of(itemReplaceUse)));
        tbl.set("chat", new LuaUserdata(MessageChannelEvent.Chat.class, MetatableBindings.of(chat)));
        tbl.set("message_channel", new LuaUserdata(MessageChannelEvent.class, MetatableBindings.of(messageChannel)));
        tbl.set("player_join", new LuaUserdata(ClientConnectionEvent.Join.class, MetatableBindings.of(playerJoin)));
        tbl.set("player_quit", new LuaUserdata(ClientConnectionEvent.Disconnect.class, MetatableBindings.of(playerQuit)));
        tbl.set("rcon_connect", new LuaUserdata(RconConnectionEvent.Connect.class, MetatableBindings.of(rconConnect)));
        tbl.set("rcon_disconnect", new LuaUserdata(RconConnectionEvent.Disconnect.class, MetatableBindings.of(rconDisconnect)));
        tbl.set("server_ping", new LuaUserdata(ClientPingServerEvent.class, MetatableBindings.of(serverPing)));
        tbl.set("server_query", new LuaUserdata(QueryServerEvent.Full.class, MetatableBindings.of(serverQuery)));
        tbl.set("server_query_basic", new LuaUserdata(QueryServerEvent.Basic.class, MetatableBindings.of(serverQueryBasic)));
        tbl.set("stat_change", new LuaUserdata(ChangeStatisticEvent.TargetPlayer.class, MetatableBindings.of(statChange)));
        tbl.set("ban", new LuaUserdata(BanUserEvent.class, MetatableBindings.of(ban)));
        tbl.set("ip_ban", new LuaUserdata(BanIpEvent.class, MetatableBindings.of(ipBan)));
        tbl.set("unban", new LuaUserdata(PardonUserEvent.class, MetatableBindings.of(unban)));
        tbl.set("ip_unban", new LuaUserdata(PardonIpEvent.class, MetatableBindings.of(ipUnban)));
        tbl.set("gamerule", new LuaUserdata(ChangeWorldGameRuleEvent.class, MetatableBindings.of(gamerule)));
        tbl.set("weather", new LuaUserdata(ChangeWorldWeatherEvent.class, MetatableBindings.of(weather)));
        tbl.set("make_portal", new LuaUserdata(ConstructPortalEvent.class, MetatableBindings.of(makePortal)));
        tbl.set("explosion", new LuaUserdata(ExplosionEvent.Detonate.class, MetatableBindings.of(explosion)));
        tbl.set("chunk_gen", new LuaUserdata(GenerateChunkEvent.class, MetatableBindings.of(chunkGen)));
        tbl.set("world_load", new LuaUserdata(LoadWorldEvent.class, MetatableBindings.of(worldLoad)));
        tbl.set("world_save", new LuaUserdata(SaveWorldEvent.class, MetatableBindings.of(worldSave)));
        tbl.set("world_unload", new LuaUserdata(UnloadWorldEvent.class, MetatableBindings.of(worldUnload)));
        tbl.set("chunk_load", new LuaUserdata(LoadChunkEvent.class, MetatableBindings.of(chunkLoad)));
        tbl.set("chunk_unload", new LuaUserdata(UnloadChunkEvent.class, MetatableBindings.of(chunkUnload)));
        LuaTable listener = tableOf();
        listener.set("unregister", FunctionHelper.func1V(val -> {
            EventListener<? extends Event> listen = (EventListener<? extends Event>) val.checkuserdata(EventListener.class);
            Sponge.getEventManager().unregisterListeners(listen);
        }));
        tbl.set("listener", listener);
        env.set("event", tbl);
        instance = tbl;
        return tbl;
    }

    public static class LuaEventListener<T extends Event> implements EventListener<T> {
        LuaFunction func;
        Class<T> clazz;
        public LuaEventListener(LuaFunction func, Class<T> clazz) {
            this.func = func;
            this.clazz = clazz;
        }

        @Override
        public void handle(T event) throws Exception {
            func.call(userdataOf(event, MetatableBindings.forEvent(clazz)));
        }
    }
}
