--- Functions for working with transactions.
-- @module transaction

local transaction = {}

--- Gets the original element of this transaction.
-- @param transaction the transaction
-- @return the original element
function transaction.get_original(transaction) end

--- Gets the final result of this transaction.
-- @param transaction the transaction
-- @return the final result
function transaction.get_final(transaction) end

--- Gets the default result of this transaction. 
-- @param transaction the transaction
-- @return the default result
function transaction.get_default(transaction) end

--- Sets the final result of this transaction.
-- @param transaction the transaction
-- @param custom the custom result
function transaction.set_custom(transaction, custom) end

--- Gets the custom result of this transaction.
-- @param transaction the transaction
-- @return the custom result, or nil if none set
function transaction.get_custom(transaction) end

--- Gets whether this transaction is valid.
-- @param transaction the transaction
-- @return whether the transaction is valid
function transaction.is_valid(transaction) end

--- Sets whether this transaction is valid.
-- @param transaction the transaction
-- @param valid whether the transaction is valid
function transaction.set_valid(transaction, valid) end

return transaction