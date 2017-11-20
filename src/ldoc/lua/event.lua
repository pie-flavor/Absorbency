--- Functions for working with events, as well as all listenable events' modules.
-- Any event objects are indexed to their respective event's module, allowing for methods.
-- @submodule event

local event = {}

--- event.achievement_get
-- @section achievement_get

--- Fired when a player gets an achievement.
event.achievement_get = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.achievement_get.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.achievement_get.set_cancelled(event, cancelled) end

--- Gets the message to be used in the event.
-- @param event the event
-- @return the message
function event.achievement_get.get_message(event) end

--- Sets the message to be used in the event.
-- @param event the event
-- @param message the message, in a text table
-- @see text
function event.achievement_get.set_message(event, message) end

--- Gets the player involved in the event.
-- @param event the event
-- @return the player
-- @see player
-- @see entity
function event.achievement_get.get_player(event) end

--- Gets the channel that the message will be sent to.
-- @param event the event
-- @return the player
-- @see chat
function event.achievement_get.get_channel(event) end

--- Sets the channel that the message will be sent to.
-- @param event the event
-- @param channel the channel
-- @see chat
function event.achievement_get.set_channel(event, channel) end

--- Gets the achievement used.
-- @param event the event
-- @return the achievement
function event.achievement_get.get_achievement(event) end

--- event.fish_hook
-- @section fish_hook
event.fish_hook = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.fish_hook.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.fish_hook.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.fish_hook.get_entity(event) end

--- Gets the fish hook entity.
-- @param event the event
-- @return the hook
-- @see entity
function event.fish_hook.get_hook(event) end

--- Gets the hooked entity.
-- @param event the event
-- @return the entity
-- @see entity
function event.fish_hook.get_hooked_entity(event) end

--- Fired when the player casts their fishing rod.
-- @section fish_start
event.fish_start = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.fish_start.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.fish_start.set_cancelled(event, cancelled) end

--- Gets the fish hook entity.
-- @param event the event
-- @return the hook
-- @see entity
function event.fish_start.get_hook(event) end

--- Fired when the player pulls their fishing rod back.
-- @section fish_stop
event.fish_stop = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.fish_stop.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.fish_stop.set_cancelled(event, cancelled) end

--- Gets the item being hooked.
-- @param event the event
-- @return the item or nil if there is none
-- @see item
function event.fish_stop.get_item(event) end

--- Sets the item being hooked.
-- @param event the event
-- @param item the item
-- @see item
function event.fish_stop.set_item(event, item) end

--- Gets the fish hook entity.
-- @param event the event
-- @return the hook
-- @see entity
function event.fish_stop.get_hook(event) end

--- Gets the hooked entity.
-- @param event the event
-- @return the entity
-- @see entity
function event.fish_stop.get_hooked_entity(event) end

--- Gets the experience produced from this event.
-- @param event the event
-- @return the experience
function event.fish_stop.get_xp(event) end

--- Sets the experience produced from this event.
-- @param event the event
-- @param xp the experience
function event.fish_stop.set_xp(event, xp) end

--- Fired when lightning strikes.
-- @section lightning
event.lightning = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.lightning.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.lightning.set_cancelled(event, cancelled) end

--- Gets the lightning bolt of the event.
-- @param event the event
-- @return the lightning bold
-- @see entity
function event.lightning.get_lightning(event) end

--- Gets all entities that have been hit.
-- @param event the event
-- @return a table of all hit entities
-- @see entity
function event.lightning.get_hit_entities(event) end

--- Gets all blocks that have been hit.
-- @param event the event
-- @return a table of all hit blocks
-- @see block
function event.lightning.get_hit_blocks(event) end

--- Fired when a player starts sleeping.
-- @section sleep_start
event.sleep_start = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.sleep_start.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.sleep_start.set_cancelled(event, cancelled) end

--- Gets the player involved in the event.
-- @param event the event
-- @return the player
-- @see player
-- @see entity
function event.sleep_start.get_player(event) end

--- Gets the bed the player used.
-- @param event the event
-- @return the bed
-- @see block
function event.sleep_start.get_bed(event) end

--- Fired when a player stops sleeping.
-- @section sleep_end
event.sleep_end = {}

--- Gets the player involved in the event.
-- @param event the event
-- @return the player
-- @see player
-- @see entity
function event.sleep_end.get_player(event) end

--- Gets the bed the player used.
-- @param event the event
-- @return the bed
-- @see block
function event.sleep_end.get_bed(event) end

