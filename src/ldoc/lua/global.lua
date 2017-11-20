--- Global functions.
-- @module global

--- Recursively copies a table. If any items are not copiable, an error is thrown.
-- @param tbl the table to copy
-- @return the copied table
function _G.copy(tbl) end

--- Recursively safe-copies a table. If any items are not copiable, they are reused instead of being copied.
-- @param tbl the table to copy
-- @return the copied table
function _G.pcopy(tbl) end

--- Shallow copies a table. If any items are not copiable, an error is thrown.
-- @param tbl the table to cpy
-- @return the copied table
function _G.scopy(tbl) end

--- Shallow safe-copies a table. If any items are not copiable, they are reused instead of being copied.
-- @param tbl the table to copy
-- @return the copied table
function _G.spcopy(tbl) end

--- Runs a function on the server thread. The supplied function should be no-arg and no-return.
-- @param func the function to run
function _G.sync(func) end

--- Runs a function asynchronously on the server thread. The supplied function should be no-arg and no-return.
-- @param func the function to run
function _G.async(func) end