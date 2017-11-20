--- Functions for dealing with chat messages.
-- Any chat channels or message receivers are indexed to this module, allowing for methods.
-- @module chat

local chat = {}

--- A chat channel for all players plus the console.
-- @field all
chat.all = {}

--- A chat channel for all players.
-- @field all_players
chat.all_players = {}

--- A chat channel for the console.
-- @field console
chat.console = {}

--- A chat channel for nobody.
-- @field none
chat.none = {}

--- Sends a message to a chat channel or message receiver.
-- @param target the channel or receiver
-- @param message a text table
-- @param[opt='system'] type the chat type (can be 'action_bar', 'chat', or 'system')
-- @see text
function chat.send(target, message, type) end

--- Sends a message from a sender to a chat channel or message receiver.
-- @param target the channel or receiver
-- @param sender the sender
-- @param message a text table
-- @param[opt='system'] type the chat type (can be 'action_bar', 'chat', or 'system')
-- @see text
function chat.send_from(target, sender, message, type) end

--- Creates a chat channel from some number of message receivers.
-- @param arg a vararg of either message receivers or tables of message receivers
-- @return the created channel
function chat.fixed(...) end

--- Creates a chat channel from some number of other channels.
-- @param arg a vararg of either channels or tables of channels
-- @return the created channel
function chat.combined(...) end

--- Creates a chat channel for anyone with a given permission.
-- @param permission the permission to use
-- @return the created channel
function chat.permission(permission) end

--- Creates a chat channel for anyone in a given world.
-- @param world the world name, the world ID, a world, or world properties
-- @return the created channel
function chat.world(world) end

return chat