--- Fired when a block is broken.
-- @section block_break
event.block_break = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.block_break.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.block_break.set_cancelled(event, cancelled) end

--- Gets a list of transactions involved in this event.
-- @param event the event
-- @return a table of transactions of blocks
-- @see block
-- @see util.transaction
function event.block_break.get_blocks(event) end

--- Fired when a block decays.
-- @section block_decay
event.block_decay = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.block_decay.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.block_decay.set_cancelled(event, cancelled) end

--- Gets a list of transactions involved in this event.
-- @param event the event
-- @return a table of transactions of blocks
-- @see block
-- @see util.transaction
function event.block_decay.get_blocks(event) end

--- Fired when a block grows.
-- @section block_grow
event.block_grow = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.block_grow.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.block_grow.set_cancelled(event, cancelled) end

--- Gets a list of transactions involved in this event.
-- @param event the event
-- @return a table of transactions of blocks
-- @see block
-- @see util.transaction
function event.block_grow.get_blocks(event) end

--- Fired when a block is modified.
-- @section block_modify
event.block_modify = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.block_modify.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.block_modify.set_cancelled(event, cancelled) end

--- Gets a list of transactions involved in this event.
-- @param event the event
-- @return a table of transactions of blocks
-- @see block
-- @see util.transaction
function event.block_modify.get_blocks(event) end

--- Fired when a block changes for any reason.
-- @section block_change
event.block_change = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.block_change.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.block_change.set_cancelled(event, cancelled) end

--- Gets a list of transactions involved in this event.
-- @param event the event
-- @return a table of transactions of blocks
-- @see block
-- @see util.transaction
function event.block_change.get_blocks(event) end

--- Fired when a block is placed.
-- @section block_place
event.block_place = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.block_place.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.block_place.set_cancelled(event, cancelled) end

--- Gets a list of transactions involved in this event.
-- @param event the event
-- @return a table of transactions of blocks
-- @see block
-- @see util.transaction
function event.block_place.get_blocks(event) end

--- Fired when an entity collides with a block.
-- @section block_collide
event.block_collide = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.block_collide.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.block_collide.set_cancelled(event, cancelled) end

event.block_rightclick_mainhand = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.block_rightclick_mainhand.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.block_rightclick_mainhand.set_cancelled(event, cancelled) end

event.block_leftclick = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.block_leftclick.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.block_leftclick.set_cancelled(event, cancelled) end

event.block_rightclick_offhand = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.block_rightclick_offhand.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.block_rightclick_offhand.set_cancelled(event, cancelled) end

event.block_rightclick = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.block_rightclick.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.block_rightclick.set_cancelled(event, cancelled) end

event.block_update = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.block_update.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.block_update.set_cancelled(event, cancelled) end

event.brew_finish = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.brew_finish.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.brew_finish.set_cancelled(event, cancelled) end

event.brew_start = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.brew_start.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.brew_start.set_cancelled(event, cancelled) end

event.brew_stop = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.brew_stop.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.brew_stop.set_cancelled(event, cancelled) end

event.sign_change = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.sign_change.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.sign_change.set_cancelled(event, cancelled) end

event.smelt_start = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.smelt_start.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.smelt_start.set_cancelled(event, cancelled) end

event.smelt_consume = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.smelt_consume.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.smelt_consume.set_cancelled(event, cancelled) end

event.smelt_stop = {}

event.smelt_finish = {}

event.command = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.command.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.command.set_cancelled(event, cancelled) end

event.tab_command = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.tab_command.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.tab_command.set_cancelled(event, cancelled) end

event.tab_chat = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.tab_chat.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.tab_chat.set_cancelled(event, cancelled) end

event.tab = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.tab.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.tab.set_cancelled(event, cancelled) end

event.economy = {}

event.attack_entity = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.attack_entity.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.attack_entity.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.attack_entity.get_entity(event) end

event.breed = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.breed.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.breed.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.breed.get_entity(event) end

event.equip = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.equip.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.equip.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.equip.get_entity(event) end

event.equip_entity = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.equip_entity.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.equip_entity.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.equip_entity.get_entity(event) end

event.equip_player = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.equip_player.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.equip_player.set_cancelled(event, cancelled) end

--- Gets the player involved in the event.
-- @param event the event
-- @return the player
-- @see player
-- @see entity
function event.equip_player.get_player(event) end

event.change_xp = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.change_xp.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.change_xp.set_cancelled(event, cancelled) end

