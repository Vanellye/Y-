package org.yaro.language

import java.io.File

/** Ɣơ/2 reversible byte-text adapter; binary storage remains authoritative. */
object YoroStarCodec {
    private const val PREFIX = "Ɣơ/2:"
    fun encode(bytes: ByteArray): String = PREFIX + bytes.joinToString(".") { byte ->
        (7 downTo 0).joinToString("") { bit -> if (((byte.toInt() and 0xff) ushr bit) and 1 == 1) "k" else "☆" }
    }
    fun decode(text: String): ByteArray {
        require(text.startsWith(PREFIX)) { "Unsupported Ɣơ profile" }
        val body = text.removePrefix(PREFIX)
        if (body.isEmpty()) return byteArrayOf()
        val groups = body.split(".")
        require(groups.all { it.length == 8 && it.all { c -> c == '☆' || c == 'k' } }) { "Each byte must contain eight ☆/k symbols" }
        return groups.map { group ->
            var value = 0
            group.forEach { c -> value = (value shl 1) or if (c == 'k') 1 else 0 }
            value.toByte()
        }.toByteArray()
    }
    fun validateVectors(file: File): Int {
        var count = 0
        file.readLines().filter { it.isNotBlank() && !it.startsWith("#") }.forEach { line ->
            val fields = line.split("|")
            require(fields.size == 3) { "Malformed vector: $line" }
            val bytes = if (fields[1].isEmpty()) byteArrayOf() else fields[1].chunked(2).map { it.toInt(16).toByte() }.toByteArray()
            require(encode(bytes) == fields[2]) { "Encode mismatch: ${fields[0]}" }
            require(decode(fields[2]).contentEquals(bytes)) { "Decode mismatch: ${fields[0]}" }
            count++
        }
        return count
    }
}

fun main(args: Array<String>) {
    require(args.size == 1) { "Usage: YoroStarCodec <path-to-yoro-star-v2.tsv>" }
    val count = YoroStarCodec.validateVectors(File(args[0]))
    for (value in 0..255) {
        val raw = byteArrayOf(value.toByte())
        check(YoroStarCodec.decode(YoroStarCodec.encode(raw)).contentEquals(raw)) { "Round-trip mismatch: $value" }
    }
    println("YARO_KOTLIN_PASS vectors=$count byte_roundtrips=256")
}
