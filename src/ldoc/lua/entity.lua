--- Functions for manipulating entities.
-- This module is indexed to the player module, for ease of use with player entities.
-- Any entity objects are indexed to this module, allowing for methods.
-- @module entity

local entity = {}

local mt = {}
mt.__index = player
setmetatable(entity, mt)
return entity