--- Gets the player involved in the event.
-- @param event the event
-- @return the player
-- @see player
-- @see entity
function event.change_xp.get_player(event) end

--- Gets the experience produced from this event.
-- @param event the event
-- @return the experience
function event.change_xp.get_xp(event) end

--- Sets the experience produced from this event.
-- @param event the event
-- @param xp the experience
function event.change_xp.set_xp(event, xp) end

event.potion_get = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.potion_get.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.potion_get.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.potion_get.get_entity(event) end

event.potion_remove = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.potion_remove.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.potion_remove.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.potion_remove.get_entity(event) end

event.potion_stop = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.potion_stop.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.potion_stop.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.potion_stop.get_entity(event) end

event.entity_collide = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.entity_collide.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.entity_collide.set_cancelled(event, cancelled) end

event.damage_entity = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.damage_entity.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.damage_entity.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.damage_entity.get_entity(event) end

event.entity_death = {}

--- Gets the message to be used in the event.
-- @param event the event
-- @return the message
function event.entity_death.get_message(event) end

--- Sets the message to be used in the event.
-- @param event the event
-- @param message the message, in a text table
-- @see text
function event.entity_death.set_message(event, message) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.entity_death.get_entity(event) end

--- Gets the channel that the message will be sent to.
-- @param event the event
-- @return the player
-- @see chat
function event.entity_death.get_channel(event) end

--- Sets the channel that the message will be sent to.
-- @param event the event
-- @param channel the channel
-- @see chat
function event.entity_death.set_channel(event, channel) end

event.entity_remove = {}

--- Gets the message to be used in the event.
-- @param event the event
-- @return the message
function event.entity_remove.get_message(event) end

--- Sets the message to be used in the event.
-- @param event the event
-- @param message the message, in a text table
-- @see text
function event.entity_remove.set_message(event, message) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.entity_remove.get_entity(event) end

--- Gets the channel that the message will be sent to.
-- @param event the event
-- @return the player
-- @see chat
function event.entity_remove.get_channel(event) end

--- Sets the channel that the message will be sent to.
-- @param event the event
-- @param channel the channel
-- @see chat
function event.entity_remove.set_channel(event, channel) end

event.item_expire = {}

--- Gets the item entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.item_expire.get_item_entity(event) end

event.entity_heal = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.entity_heal.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.entity_heal.set_cancelled(event, cancelled) end

event.entity_ignite = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.entity_ignite.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.entity_ignite.set_cancelled(event, cancelled) end

event.entity_rightclick_mainhand = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.entity_rightclick_mainhand.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.entity_rightclick_mainhand.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.entity_rightclick_mainhand.get_entity(event) end

event.entity_rightclick_offhand = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.entity_rightclick_offhand.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.entity_rightclick_offhand.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.entity_rightclick_offhand.get_entity(event) end

event.entity_rightclick = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.entity_rightclick.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.entity_rightclick.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.entity_rightclick.get_entity(event) end

event.entity_leftclick = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.entity_leftclick.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.entity_leftclick.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.entity_leftclick.get_entity(event) end

event.entity_leash = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.entity_leash.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.entity_leash.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.entity_leash.get_entity(event) end

event.entity_unleash = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.entity_unleash.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.entity_unleash.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.entity_unleash.get_entity(event) end

event.entity_move = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.entity_move.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.entity_move.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.entity_move.get_entity(event) end

event.entity_teleport = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.entity_teleport.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.entity_teleport.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.entity_teleport.get_entity(event) end

event.entity_portal = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.entity_portal.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.entity_portal.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.entity_portal.get_entity(event) end

event.entity_ride = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.entity_ride.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.entity_ride.set_cancelled(event, cancelled) end

event.entity_dismount = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.entity_dismount.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.entity_dismount.set_cancelled(event, cancelled) end

event.entity_spawn = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.entity_spawn.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.entity_spawn.set_cancelled(event, cancelled) end

event.entity_chunkload = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.entity_chunkload.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.entity_chunkload.set_cancelled(event, cancelled) end

event.spawner_spawn = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.spawner_spawn.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.spawner_spawn.set_cancelled(event, cancelled) end

event.entity_tame = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.entity_tame.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.entity_tame.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.entity_tame.get_entity(event) end

event.explosive_prime = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.explosive_prime.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.explosive_prime.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.explosive_prime.get_entity(event) end

event.explosive_explode = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.explosive_explode.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.explosive_explode.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.explosive_explode.get_entity(event) end

