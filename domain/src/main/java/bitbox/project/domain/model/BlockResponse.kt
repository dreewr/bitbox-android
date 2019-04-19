package bitbox.project.domain.model

/**
 * Created by André Santos
 * andre.santos@ubivis.io
 * on ubivisnb10
 * on 3/7/19.
 */

data class BlockResponse(
        val block: Block,
        val success: Int
)

data class Block(
    val average_coin_age: String,
    val confirmations: Int,
    val difficulty: Double,
    val hash: String,
    val height: Int,
    val is_orphan: Boolean,
    val merkleroot: String,
    val next_block_hash: String,
    val nonce: Long,
    val num_txs: Int,
    val previous_block_hash: String,
    val time: Int,
    val txs: List<String>,
    val value_in: String,
    val value_out: String,
    val version: Int
)