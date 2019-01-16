package example

import org.apache.commons.codec.binary.Hex
import org.bitcoinj.core.Context
import org.bitcoinj.params.MainNetParams
import org.bitcoinj.utils.BlockFileLoader
import java.io.File

val mainNetParams = MainNetParams().apply { Context.getOrCreate(this) }

val blockChainFiles = listOf(File("./btc-data/blocks/blk00000.dat"))

fun main(args: Array<String>) {

    val loader = BlockFileLoader(mainNetParams, blockChainFiles)
    for (block in loader) {
        if (block.hashAsString == "000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f") {
//            System.out.println(block)
            block.transactions!!.forEach { t ->
                t.outputs.forEach { output ->
                    //                    val address = output.getAddressFromP2SH(mainNetParams)
//                    println(address)
//                    println(Hex.encodeHex(output.scriptBytes))
                    if (output.scriptPubKey.isSentToRawPubKey) {
//                        val pubKey = output.scriptPubKey.pubKey
//                        println(Hex.encodeHex(pubKey))
                        val hash = output.scriptPubKey.pubKey
                        ScriptPattern.extractHashFromPayToScriptHash()
                        println(hash)
                    }

                    // 1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa
                }
            }
        } else {
            return
        }
    }
}