event.gamemode_change = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.gamemode_change.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.gamemode_change.set_cancelled(event, cancelled) end

--- Gets the player involved in the event.
-- @param event the event
-- @return the player
-- @see player
-- @see entity
function event.gamemode_change.get_player(event) end

event.xp_level_change = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.xp_level_change.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.xp_level_change.set_cancelled(event, cancelled) end

--- Gets the player involved in the event.
-- @param event the event
-- @return the player
-- @see player
-- @see entity
function event.xp_level_change.get_player(event) end

event.player_kick = {}

--- Gets the message to be used in the event.
-- @param event the event
-- @return the message
function event.player_kick.get_message(event) end

--- Sets the message to be used in the event.
-- @param event the event
-- @param message the message, in a text table
-- @see text
function event.player_kick.set_message(event, message) end

--- Gets the player involved in the event.
-- @param event the event
-- @return the player
-- @see player
-- @see entity
function event.player_kick.get_player(event) end

--- Gets the channel that the message will be sent to.
-- @param event the event
-- @return the player
-- @see chat
function event.player_kick.get_channel(event) end

--- Sets the channel that the message will be sent to.
-- @param event the event
-- @param channel the channel
-- @see chat
function event.player_kick.set_channel(event, channel) end

event.resourcepack_status = {}

event.player_respawn = {}

--- Gets the player involved in the event.
-- @param event the event
-- @return the player
-- @see player
-- @see entity
function event.player_respawn.get_player(event) end

event.projectile_launch = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.projectile_launch.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.projectile_launch.set_cancelled(event, cancelled) end

--- Gets the entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.projectile_launch.get_entity(event) end

event.reload = {}

event.inv_drag_left = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.inv_drag_left.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.inv_drag_left.set_cancelled(event, cancelled) end

event.inv_drag_right = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.inv_drag_right.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.inv_drag_right.set_cancelled(event, cancelled) end

event.inv_place_left = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.inv_place_left.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.inv_place_left.set_cancelled(event, cancelled) end

event.inv_place_right = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.inv_place_right.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.inv_place_right.set_cancelled(event, cancelled) end

event.inv_swap = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.inv_swap.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.inv_swap.set_cancelled(event, cancelled) end

event.inv_shift_click = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.inv_shift_click.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.inv_shift_click.set_cancelled(event, cancelled) end

event.inv_click = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.inv_click.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.inv_click.set_cancelled(event, cancelled) end

event.change_held_item = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.change_held_item.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.change_held_item.set_cancelled(event, cancelled) end

event.item_pickup = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.item_pickup.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.item_pickup.set_cancelled(event, cancelled) end

--- Gets the item entity involved in the event.
-- @param event the event
-- @return the entity
-- @see entity
function event.item_pickup.get_item_entity(event) end

event.item_transfer = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.item_transfer.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.item_transfer.set_cancelled(event, cancelled) end

event.item_drop = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.item_drop.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.item_drop.set_cancelled(event, cancelled) end

event.item_drop_destruct = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.item_drop_destruct.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.item_drop_destruct.set_cancelled(event, cancelled) end

event.item_dispense = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.item_dispense.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.item_dispense.set_cancelled(event, cancelled) end

event.inv_open = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.inv_open.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.inv_open.set_cancelled(event, cancelled) end

event.inv_close = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.inv_close.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.inv_close.set_cancelled(event, cancelled) end

event.item_leftclick = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.item_leftclick.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.item_leftclick.set_cancelled(event, cancelled) end

event.item_rightclick = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.item_rightclick.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.item_rightclick.set_cancelled(event, cancelled) end

event.item_click = {}

event.item_rightclick_mainhand = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.item_rightclick_mainhand.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.item_rightclick_mainhand.set_cancelled(event, cancelled) end

event.item_rightclick_offhand = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.item_rightclick_offhand.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.item_rightclick_offhand.set_cancelled(event, cancelled) end

event.item_start_use = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.item_start_use.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.item_start_use.set_cancelled(event, cancelled) end

event.item_stop_use = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.item_stop_use.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.item_stop_use.set_cancelled(event, cancelled) end

event.item_finish_use = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.item_finish_use.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.item_finish_use.set_cancelled(event, cancelled) end

event.item_replace_use = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.item_replace_use.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.item_replace_use.set_cancelled(event, cancelled) end

event.chat = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.chat.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.chat.set_cancelled(event, cancelled) end

--- Gets the message to be used in the event.
-- @param event the event
-- @return the message
function event.chat.get_message(event) end

