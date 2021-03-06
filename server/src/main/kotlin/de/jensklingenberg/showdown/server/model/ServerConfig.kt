package de.jensklingenberg.showdown.server.model

import com.soywiz.klock.DateTime
import de.jensklingenberg.showdown.model.ClientGameConfig
import de.jensklingenberg.showdown.model.GameConfig
import de.jensklingenberg.showdown.model.fibo

fun ServerConfig.toClient(): ClientGameConfig {
    return ClientGameConfig(
        this.voteOptions,
        this.autoReveal,
        this.createdAt,
        roomHasPassword = this.room.password.isNotEmpty()
    )
}

data class ServerConfig(
    override var voteOptions: List<String> = fibo,
    override val autoReveal: Boolean = false,
    override var createdAt: String,
    var room: Room
) :
    GameConfig

/**
 * The default config for a new room
 */
fun getDefaultConfig(roomName: String) = ServerConfig(
    fibo, autoReveal = false, createdAt = DateTime.now().unixMillisDouble.toString(),
    room = Room(roomName, "")
)