--- Sets the message to be used in the event.
-- @param event the event
-- @param message the message, in a text table
-- @see text
function event.chat.set_message(event, message) end

--- Gets the channel that the message will be sent to.
-- @param event the event
-- @return the player
-- @see chat
function event.chat.get_channel(event) end

--- Sets the channel that the message will be sent to.
-- @param event the event
-- @param channel the channel
-- @see chat
function event.chat.set_channel(event, channel) end

event.message_channel = {}

--- Gets the message to be used in the event.
-- @param event the event
-- @return the message
function event.message_channel.get_message(event) end

--- Sets the message to be used in the event.
-- @param event the event
-- @param message the message, in a text table
-- @see text
function event.message_channel.set_message(event, message) end

--- Gets the channel that the message will be sent to.
-- @param event the event
-- @return the player
-- @see chat
function event.message_channel.get_channel(event) end

--- Sets the channel that the message will be sent to.
-- @param event the event
-- @param channel the channel
-- @see chat
function event.message_channel.set_channel(event, channel) end

event.player_join = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.player_join.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.player_join.set_cancelled(event, cancelled) end

--- Gets the message to be used in the event.
-- @param event the event
-- @return the message
function event.player_join.get_message(event) end

--- Sets the message to be used in the event.
-- @param event the event
-- @param message the message, in a text table
-- @see text
function event.player_join.set_message(event, message) end

--- Gets the player involved in the event.
-- @param event the event
-- @return the player
-- @see player
-- @see entity
function event.player_join.get_player(event) end

--- Gets the channel that the message will be sent to.
-- @param event the event
-- @return the player
-- @see chat
function event.player_join.get_channel(event) end

--- Sets the channel that the message will be sent to.
-- @param event the event
-- @param channel the channel
-- @see chat
function event.player_join.set_channel(event, channel) end

event.player_quit = {}

--- Gets the message to be used in the event.
-- @param event the event
-- @return the message
function event.player_quit.get_message(event) end

--- Sets the message to be used in the event.
-- @param event the event
-- @param message the message, in a text table
-- @see text
function event.player_quit.set_message(event, message) end

--- Gets the player involved in the event.
-- @param event the event
-- @return the player
-- @see player
-- @see entity
function event.player_quit.get_player(event) end

--- Gets the channel that the message will be sent to.
-- @param event the event
-- @return the player
-- @see chat
function event.player_quit.get_channel(event) end

--- Sets the channel that the message will be sent to.
-- @param event the event
-- @param channel the channel
-- @see chat
function event.player_quit.set_channel(event, channel) end

event.rcon_connect = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.rcon_connect.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.rcon_connect.set_cancelled(event, cancelled) end

event.rcon_disconnect = {}

event.server_ping = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.server_ping.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.server_ping.set_cancelled(event, cancelled) end

event.server_query = {}

event.server_query_basic = {}

event.stat_change = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.stat_change.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.stat_change.set_cancelled(event, cancelled) end

--- Gets the player involved in the event.
-- @param event the event
-- @return the player
-- @see player
-- @see entity
function event.stat_change.get_player(event) end

event.ban = {}

--- Gets the player involved in the event.
-- @param event the event
-- @return the player
-- @see player
-- @see entity
function event.ban.get_player(event) end

event.ip_ban = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.ip_ban.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.ip_ban.set_cancelled(event, cancelled) end

event.unban = {}

event.ip_unban = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.ip_unban.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.ip_unban.set_cancelled(event, cancelled) end

event.gamerule = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.gamerule.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.gamerule.set_cancelled(event, cancelled) end

event.weather = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.weather.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.weather.set_cancelled(event, cancelled) end

event.make_portal = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.make_portal.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.make_portal.set_cancelled(event, cancelled) end

event.explosion = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.explosion.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.explosion.set_cancelled(event, cancelled) end

event.chunk_gen = {}

event.world_load = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.world_load.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.world_load.set_cancelled(event, cancelled) end

event.world_save = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.world_save.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.world_save.set_cancelled(event, cancelled) end

event.world_unload = {}

--- Gets whether or not the event is cancelled.
-- @param event the event
-- @return whether the event is cancelled
function event.world_unload.is_cancelled(event) end

--- Sets whether or not the event is cancelled.
-- @param event the event
-- @param cancelled whether the event is cancelled
function event.world_unload.set_cancelled(event, cancelled) end

event.chunk_load = {}

event.chunk_unload = {}